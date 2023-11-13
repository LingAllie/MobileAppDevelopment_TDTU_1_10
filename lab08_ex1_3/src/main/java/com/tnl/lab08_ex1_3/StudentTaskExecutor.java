package com.tnl.lab08_ex1_3;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentTaskExecutor {
    private StudentTaskListener listener;
    private Ex13Activity activity;
    private ProgressBar progressBar;

    public void setListener(StudentTaskListener listener) {
        this.listener = listener;
    }

    public StudentTaskExecutor(Ex13Activity activity) {
        this.activity = activity;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
    public void executeTask() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        if (progressBar != null) {
            activity.runOnUiThread(() -> {
                progressBar.setVisibility(View.VISIBLE);
            });
        }

        executor.execute(() -> {
            List<String> students = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                if (listener == null) {
                    notifyError("Listener is null");
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                students.add("Student " + i);
                notifyProgress(i * 5);
            }
            notifyFinish(students);
            executor.shutdown();
        });
    }


    private void notifyProgress(int progress) {
        if (listener != null) {
            listener.onProgressChange(progress);
        }
    }

    private void notifyFinish(List<String> students) {
        if (listener != null) {
            listener.onDownloadFinish(students);
        }
        if (activity != null) {
            activity.runOnUiThread(() -> {
                Toast.makeText(activity, "Download successfully !", Toast.LENGTH_SHORT).show();
            });
        }
    }

    public void notifyError(String errorMessage) {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (listener != null) {
                listener.onTaskError(errorMessage);
            }
        });
    }
}

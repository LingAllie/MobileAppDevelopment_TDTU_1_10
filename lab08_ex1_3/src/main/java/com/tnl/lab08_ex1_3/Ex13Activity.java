package com.tnl.lab08_ex1_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex13Activity extends AppCompatActivity implements StudentTaskListener {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private View emptyView;
    private Button btnTryAgain;
    private ProgressDialog dialog;
    private ExecutorService service;
    private StudentViewModel viewModel;
    private List<String> students;
    private StudentTaskExecutor task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex13);

        service = Executors.newFixedThreadPool(4);
        listView = findViewById(R.id.listView);
        emptyView = findViewById(R.id.emptyView);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        task = new StudentTaskExecutor(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Student " + position + " has been clicked !", Toast.LENGTH_SHORT).show();
        });

        viewModel.getStudents().observe(this, updatedStudents -> {
            adapter.clear();
            if (updatedStudents != null) {
                adapter.addAll(updatedStudents);
            }
            adapter.notifyDataSetChanged();
        });

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Loading Students...");
        dialog.setMax(100);

        btnTryAgain.setOnClickListener(v -> {
            dialog.show();
            loadData();
            downloadStudents();
        });
    }

    private void downloadStudents() {
        task = new StudentTaskExecutor(this);
        task.setListener(this);
        task.executeTask();
    }

    private void refreshListView() {
        adapter.notifyDataSetChanged();

        if (!students.isEmpty()) {
            listView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private void loadData() {
        students = new ArrayList<>();

        service.submit(() -> {
            for (int i = 0; i <= 20; i++) {
                students.add(0, "Item" + (i + 1));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(this::refreshListView);
        });
    }

    @Override
    protected void onDestroy() {
        if (task != null) {
            task.setListener(null);
        }
        super.onDestroy();
    }

    @Override
    public void onDownloadStart() {
        runOnUiThread(dialog::show);
    }

    @Override
    public void onProgressChange(int progress) {
        runOnUiThread(() -> dialog.setProgress(progress));
    }

    @Override
    public void onDownloadFinish(List<String> students) {
        runOnUiThread(() -> {
            if (students == null) {
                dialog.dismiss();
                Toast.makeText(this, "Error connecting to the server!", Toast.LENGTH_SHORT).show();
            } else {
                this.students.addAll(students);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                Toast.makeText(this, "Download successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTaskError(String errorMessage) {
        runOnUiThread(dialog::dismiss);
    }
}

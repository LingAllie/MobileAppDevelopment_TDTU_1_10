package com.tnl.lab08_ex1_3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
public class StudentViewModel extends ViewModel {
    private final MutableLiveData<Integer> progress;
    private final MutableLiveData<List<String>> students;

    public StudentViewModel() {
        this.progress = new MutableLiveData<>();
        this.students = new MutableLiveData<>();

        loadDataFromBackground();
    }

    private void loadDataFromBackground() {
        progress.setValue(0);
        new Thread(() -> {
            List<String> data = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.add("Student " + i);
                progress.postValue(i);
            }
            students.postValue(data);
        }).start();
    }

    public LiveData<Integer> getProgress() {
        return progress;
    }

    public LiveData<List<String>> getStudents() {
        return students;
    }
}

package com.tnl.lab08_ex1_3;

import java.util.List;

public interface StudentTaskListener {
    void onDownloadStart();
    void onProgressChange(int progress);
    void onDownloadFinish(List<String> students);
    void onTaskError(String errorMessage);
}
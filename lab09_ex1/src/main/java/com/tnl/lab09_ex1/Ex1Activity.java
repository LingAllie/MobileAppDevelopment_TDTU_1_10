package com.tnl.lab09_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tnl.lab09_ex1.network.Downloader;
import com.tnl.lab09_ex1.recycler.DownloadFile;
import com.tnl.lab09_ex1.recycler.FileAdapter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1Activity extends AppCompatActivity
        implements View.OnClickListener, Downloader.Callback {

    private EditText txtUrl;
    private Button btnDownload;
    private RecyclerView recyclerView;
    private LinearLayout emptyView;
    private FileAdapter adapter;

    private ExecutorService pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
        setTitle("Download Manager");

        initViews();
        initObject();
        updateUI();
    }

    private void updateUI() {
        if (adapter.getItemCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void initObject() {
        adapter = new FileAdapter();
//        adapter.setFiles(DownloadFile.generate());
        recyclerView.setAdapter(adapter);

        pool = Executors.newFixedThreadPool(2);
    }

    private void initViews() {
        txtUrl = findViewById(R.id.txtUrl);
        btnDownload = findViewById(R.id.btnDownload);
        emptyView = findViewById(R.id.emptyView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerView.setItemAnimator(null);
        btnDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url = txtUrl.getText().toString();
        txtUrl.setText("");

        DownloadFile file = new DownloadFile();
        file.setDownloadUrl(url);

        recyclerView.post(() -> {
            adapter.add(file);
            recyclerView.scrollToPosition(0);

            updateUI();
        });

        downloadFile(file);
    }

    private void downloadFile(DownloadFile file) {

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

        new Thread(() -> {
            Downloader.fetchInfo(file, this);
        }).start();

        pool.submit(() -> {
            Downloader.download(file, folder, this);
        });
    }

    @Override
    public void onFileInfoChanged(DownloadFile file) {

        recyclerView.post(() -> adapter.notifyItemChanged(file));
    }

    @Override
    public void onComplete(DownloadFile file) {
        file.setProgress(DownloadFile.STATUS_COMPLETE);
        recyclerView.post(() -> adapter.notifyItemChanged(file));
    }

    @Override
    public void onError(DownloadFile file, Throwable t) {
        file.setProgress(DownloadFile.STATUS_FAIL);
        recyclerView.post(() -> adapter.notifyItemChanged(file));
    }
}
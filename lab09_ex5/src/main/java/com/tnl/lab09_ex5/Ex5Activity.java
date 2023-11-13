package com.tnl.lab09_ex5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ex5Activity extends AppCompatActivity {

    private EditText txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);

        txtLink = findViewById(R.id.txtLink);
    }

    public void download(View view) {
        String link = txtLink.getText().toString().trim();

        beginDownloading(link);
    }

    private void beginDownloading(final String link) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int code = conn.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        int size = conn.getContentLength();
                        String filename = null;
                        String disposition = conn.getHeaderField("Content-Disposition");

                        try {
                            if (disposition != null) {
                                int index = disposition.indexOf("filename=");
                                if (index > 0) {
                                    filename = disposition.substring(index + 10);
                                }
                            } else {
                                int index = link.lastIndexOf("/");
                                if (index > 0) {
                                    filename = link.substring(index + 1);
                                }
                            }
                        } catch (Exception e) {
                            filename = "No name";
                        }
                        if (filename == null) {
                            filename = "No name";
                        }

                        Log.e("TAG", "Filename: " + filename);
                        Log.e("TAG", "Size: " + size);


                        File output = new File(getExternalFilesDir(null), filename);

                        //start downloading
                        InputStream is = conn.getInputStream();
                        FileOutputStream fos = new FileOutputStream(output);

                        byte[] buffer = new byte[1024];
                        int byteCount = 0;
                        int count;
                        while ((count = is.read(buffer, 0, 1024)) > 0) {
                            fos.write(buffer, 0, count);
                            byteCount += count;
                            int progress = (int) (byteCount * 100.0 / size);
                            Log.e("TAG", "Downloading: " + progress + "%");
                        }

                        fos.close();
                        is.close();

                        Log.e("TAG", "Download completed !");

                    } else {
                        Log.e("TAG", "Download failed !");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
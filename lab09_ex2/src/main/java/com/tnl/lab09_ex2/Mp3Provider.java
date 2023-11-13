package com.tnl.lab09_ex2;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mp3Provider {
    public static List<File> getAll() {
        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.e("TAG", "path: " + musicDir);
        List<File> mp3List = new ArrayList<>();

        for (File file: musicDir.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".mp3")) {
                mp3List.add(file);
            }
        }
        return mp3List;
    }
}

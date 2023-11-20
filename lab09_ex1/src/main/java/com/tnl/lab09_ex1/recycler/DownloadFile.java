package com.tnl.lab09_ex1.recycler;

import androidx.annotation.Nullable;

import com.tnl.lab09_ex1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class DownloadFile {

    public static final int STATUS_COMPLETE = 101;
    public static final int STATUS_WAITING = -1;
    public static final int STATUS_FAIL = -2;

    private static final int[] ICONS = {
            R.drawable.icon_archive,
            R.drawable.icon_image,
            R.drawable.icon_movie,
            R.drawable.icon_music,
            R.drawable.icon_office,
            R.drawable.icon_text,
            R.drawable.icon_other
    };

    private static final Random random = new Random();

    private String id;
    private String name;
    private String localPath;
    private long size;
    private String downloadUrl;
    private int progress;
    private int icon;

    public DownloadFile() {
        setId(UUID.randomUUID().toString());
        setProgress(STATUS_WAITING);
    }

    public void markAsComplete() {
        setProgress(STATUS_COMPLETE);
    }

    public void markAsFail() {
        setProgress(STATUS_FAIL);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadFile that = (DownloadFile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setName(String name) {
        this.name = name;
        String ext = name.substring(name.lastIndexOf('.') + 1).toLowerCase();

        if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif") || ext.equals("bmp")) {
            setIcon(R.drawable.icon_image);
        }
        else if (ext.equals("zip") || ext.equals("rar") || ext.equals("gz") || ext.equals("7z")) {
            setIcon(R.drawable.icon_archive);
        }
        else if (ext.equals("mp4") || ext.equals("mkv") || ext.equals("mov") || ext.equals("mpg")
                || ext.equals("webm") || ext.equals("avi") || ext.equals("m4v")) {
            setIcon(R.drawable.icon_movie);
        }
        else if (ext.equals("mp3") || ext.equals("wav") || ext.equals("mpa") || ext.equals("m4a")
                || ext.equals("flac") || ext.equals("wma") || ext.equals("aac")) {
            setIcon(R.drawable.icon_music);
        }
        else if (ext.equals("doc") || ext.equals("docx") || ext.equals("xls")
                || ext.equals("xlsx") || ext.equals("ppt") || ext.equals("pptx")) {
            setIcon(R.drawable.icon_office);
        }
        else if (ext.equals("txt") || ext.equals("html") || ext.equals("php")
                || ext.equals("py") || ext.equals("js") || ext.equals("css")) {
            setIcon(R.drawable.icon_text);
        }
        else {
            setIcon(R.drawable.icon_office);
        }
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
        setName(localPath.substring(localPath.lastIndexOf('/') + 1));
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isCompleted() {
        return progress == STATUS_COMPLETE;
    }

    public boolean isFailed() {
        return progress == STATUS_FAIL;
    }

    public boolean isWaiting() {
        return progress == STATUS_WAITING;
    }

    public static List<DownloadFile> generate() {
        List<DownloadFile> files = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            files.add(createFile(i));
        }

        return files;
    }

    private static DownloadFile createFile(int id) {
        DownloadFile file = new DownloadFile();
        file.setId(UUID.randomUUID().toString());
        file.setName("File " + id);
        file.setIcon(ICONS[random.nextInt(ICONS.length)]);

        int progress = random.nextInt(100);
        if (id % 3 == 0) {
            file.setProgress(random.nextInt(progress));
        }else {
            if (progress > 40) {
                file.setProgress(STATUS_WAITING);
            }else if (progress > 20){
                file.setProgress(STATUS_FAIL);
            }else {
                file.setProgress(STATUS_COMPLETE);
            }
        }

        file.setSize(random.nextInt(2000000000));

        return file;
    }

    public String getDisplaySize() {
        if (size < 1024) {
            return size + " bytes";
        }

        double size = this.size * 1.0 / 1024;
        if (size < 1024) {
            return round(size) + " KB";
        }

        size = size / 1024;
        if (size < 1024) {
            return round(size) + " MB";
        }

        size = size / 1024;
        return round(size) + " GB";
    }

    private double round(double input) {
        return Math.round(input * 100.0) / 100.0;
    }
}

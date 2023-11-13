package com.tnl.lab05_ex1;

import android.graphics.drawable.Drawable;

public class AppInfo {

    private String name;
    private long size;
    private Drawable icon;
    private String packageName;
    private String installedDate;

    public static AppInfo newInstance(String name, long size, Drawable icon, String packageName, String installedDate) {
        return new AppInfo(name, size, icon, packageName, installedDate);
    }

    public AppInfo(String name, long size, Drawable icon, String packageName, String installedDate) {
        this.name = name;
        this.size = size;
        this.icon = icon;
        this.packageName = packageName;
        this.installedDate = installedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(String installedDate) {
        this.installedDate = installedDate;
    }

    public String displaySize() {
        if (size < 1024) {
            return size + " bytes";
        }
        else if (size < 1024 * 1024) {
            return Math.round(size / 1024.0) + " KB";
        }
        else {
            return Math.round(size / 1024.0 / 1024.0) + " MB";
        }
    }
}

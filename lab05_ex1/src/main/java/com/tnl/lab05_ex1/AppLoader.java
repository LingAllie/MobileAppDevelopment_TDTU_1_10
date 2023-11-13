package com.tnl.lab05_ex1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppLoader {

    public static List<AppInfo> loadAppList(Context context) {

        PackageManager pm = context.getPackageManager();
        Intent main = new Intent(Intent.ACTION_MAIN, null);
        main.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packages = pm.queryIntentActivities(main, 0);

        List<AppInfo> appList = new ArrayList<>();

        for(ResolveInfo resolve_info : packages) {
            try {
                String package_name = resolve_info.activityInfo.packageName;
                ApplicationInfo info = pm.getApplicationInfo(package_name, PackageManager.GET_META_DATA);

                appList.add(fetchAppDetails(pm, info));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return appList;
    }

    public static AppInfo fetchAppDetails(PackageManager manager, ApplicationInfo info) {

        File apkFile = new File(info.publicSourceDir);
        long firstInstalled = 0;
        try {
            firstInstalled = manager.getPackageInfo(info.packageName, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        // app infomation
        long sizeInByte = apkFile.length();
        String appName = info.loadLabel(manager).toString().trim();
        Drawable icon =  info.loadIcon(manager);
        String installedDate = format.format(new Date(firstInstalled));
        String packageName = packageName = info.packageName;

        Log.e("TAG", appName + " " + packageName + " " + sizeInByte + " - " + installedDate);

        return AppInfo.newInstance(appName, sizeInByte, icon, packageName, installedDate);
    }
}
package com.novoda.sandbox.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by jingli on 04/06/16.
 */
public class AppInfo {
    private Drawable icon;
    private String name;
    private String dir;
    private String pkg;
    private String sdk;

    public AppInfo(Drawable icon, String name, String dir, String pkg, String sdk) {
        this.icon = icon;
        this.name = name;
        this.dir = dir;
        this.pkg = pkg;
        this.sdk = sdk;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public String getPkg() {
        return pkg;
    }

    public String getSdk() {
        return sdk;
    }

    public static class Builder {
        private Drawable icon;
        private String name;
        private String dir;
        private String pkg;
        private String sdk;

        public Builder parse(@NonNull Context context, String packageName) throws PackageManager.NameNotFoundException {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            this.icon = applicationInfo.loadIcon(packageManager);
            this.name = applicationInfo.loadLabel(packageManager).toString();
            this.dir = applicationInfo.dataDir;
            this.pkg = applicationInfo.packageName;
            this.sdk = String.valueOf(applicationInfo.targetSdkVersion);
            return this;
        }

        public AppInfo build() {
            return new AppInfo(this);
        }
    }

    private AppInfo(Builder builder) {
        this.icon = builder.icon;
        this.name = builder.name;
        this.dir = builder.dir;
        this.pkg = builder.pkg;
        this.sdk = builder.sdk;
    }
}

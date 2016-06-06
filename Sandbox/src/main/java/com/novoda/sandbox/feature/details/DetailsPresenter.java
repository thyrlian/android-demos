package com.novoda.sandbox.feature.details;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;

import com.novoda.sandbox.BuildConfig;
import com.novoda.sandbox.data.AppInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by jingli on 06/06/16.
 */
public class DetailsPresenter implements DetailsContract.Presenter {
    public static final String ACTION = BuildConfig.APPLICATION_ID + ".DETAILS";
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";

    @NonNull
    private DetailsContract.View detailsContractView;
    private String packageName;

    public DetailsPresenter(@NonNull DetailsContract.View detailsContractView, String packageName) {
        this.detailsContractView = detailsContractView;
        this.detailsContractView.setPresenter(this);
        this.packageName = packageName;
    }

    @Override
    public void start() {
        detailsContractView.showAppInfo(packageName);
        detailsContractView.setLaunchAppButton();
    }

    @Override
    public AppInfo getAppInfo(Context context) {
        AppInfo appInfo = null;
        try {
            appInfo = new AppInfo.Builder().parse(context, packageName).build();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            detailsContractView.showErrorLoadPackage();
        }
        return appInfo;
    }

    @Override
    public void launchApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(new Intent().setPackage(packageName), 0);
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(packageManager));
        if(resolveInfos.size() > 0) {
            detailsContractView.showAppUI(createIntentForOther(resolveInfos.get(0).activityInfo));
        } else {
            detailsContractView.showErrorOpenPackage();
        }
    }

    public static Intent createIntent(String packageName) {
        return new Intent(ACTION).putExtra(EXTRA_PACKAGE_NAME, packageName);
    }

    private static Intent createIntentForOther(ComponentName componentName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setComponent(componentName);
        return intent;
    }

    private static Intent createIntentForOther(ActivityInfo activityInfo) {
        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
        return createIntentForOther(componentName);
    }
}

package com.novoda.sandbox.feature.details;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.novoda.sandbox.BuildConfig;
import com.novoda.sandbox.R;
import com.novoda.sandbox.data.AppInfo;

import java.util.Collections;
import java.util.List;

public class DetailsActivity extends Activity {

    private static final String ACTION = BuildConfig.APPLICATION_ID + ".DETAILS";
    private static final String EXTRA_PACKAGE_NAME = "extra_package_name";

    public static Intent createIntent(String packageName) {
        return new Intent(ACTION).putExtra(EXTRA_PACKAGE_NAME, packageName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String packageName = getIntent().getStringExtra(EXTRA_PACKAGE_NAME);
        showAppInfo(packageName);
        setLaunchAppButton(packageName);
    }

    private void showAppInfo(String packageName) {
        AppInfo appInfo = null;
        try {
            appInfo = new AppInfo.Builder().parse(this, packageName).build();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(DetailsActivity.this, getResources().getString(R.string.error_load_package), Toast.LENGTH_SHORT).show();
            finish();
        }
        if (appInfo != null) {
            ImageView logoView = (ImageView) findViewById(R.id.details_activity_app_icon);
            logoView.setImageDrawable(appInfo.getIcon());
            setItem(R.string.item_key_name, appInfo.getName(), R.id.details_activity_app_name);
            setItem(R.string.item_key_dir, appInfo.getDir(), R.id.details_activity_app_dir);
            setItem(R.string.item_key_pkg, appInfo.getPkg(), R.id.details_activity_app_pkg);
            setItem(R.string.item_key_sdk, appInfo.getSdk(), R.id.details_activity_app_sdk);
        }
    }

    private void setLaunchAppButton(final String packageName) {
        findViewById(R.id.details_activity_launch_application).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(new Intent().setPackage(packageName), 0);
                Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(packageManager));
                if(resolveInfos.size() > 0) {
                    startActivity(createIntentForOther(resolveInfos.get(0).activityInfo));
                } else {
                    Toast.makeText(DetailsActivity.this, getResources().getString(R.string.error_open_package), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setItem(@StringRes int string, String value, @IdRes int id) {
        String key = getResources().getString(string);
        TextView name = (TextView) findViewById(id);
        name.setText(key + " : " + value);
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

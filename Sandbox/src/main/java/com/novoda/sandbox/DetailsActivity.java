package com.novoda.sandbox;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class DetailsActivity extends Activity {

    private static final String ACTION = BuildConfig.APPLICATION_ID + ".DETAILS";
    private static final String EXTRA_PACKAGE_NAME = "extra_package_name";

    public static Intent createIntent(String packageName) {
        return new Intent(ACTION)
                .putExtra(EXTRA_PACKAGE_NAME, packageName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final String packageName = getIntent().getStringExtra(EXTRA_PACKAGE_NAME);

        try {
            final PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            Drawable drawable = applicationInfo.loadIcon(packageManager);

            ImageView logoView = (ImageView) findViewById(R.id.details_activity_application_logo);
            logoView.setImageDrawable(drawable);

            setItem("Name", applicationInfo.loadLabel(packageManager).toString(), R.id.details_activity_application_name);
            setItem("Data dir", applicationInfo.dataDir, R.id.details_activity_data_directory);
            setItem("Package", applicationInfo.packageName, R.id.details_activity_package_name);
            setItem("Target Sdk", String.valueOf(applicationInfo.targetSdkVersion), R.id.details_activity_target_sdk);

            findViewById(R.id.details_activity_launch_application).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(new Intent().setPackage(packageName), 0);
                    Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(packageManager));
                    if(resolveInfos.size() > 0) {
                        ActivityInfo activityInfo = resolveInfos.get(0).activityInfo;
                        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        intent.setComponent(componentName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DetailsActivity.this, "Uh oh, can't open this package!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItem(String keyName, String value, @IdRes int id) {
        TextView name = (TextView) findViewById(id);
        name.setText(keyName + " : " + value);
    }
}

package com.novoda.sandbox.feature.main;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.novoda.sandbox.global.Application;
import com.novoda.sandbox.R;
import com.novoda.sandbox.feature.details.DetailsActivity;
import com.novoda.sandbox.feature.login.SignInActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (Button) findViewById(R.id.main_activity_sign_in_button);
        signInButton.setOnClickListener(onSignInOutClicked);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setOnClickListener(onItemClicked);
                view.setTag(position);
                return view;
            }
        };

        ListView listView = (ListView) findViewById(R.id.packages_list);
        listView.setAdapter(adapter);
    }

    private final View.OnClickListener onSignInOutClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Application.isSignedIn()) {
                Application.setSignedOut();
                refreshUi();
            } else {
                startActivity(SignInActivity.createIntent());
            }
        }
    };

    private final View.OnClickListener onItemClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String packageName = adapter.getItem((Integer) v.getTag());
            startActivity(DetailsActivity.createIntent(packageName));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        refreshUi();
    }

    private void refreshUi() {
        updateSignInState();
        showData();
    }

    private void updateSignInState() {
        String signInText = getResources().getString(R.string.sign_in_button);
        String signOutText = getResources().getString(R.string.sign_out_button);
        signInButton.setText(Application.isSignedIn() ? signOutText : signInText);
    }

    private void showData() {
        adapter.clear();
        List<ApplicationInfo> installedApplications = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        if (Application.isSignedIn()) {
            for (ApplicationInfo application : installedApplications) {
                adapter.add(application.packageName);
            }
        } else {
            adapter.add(installedApplications.get(0).packageName);
        }
    }
}

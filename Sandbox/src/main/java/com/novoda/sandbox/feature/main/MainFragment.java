package com.novoda.sandbox.feature.main;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.novoda.sandbox.R;
import com.novoda.sandbox.feature.details.DetailsActivity;
import com.novoda.sandbox.feature.login.SignInActivity;
import com.novoda.sandbox.global.SandboxApplication;

import java.util.List;

import static com.novoda.sandbox.util.Preconditions.checkNotNull;

/**
 * Created by jingli on 05/06/16.
 */
public class MainFragment extends Fragment implements MainContract.View {
    private MainContract.Presenter presenter;
    private PackagesAdapter adapter;
    private Button signInButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
    }

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        signInButton = (Button) root.findViewById(R.id.main_activity_sign_in_button);
        signInButton.setOnClickListener(new OnSignInOutClickListener());

        Context context = this.getContext();
        if (context != null) {
            OnPackageClickListener onPackageClickListener = new OnPackageClickListener();
            adapter = new PackagesAdapter(context, onPackageClickListener);
            ListView listView = (ListView) root.findViewById(R.id.packages_list);
            listView.setAdapter(adapter);
        }

        return root;
    }

    @Override
    public void updateSignInState() {
        String signInText = getResources().getString(R.string.sign_in_button);
        String signOutText = getResources().getString(R.string.sign_out_button);
        signInButton.setText(SandboxApplication.isSignedIn() ? signOutText : signInText);
    }

    @Override
    public void showSinglePackage(ApplicationInfo applicationInfo) {
        adapter.clear();
        adapter.add(applicationInfo.packageName);
    }

    @Override
    public void showAllPackages(List<ApplicationInfo> applicationInfos) {
        adapter.clear();
        for (ApplicationInfo applicationInfo : applicationInfos) {
            adapter.add(applicationInfo.packageName);
        }
    }

    private class PackagesAdapter extends ArrayAdapter<String> {
        private OnPackageClickListener onPackageClickListener;

        public PackagesAdapter(Context context, OnPackageClickListener onPackageClickListener) {
            super(context, android.R.layout.simple_list_item_1);
            this.onPackageClickListener = onPackageClickListener;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            android.view.View view = super.getView(position, convertView, parent);
            view.setTag(position);
            view.setOnClickListener(onPackageClickListener);
            return view;
        }
    }

    private class OnPackageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String packageName = adapter.getItem((Integer) view.getTag());
            startActivity(DetailsActivity.createIntent(packageName));
        }
    }

    private class OnSignInOutClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (SandboxApplication.isSignedIn()) {
                SandboxApplication.setSignedOut();
                ApplicationInfo installedApplication = presenter.getInstalledApplication();
                if (installedApplication != null) {
                    showSinglePackage(presenter.getInstalledApplication());
                }
            } else {
                startActivity(SignInActivity.createIntent());
            }
        }
    }
}

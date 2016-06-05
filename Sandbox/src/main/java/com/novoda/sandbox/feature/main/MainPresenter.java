package com.novoda.sandbox.feature.main;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.novoda.sandbox.global.SandboxApplication;

import java.util.List;

import static com.novoda.sandbox.util.Preconditions.checkNotNull;

/**
 * Created by jingli on 05/06/16.
 */
public class MainPresenter implements MainContract.Presenter {
    @NonNull
    private MainContract.View mainContractView;
    private Context context;

    public MainPresenter(@NonNull MainContract.View mainContractView, Context context) {
        checkNotNull(mainContractView);
        this.mainContractView = mainContractView;
        this.mainContractView.setPresenter(this);
        this.context = context;
    }

    @Override
    public void start() {
        mainContractView.updateSignInState();
        if (context != null) {
            if (SandboxApplication.isSignedIn()) {
                mainContractView.showAllPackages(getInstalledApplications());
            } else {
                mainContractView.showSinglePackage(getInstalledApplication());
            }
        }
    }

    @Override
    public List<ApplicationInfo> getInstalledApplications() {
        if (context != null) {
            return context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        } else {
            return null;
        }
    }

    @Override
    public ApplicationInfo getInstalledApplication() {
        List<ApplicationInfo> installedApplications = getInstalledApplications();
        if (installedApplications != null) {
            return installedApplications.get(0);
        } else {
            return null;
        }
    }
}

package com.novoda.sandbox.feature.main;

import android.content.pm.ApplicationInfo;

import com.novoda.sandbox.BasePresenter;
import com.novoda.sandbox.BaseView;

import java.util.List;

/**
 * Created by jingli on 05/06/16.
 */
public interface MainContract {
    interface View extends BaseView<Presenter> {

        void updateSignInState();

        void showSinglePackage(ApplicationInfo applicationInfo);

        void showAllPackages(List<ApplicationInfo> applicationInfos);

    }

    interface Presenter extends BasePresenter {

        List<ApplicationInfo> getInstalledApplications();

        ApplicationInfo getInstalledApplication();

    }
}

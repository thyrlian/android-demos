package com.novoda.sandbox.feature.details;

import android.content.Context;
import android.content.Intent;

import com.novoda.sandbox.BasePresenter;
import com.novoda.sandbox.BaseView;
import com.novoda.sandbox.data.AppInfo;

/**
 * Created by jingli on 06/06/16.
 */
public interface DetailsContract {
    interface View extends BaseView<Presenter> {

        void showAppInfo(String packageName);

        void showAppUI(Intent intent);

        void showErrorLoadPackage();

        void showErrorOpenPackage();

        void setLaunchAppButton();

    }

    interface Presenter extends BasePresenter {

        AppInfo getAppInfo(Context context);

        void launchApp(Context context);

    }
}

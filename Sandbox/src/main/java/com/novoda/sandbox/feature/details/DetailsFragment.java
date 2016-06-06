package com.novoda.sandbox.feature.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.novoda.sandbox.R;
import com.novoda.sandbox.data.AppInfo;

import static com.novoda.sandbox.util.Preconditions.checkNotNull;

/**
 * Created by jingli on 06/06/16.
 */
public class DetailsFragment extends Fragment implements DetailsContract.View {
    private DetailsContract.Presenter presenter;
    private View root;

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    public DetailsFragment() {
    }

    @Override
    public void setPresenter(@NonNull DetailsContract.Presenter presenter) {
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
        root = inflater.inflate(R.layout.details_fragment, container, false);
        return root;
    }

    @Override
    public void showAppInfo(String packageName) {
        AppInfo appInfo = presenter.getAppInfo(getContext());
        if (appInfo != null) {
            ImageView logoView = (ImageView) root.findViewById(R.id.details_activity_app_icon);
            logoView.setImageDrawable(appInfo.getIcon());
            setItem(R.string.item_key_name, appInfo.getName(), R.id.details_activity_app_name);
            setItem(R.string.item_key_dir, appInfo.getDir(), R.id.details_activity_app_dir);
            setItem(R.string.item_key_pkg, appInfo.getPkg(), R.id.details_activity_app_pkg);
            setItem(R.string.item_key_sdk, appInfo.getSdk(), R.id.details_activity_app_sdk);
        } else {
            getActivity().finish();
        }
    }

    @Override
    public void showAppUI(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showErrorLoadPackage() {
        Toast.makeText(getActivity(), getResources().getString(R.string.error_load_package), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorOpenPackage() {
        Toast.makeText(getActivity(), getResources().getString(R.string.error_open_package), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLaunchAppButton() {
        root.findViewById(R.id.details_activity_launch_application).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.launchApp(getContext());
            }
        });
    }

    private void setItem(@StringRes int string, String value, @IdRes int id) {
        String key = getResources().getString(string);
        TextView name = (TextView) root.findViewById(id);
        name.setText(key + " : " + value);
    }
}

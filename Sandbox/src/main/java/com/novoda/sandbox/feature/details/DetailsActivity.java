package com.novoda.sandbox.feature.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.ActivityUtils;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        // Set up view
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailsFragment, R.id.contentFrame);

        // Set up presenter
        String packageName = getIntent().getStringExtra(DetailsPresenter.EXTRA_PACKAGE_NAME);
        new DetailsPresenter(detailsFragment, packageName);
    }
}

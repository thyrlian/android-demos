package com.novoda.sandbox.feature.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Set up view
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.contentFrame);

        // Set up presenter
        new MainPresenter(mainFragment, getApplicationContext());
    }
}

package com.novoda.sandbox.feature.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.ActivityUtils;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        // Set up view
        SignInFragment signInFragment = (SignInFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (signInFragment == null) {
            signInFragment = SignInFragment.newInstance();
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), signInFragment, R.id.contentFrame);

        // Set up presenter
        new SignInPresenter(signInFragment);
    }
}

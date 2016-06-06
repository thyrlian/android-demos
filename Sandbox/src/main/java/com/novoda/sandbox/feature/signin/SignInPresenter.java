package com.novoda.sandbox.feature.signin;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.novoda.sandbox.BuildConfig;

/**
 * Created by jingli on 05/06/16.
 */
public class SignInPresenter implements SignInContract.Presenter {
    public static final String ACTION = BuildConfig.APPLICATION_ID + ".SIGN_IN";
    public static final int MINIMUM_INPUT_LENGTH = 4;

    @NonNull
    private SignInContract.View signInContractView;

    public SignInPresenter(@NonNull SignInContract.View signInContractView) {
        this.signInContractView = signInContractView;
        this.signInContractView.setPresenter(this);
    }

    public static Intent createIntent() {
        return new Intent(ACTION);
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isUsernameInvalid() {
        return signInContractView.getUsername().length() < MINIMUM_INPUT_LENGTH;
    }

    @Override
    public boolean isPasswordInvalid() {
        return signInContractView.getPassword().length() < MINIMUM_INPUT_LENGTH;
    }

    @Override
    public boolean isAnyInputInvalid() {
        return isUsernameInvalid() || isPasswordInvalid();
    }
}

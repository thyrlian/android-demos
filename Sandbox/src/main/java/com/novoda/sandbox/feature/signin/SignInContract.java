package com.novoda.sandbox.feature.signin;

import com.novoda.sandbox.BasePresenter;
import com.novoda.sandbox.BaseView;

/**
 * Created by jingli on 05/06/16.
 */
public interface SignInContract {
    interface View extends BaseView<Presenter> {

        String getUsername();

        String getPassword();

        void clearValidationError();

        void showUsernameValidationError();

        void showPasswordValidationError();

    }

    interface Presenter extends BasePresenter {

        boolean isUsernameInvalid();

        boolean isPasswordInvalid();

        boolean isAnyInputInvalid();

    }
}

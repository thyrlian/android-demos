package com.novoda.sandbox.feature.login;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.novoda.sandbox.SandboxApplication;
import com.novoda.sandbox.BuildConfig;
import com.novoda.sandbox.R;

public class SignInActivity extends Activity {

    private static final String ACTION = BuildConfig.APPLICATION_ID + ".SIGN_IN";
    private static final int MINIMUM_INPUT_LENGTH = 4;

    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private View submitButton;

    public static Intent createIntent() {
        return new Intent(ACTION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.sign_in_activity_username_wrapper);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.sign_in_activity_password_wrapper);
        usernameEditText = (EditText) findViewById(R.id.sign_in_activity_username_field);
        passwordEditText = (EditText) findViewById(R.id.sign_in_activity_password_field);
        submitButton = findViewById(R.id.sign_in_activity_submit_button);

        usernameEditText.addTextChangedListener(new SignInTextWatcher(usernameEditText, false));
        passwordEditText.addTextChangedListener(new SignInTextWatcher(passwordEditText, true));
        submitButton.setEnabled(false);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearValidationError();
                if (isAnyInputInvalid()) {
                    showValidationError();
                } else {
                    SandboxApplication.setSignedIn();
                    finish();
                }
            }
        });

    }

    private boolean isUsernameInvalid() {
        return usernameEditText.getText().toString().length() < MINIMUM_INPUT_LENGTH;
    }

    private boolean isPasswordInvalid() {
        return passwordEditText.getText().toString().length() < MINIMUM_INPUT_LENGTH;
    }

    private boolean isAnyInputInvalid() {
        return isUsernameInvalid() || isPasswordInvalid();
    }

    private void clearValidationError() {
        usernameTextInputLayout.setError(null);
        passwordTextInputLayout.setError(null);
        usernameTextInputLayout.setErrorEnabled(false);
        passwordTextInputLayout.setErrorEnabled(false);
    }

    private void showValidationError() {
        Resources res = getResources();
        if (isUsernameInvalid()) {
            usernameTextInputLayout.setErrorEnabled(true);
            usernameTextInputLayout.setError(String.format(res.getString(R.string.username_validation_error), MINIMUM_INPUT_LENGTH));
        }
        if (isPasswordInvalid()) {
            passwordTextInputLayout.setErrorEnabled(true);
            passwordTextInputLayout.setError(String.format(res.getString(R.string.password_validation_error), MINIMUM_INPUT_LENGTH));
        }
    }

    private class SignInTextWatcher implements TextWatcher {
        private EditText editText;
        private boolean isSpaceAllowed;

        public SignInTextWatcher(EditText editText, boolean isSpaceAllowed) {
            this.editText = editText;
            this.isSpaceAllowed = isSpaceAllowed;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            submitButton.setEnabled(usernameEditText.getText().length() > 0 && passwordEditText.getText().length() > 0);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isSpaceAllowed) {
                String noSpaceText = s.toString().replaceAll(" ", "");
                if (!noSpaceText.equals(s.toString())) {
                    int selectionIndex = editText.getSelectionStart() - 1;
                    editText.setText(noSpaceText);
                    editText.setSelection(selectionIndex);
                }
            }
        }
    }
}

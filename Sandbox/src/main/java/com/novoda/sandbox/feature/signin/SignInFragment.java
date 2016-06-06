package com.novoda.sandbox.feature.signin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.novoda.sandbox.R;
import com.novoda.sandbox.SandboxApplication;

import static com.novoda.sandbox.util.Preconditions.checkNotNull;

/**
 * Created by jingli on 05/06/16.
 */
public class SignInFragment extends Fragment implements SignInContract.View {
    private SignInContract.Presenter presenter;
    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button submitButton;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    public SignInFragment() {
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
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
        View root = inflater.inflate(R.layout.sign_in_fragment, container, false);

        usernameTextInputLayout = (TextInputLayout) root.findViewById(R.id.sign_in_activity_username_wrapper);
        passwordTextInputLayout = (TextInputLayout) root.findViewById(R.id.sign_in_activity_password_wrapper);
        usernameEditText = (EditText) root.findViewById(R.id.sign_in_activity_username_field);
        passwordEditText = (EditText) root.findViewById(R.id.sign_in_activity_password_field);
        submitButton = (Button) root.findViewById(R.id.sign_in_activity_submit_button);

        usernameEditText.addTextChangedListener(new SignInTextWatcher(usernameEditText, false));
        passwordEditText.addTextChangedListener(new SignInTextWatcher(passwordEditText, true));

        submitButton.setEnabled(false);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearValidationError();
                if (presenter.isAnyInputInvalid()) {
                    showValidationError();
                } else {
                    SandboxApplication.setSignedIn();
                    getActivity().finish();
                }
            }
        });

        return root;
    }

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void clearValidationError() {
        usernameTextInputLayout.setError(null);
        passwordTextInputLayout.setError(null);
        usernameTextInputLayout.setErrorEnabled(false);
        passwordTextInputLayout.setErrorEnabled(false);
    }

    @Override
    public void showUsernameValidationError() {
        usernameTextInputLayout.setErrorEnabled(true);
        usernameTextInputLayout.setError(String.format(getResources().getString(R.string.username_validation_error), SignInPresenter.MINIMUM_INPUT_LENGTH));
    }

    @Override
    public void showPasswordValidationError() {
        passwordTextInputLayout.setErrorEnabled(true);
        passwordTextInputLayout.setError(String.format(getResources().getString(R.string.password_validation_error), SignInPresenter.MINIMUM_INPUT_LENGTH));
    }

    public void showValidationError() {
        if (presenter.isUsernameInvalid()) {
            showUsernameValidationError();
        }
        if (presenter.isPasswordInvalid()) {
            showPasswordValidationError();
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

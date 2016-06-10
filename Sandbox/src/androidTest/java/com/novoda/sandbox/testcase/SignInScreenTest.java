package com.novoda.sandbox.testcase;

import android.support.test.rule.ActivityTestRule;

import com.novoda.sandbox.feature.signin.SignInActivity;
import com.novoda.sandbox.screen.SignInScreen;
import com.novoda.sandbox.util.TestRuleHelper;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by jingli on 06/06/16.
 */
public class SignInScreenTest {

    @Rule
    public ActivityTestRule<SignInActivity> signInActivityTestRule = TestRuleHelper.createTestRuleWithLoginState(SignInActivity.class, true);

    private SignInScreen signInScreen = new SignInScreen();

    /**
     * Given I try and click submit
     * When I have not entered a username or password
     * Then the submit button is not clickable
     */
    @Test
    public void checkSubmitButtonIsNotEnabledWithoutCredential() {
        signInScreen.verifySubmitButtonIsNotEnabled();
    }

    /**
     * Given I try and click submit
     * When I have entered a username or password
     * Then the submit button is clickable
     */
    @Test
    public void checkSubmitButtonIsEnabledWithValidInput() {
        signInScreen.enterUsername("root");
        signInScreen.enterPassword("admin");
        signInScreen.verifySubmitButtonIsEnabled();
    }

    /**
     * Given I try and click submit
     * When the username is less than 4 characters
     * Then I am not allowed to sign in
     */
    @Test
    public void checkSubmitButtonIsNotEnabledWithInvalidUsername() {
        signInScreen.enterUsername("god");
        signInScreen.enterPassword("admin");
        signInScreen.clickSubmitButton();
        signInScreen.verifyUsernameValidationErrorIsShown();
    }

}

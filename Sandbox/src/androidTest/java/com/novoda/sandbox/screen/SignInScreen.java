package com.novoda.sandbox.screen;

import com.novoda.sandbox.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by jingli on 10/06/16.
 */
public class SignInScreen {

    public void verifyIsOnSignInScreen() {
        onView(withId(R.id.sign_in_activity_username_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_password_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_submit_button)).check(matches(isDisplayed()));
    }

    public void verifySubmitButtonIsEnabled() {
        onView(withId(R.id.sign_in_activity_submit_button)).check(matches(isEnabled()));
    }

    public void verifySubmitButtonIsNotEnabled() {
        onView(withId(R.id.sign_in_activity_submit_button)).check(matches(not(isEnabled())));
    }

    public void enterUsername(String username) {
        onView(withId(R.id.sign_in_activity_username_field)).perform(clearText(), typeText(username));
    }

    public void enterPassword(String password) {
        onView(withId(R.id.sign_in_activity_password_field)).perform(clearText(), typeText(password));
    }

    public void clearInputFields() {
        onView(withId(R.id.sign_in_activity_username_field)).perform(clearText());
        onView(withId(R.id.sign_in_activity_password_field)).perform(clearText());
    }

    public void clickSubmitButton() {
        onView(withId(R.id.sign_in_activity_submit_button)).perform(click());
    }

    public void signInWithCredential(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmitButton();
    }

}

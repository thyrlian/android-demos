package com.novoda.sandbox.screen;

import com.novoda.sandbox.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jingli on 10/06/16.
 */
public class SignInScreen {

    public void verifyIsOnSignInScreen() {
        onView(withId(R.id.sign_in_activity_username_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_username_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_submit_button)).check(matches(isDisplayed()));
    }

}

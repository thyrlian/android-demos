package com.novoda.sandbox.screen;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.AppAssistant;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jingli on 10/06/16.
 */
public class DetailsScreen {

    public void verifyIsOnDetailsScreen() {
        onView(withId(R.id.details_activity_launch_application)).check(matches(isDisplayed()));
    }

    public void verifyPackageName(String expectedPackageName) {
        onView(withId(R.id.details_activity_app_pkg)).check(matches(withText(AppAssistant.getString(R.string.item_key_pkg) + " : " + expectedPackageName)));
    }

    public void clickLaunchAppButton() {
        onView(withId(R.id.details_activity_launch_application)).perform(click());
    }

}

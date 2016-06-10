package com.novoda.sandbox.screen;

import android.content.Intent;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.AppAssistant;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasFlags;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;

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

    public void clickLaunchAppButton(String expectedPackageName) {
        onView(withId(R.id.details_activity_launch_application)).perform(click());
        intended(allOf(hasAction(equalTo(Intent.ACTION_MAIN)), hasFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED), toPackage(expectedPackageName)));
    }

}

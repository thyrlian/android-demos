package com.novoda.sandbox.testcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.novoda.sandbox.R;
import com.novoda.sandbox.SandboxApplication;
import com.novoda.sandbox.feature.main.MainActivity;
import com.novoda.sandbox.util.AppAssistant;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jingli on 06/06/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                }
            };

    /**
     * Given I am viewing the apps
     * When I am signed out
     * Then only one app is visible
     */
    @Test
    public void showOnlyOneAppWhenSignedOut() {
        SandboxApplication.setSignedOut();
        onView(withText(AppAssistant.getString(R.string.sign_in_button))).check(matches(isDisplayed()));
    }

    /**
     * Given I am viewing the apps
     * When I am signed in
     * Then a full list of apps is visible
     */
    @Test
    public void showAllAppsWhenSignedIn() {
    }

}

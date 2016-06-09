package com.novoda.sandbox.testcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.novoda.sandbox.R;
import com.novoda.sandbox.feature.main.MainActivity;
import com.novoda.sandbox.util.AppAssistant;
import com.novoda.sandbox.util.PackagesCounter;
import com.novoda.sandbox.util.TestRuleHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by jingli on 06/06/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenSignedOutTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = TestRuleHelper.createTestRuleWithLoginState(MainActivity.class, false);

    /**
     * Given I am viewing the apps
     * When I am signed out
     * Then only one app is visible
     */
    @Test
    public void showOnlyOneAppWhenSignedOut() {
        onView(withText(AppAssistant.getString(R.string.sign_in_button))).check(matches(isDisplayed()));
        assertEquals(1, PackagesCounter.getCount());
    }

    /**
     * Given I am viewing the apps (signed out)
     * When I click an app
     * Then I go to the apps details
     */
    @Test
    public void goToAppDetailsWhenClickApp() {
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(R.id.packages_list)).atPosition(0).perform(click());
        onView(withId(R.id.details_activity_launch_application)).check(matches(isDisplayed()));
    }

    /**
     * Given I am signed out
     * When I click sign in button
     * Then I go to the sign in page
     */
    @Test
    public void goToSignInPageWhenClickSignInButton() {
        onView(withText(AppAssistant.getString(R.string.sign_in_button))).perform(click());
        onView(withId(R.id.sign_in_activity_username_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_username_field)).check(matches(isDisplayed()));
        onView(withId(R.id.sign_in_activity_submit_button)).check(matches(isDisplayed()));
    }

}

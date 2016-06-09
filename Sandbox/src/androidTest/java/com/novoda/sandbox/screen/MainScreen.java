package com.novoda.sandbox.screen;

import com.novoda.sandbox.R;
import com.novoda.sandbox.util.AppAssistant;
import com.novoda.sandbox.util.PackageCompanion;

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
 * Created by jingli on 10/06/16.
 */
public class MainScreen {

    public void verifyIsSignedOut() {
        onView(withText(AppAssistant.getString(R.string.sign_in_button))).check(matches(isDisplayed()));
    }

    public void verifyIsSignedIn() {
        onView(withText(AppAssistant.getString(R.string.sign_out_button))).check(matches(isDisplayed()));
    }

    public int getPackagesCount() {
        return PackageCompanion.getCountFromList();
    }

    public String getPackageName(int position) {
        return PackageCompanion.getTextInList(position);
    }

    public void clickPackage(int position) {
        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(R.id.packages_list)).atPosition(position).perform(click());
    }

    public void clickSignInButton() {
        onView(withText(AppAssistant.getString(R.string.sign_in_button))).perform(click());
    }

    public void clickSignOutButton() {
        onView(withText(AppAssistant.getString(R.string.sign_out_button))).perform(click());
    }

    public void verifyPackagesCount(int expectedCount) {
        assertEquals(expectedCount, getPackagesCount());
    }

}

package com.novoda.sandbox.testcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.novoda.sandbox.feature.main.MainActivity;
import com.novoda.sandbox.screen.DetailsScreen;
import com.novoda.sandbox.screen.MainScreen;
import com.novoda.sandbox.screen.SignInScreen;
import com.novoda.sandbox.util.TestRuleHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jingli on 06/06/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenSignedOutTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = TestRuleHelper.createTestRuleWithLoginState(MainActivity.class, false);

    private MainScreen mainScreen = new MainScreen();
    private DetailsScreen detailsScreen = new DetailsScreen();
    private SignInScreen signInScreen = new SignInScreen();

    /**
     * Given I am viewing the apps
     * When I am signed out
     * Then only one app is visible
     */
    @Test
    public void showOnlyOneAppWhenSignedOut() {
        mainScreen.verifyIsSignedOut();
        mainScreen.verifyPackagesCount(1);
    }

    /**
     * Given I am viewing the apps (signed out)
     * When I click an app
     * Then I go to the apps details
     */
    @Test
    public void goToAppDetailsWhenClickApp() {
        int position = 0;
        String packageName = mainScreen.getPackageName(position);
        mainScreen.clickPackage(position);
        detailsScreen.verifyIsOnDetailsScreen();
        detailsScreen.verifyPackageName(packageName);
    }

    /**
     * Given I am signed out
     * When I click sign in button
     * Then I go to the sign in page
     */
    @Test
    public void goToSignInPageWhenClickSignInButton() {
        mainScreen.clickSignInButton();
        signInScreen.verifyIsOnSignInScreen();
    }

}

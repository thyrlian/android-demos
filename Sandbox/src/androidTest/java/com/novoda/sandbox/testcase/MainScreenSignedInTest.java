package com.novoda.sandbox.testcase;

import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.novoda.sandbox.feature.main.MainActivity;
import com.novoda.sandbox.screen.DetailsScreen;
import com.novoda.sandbox.screen.MainScreen;
import com.novoda.sandbox.util.TestRuleHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jingli on 09/06/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenSignedInTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = TestRuleHelper.createTestRuleWithLoginState(MainActivity.class, true);

    private MainScreen mainScreen = new MainScreen();
    private DetailsScreen detailsScreen = new DetailsScreen();
    private int installedAppsCount = InstrumentationRegistry.getTargetContext().getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA).size();

    /**
     * Given I am viewing the apps
     * When I am signed in
     * Then a full list of apps is visible
     */
    @Test
    public void showAllAppsWhenSignedIn() {
        mainScreen.verifyIsSignedIn();
        mainScreen.verifyPackagesCount(installedAppsCount);
    }

    /**
     * Given I am viewing the apps (signed in)
     * When I click an app
     * Then I go to the apps details
     */
    @Test
    public void goToAppDetailsWhenClickApp() {
        int appPositionToClick = 1;
        String packageName = mainScreen.getPackageName(appPositionToClick);
        mainScreen.clickPackage(appPositionToClick);
        detailsScreen.verifyIsOnDetailsScreen();
        detailsScreen.verifyPackageName(packageName);
    }

    /**
     * Given I am signed in
     * When I click sign out button
     * Then I sign out
     */
    @Test
    public void goToSignInPageWhenClickSignInButton() {
        mainScreen.clickSignOutButton();
        mainScreen.verifyIsSignedOut();
        mainScreen.verifyPackagesCount(1);
    }

}

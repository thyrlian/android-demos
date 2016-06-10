package com.novoda.sandbox.testcase;

import android.support.test.rule.ActivityTestRule;

import com.novoda.sandbox.feature.details.DetailsActivity;
import com.novoda.sandbox.screen.DetailsScreen;
import com.novoda.sandbox.util.TestRuleHelper;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by jingli on 06/06/16.
 */
public class DetailsScreenTest {

    private String packageName = "com.android.browser";

    @Rule
    public ActivityTestRule<DetailsActivity> detailsActivityTestRule = TestRuleHelper.createTestRuleForDetailsActivity(packageName);

    private DetailsScreen detailsScreen = new DetailsScreen();

    /**
     * Given I am viewing the details
     * When I click launch application
     * Then the 3rd party app is opened
     */
    @Test
    public void testExternalAppCanBeOpened() {
        detailsScreen.clickLaunchAppButton(packageName);
    }

}

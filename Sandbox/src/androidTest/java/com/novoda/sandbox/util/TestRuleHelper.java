package com.novoda.sandbox.util;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.novoda.sandbox.SandboxApplication;
import com.novoda.sandbox.feature.details.DetailsActivity;
import com.novoda.sandbox.feature.details.DetailsPresenter;

/**
 * Created by jingli on 08/06/16.
 */
public class TestRuleHelper<T extends Activity> {

    private TestRuleHelper() {
    }

    public static <T>ActivityTestRule createTestRuleWithLoginState(Class<T> activityClass, final boolean loginState) {
        return new ActivityTestRule(activityClass) {
            @Override
            protected void beforeActivityLaunched() {
                super.beforeActivityLaunched();
                if (loginState) {
                    SandboxApplication.setSignedIn();
                } else {
                    SandboxApplication.setSignedOut();
                }
            }
        };
    }

    public static ActivityTestRule<DetailsActivity> createTestRuleForDetailsActivity(final String packageName) {
        return new ActivityTestRule<DetailsActivity>(DetailsActivity.class) {
            @Override
            protected Intent getActivityIntent() {
                return DetailsPresenter.createIntent(packageName);
            }
        };
    }

}

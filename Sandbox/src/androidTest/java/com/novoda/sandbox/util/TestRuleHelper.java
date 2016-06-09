package com.novoda.sandbox.util;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.novoda.sandbox.SandboxApplication;

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

}

package com.novoda.sandbox.util;

import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;

/**
 * Created by jingli on 06/06/16.
 */
public class AppAssistant {

    public static String getString(@StringRes int stringName) {
        return InstrumentationRegistry.getTargetContext().getResources().getString(stringName);
    }

}

package com.novoda.sandbox.util;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;

import com.novoda.sandbox.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jingli on 10/06/16.
 */
public class PackagesCounter {

    private static int count;

    private PackagesCounter() {
    }

    public static int getCount() {
        count = 0;

        Matcher<Object> matcher = new BoundedMatcher<Object, String>(String.class) {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(String item) {
                count += 1;
                return true;
            }
        };

        DataInteraction dataInteraction = onData(matcher).inAdapterView(withId(R.id.packages_list));
        try {
            // do a nonsense operation with no impact
            // because ViewMatchers would only start matching when action is performed on DataInteraction
            dataInteraction.perform(typeText(""));
        } catch (Exception e) {
        }

        int result = count;
        count = 0;
        return result;
    }

}

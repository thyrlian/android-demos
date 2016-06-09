package com.novoda.sandbox.util;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.novoda.sandbox.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by jingli on 10/06/16.
 */
public class PackageCompanion {

    private static int count;
    private static String text;
    private static final int listViewId = R.id.packages_list;

    private PackageCompanion() {
    }

    public static int getCountFromList() {
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

        DataInteraction dataInteraction = onData(matcher).inAdapterView(withId(listViewId));
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

    public static String getTextInList(int position) {
        text = "";

        onData(allOf(is(instanceOf(String.class)))).inAdapterView(withId(listViewId)).atPosition(position).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                text = ((AppCompatTextView) view).getText().toString();
            }
        });

        String result = text;
        text = "";
        return result;
    }

}

package com.novoda.sandbox.util;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ListView;

import com.novoda.sandbox.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                count = ((ListView) item).getCount();
                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        };

        onView(withId(listViewId)).check(matches(matcher));

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

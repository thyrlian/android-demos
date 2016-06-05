package com.novoda.sandbox.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.novoda.sandbox.util.Preconditions.checkNotNull;

/**
 * Created by jingli on 05/06/16.
 */
public class ActivityUtils {
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}

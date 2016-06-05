package com.novoda.sandbox.util;

/**
 * Created by jingli on 05/06/16.
 */
public class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}

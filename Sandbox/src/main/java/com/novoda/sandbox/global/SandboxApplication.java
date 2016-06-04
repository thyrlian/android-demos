package com.novoda.sandbox.global;

import android.app.Application;

public class SandboxApplication extends Application {

    private static boolean signedIn = false;

    public static void setSignedIn() {
        signedIn = true;
    }

    public static void setSignedOut() {
        signedIn = false;
    }

    public static boolean isSignedIn() {
        return signedIn;
    }
}

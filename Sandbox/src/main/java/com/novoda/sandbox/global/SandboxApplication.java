package com.novoda.sandbox.global;

import android.app.Application;

public class SandboxApplication extends Application {

    private static boolean signedIn;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    private void initialize() {
        signedIn = false;
    }

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

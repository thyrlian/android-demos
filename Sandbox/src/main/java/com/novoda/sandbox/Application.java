package com.novoda.sandbox;

public class Application extends android.app.Application {

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

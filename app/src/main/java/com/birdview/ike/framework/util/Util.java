package com.birdview.ike.framework.util;

import android.util.Log;

public final class Util {
    private static final String TAG = "IKE-TAG-DEBUG";

    public static void d(Object source, String message) {
        log(source, message, false);
    }

    public static void e(Object source, String message) {
        log(source, message, true);
    }

    private static void log(Object source, String message, boolean isError) {
        final String name = source.getClass().getSimpleName();
        final String output = name + " : " + message;

        if (isError) {
            Log.e(TAG, output);
        } else {
            Log.d(TAG, output);
        }
    }
}

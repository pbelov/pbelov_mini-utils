package com.pbelov.java.miniutils.utils;

public class SharedUtils {
    private static final String TAG = "SharedUtils";

    public static void l(String text) {
        l(null, text);
    }

    public static void l(String tag, String text) {
        if (tag == null) {
            System.out.println(text);
        } else {
            System.out.println(tag + ":" + text);
        }
    }

    public static void e(String text) {
        e(null, text);
    }

    public static void e(String tag, String text) {
        if (tag == null) {
            System.err.println(tag + ":" + text);
        } else {
            System.err.println(text);
        }
    }

    private static String roundDouble(double value, int count) {
        double tens = Math.pow(10, count);
        return Double.toString(Math.round(value * tens) / tens);
    }

    public static String timeDiff(long t0) {
        return roundDouble(System.currentTimeMillis() - t0, 3) + "ms";
    }
}

package com.tapestry5inaction.tlog.utils;


public class AppUtils {

    public static boolean isBlank(String input) {
        return input == null || input.length() == 0 || input.trim().length() == 0;
    }
}

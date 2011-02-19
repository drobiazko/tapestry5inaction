package com.tapestry5inaction.tlog.core.utils;


public class Utils {

    public static boolean isBlank(String input) {
        return input == null || input.length() == 0 || input.trim().length() == 0;
    }
}

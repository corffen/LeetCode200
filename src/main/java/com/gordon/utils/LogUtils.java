package com.gordon.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {
    private static final Logger logger = Logger.getLogger("LeetCode");

    private LogUtils() {

    }

    public static void log(String msg) {
        logger.log(Level.INFO, msg);
    }
}

package org.yliadevelopment.util;

public class Log {
    public static void info(String message) {
        System.out.printf("Info: %s\n", message);
    }

    public static void error(String message) {
        System.out.printf("Error: %s\n", message);
    }
}

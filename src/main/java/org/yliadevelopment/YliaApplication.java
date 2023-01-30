package org.yliadevelopment;

import org.yliadevelopment.logger.MainLogger;

public class YliaApplication {

    public static void main(String[] args) {
        MainLogger.get().info("Hello, %s!", "world!");
        MainLogger.get().warn("Hey, something unexpected happened: %s", "Bro momento");
        MainLogger.get().error("Something went wrong: %s", "de potassio");
    }
    
}

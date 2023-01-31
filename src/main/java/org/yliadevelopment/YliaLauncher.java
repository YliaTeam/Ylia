package org.yliadevelopment;

import org.yliadevelopment.cli.impl.CliImpl;
import org.yliadevelopment.logger.MainLogger;

public class YliaLauncher {

    public static void main(String[] args) {
        var cli = new CliImpl();
        if (!cli.startCli()) {
            MainLogger.get().error("Can't start cli. Exiting...");
            System.exit(1);
        }
    }

}

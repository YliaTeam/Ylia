package org.yliadevelopment;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.State;
import org.yliadevelopment.network.client.ProxyConnector;
import org.yliadevelopment.network.server.ProxyServer;

public class YliaLauncher {

    static MainLogger logger = MainLogger.get();

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            logger.error("I need more arguments! <SERVER_ADDR> <SERVER_PORT> <PROXY_ADDR> <PROXY_PORT>");
            System.exit(1);
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String proxyAddress = args[2];
        int proxyPort = Integer.parseInt(args[3]);

        logger.info("Starting on %s:%d using %s:%d", serverAddress, serverPort, proxyAddress, proxyPort);
        var state = new State(serverAddress, serverPort, proxyAddress, proxyPort);
        var connector = new ProxyConnector(state);
        var server = new ProxyServer(state);

        server.start();
        connector.start();

        server.waitFinish();
        connector.waitFinish();
        logger.info("Server has stopped!");
    }

}

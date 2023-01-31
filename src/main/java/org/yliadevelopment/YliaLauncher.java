package org.yliadevelopment;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.client.ProxyConnector;
import org.yliadevelopment.network.server.ProxyServer;

public class YliaLauncher {

    static MainLogger logger = MainLogger.get();

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 4) {
            logger.error("I need more arguments! <SERVER_ADDR> <SERVER_PORT> <PROXY_ADDR> <PROXY_PORT>");
            System.exit(1);
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String proxyAddress = args[2];
        int proxyPort = Integer.parseInt(args[3]);

        logger.info("Starting on %s:%d using %s:%d", serverAddress, serverPort, proxyAddress, proxyPort);

        logger.info("Started connection...");
        ProxyConnector connector = new ProxyConnector(serverAddress, serverPort);
        ProxyServer server = new ProxyServer(proxyAddress, proxyPort);

        server.start();
        connector.start();


        logger.info("Connected!");

        connector.waitFinish();
        server.waitFinish();

        connector.shutdown();
        server.shutdown();

        logger.info("Server has stopped!");
    }

}

package org.yliadevelopment;

import java.net.InetSocketAddress;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.State;
import org.yliadevelopment.network.raknet.RaknetSocket;
import org.yliadevelopment.network.server.ProxyServer;

public class YliaLauncher {

    static MainLogger logger = MainLogger.get();

    public static void main(String[] args) throws Exception {
        logger.debug("Ylia - v1.0.0");
        if (args.length < 4) {
            logger.error("I need more arguments! <SERVER_ADDR> <SERVER_PORT> <PROXY_ADDR> <PROXY_PORT>");
            System.exit(1);
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);
        String proxyAddress = args[2];
        int proxyPort = Integer.parseInt(args[3]);
        State state = new State(serverAddress, serverPort, proxyAddress, proxyPort);

        ProxyServer server = new ProxyServer(state);
        server.start();

        server.waitFinish();
        logger.warn("Server has stopped!");
    }

}

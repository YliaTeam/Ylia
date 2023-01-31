package org.yliadevelopment;

import java.net.InetSocketAddress;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.raknet.RaknetSocket;

public class YliaLauncher {

    static MainLogger logger = MainLogger.get();

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 4) {
            logger.error("I need more arguments! <SERVER_ADDR> <SERVER_PORT> <PROXY_ADDR> <PROXY_PORT>");
            System.exit(1);
        }

        String proxyAddress = args[2];
        int proxyPort = Integer.parseInt(args[3]);

        RaknetSocket socket = new RaknetSocket(new InetSocketAddress(proxyAddress, proxyPort));

        socket.startListening();

        logger.info("Server has stopped!");
    }

}

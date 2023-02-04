package org.yliadevelopment;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.BinaryStream;
import org.yliadevelopment.network.raknet.RaknetSocket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPingPacket;

public class YliaLauncher {

    static MainLogger logger = MainLogger.get();

    public static void main(String[] args) throws Exception {
        logger.debug("Ylia - v1.0.0");

        var pk = new UnconnectedPingPacket();
        pk.pingId = 0xaabb;
        pk.encode();

        var fos = new FileOutputStream("dump.hex");
        fos.write(pk.getBuffer());
        fos.close();

        logger.info("Dumped a unconnected ping packet into file.");

        if (args.length < 4) {
            logger.error("I need more arguments! <SERVER_ADDR> <SERVER_PORT> <PROXY_ADDR> <PROXY_PORT>");
            System.exit(1);
        }

        String proxyAddress = args[2];
        int proxyPort = Integer.parseInt(args[3]);

        var socket = new RaknetSocket(new InetSocketAddress(proxyAddress, proxyPort));

        socket.startListening();

        logger.warn("Server has stopped!");
    }

}

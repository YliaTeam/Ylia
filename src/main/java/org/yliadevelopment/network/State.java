package org.yliadevelopment.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class State {
    // TODO
    public volatile ConcurrentLinkedQueue<Integer> connectorQueue = new ConcurrentLinkedQueue<>();
    public volatile ConcurrentLinkedQueue<Integer> serverQueue = new ConcurrentLinkedQueue<>();

    public InetSocketAddress proxyAddress;
    public InetSocketAddress serverAddress;

    public State(String serverHost, int serverPort, String proxyHost, int proxyPort) throws UnknownHostException {
        this.proxyAddress = new InetSocketAddress(InetAddress.getByName(proxyHost), proxyPort);
        this.serverAddress = new InetSocketAddress(InetAddress.getByName(serverHost), serverPort);
    }
}

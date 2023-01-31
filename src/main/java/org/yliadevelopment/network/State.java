package org.yliadevelopment.network;

import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class State {
    public volatile ConcurrentLinkedQueue<DatagramPacket> connectorQueue = new ConcurrentLinkedQueue<>();
    public volatile ConcurrentLinkedQueue<DatagramPacket> serverQueue = new ConcurrentLinkedQueue<>();

    public InetSocketAddress proxyAddress;
    public InetSocketAddress serverAddress;

    public State(String serverHost, int serverPort, String proxyHost, int proxyPort) throws UnknownHostException {
        this.proxyAddress = new InetSocketAddress(InetAddress.getByName(proxyHost), proxyPort);
        this.serverAddress = new InetSocketAddress(InetAddress.getByName(serverHost), serverPort);
    }
}

package org.yliadevelopment.network;

import io.netty.channel.socket.DatagramPacket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class State {
    public volatile Queue<DatagramPacket> connectorQueue = new LinkedBlockingQueue<>();
    public volatile Queue<DatagramPacket> serverQueue = new LinkedBlockingQueue<>();

    public InetSocketAddress target;
    public InetSocketAddress source;

    public State(String sourceHost, int sourceTarget, String targetHost, int targetPort) throws UnknownHostException {
        this.target = new InetSocketAddress(InetAddress.getByName(targetHost), targetPort);
        this.source = new InetSocketAddress(InetAddress.getByName(sourceHost), sourceTarget);
    }
}

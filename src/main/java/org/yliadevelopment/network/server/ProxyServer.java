package org.yliadevelopment.network.server;

import org.yliadevelopment.network.IService;
import org.yliadevelopment.network.State;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ProxyServer implements IService {
    DatagramSocket socket;
    State state;

    public ProxyServer(State state) throws SocketException {
        this.state = state;
        this.socket = new DatagramSocket(state.proxyAddress);
    }

    @Override
    public void waitFinish() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void run() {

    }
}

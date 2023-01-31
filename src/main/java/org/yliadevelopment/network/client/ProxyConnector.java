package org.yliadevelopment.network.client;

import org.yliadevelopment.network.IService;
import org.yliadevelopment.network.State;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ProxyConnector implements IService {
    public DatagramSocket socket = new DatagramSocket();
    State state;

    public ProxyConnector(State state) throws SocketException {
      this.state = state;
    }

    @Override
    public void run() {

    }

    @Override
    public void waitFinish() {

    }

    @Override
    public void shutdown() {

    }
}

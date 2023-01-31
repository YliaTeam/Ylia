package org.yliadevelopment.network.server;

import org.yliadevelopment.network.IService;
import org.yliadevelopment.network.State;
import org.yliadevelopment.network.raknet.RaknetSocket;

public class ProxyServer implements IService {
    RaknetSocket socket;
    State state;
    String threadName = "Proxy Server";

    public ProxyServer(State state) {
        this.state = state;
        this.socket = new RaknetSocket(state.proxyAddress);
    }

    @Override
    public void waitFinish() {
        while (true) {
        }
    }

    @Override
    public void shutdown() {

    }

    public Thread start() {
        var thread = new Thread(this);
        thread.setName("Proxy Server Thread");
        thread.start();

        return thread;
    }

    @Override
    public void run() {
        socket.startListening();
    }
}

package org.yliadevelopment.network;

public interface IService extends Runnable {
    default Thread start() {
        var thread = new Thread(this);
        thread.start();

        return thread;
    }

    void waitFinish();

    void shutdown();
}

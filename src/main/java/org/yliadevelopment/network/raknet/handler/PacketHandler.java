package org.yliadevelopment.network.raknet.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.yliadevelopment.network.raknet.RaknetPacket;
import org.yliadevelopment.network.raknet.RaknetSocket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class PacketHandler {

    public static Set<PacketHandler> DEFAULT_HANDLERS = new LinkedHashSet<>();

    static {
        for (Class<? extends PacketHandler> clazz : new Reflections(PacketHandler.class.getPackageName())
                .getSubTypesOf(PacketHandler.class)) {

            try {
                var instance = clazz.getConstructor().newInstance();

                DEFAULT_HANDLERS.add(instance);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T extends RaknetPacket> void invokeHandlerFor(RaknetSocket socket, T value) {
        for (var handler : DEFAULT_HANDLERS) {
            for (var method : handler.getClass().getMethods()) {
                if (method.getParameterTypes().length != 2 ||
                    !method.getParameterTypes()[0].equals(value.getClass()) ||
                    !method.getParameterTypes()[1].equals(RaknetSocket.class))
                    continue;

                try {
                    method.invoke(handler, value, socket);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

package org.yliadevelopment.network.raknet.protocol;

import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

import org.reflections.Reflections;
import org.yliadevelopment.logger.MainLogger;

public final class RaknetPacketList {
    
    private RaknetPacketList() {}

    static MainLogger logger = MainLogger.get();

    public static final Map<Integer, Class<? extends RaknetPacket>> PACKETS = new LinkedHashMap<>();

    static {
        for (Class<? extends RaknetPacket> clazz : new Reflections(RaknetPacketList.class.getPackageName())
            .getSubTypesOf(RaknetPacket.class)) {
            
            try {
                var idField = clazz.getField("NETWORK_ID");

                if (!Modifier.isStatic(idField.getModifiers())) {
                    throw new IllegalArgumentException(); /* The field needs to be static */
                }

                idField.setAccessible(true);

                var id = (int) idField.get(null);

                PACKETS.put(id, clazz);                      
            } catch (NoSuchFieldException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
                logger.error("The packet class %s does not have a NETWORK_ID field or it is not acessible!", clazz.getName());
            }
        }
    }

}

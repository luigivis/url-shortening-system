package com.luigivis.coreservice.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ShortUUID {
    public static String shortUUID() {
        final var uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }
}

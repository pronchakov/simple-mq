package edu.mq.simple;

import lombok.SneakyThrows;

import java.util.UUID;

public class Utils {

    @SneakyThrows
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}

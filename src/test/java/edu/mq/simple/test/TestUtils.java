package edu.mq.simple.test;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TestUtils {

    @SneakyThrows
    public static void deleteQueue(String baseDir, String queueName) {
        FileUtils.deleteDirectory(new File(baseDir + "/" + queueName));
    }

    public static long messageCount(String baseDir, String queueName) {
        return new File(baseDir + "/" + queueName).list().length;
    }

    @SneakyThrows
    public static String readMessage(String baseDir, String queueName) {
        final var file = new File(baseDir + "/" + queueName).listFiles()[0];
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static void putMessage(String baseDir, String queueName, String message) {
        new File(baseDir + "/" + queueName).mkdirs();
        final var file = new File(baseDir + "/" + queueName + "/" + UUID.randomUUID() + ".smq");

        FileUtils.writeStringToFile(file, message, StandardCharsets.UTF_8);
    }
}

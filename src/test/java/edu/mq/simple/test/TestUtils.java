package edu.mq.simple.test;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class TestUtils {

    @SneakyThrows
    public static void deleteQueue(String baseDir, String queueName) {
        FileUtils.deleteDirectory(new File(baseDir + "/" + queueName));
    }

}

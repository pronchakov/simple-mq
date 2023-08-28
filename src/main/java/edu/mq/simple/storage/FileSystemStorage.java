package edu.mq.simple.storage;

import lombok.Cleanup;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileSystemStorage implements Storage {

    private final String basePath;

    public FileSystemStorage(String basePath) {
        if (!basePath.endsWith("/")) {
            basePath = basePath + "/";
        }
        this.basePath = basePath;
    }

    @Override
    public String getBasePath() {
        return basePath;
    }

    @Override
    public void sendMessage(String queueName, String message) throws CannotSendMessageException {
        final var filePath = basePath +
                queueName +
                "/" +
                new Date().getTime() +
                ".mq";

        final var file = new File(filePath);
        try {
            @Cleanup final var fileWriter = new FileWriter(file);
            IOUtils.write(message, fileWriter);
        } catch (IOException e) {
            throw new CannotSendMessageException("ERROR: cannot write to file " + file.getAbsolutePath() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void createQueue(String queueName) {
        final var dir = new File(basePath + queueName);
        dir.mkdirs();
    }
}

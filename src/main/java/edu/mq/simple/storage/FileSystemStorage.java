package edu.mq.simple.storage;

import edu.mq.simple.entity.UniversalMessage;
import edu.mq.simple.storage.json.CannotTransformMessageToJSONException;
import edu.mq.simple.storage.json.JSONMessageMapper;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileSystemStorage implements Storage {

    private final String basePath;
    private JSONMessageMapper jsonMapper = new JSONMessageMapper();

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
    public void sendMessage(String queueName, UniversalMessage message) throws CannotSendMessageException {
        final var filePath = basePath +
                queueName +
                "/" +
                new Date().getTime() +
                ".mq";

        final var file = new File(filePath);
        try {
            var text = jsonMapper.transformMessage(message);

            @Cleanup final var fileWriter = new FileWriter(file);
            IOUtils.write(text, fileWriter);
        } catch (CannotTransformMessageToJSONException e) {
            throw new CannotSendMessageException("Cannot prepare JMS message for saving", e);
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

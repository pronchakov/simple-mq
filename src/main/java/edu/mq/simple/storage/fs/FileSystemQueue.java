package edu.mq.simple.storage.fs;

import edu.mq.simple.storage.exception.CannotSendMessageException;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

@Data
public class FileSystemQueue {

    private String basePath;
    private String queueName;
    private Queue<File> files = new LinkedList();

    public FileSystemQueue(String basePath, String queueName) {
        this.basePath = basePath;
        this.queueName = queueName;

        final File queueDir = ensureDirExists(basePath, queueName);
        readExistingFilesData(queueDir);
    }

    public synchronized String readFile() {
        try {
            @Cleanup final FileReader fileReader = new FileReader(files.peek(), StandardCharsets.UTF_8);
            final var string = IOUtils.toString(fileReader);
            files.poll();
            return string;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // todo:
        } catch (IOException e) {
            throw new RuntimeException(e); // todo:
        }
    }

    public synchronized void writeFile(String text) throws CannotSendMessageException {
        final var filePath = basePath +
                queueName +
                "/" +
                new Date().getTime() +
                ".mq";

        final var file = new File(filePath);
        try {
            @Cleanup final var fileWriter = new FileWriter(file);
            IOUtils.write(text, fileWriter);
            files.add(file);
        }  catch (IOException e) {
            throw new CannotSendMessageException("ERROR: cannot write to file " + file.getAbsolutePath() + ": " + e.getMessage(), e);
        }
    }

    private void readExistingFilesData(File queueDir) {
        final var listFiles = queueDir.listFiles((dir1, name) -> name.endsWith(".smq"));
        for (File file : listFiles) {
            files.add(file);
        }
    }

    private static File ensureDirExists(String basePath, String queueName) {
        final var queueDir = new File(basePath + queueName);
        queueDir.mkdirs();
        return queueDir;
    }
}

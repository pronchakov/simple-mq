package edu.mq.simple.storage.fs;

import edu.mq.simple.storage.exception.CannotWriteMessageException;
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

    private final File queueDir;
    private String basePath;
    private String queueName;
    private Queue<File> unreadFiles = new LinkedList<>();
    private Queue<File> readFiles = new LinkedList<>();

    public FileSystemQueue(String basePath, String queueName) {
        this.basePath = basePath;
        this.queueName = queueName;

        queueDir = ensureDirExists(basePath, queueName);
        readExistingFilesData(queueDir);
    }

    public synchronized String readFile() {
        try {
            var nextFileToRead = unreadFiles.poll();
            if (nextFileToRead == null) {
                return null;
            }
            @Cleanup final FileReader fileReader = new FileReader(nextFileToRead, StandardCharsets.UTF_8);
            final var string = IOUtils.toString(fileReader);
            return string;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // todo:
        } catch (IOException e) {
            throw new RuntimeException(e); // todo:
        }
    }

    public synchronized void writeFile(String text) throws CannotWriteMessageException {
        final var file = constructFile();
        try {
            @Cleanup final var fileWriter = new FileWriter(file);
            IOUtils.write(text, fileWriter);
            unreadFiles.add(file);
        }  catch (IOException e) {
            throw new CannotWriteMessageException("ERROR: cannot write to file " + file.getAbsolutePath() + ": " + e.getMessage(), e);
        }
    }

    private File constructFile() {
        final var time = new Date().getTime();
        var index = 0L;

        var filePath = constructFileName(time, index);
        var file = new File(filePath);

        while (file.exists()) {
            filePath = constructFileName(time, index);
            file = new File(filePath);

            index++;
        }
        return file;
    }

    private String constructFileName(long time, long index) {
        return new StringBuilder()
                .append(basePath)
                .append(queueName)
                .append("/")
                .append(time)
                .append(".")
                .append(String.format("%06d", index))
                .append(".smq")
                .toString();
    }

    private void readExistingFilesData(File queueDir) {
        final var listFiles = queueDir.listFiles((dir1, name) -> name.endsWith(".smq"));
        for (File file : listFiles) {
            unreadFiles.add(file);
        }
    }

    private static File ensureDirExists(String basePath, String queueName) {
        final var queueDir = new File(basePath + queueName);
        queueDir.mkdirs();
        return queueDir;
    }
}

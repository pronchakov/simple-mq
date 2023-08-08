package edu.mq.simple;

import edu.mq.simple.json.MessageToJsonMapper;
import edu.mq.simple.message.SimpleMQTextMessage;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class SimpleMQMessageProducer extends SimpleMQAbstractMessageProducer {

    private String baseDir;
    private Destination destination;
    private MessageToJsonMapper mapper;

    public SimpleMQMessageProducer(String baseDir, Destination destination) {
        this.baseDir = baseDir;
        this.destination = destination;
        mapper = new MessageToJsonMapper();
    }

    @SneakyThrows
    @Override
    public void send(Message message) throws JMSException {
        var queue = (Queue) destination;
        final var file = new File(baseDir + "/" + queue.getQueueName() + "/" + new Date().getTime() + ".mqd");

        final var textMessage = (SimpleMQTextMessage) message;
        final var text = mapper.transformMessage(textMessage.getMessage());

        final var fileWriter = new FileWriter(file);
        fileWriter.write(text);
        fileWriter.close();
    }

    @Override
    public void close() throws JMSException {

    }
}

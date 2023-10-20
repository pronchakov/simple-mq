package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.jms.session.SimpleMQSession;
import edu.mq.simple.storage.exception.CannotReadMessageException;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleMQMessageConsumer extends SimpleMQAbstractMessageConsumer {
    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;

    @Override
    public Message receive() throws JMSException { // TODO: wrong implementation. add waiting
        final SimpleMQMessage jmsMessage;
        try {
            jmsMessage = session.getStorage().readMessage(((Queue) destination).getQueueName()); // todo: cam be topic
            if (jmsMessage == null) {
                return null;
            }
        } catch (CannotReadMessageException e) {
            throw new RuntimeException(e); // todo:
        }
        return jmsMessage;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        final SimpleMQMessage jsonMessage;
        try {
            jsonMessage = session.getStorage().readMessage(((Queue) destination).getQueueName()); // todo: cam be topic
            if (jsonMessage == null) {
                return null;
            }
        } catch (CannotReadMessageException e) {
            throw new RuntimeException(e); // todo:
        }
        return jsonMessage;
    }

    @Override
    public void close() throws JMSException {

    }
}

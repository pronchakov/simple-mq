package edu.mq.simple;

import edu.mq.simple.message.SimpleMQAbstractMessage;
import edu.mq.simple.storage.CannotSendMessageException;
import edu.mq.simple.json.CannotTransformMessageToJSONException;
import edu.mq.simple.json.MessageToJsonMapper;
import edu.mq.simple.message.SimpleMQTextMessage;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleMQMessageProducer extends SimpleMQAbstractMessageProducer {

    @NonNull
    private SimpleMQSession session;
    @NonNull
    private Destination destination;
    private MessageToJsonMapper mapper = new MessageToJsonMapper();

    @Override
    public void send(Message message) throws JMSException {
        if (!(message instanceof SimpleMQAbstractMessage)) {
            throw new JMSException("Unknown message type", "SMQ003");
        }

        var queue = (Queue) destination;

        final var abstractMessage = (SimpleMQAbstractMessage) message;
        try {
            var text = mapper.transformMessage(abstractMessage.getMessage());
            session.getStorage().sendMessage(queue.getQueueName(), text);
        } catch (CannotTransformMessageToJSONException e) {
            throw new JMSException("Cannot prepare JMS message for saving", "SMQ001", e);
        } catch (CannotSendMessageException e) {
            throw new JMSException("Cannot save JMS message", "SMQ002", e);
        }
    }

    @Override
    public void close() throws JMSException {

    }
}

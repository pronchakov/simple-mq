package edu.mq.simple.jms.message.type.text;

import edu.mq.simple.jms.message.SimpleMQMessage;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public abstract class SimpleMQAbstractTextMessage extends SimpleMQMessage implements TextMessage {
    @Override
    public String getText() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setText(String string) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

}

package edu.mq.simple.jms.message.abstrct;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public abstract class SimpleMQAbstractTextMessage extends SimpleMQAbstractMessage implements TextMessage {
    @Override
    public String getText() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setText(String string) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

}

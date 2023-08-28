package edu.mq.simple.message;

import edu.mq.simple.message.abstrct.SimpleMQAbstractTextMessage;
import jakarta.jms.JMSException;

public class SimpleMQTextMessage extends SimpleMQAbstractTextMessage {

    private String text;

    public SimpleMQTextMessage(String text) {
        this.text = text;
    }

    @Override
    public String getText() throws JMSException {
        return text;
    }

    @Override
    public void setText(String text) throws JMSException {
        this.text = text;
    }

    @Override
    public String getBodyAsString() {
        return text;
    }

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.TEXT;
    }
}

package edu.mq.simple.jms.message.type.text;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import jakarta.jms.JMSException;
import lombok.Getter;

public class SimpleMQTextMessage extends SimpleMQAbstractTextMessage {

    @Getter
    private String value;

    public SimpleMQTextMessage(String value) {
        this.value = value;
    }

    @Override
    public String getText() throws JMSException {
        return value;
    }

    @Override
    public void setText(String text) throws JMSException {
        this.value = text;
    }

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.TEXT;
    }
}

package edu.mq.simple.message;

import jakarta.jms.JMSException;
import lombok.Getter;

@Getter
public class SimpleMQTextMessage extends SimpleMQAbstractTextMessage {

    public SimpleMQTextMessage(String text) {
        message.setBody(text);
        message.setBodyType("text");
    }

    @Override
    public void setText(String text) throws JMSException {
        this.message.setBody(text);
    }

    @Override
    public String getText() throws JMSException {
        return message.getBody();
    }

}

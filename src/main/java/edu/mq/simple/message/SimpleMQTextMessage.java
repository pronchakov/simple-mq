package edu.mq.simple.message;

import edu.mq.simple.entity.UniversalMessage;
import jakarta.jms.JMSException;
import lombok.Getter;

@Getter
public class SimpleMQTextMessage extends SimpleMQAbstractTextMessage {

    private UniversalMessage message;

    public SimpleMQTextMessage(String text) {
        this.message = UniversalMessage.builder().body(text).build();
    }

    @Override
    public void setText(String string) throws JMSException {
        this.message.setBody(string);
    }

    @Override
    public String getText() throws JMSException {
        return message.getBody();
    }
}

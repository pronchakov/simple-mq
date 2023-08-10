package edu.mq.simple.message;

import jakarta.jms.JMSException;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
public class SimpleMQBytesMessage extends SimpleMQAbstractBytesMessage {

    public SimpleMQBytesMessage() {
        message.setBodyType("bytes");
    }

    @Override
    public void writeBytes(byte[] value) throws JMSException {
        message.setBody(Base64.getEncoder().encodeToString(value));
    }

}

package edu.mq.simple.message;

import jakarta.jms.JMSException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
public class SimpleMQBytesMessage extends SimpleMQAbstractBytesMessage {

    private int length;

    public SimpleMQBytesMessage() {
        message.setBodyType("bytes");
    }

    @SneakyThrows
    public SimpleMQBytesMessage(String base64String) {
        message.setBodyType("bytes");
        message.setBody(base64String);
        length = Base64.getDecoder().decode(base64String).length;
    }

    @Override
    public void writeBytes(byte[] value) throws JMSException {
        message.setBody(Base64.getEncoder().encodeToString(value));
        length = value.length;
    }

    @Override
    public int readBytes(byte[] value) throws JMSException {
        final var base64EncidedBody = message.getBody();
        final var bytesBody = Base64.getDecoder().decode(base64EncidedBody);
        final var lengthToRead = min(value.length, length);
        System.arraycopy(bytesBody, 0, value, 0, lengthToRead);
        return lengthToRead;
    }

    @Override
    public long getBodyLength() throws JMSException {
        return length;
    }

    private int min (int a, int b) {
        if (a < b) return a;
        if (b < a) return b;
        return a;
    }
}

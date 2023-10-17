package edu.mq.simple.jms.message.type.bytes;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import jakarta.jms.JMSException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleMQBytesMessage extends SimpleMQAbstractBytesMessage {

    @Getter
    private ByteArrayOutputStream outputStream;
    private InputStream is;

    public SimpleMQBytesMessage() {
        outputStream = new ByteArrayOutputStream();
    }

    public SimpleMQBytesMessage(InputStream inputStream) {
        is = inputStream;
    }

    @SneakyThrows
    @Override
    public long getBodyLength() throws JMSException {
        if (outputStream != null) {
            return outputStream.size();
        } else {
            return is.available();
        }
    }

    @SneakyThrows
    @Override
    public void writeBytes(byte[] value) throws JMSException {
        outputStream.writeBytes(value);
    }

    @Override
    public int readBytes(byte[] value) throws JMSException {
        try {
            return is.read(value);
        } catch (IOException e) {
            throw new JMSException("Cannot read bytes", "SMQ004", e);
        }
    }

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.BYTES;
    }
}

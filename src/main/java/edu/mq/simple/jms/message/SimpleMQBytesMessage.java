package edu.mq.simple.jms.message;

import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractBytesMessage;
import jakarta.jms.JMSException;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class SimpleMQBytesMessage extends SimpleMQAbstractBytesMessage {

    private ByteArrayOutputStream os;
    private ByteArrayInputStream is;

    private SimpleMQBytesMessage() {

    }

    public static SimpleMQBytesMessage forSend() {
        final var bytesMessage = new SimpleMQBytesMessage();
        bytesMessage.os = new ByteArrayOutputStream();
        return bytesMessage;
    }

    public static SimpleMQBytesMessage forReceive(String base64encodedBytes) {
        final var bytes = Base64.getDecoder().decode(base64encodedBytes);

        final var bytesMessage = new SimpleMQBytesMessage();
        bytesMessage.is = new ByteArrayInputStream(bytes);
        return bytesMessage;
    }

    @SneakyThrows
    @Override
    public void writeBytes(byte[] value) throws JMSException {
        os.writeBytes(value);
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
    public long getBodyLength() throws JMSException {
        if (is != null) {
            return is.available(); // todo:
        }
        return os.size();
    }

    @Override
    public String getData() {
        final var bytes = os.toByteArray();
        final var base64Bytes = Base64.getEncoder().encodeToString(bytes);
        return base64Bytes;
    }

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.BYTES;
    }
}

package edu.mq.simple.storage.fs.json.converter;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.jms.message.type.bytes.SimpleMQBytesMessage;
import edu.mq.simple.storage.fs.json.BodyTypeConverter;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class BytesMessageBodyTypeConverter implements BodyTypeConverter {
    @Override
    public SimpleMQMessage convert(String message) {
        final var bytes = Base64.getDecoder().decode(message);

        final var is = new ByteArrayInputStream(bytes);
        final var bytesMessage = new SimpleMQBytesMessage(is);
        return bytesMessage;
    }

    @Override
    public Object convert(SimpleMQMessage message) {
        final var bytes = ((SimpleMQBytesMessage) message).getOutputStream().toByteArray();
        final var base64Bytes = Base64.getEncoder().encodeToString(bytes);
        return base64Bytes;
    }
}

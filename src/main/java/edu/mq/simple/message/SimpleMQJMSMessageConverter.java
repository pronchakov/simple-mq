package edu.mq.simple.message;

import edu.mq.simple.entity.UniversalMessage;
import edu.mq.simple.message.abstrct.SimpleMQAbstractMessage;

import java.util.Locale;

public class SimpleMQJMSMessageConverter {

    public UniversalMessage convert(SimpleMQAbstractMessage message) {
        final var builder = UniversalMessage.builder();

        builder.bodyType(message.getType().name().toLowerCase());
        builder.body(message.getBodyAsString());

        return builder.build();
    }

    public SimpleMQAbstractMessage convert(UniversalMessage message) throws UnknownTypeException {

        final var type = message.getBodyType().toUpperCase();
        final var messageType = SimpleMQJMSMessageType.valueOf(type);
        var result = switch (messageType) {
            case TEXT -> new SimpleMQTextMessage(message.getBody());
            case BYTES -> new SimpleMQBytesMessage(message.getBody());
            default -> throw new UnknownTypeException(type);
        };

        return result;
    }

}

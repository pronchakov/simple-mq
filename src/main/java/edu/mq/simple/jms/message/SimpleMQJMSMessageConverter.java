package edu.mq.simple.jms.message;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMessage;

import java.util.Map;

public class SimpleMQJMSMessageConverter {

    public SimpleMQMessage convert(SimpleMQAbstractMessage message) {
        final var builder = SimpleMQMessage.builder();

        builder.bodyType(message.getType().name().toLowerCase());
        builder.body(message.getData());

        return builder.build();
    }

    public SimpleMQAbstractMessage convert(SimpleMQMessage message) throws UnknownTypeException {

        final var type = message.getBodyType().toUpperCase();
        final var messageType = SimpleMQJMSMessageType.valueOf(type);
        var result = switch (messageType) {
            case TEXT -> new SimpleMQTextMessage((String) message.getBody());
            case BYTES -> SimpleMQBytesMessage.forReceive((String) message.getBody());
            case MAP -> new SimpleMQMapMessage((Map<String, Map<String, Object>>) message.getBody());
            default -> throw new UnknownTypeException(type);
        };

        return result;
    }

}

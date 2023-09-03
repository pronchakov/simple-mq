package edu.mq.simple.jms.message;

import edu.mq.simple.entity.SimpleMQMessage;
import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMessage;

public class SimpleMQJMSMessageConverter {

    public SimpleMQMessage convert(SimpleMQAbstractMessage message) {
        final var builder = SimpleMQMessage.builder();

        builder.bodyType(message.getType().name().toLowerCase());
        builder.body(message.getBodyAsString());

        return builder.build();
    }

    public SimpleMQAbstractMessage convert(SimpleMQMessage message) throws UnknownTypeException {

        final var type = message.getBodyType().toUpperCase();
        final var messageType = SimpleMQJMSMessageType.valueOf(type);
        var result = switch (messageType) {
            case TEXT -> new SimpleMQTextMessage(message.getBody());
            case BYTES -> SimpleMQBytesMessage.forReceive(message.getBody());
            default -> throw new UnknownTypeException(type);
        };

        return result;
    }

}

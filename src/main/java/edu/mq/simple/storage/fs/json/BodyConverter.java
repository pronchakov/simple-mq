package edu.mq.simple.storage.fs.json;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.fs.json.converter.BytesMessageBodyTypeConverter;
import edu.mq.simple.storage.fs.json.converter.MapMessageBodyTypeConverter;
import edu.mq.simple.storage.fs.json.converter.TextMessageBodyTypeConverter;
import edu.mq.simple.storage.fs.json.entity.JsonMessage;

import java.util.HashMap;
import java.util.Map;

public class BodyConverter {

    private Map<SimpleMQJMSMessageType, BodyTypeConverter> bodyConverters = new HashMap<>() {{
        put(SimpleMQJMSMessageType.TEXT, new TextMessageBodyTypeConverter());
        put(SimpleMQJMSMessageType.BYTES, new BytesMessageBodyTypeConverter());
        put(SimpleMQJMSMessageType.MAP, new MapMessageBodyTypeConverter());
    }};

    public SimpleMQMessage convertBody(JsonMessage jsonMessage) {
        final var bodyType = jsonMessage.getType();

        final var body = String.valueOf(jsonMessage.getBody());
        final SimpleMQMessage simpleMessage = switch (bodyType) {
            case "text" -> bodyConverters.get(SimpleMQJMSMessageType.TEXT).convert(body);
            case "bytes" -> bodyConverters.get(SimpleMQJMSMessageType.BYTES).convert(body);
            case "map" -> bodyConverters.get(SimpleMQJMSMessageType.MAP).convert(body);
            default -> throw new IllegalStateException("Unexpected value: " + bodyType);
        };
        return simpleMessage;
    }

    public Object convertBody(SimpleMQMessage message) {
        final var result = bodyConverters.get(message.getType()).convert(message);
        return result;
    }
}

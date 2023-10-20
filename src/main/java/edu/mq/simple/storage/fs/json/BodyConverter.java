package edu.mq.simple.storage.fs.json;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.storage.fs.json.converter.BytesMessageBodyTypeConverter;
import edu.mq.simple.storage.fs.json.converter.MapMessageBodyTypeConverter;
import edu.mq.simple.storage.fs.json.converter.TextMessageBodyTypeConverter;

import java.util.HashMap;
import java.util.Map;

public class BodyConverter { // todo: this class will become abstract builder for particular message builder

    private Map<SimpleMQJMSMessageType, BodyTypeConverter> bodyConverters = new HashMap<>() {{
        put(SimpleMQJMSMessageType.TEXT, new TextMessageBodyTypeConverter());
        put(SimpleMQJMSMessageType.BYTES, new BytesMessageBodyTypeConverter());
        put(SimpleMQJMSMessageType.MAP, new MapMessageBodyTypeConverter());
    }};

    public SimpleMQMessage toMessage(String type, String body) {
        final SimpleMQMessage simpleMessage = switch (type) {
            case "text" -> bodyConverters.get(SimpleMQJMSMessageType.TEXT).convert(body);
            case "bytes" -> bodyConverters.get(SimpleMQJMSMessageType.BYTES).convert(body);
            case "map" -> bodyConverters.get(SimpleMQJMSMessageType.MAP).convert(body);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return simpleMessage;
    }

    public Object toObject(SimpleMQMessage message) {
        final var result = bodyConverters.get(message.getType()).convert(message);
        return result;
    }
}

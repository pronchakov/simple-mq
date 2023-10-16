package edu.mq.simple.storage.fs.json.converter;

import edu.mq.simple.jms.message.SimpleMQMessage;
import edu.mq.simple.jms.message.type.text.SimpleMQTextMessage;
import edu.mq.simple.storage.fs.json.BodyTypeConverter;

public class TextMessageBodyTypeConverter implements BodyTypeConverter {
    @Override
    public SimpleMQMessage convert(String message) {
        return new SimpleMQTextMessage(message);
    }

    @Override
    public String convert(SimpleMQMessage message) {
        return ((SimpleMQTextMessage) message).getValue();
    }
}

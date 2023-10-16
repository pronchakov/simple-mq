package edu.mq.simple.storage.fs.json;

import edu.mq.simple.jms.message.SimpleMQMessage;

public interface BodyTypeConverter {
    SimpleMQMessage convert(String message);

    Object convert(SimpleMQMessage message);
}

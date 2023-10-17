package edu.mq.simple.storage.fs;

import edu.mq.simple.jms.message.SimpleMQMessage;

public interface FileFormatter {

    SimpleMQMessage transform(String text);

    String transform(SimpleMQMessage message);
}

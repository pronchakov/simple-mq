package edu.mq.simple.storage.fs;

import edu.mq.simple.jms.message.SimpleMQMessage;

public interface FileFormatter {

    SimpleMQMessage toMessage(String text);

    String toText(SimpleMQMessage message);
}

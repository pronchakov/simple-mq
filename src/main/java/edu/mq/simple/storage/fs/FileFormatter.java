package edu.mq.simple.storage.fs;

import edu.mq.simple.jms.message.SimpleMQMessage;

public interface FileFormatter {

    SimpleMQMessage convertText(String text);

    String convertMessage(SimpleMQMessage message);
}

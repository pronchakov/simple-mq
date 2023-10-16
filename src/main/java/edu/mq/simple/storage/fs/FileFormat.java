package edu.mq.simple.storage.fs;

import edu.mq.simple.jms.message.SimpleMQMessage;

public interface FileFormat {

    SimpleMQMessage transformTextToMessage(String text);

    String transformMessageToText(SimpleMQMessage message);
}

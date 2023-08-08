package edu.mq.simple;

import jakarta.jms.JMSException;
import jakarta.jms.Topic;

public class SimpleMQTopic implements Topic {
    @Override
    public String getTopicName() throws JMSException {
        return null;
    }
}

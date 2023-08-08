package edu.mq.simple;

import jakarta.jms.JMSException;
import jakarta.jms.Queue;

public class SimpleMQQueue implements Queue {
    @Override
    public String getQueueName() throws JMSException {
        return null;
    }
}

package edu.mq.simple.jms.destination;

import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public class SimpleMQQueue implements Queue {
    private String queueName;

    @Override
    public String getQueueName() throws JMSException {
        return queueName;
    }

    @Override
    public String toString() {
        return queueName;
    }
}

package edu.mq.simple;

import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleMQQueue implements Queue {
    private String queueName;

    @Override
    public String getQueueName() throws JMSException {
        return queueName;
    }
}

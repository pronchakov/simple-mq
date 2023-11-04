package edu.mq.simple.jms.destination;

import jakarta.jms.JMSException;
import jakarta.jms.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
public class SimpleMQTopic implements Topic {
    private String topicName;

    @Override
    public String getTopicName() throws JMSException {
        return topicName;
    }
}

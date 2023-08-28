package edu.mq.simple;

import jakarta.jms.JMSException;
import jakarta.jms.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleMQTopic implements Topic {
    private String topicName;

    @Override
    public String getTopicName() throws JMSException {
        return topicName;
    }
}

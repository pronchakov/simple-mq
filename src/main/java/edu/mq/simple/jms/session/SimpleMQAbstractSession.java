package edu.mq.simple.jms.session;

import jakarta.jms.*;

import java.io.Serializable;

public abstract class SimpleMQAbstractSession implements Session {
    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Message createMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean getTransacted() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void commit() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void rollback() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void close() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void recover() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void run() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Topic createTopic(String topicName) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void unsubscribe(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}

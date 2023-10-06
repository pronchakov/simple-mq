package edu.mq.simple.jms.message.abstrct;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

import java.util.Enumeration;

public abstract class SimpleMQAbstractMessage implements Message {

    public abstract SimpleMQJMSMessageType getType();

    public abstract Object getData();

    @Override
    public String getJMSMessageID() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSMessageID(String id) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSTimestamp(long timestamp) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] correlationID) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSCorrelationID(String correlationID) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSReplyTo(Destination replyTo) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSRedelivered(boolean redelivered) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getJMSType() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSType(String type) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getJMSExpiration() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSExpiration(long expiration) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getJMSDeliveryTime() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getJMSPriority() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setJMSPriority(int priority) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void clearProperties() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean propertyExists(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean getBooleanProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public byte getByteProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public short getShortProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getIntProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getLongProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public float getFloatProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public double getDoubleProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getStringProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object getObjectProperty(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setBooleanProperty(String name, boolean value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setByteProperty(String name, byte value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setShortProperty(String name, short value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setIntProperty(String name, int value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setLongProperty(String name, long value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setFloatProperty(String name, float value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDoubleProperty(String name, double value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setStringProperty(String name, String value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setObjectProperty(String name, Object value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void acknowledge() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void clearBody() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public <T> T getBody(Class<T> c) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean isBodyAssignableTo(Class c) throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}

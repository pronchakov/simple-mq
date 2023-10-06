package edu.mq.simple.jms.message.abstrct;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;

import java.util.Enumeration;

public abstract class SimpleMQAbstractMapMessage extends SimpleMQAbstractMessage implements MapMessage {
    @Override
    public SimpleMQJMSMessageType getType() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object getData() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean getBoolean(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public byte getByte(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public short getShort(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public char getChar(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public int getInt(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public long getLong(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public float getFloat(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public double getDouble(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String getString(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public byte[] getBytes(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object getObject(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Enumeration getMapNames() throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setBoolean(String name, boolean value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setByte(String name, byte value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setShort(String name, short value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setChar(String name, char value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setInt(String name, int value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setLong(String name, long value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setFloat(String name, float value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setDouble(String name, double value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setString(String name, String value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setBytes(String name, byte[] value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void setObject(String name, Object value) throws JMSException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public boolean itemExists(String name) throws JMSException {
        throw new RuntimeException("Not implemented");
    }
}

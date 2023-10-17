package edu.mq.simple.jms.message.type.map;

import edu.mq.simple.jms.message.SimpleMQJMSMessageType;
import jakarta.jms.JMSException;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

public class SimpleMQMapMessage extends SimpleMQAbstractMapMessage {

    @Getter
    private Map<String, Object> map = new TreeMap<>();

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.MAP;
    }

    @Override
    public void setBoolean(String name, boolean value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setByte(String name, byte value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setShort(String name, short value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setChar(String name, char value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setInt(String name, int value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setLong(String name, long value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setFloat(String name, float value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setDouble(String name, double value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setString(String name, String value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setBytes(String name, byte[] value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public void setBytes(String name, byte[] value, int offset, int length) throws JMSException {
        final var result = new byte[length];
        System.arraycopy(value, offset, result, 0, length);
        map.put(name, result);
    }

    @Override
    public void setObject(String name, Object value) throws JMSException {
        map.put(name, value);
    }

    @Override
    public boolean getBoolean(String name) throws JMSException {
        return (Boolean) map.get(name);
    }

    @Override
    public byte getByte(String name) throws JMSException {
        return (Byte) map.get(name);
    }

    @Override
    public short getShort(String name) throws JMSException {
        return (Short) map.get(name);
    }

    @Override
    public char getChar(String name) throws JMSException {
        return (Character) map.get(name);
    }

    @Override
    public int getInt(String name) throws JMSException {
        return (Integer) map.get(name);
    }

    @Override
    public long getLong(String name) throws JMSException {
        return (Long) map.get(name);
    }

    @Override
    public float getFloat(String name) throws JMSException {
        return (Float) map.get(name);
    }

    @Override
    public double getDouble(String name) throws JMSException {
        return (Double) map.get(name);
    }

    @Override
    public String getString(String name) throws JMSException {
        return (String) map.get(name);
    }

    @Override
    public byte[] getBytes(String name) throws JMSException {
        return (byte[]) map.get(name);
    }

    @Override
    public Object getObject(String name) throws JMSException {
        return map.get(name);
    }

}

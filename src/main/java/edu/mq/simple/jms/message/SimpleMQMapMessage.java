package edu.mq.simple.jms.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.jms.message.abstrct.SimpleMQAbstractMapMessage;
import jakarta.jms.JMSException;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.TreeMap;

public class SimpleMQMapMessage extends SimpleMQAbstractMapMessage {

    private Map<String, Object> map = new TreeMap<>();

    @Override
    public SimpleMQJMSMessageType getType() {
        return SimpleMQJMSMessageType.MAP;
    }

    @SneakyThrows
    @Override
    public Object getData() {
        return map;
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
}

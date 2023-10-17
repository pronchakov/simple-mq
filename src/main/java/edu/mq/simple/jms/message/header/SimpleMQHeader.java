package edu.mq.simple.jms.message.header;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class SimpleMQHeader<T> {
    private String name;

    public abstract String getType();

    public abstract T getValue();
}

package edu.mq.simple.jms.message.header;

import lombok.Setter;

@Setter
public class SimpleMQTextHeader extends SimpleMQHeader<String> {

    private String data;

    public SimpleMQTextHeader(String name) {
        super(name);
    }

    public SimpleMQTextHeader(String name, String data) {
        super(name);
        this.data = data;
    }

    @Override
    public String getValue() {
        return data;
    }
}

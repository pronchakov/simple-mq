package edu.mq.simple.jms.message.type.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMQMapMessageElement<T> {
    private String type;
    private T value;
}

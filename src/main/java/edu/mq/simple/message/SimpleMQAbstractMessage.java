package edu.mq.simple.message;

import edu.mq.simple.entity.UniversalMessage;
import lombok.Getter;

public abstract class SimpleMQAbstractMessage {

    @Getter
    protected UniversalMessage message = new UniversalMessage();

}

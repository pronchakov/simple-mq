import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.UniversalMessage;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonTest {
    @Test
    public void test() throws JsonProcessingException {
        UniversalMessage.UniversalMessageBuilder messageBuilder = UniversalMessage.builder();
        messageBuilder.headers(Map.of("k1", "v1", "k2", 12));
        messageBuilder.body("Test body");
        final var message = messageBuilder.build();


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        System.out.println(mapper.writeValueAsString(message));

    }
}

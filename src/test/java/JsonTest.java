import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.SimpleMQMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonTest {
    @Test
    public void test() throws JsonProcessingException {
        String extected = """
                {
                  "headers" : {
                    "k2" : 12,
                    "k1" : "v1"
                  },
                  "bodyType" : "text",
                  "body" : "Test body"
                }""";

        final var message = SimpleMQMessage.builder()
                .headers(Map.of("k1", "v1", "k2", 12))
                .bodyType("text")
                .body("Test body")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        final var result = mapper.writeValueAsString(message);
        Assertions.assertEquals(extected, result);
    }
}

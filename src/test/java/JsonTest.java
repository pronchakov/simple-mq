import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mq.simple.entity.UniversalMessage;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonTest {
    @Test
    public void test() throws JsonProcessingException {
        UniversalMessage message = UniversalMessage.builder()
                .headers(Map.of("k1", "v1", "k2", 12))
                .body("Test body")
                .build();


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        System.out.println(mapper.writeValueAsString(message));

    }
}

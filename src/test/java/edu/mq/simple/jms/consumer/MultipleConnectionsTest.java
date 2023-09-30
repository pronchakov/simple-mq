package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleConnectionsTest {

    @SneakyThrows
    @Test
    public void testMultipleConnections() {
        TestUtils.deleteQueue("./db", "edu.queue.multiple-connections");

        final var connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        @Cleanup final var connection = connectionFactory.createConnection();
        @Cleanup final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.consume-produced");
        @Cleanup final var producer = session.createProducer(queue);

        final var messageCount = 10_000;
        for (int i = 0; i < messageCount; i++) {
            producer.send(session.createTextMessage("hello-" + String.format("%05d", i)));
        }

        final var availableProcessors = Runtime.getRuntime().availableProcessors();

        final var futures = new CompletableFuture[availableProcessors];
        for (int i = 0; i < availableProcessors; i++) {
            final var future = CompletableFuture.supplyAsync(new MessagesSupplier(connectionFactory));
            futures[i] = future;
        }
        CompletableFuture.allOf(futures).get();

        final var messageTexts = Arrays.stream(futures)
                .flatMap(MultipleConnectionsTest::apply)
                .collect(Collectors.toList());

        Collections.sort(messageTexts);

        for (int i = 0; i < messageCount; i++) {
            Assertions.assertEquals("hello-" + String.format("%05d", i), messageTexts.get(i));
        }

        TestUtils.deleteQueue("./db", "edu.queue.multiple-connections");
    }

    @AllArgsConstructor
    private static class MessagesSupplier implements Supplier<List<String>> {

        private SimpleMQConnectionFactory connectionFactory;

        @SneakyThrows
        @Override
        public List<String> get() {
            @Cleanup final var connection = connectionFactory.createConnection();
            @Cleanup final var session = connection.createSession();
            final var queue = session.createQueue("edu.queue.consume-produced");
            @Cleanup final var consumer = session.createConsumer(queue);

            final var result = new ArrayList<String>(1000);

            Message message;
            while ((message = consumer.receiveNoWait()) != null) {
                final var messageText = ((TextMessage) message).getText();
                result.add(messageText);
            }

            return result;
        }
    }

    @SneakyThrows
    private static Stream<? extends String> apply(CompletableFuture completableFuture) {
        return ((List<String>) completableFuture.get()).stream();
    }

}

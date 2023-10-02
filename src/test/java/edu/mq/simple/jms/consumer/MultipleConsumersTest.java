package edu.mq.simple.jms.consumer;

import edu.mq.simple.jms.connection.SimpleMQConnectionFactory;
import edu.mq.simple.test.TestUtils;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleConsumersTest {

    @SneakyThrows
    @Test
    public void testMultipleConsumers() {
        TestUtils.deleteQueue("./db", "edu.queue.multiple-consumers");

        final var connectionFactory = SimpleMQConnectionFactory.builder()
                .baseDir("./db")
                .build();
        @Cleanup final var connection = connectionFactory.createConnection();
        @Cleanup final var session = connection.createSession();
        final var queue = session.createQueue("edu.queue.multiple-consumers");
        @Cleanup final var producer = session.createProducer(queue);

        final var messageCount = 20_000;
        for (int i = 0; i < messageCount; i++) {
            producer.send(session.createTextMessage("hello-" + String.format("%05d", i)));
        }

        final var availableProcessors = Runtime.getRuntime().availableProcessors();

        final var futures = new CompletableFuture[availableProcessors];
        for (int i = 0; i < availableProcessors; i++) {
            final var future = CompletableFuture.supplyAsync(new MultipleConsumersTest.MessagesSupplier(session));
            futures[i] = future;
        }
        CompletableFuture.allOf(futures).get();

        final var messageTexts = Arrays.stream(futures)
                .flatMap(MultipleConsumersTest::apply)
                .collect(Collectors.toList());

        Assertions.assertEquals(messageCount, messageTexts.size());

        Collections.sort(messageTexts);

        for (int i = 0; i < messageCount; i++) {
            Assertions.assertEquals("hello-" + String.format("%05d", i), messageTexts.get(i));
        }

        TestUtils.deleteQueue("./db", "edu.queue.multiple-consumers");
    }

    @AllArgsConstructor
    private static class MessagesSupplier implements Supplier<List<String>> {

        private Session session;

        @SneakyThrows
        @Override
        public List<String> get() {
            final var queue = session.createQueue("edu.queue.multiple-consumers");
            @Cleanup final var consumer = session.createConsumer(queue);

            final var result = new ArrayList<String>(1000);

//            int i = 0;
            Message message;
            while ((message = consumer.receiveNoWait()) != null) {
                final var messageText = ((TextMessage) message).getText();
                result.add(messageText);
//                i++;
//                if (i % 1000 == 0) {
//                    System.out.println(Thread.currentThread().getName() + " read " + i);
//                }
            }

            return result;
        }
    }

    @SneakyThrows
    private static Stream<? extends String> apply(CompletableFuture completableFuture) {
        return ((List<String>) completableFuture.get()).stream();
    }

}

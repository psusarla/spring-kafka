package com.aiven.spring.kafka;

import com.aiven.spring.kafka.dao.AivenMessageEntity;
import com.aiven.spring.kafka.dao.AivenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.aiven.spring.kafka.dao")
public class KafkaApplication {
    static ConfigurableApplicationContext context = null;
    public static void main(String[] args) throws Exception {
        context  = SpringApplication.run(KafkaApplication.class, args);
        
        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

        producer.sendAivenMessage(new AivenMessage("Greetings 1", "World!"));
        producer.sendAivenMessage(new AivenMessage("Greetings 2", "World!"));

        listener.greetingLatch.await(100, TimeUnit.SECONDS);
        context.close();
    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }

    public static class MessageProducer {
        @Autowired
        private KafkaTemplate<String, String> greetingKafkaTemplate;

        @Value(value = "${aivenMessage.topic.name}")
        private String greetingTopicName;

        public void sendAivenMessage(AivenMessage aivenMessage) {
            greetingKafkaTemplate.send(greetingTopicName, aivenMessage.toString());
        }
    }

    public static class MessageListener {

        private CountDownLatch greetingLatch = new CountDownLatch(1);

        @KafkaListener(topics = "${aivenMessage.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
        public void greetingListener(String aivenMessage) {
            System.out.println("Recieved aiven message: " + aivenMessage);

            AivenRepository aivenRepository = context.getBean(AivenRepository.class);
            AivenMessageEntity aivenMessageEntity = new AivenMessageEntity();
            aivenMessageEntity.setMessageStr("Received Aiven message: " + aivenMessage);
            aivenRepository.save(aivenMessageEntity);
            this.greetingLatch.countDown();
        }
    }
}

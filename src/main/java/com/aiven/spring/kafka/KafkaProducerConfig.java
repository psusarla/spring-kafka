package com.aiven.spring.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${run.in.cloud}")
    private boolean runInCloud;

    @Bean
    public ProducerFactory<String, String> greetingProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        if (runInCloud) {
            configProps.put("security.protocol", "SSL");
            configProps.put("ssl.endpoint.identification.algorithm", "");
            configProps.put("ssl.truststore.location", "client.truststore.jks");
            configProps.put("ssl.truststore.password", "secret");
            configProps.put("ssl.keystore.type", "PKCS12");
            configProps.put("ssl.keystore.location", "client.keystore.p12");
            configProps.put("ssl.keystore.password", "phani");
            configProps.put("ssl.key.password", "phani");
            configProps.put("key.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");
            configProps.put("value.serializer",
                    "org.apache.kafka.common.serialization.StringSerializer");
        }

        return new DefaultKafkaProducerFactory<>(configProps);
    }
    
    @Bean
    public KafkaTemplate<String, String> greetingKafkaTemplate() {
        return new KafkaTemplate<>(greetingProducerFactory());
    }
    
}

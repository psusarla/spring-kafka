package com.aiven.spring.kafka.dao;

import com.aiven.spring.kafka.KafkaApplication;
import com.aiven.spring.kafka.dao.AivenMessageEntity;
import com.aiven.spring.kafka.dao.AivenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class AivenRepositoryTests {

    @Autowired
    AivenRepository aivenRepository;

    @Test
    public void saveMessage() {
        AivenMessageEntity aivenMessageEntity = new AivenMessageEntity();
        aivenMessageEntity.setMessageStr("hello");
        aivenRepository.save(aivenMessageEntity);
    }
}

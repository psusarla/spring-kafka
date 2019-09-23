package com.aiven.spring.kafka.dao;


import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name="aiven_message")
public class AivenMessageEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String messageStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageStr() {
        return messageStr;
    }

    public void setMessageStr(String messageStr) {
        this.messageStr = messageStr;
    }

    @Override
    public String toString() {
        return "AivenMessageEntity{" +
                "id=" + id +
                ", messageStr='" + messageStr + '\'' +
                '}';
    }
}

package com.aiven.spring.kafka.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AivenRepository extends JpaRepository<AivenMessageEntity,Long> {
}



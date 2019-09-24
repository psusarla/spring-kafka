# Introduction

This application has two main components:  
A producer sends events to a Kafka Topic  
A consumer receives the events and writes to a postgres database  


# Spring-Kafka

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka.

As Kafka topics are not created automatically by default, this application requires that you create the following topic manually.

`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic aivenMessage`<br>

When the application runs successfully, following output is logged on to console (along with spring logs):

#### Messages received from the 'aivenMessage' topic
>Received aiven message: Greetings 1, World!!  
>Received aiven message: Greetings 2, World!!  

Also, 'aiven_message' table in the postgres database should have the same messages

# Running pointing to local services
Install and run Kafka and Postgres   
The application by default runs locally without any changes by running as a spring boot app.

# Running pointing to Aiven servives
Replace the application.properties sent in email  
Download and add below two files to the root of the project:  
client.keystore.p12  
client.truststore.jks

# References
https://www.baeldung.com/spring-kafka  
https://codenotfound.com/spring-kafka-consumer-producer-example.html  
https://www.mkyong.com/spring-boot/spring-boot-spring-data-jpa-postgresql/

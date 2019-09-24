# Introduction

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using spring-kafka and Postgres database.

This application has two main components:  
A producer sends events to a Kafka Topic  
A consumer receives the events and writes to a postgres database  

# Testing the app

As Kafka topics are not created automatically by default, this application requires that you create the following topic manually.

`$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic aivenMessage`<br>

When the application runs successfully, following output is logged on to console (along with spring logs):

#### Messages received from the 'aivenMessage' topic
>Received aiven message: Greetings 1, World!!  
>Received aiven message: Greetings 2, World!!  

### Entries in the database
Also,  aiven_message table gets created in the Postgres database, if it does not already exist. And the above two messages will be inserted to the table. 

# Running pointing to local services
Install and run Kafka and Postgres   
The application by default runs locally without any changes by running as a spring boot app.

# Running pointing to Aiven servives
Below 3 files are checked into git but are hidden using git-secret:
application.properties-aiven  
client.keystore.p12  
client.truststore.jks  

Message me if you need these files, I will need your public key to allow access.  
Once you decrypt the files, you will have to replace the contents of exising application.properties with the one in application.properties-aiven

# Unit Tests
There are 2 basic integration tests in the application:  
SpringContextLiveTest - tests if the spring configuration is correct
AivenRepositoryTests - tests if the app is able to save to the database

# References
https://www.baeldung.com/spring-kafka  
https://codenotfound.com/spring-kafka-consumer-producer-example.html  
https://www.mkyong.com/spring-boot/spring-boot-spring-data-jpa-postgresql/

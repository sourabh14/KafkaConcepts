package com.example.KafkaConcepts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConceptsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConceptsApplication.class, args);
		/*

			https://www.youtube.com/watch?v=U17DtHLOsTU&list=PLGRDMO4rOGcNLwoack4ZiTyewUcF6y6BU

			Intro:
				- Apache Kafka is a distributed event streaming platform used for
				high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.
				- Initially built by Linked-in

			Architecture and Working:
				- see img [resources/images/kafka-architecture.png]
				Kafka cluster:
					- Kafka acts as cluster has one or more brokers. Keep at-least use 3 brokers in prod environment.
					- Kafka is fault-tolerant. If one or more broker goes down then it can manage through other nodes.
				Broker:
					- A kafka broker is basically kafka server. Producers and consumers use kafka broker as an agent to
						exchange messages.
					- Zookeeper manages state of kafka brokers.
				Producer and Consumer:
					- Producer is an application that produces message and pushes to kafka cluster.
					- Consumer pulls the message and consumes.
					- We create topics in kafka cluster so that consumers may subscribe.
				Topics:
					- Kafka broker contains topics. A topic is like a category or table in database or a folder in file-system.
					- Topic is identified by a name. You can have any number of topics.
				Partitions:
					- see img [resources/images/kafka-partitions.png]
					- Kafka topics are divided into number of partitions, which contains records in unchangeable sequence.
					- If the size of data is enormous then it may not be possible to store in a single computer. Therefore,
					it will be stored in distributed way.
				Offsets:
					- Offset is a sequence of ids given to message as they arrive at partition. Eg: 0, 1, 2..
				Consumer Groups:
					- The Kafka consumer works by issuing “fetch” requests to the brokers leading the partitions it
						wants to consume.
					- The consumer offset is specified in the log with each request.
					- The consumer receives back a chunk of log beginning from the offset position.
					- A consumer group contains one or more consumers working together to process messages

			Event driven architecture:
				- EDA is a design pattern where decoupled applications can asynchronously publish and subscribe
				to events via a message broker.
				- Through EDA apps become loosely coupled, that

			Letterbox analogy:
				- Lets suppose mailman want to deliver a letter to your home but you are not present.
				- He will come and go several time and if the receiver is not found then he will return the letter
					to the sender. In this case receiver have lost the letter.
				- Instead of handing it directly, the mailman will drop the letter in letter box. And when the
				 	receiver is available, he will fetch the letters himself.

			Why do we need kafka:
				- It makes sender and receiver loosely coupled
				- Lets say we have 4 app which want to produce data to the db server. See app-architecture.png
					In future the app might grow and we might have n number of service to communicate with each
					other. In this situation there can be many challenges:
						-- Data format : The frontend might want to produce different type of data to different app.
								Similarly others also.
						-- Connection type : There could be different type of connection, such as http, tcp, jdbc etc.
							connection type will difficult to maintain.
						-- Number of connections : The total count of connection grows by multifold. Just to maintain
						the communication bw different services it will become difficult.

			Running kafka app
				# Start zookeeper
				zkServer.sh start

				# Start kafka
				cd Downloads/kafka_2.12-3.6.0
				bin/kafka-server-start.sh config/server.properties

				# Start server

				# Publish message
				localhost:8081/api/v1/kafka/publish?message=aaa

		 */
	}

}

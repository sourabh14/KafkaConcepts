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
				- It has numerous use cases including pub/sub messaging, distributed logging, stream processing and data integration .
				- Initially built by Linked-in

			Architecture and Working:
				- see img [resources/images/kafka-architecture.png]
				Event:
					- Event is any significant occurrence or change in state that the system can recognize and respond to.
					- For example, a payment, a website click, or a temperature reading, along with a description of what happened.
					- In other words, an event is a combination of notification—the element of when-ness that can be used
						to trigger some other activity—and state.
					- That state is usually fairly small, say less than a megabyte or so, and is normally represented in some
						structured format, say in JSON

				Serialization/Deserialization:
					- Kafka models events as key/value pairs. Internally, keys and values are just sequences of bytes,
						but externally in your programming language, they are often structured objects represented in
						your language’s type system.
					- Kafka famously calls the translation between language types and internal bytes serialization and
							deserialization. The serialized format is usually JSON, JSON Schema
					- Values are typically the serialized representation of an application domain object
					- Keys can also be complex domain objects but are often primitive types like strings or integers.

				Broker:
					- A kafka broker is basically kafka server. Producers and consumers use kafka broker as an agent to
						exchange messages.
					- Zookeeper manages state of kafka brokers.

				Kafka cluster:
					- Kafka acts as cluster has one or more brokers. Keep at-least 3 brokers in prod environment.
					- Kafka is fault-tolerant. If one or more broker goes down then it can manage through other nodes.

				Producer and Consumer:
					- Producer is an application that produces message and pushes to kafka cluster.
					- Consumer pulls the message and consumes.
					- We create topics in kafka cluster so that consumers may subscribe.

				Topics:
					- Kafka broker contains topics.
					- A kafka topic is a durable log of events. Note that it is not a queue where things are enqueued/dequeued
					- A topic is like a category or table in database or a folder in file-system. Eg:
							user-activity: Tracks general user actions (e.g., login, logout).
							purchase-events: Stores data related to product purchases.
					- Topic is identified by a name. You can have any number of topics.
					- Topics are multi-producer and multi-consumer, meaning multiple producers can send messages
						to the same topic, and multiple consumers can consume messages from the same topic.

				Partitions:
					- see img [resources/images/kafka-partitions.png]
					- Kafka topics are divided into number of partitions, which contains records in unchangeable sequence.
					- If the size of data is enormous then it may not be possible to store in a single computer. Therefore,
						it will be stored in distributed way.
					- When you produce messages to a Kafka topic, these messages are distributed across different partitions.

					Why Use Partitions?
						- Scalability: Kafka partitions allow a topic to be split across multiple brokers,
							enabling horizontal scaling. Each partition can be hosted on a different broker, distributing the load.
						- Parallelism: Partitions enable parallel consumption. Consumers in a consumer group can
							each read from different partitions, increasing throughput and processing speed.
						- Fault Tolerance: Kafka replicates partitions across multiple brokers for fault tolerance,
							ensuring data availability even in case of broker failures.

				Offsets:
					- Offset is a unique identifier assigned to each message in a Kafka partition. Eg: 0, 1, 2..
					- It is a sequential number that represents the position of a message within the partition.
					- If a topic has multiple partitions, each one will have independent offsets.
							Partition 0: 0, 1, 2, 3, 4, 5...
							Partition 1: 0, 1, 2, 3, 4, 5...
					- When a consumer reads from a partition, it keeps track of the offset to know which messages
						have already been consumed.
					- Kafka doesnt automatically manage the offsets for consumers. Consumers are responsible for
						committing their offsets (i.e., marking the messages they’ve consumed) so they can resume
						from that point if restarted.
					- Offsets enable consumers to reprocess messages by "rewinding" to a previous offset.
							Consumers can start reading messages from a specific offset (e.g., from the beginning,
							latest, or a custom offset).

				Consumer Groups:
					- A consumer group contains one or more consumers working together to process messages
					- Each consumer group is identified by a unique group ID
					- The consumer receives back a chunk of log beginning from the offset position.

					Partition Assignment
					- When consumers belong to the same group, Kafka ensures that messages are divided among them,
						and each message is consumed by only one consumer in the group.
					- Kafka ensures that each partition is consumed by only one consumer within the same consumer
						group at any point in time.
					- If there are more partitions than consumers, some consumers may consume from multiple partitions.

					partition assignment strategy
						- Kafka uses a partition assignment strategy to determine how partitions are distributed among consumers. Two common strategies are:
							- Range Assignment: Each consumer gets a contiguous range of partitions.
							- Round Robin Assignment: Partitions are assigned in a round-robin fashion to evenly distribute

					Offset management
					- Different consumer groups can read the same topic independently. For example,
						one consumer group could be responsible for real-time processing, while another
						consumer group could be archiving data.
					- Each consumer group tracks its own offsets independently, meaning each group can consume at
						its own pace without affecting others.

					Rebalancing:
						- When consumers join or leave the group (or when new partitions are added), Kafka triggers
							a rebalance to redistribute partitions among the consumers.

					Why consumer groups?
						- Scalability: Consumer groups allow horizontal scaling of message processing. You can
							add more consumers to a group to increase the processing speed.
						- Load Balancing: Kafka automatically distributes partitions among consumers, ensuring
							balanced load distribution. As the number of consumers or partitions changes,
							Kafka rebalances the assignment.
						- Fault Tolerance: If a consumer in the group crashes or goes offline, Kafka will
							redistribute its partitions to other consumers in the group, ensuring continued message processing.

			Kafka Tuning
				Broker-Level Tuning
					- Kafka Heap Size: Kafka brokers run on the JVM, so the heap size must be tuned to avoid
						frequent garbage collection (GC) pauses.
						Allocate a heap size (Xms and Xmx JVM parameters) that fits well within the system’s memory.
					- Replication Factor: Set the replication factor (min.insync.replicas) for topics based
						on fault tolerance requirements. A replication factor of 3 is common in production
						environments for high availability.
					- Network Threads: Configure the number of I/O threads (num.network.threads) and
						request handler threads (num.io.threads) based on the expected network traffic.
					- Socket Buffers: Adjust the size of socket send and receive buffers (socket.send.buffer.bytes,
						socket.receive.buffer.bytes) to avoid network congestion and delays during high throughput periods.

				Producer Tuning
					- Batch Size: Producers can batch messages before sending them to the broker to reduce the number
						of network requests. Increasing the batch size (batch.size) can improve throughput but
						may add slight delays (latency).
					- Compression Type: Enabling compression can reduce the size of the data transferred and improve throughput.
						lz4 is often recommended for fast compression

				Consumer Tuning
					- Fetch Min and Max Bytes: Tune the minimum and maximum fetch sizes (fetch.min.bytes, fetch.max.bytes)
						to optimize how much data consumers request at a time. A higher fetch.min.bytes can reduce
						the number of fetch requests but may increase latency.
					- Max Poll Records (max.poll.records) controls how many records a consumer retrieves in one poll.
						Larger values improve throughput but may increase processing delays if the consumer takes
						too long to process a large batch of records.

				Topic-Level Tuning
					- Partition Count: The number of partitions in a topic affects how well Kafka can scale. More
						partitions allow for higher throughput since they distribute the load across more brokers.
						However, too many partitions can increase overhead and slow down rebalancing.
						Choose the number of partitions based on the expected load and number of consumers.


			Kafka Monitoring
				- Kafka provides metrics to monitor its performance using JMX or third-party tools such as Prometheus,
					GrafanaKey metrics to track include:

				- Broker CPU, memory, and disk usage: Helps identify resource bottlenecks.
				- Consumer lag: Indicates how far behind consumers are in reading messages.
				- Network I/O: Shows the rate at which messages are being produced and consumed.
				- Garbage Collection metrics: Track GC times to avoid long pauses that could degrade performance.


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

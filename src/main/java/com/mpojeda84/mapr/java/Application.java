package com.mpojeda84.mapr.java;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class Application {

    private static String TOPIC = "/user/mapr/yelp/streams:reviews";
    private static String fileName = "/Users/mpereira/projects/yelp-data/yelp_academic_dataset_review.json";

    public static void main(String[] argv)throws Exception {

        KafkaProducer<String, String> producer = getProducer();


        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(x -> {
                System.out.println(x);
                producer.send(new ProducerRecord<>(TOPIC, x));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        producer.close();
    }

    private static KafkaProducer<String, String> getProducer() {
        Properties props = new Properties();
        props.setProperty("batch.size", "16384");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("block.on.buffer.full", "true");

        return new KafkaProducer<>(props);
    }
}

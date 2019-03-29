package com.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class CustomerProducer {
    public static void main(String[] args) {

        //new KafkaProducer<>()
        // 配置信息
        Properties props = new Properties();
        // kafka 集群
        props.put("bootstrap.servers", "hadoop102:9092");
        // 应答级别
        props.put("acks", "all");
        // 重试次数
        props.put("retries", 0);
        // 批量大小
        props.put("batch.size", 16384);
        // 提交延时
        props.put("linger.ms", 1);
        // 缓存(整个 producer 这边能缓存的数据大小)
        props.put("buffer.memory", 33554432);
        // kv 的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 所有属性都在以下类中
        //ProducerConfig

        // 创建生产者对象
        Producer<String, String> producer = new KafkaProducer<>(props);
        // 循环发送数据
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
        }

        // 关闭
        producer.close();
    }
}

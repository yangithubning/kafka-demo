package com.kafka.producer;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Description
 * @Author ningyan
 * @Date 2019/12/321:07
 */
public class CustomerProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop100:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,0);
        // 批量大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        // 提交延时
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        // 缓存
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        String topic ="three";
       /* for (int i = 0; i < 10 ; i++) {
            kafkaProducer.send(new ProducerRecord<>(topic,String.valueOf(i)));
        }*/

        for (int i = 0; i < 10 ; i++) {
            kafkaProducer.send(new ProducerRecord<>(topic, String.valueOf(i)), (recordMetadata, e) -> {
                if(e != null){
                     System.out.println("发送失败");
                }else{
                    System.out.println(recordMetadata.partition() + "---" + recordMetadata.offset() + "---" + recordMetadata.topic());

                }

            });
        }

        kafkaProducer.close();

    }
}

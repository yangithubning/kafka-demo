package com.kafka.producer;

import kafka.cluster.Partition;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @Description
 * @Author ningyan
 * @Date 2019/12/322:18
 */
public class CustomerPartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
                return 0;
    }

    @Override
    public void close() {
     System.out.println("---------close-------");
    }

    @Override
    public void configure(Map<String, ?> map) {
        System.out.println("---------configure-------" + map);
    }
}

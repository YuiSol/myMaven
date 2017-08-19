package kafka.kafkaDemo;

import kafka.Kafka;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by YuiSol on 2017/8/19.
 */
public class KafkaDemoConsumer {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("zookeeper.connect","dfs-node01,dfs-node02,dfs-node03");
        properties.put("group.id", UUID.randomUUID().toString());
        properties.put("zookeeper.session.timeout.ms","4000");
        ConsumerConnector connector= Consumer.create(new ConsumerConfig(properties));
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        hashMap.put("urls",new Integer(1));
        connector.createMessageStreams(hashMap)
    }

}

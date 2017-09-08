package kafka.kafkaDemo;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by YuiSol on 2017/8/20.
 */
public class KafkaDemoProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list","dfs-node01:9092,dfs-node02:9092,dfs-node03:9092");
        properties.put("serializer.class","kafka.serializer.StringEncoder");
        Producer producer = new Producer(new ProducerConfig(properties));
        KeyedMessage keyedMessage = new KeyedMessage("urls", "mysqasdfl");
        producer.send(keyedMessage);
    }
}

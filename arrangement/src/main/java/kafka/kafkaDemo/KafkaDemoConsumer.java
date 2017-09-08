package kafka.kafkaDemo;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.*;

/**
 * Created by YuiSol on 2017/8/19.
 */
public class KafkaDemoConsumer {


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("zookeeper.connect","dfs-node01:2181,dfs-node02:2181,dfs-node03:2181");
        properties.put("group.id", UUID.randomUUID().toString());
      /*  properties.put("zookeeper.session.timeout.ms","4000");
        properties.put("zookeeper.sync.time.ms", "200");*/
      /*  properties.put("auto.commit.interval.ms", "1000");*/
        properties.put("auto.offset.reset", "smallest");
        ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        hashMap.put("urls", new Integer(10));
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = javaConsumerConnector.createMessageStreams(hashMap);
        List<KafkaStream<byte[], byte[]>> urls = messageStreams.get("urls");
      /*  for(final Iterator<KafkaStream<byte[], byte[]>> iterator = urls.iterator();iterator.hasNext();){
           final KafkaStream<byte[], byte[]> next = iterator.next();
            new Thread(new Runnable() {
                public void run() {
                    for (final ConsumerIterator<byte[], byte[]> iterator1 = next.iterator();iterator1.hasNext();){
                        MessageAndMetadata<byte[], byte[]> next1 = iterator1.next();
                        System.out.println(new String(next1.message()));
                    }

                }
            }).start();
        }*/
        System.out.println(urls.size());
        for(final KafkaStream<byte[] , byte[]> ka:urls){
            new Thread(new Runnable() {
                public void run() {
                    for(ConsumerIterator<byte[], byte[]> iterator = ka.iterator();iterator.hasNext();){
                        MessageAndMetadata<byte[], byte[]> next = iterator.next();
                        System.out.println(next.offset());
                        System.out.println(next.partition());
                        System.out.println(new String(next.message()));
                    }

                }
            }).start();
        }
    }

}

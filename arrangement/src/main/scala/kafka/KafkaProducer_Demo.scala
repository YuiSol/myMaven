package kafka

/**
 * Created by YuiSol on 2017/9/12.
 */

import java.util
import java.util.{UUID, Properties}

import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig


object KafkaProducer_Demo {
  def main(args: Array[String]) {
    val properties: Properties = new Properties()
    properties.put("metadata.broker.list","dfs-node01:9092,dfs-node02:9092,dfs-node03:9092")
    properties.put("serializer.class","kafka.serializer.StringEncoder")
    val producer: Producer[String, String] = new Producer(new ProducerConfig(properties))
    for(i  <- 1 to 1000){
      Thread.sleep(200)
    /*  val message: KeyedMessage[Nothing, Nothing] = new KeyedMessage[Nothing,Nothing]("urls",UUID.randomUUID().toString())
      producer.send(message)*/
      val keyedMessage: KeyedMessage[String, String] = new KeyedMessage[String, String]("urls", UUID.randomUUID().toString)
      producer.send(keyedMessage)
    }
  }
}

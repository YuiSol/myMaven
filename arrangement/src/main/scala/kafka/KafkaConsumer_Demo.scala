package kafka

import java.util
import java.util.HashMap
import java.util.{UUID, Properties}

import kafka.consumer.{ConsumerIterator, KafkaStream, Consumer, ConsumerConfig}
import kafka.javaapi.consumer.ConsumerConnector
import kafka.message.MessageAndMetadata


import scala.collection.mutable
import scala.util.Properties

/**
 * Created by YuiSol on 2017/9/12.
 */
object KafkaConsumer_Demo {
  def main(args: Array[String]) {
    kafkaConsumer()
  }
  def kafkaConsumer(): Unit ={
    val properties: Properties = new Properties()
    properties.put("zookeeper.connect","dfs-node01:2181,dfs-node02:2181,dfs-node03:2181")
    properties.put("group.id",UUID.randomUUID().toString)
    properties.put("zookeeper.session.timeout.ms","4000")
    properties.put("auto.commit.interval.ms","1000")
    properties.put("auto.offset.reset","smallest")
    val javaConsumerConnector: ConsumerConnector = Consumer.createJavaConsumerConnector(new  ConsumerConfig(properties))
    val hashMap: util.HashMap[String, Integer] = new util.HashMap[String,Integer]()
   /* val hashMap: mutable.HashMap[String, Int] = mutable.HashMap[String,Int]()*/
    hashMap.put("urls",10)
    val streams: util.Map[String, util.List[KafkaStream[Array[Byte], Array[Byte]]]] = javaConsumerConnector.createMessageStreams(hashMap)
    val streams1: util.List[KafkaStream[Array[Byte], Array[Byte]]] = streams.get("urls")
    for(i <- 0 until streams1.size()){
      new Thread(new Runnable {
        override def run(): Unit = {
          val stream: KafkaStream[Array[Byte], Array[Byte]] = streams1.get(i)
          val iterator: ConsumerIterator[Array[Byte], Array[Byte]] = stream.iterator()
          while(iterator.hasNext()){
            val next: MessageAndMetadata[Array[Byte], Array[Byte]] = iterator.next()
            println(new String(next.message()))
          }
        }
      }).start()

    }
  }
}

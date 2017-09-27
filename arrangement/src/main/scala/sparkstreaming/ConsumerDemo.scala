package sparkstreaming

import java.util.UUID

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.log4j.{Level, Logger}
/**
 * Created by YuiSol on 2017/9/19.
 * kafka获取使用方法
 *
 */
object ConsumerDemo {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    val context = new StreamingContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[3]"),Seconds(10))
    context.checkpoint("\\")
    val zkQuorum = "dfs-node01:2181,dfs-node02:2181,dfs-node03:2181"
    val topics = "urls"
    val topicMap : Map[String,Int]= topics.split(",").map((_,3)).toMap
    val createStream = KafkaUtils.createStream(context,zkQuorum,UUID.randomUUID().toString,topicMap).map(_._2)
    createStream.print()
    context.start()
    context.awaitTermination()
  }
}

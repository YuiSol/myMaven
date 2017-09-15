package sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Created by YuiSol on 2017/9/15.
 */
object SpreakStreamingDemo {
  def main(args: Array[String]) {

    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc: StreamingContext = new StreamingContext(conf,Seconds(5))
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("192.168.1.81",9999)
    print(lines)
    val words: DStream[String] = lines.flatMap(_.split(" "))
    val rdd: DStream[(String, Int)] = words.map(x=>(x,1))
    val wordCount: DStream[(String, Int)] = rdd.reduceByKey(_ + _)
    wordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }
}

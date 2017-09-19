package sparkstreaming

import java.util.{UUID, Properties}

import com.alibaba.fastjson.JSON
import kafka.producer.{KeyedMessage, ProducerConfig, Producer}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

import scala.collection.mutable

/**
 * Created by Administrator on 2017/9/18.
 */
case class Click_Flow(date:String,ip:String,flowTuple:(Long,Long)) extends Serializable{

}
/*case class Winner(id: Long, numbers: List[Int])
case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])*/
object ProducerDemo {
  def main(args: Array[String]) {
    val context: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]"))
    val properties: Properties = new Properties()
    properties.put("metadata.broker.list","dfs-node01:9092,dfs-node02:9092,dfs-node03:9092")
    properties.put("serializer.class","kafka.serializer.StringEncoder")
    val producer: Producer[String, String] = new Producer[String,String](new ProducerConfig(properties))
    val file: RDD[String] = context.textFile("D:\\resoures\\大数据\\实时业务数据\\day08_sparkStreaming 日志处理\\logs\\click_flow1.log")
    file.foreach(x=>{
      val data: Array[String] = x.split("\t")
      val flow: Click_Flow = new Click_Flow(data(0),data(1),(data(2).toLong,data(3).toLong))
      val json: JsonAST.JObject = ("date" -> flow.date)~( "ip" -> flow.ip)~("flowTuple" ->(("upTuple" -> flow.flowTuple._1)~ ("downTyple" -> flow.flowTuple._2)))
      val compact1: String = compact(render(json))
      val keyedMessage: KeyedMessage[String, String] = new KeyedMessage[String, String]("kafkademo",compact1)
      producer.send(keyedMessage)
    })





   /* val producer: Producer[String, Click_Flow] = new Producer[String,Click_Flow](new ProducerConfig(properties))*/
   /*val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
    val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)
   val json =
     ("lotto" ->
       ("lotto-id" -> lotto.id) ~
         ("winning-numbers" -> lotto.winningNumbers) ~
         ("draw-date" -> lotto.drawDate.map(_.toString)) ~
         ("winners" ->
           lotto.winners.map { w =>
             (("winner-id" -> w.id) ~
               ("numbers" -> w.numbers))}))
               println(compact(render(json)))*/
  }
}

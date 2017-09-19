package sparkstreaming

import com.alibaba.fastjson.JSON
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by Administrator on 2017/9/18.
 */
/*case class Click_Flow(date:String,ipaddr:String,status:Int,readyState:Int){

}*/
object SparkStreamingDemo2 {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
    val spark: SparkContext = new SparkContext(new SparkConf().setAppName("mysparkstreaming").setMaster("local[4]"))
    val file: RDD[String] = spark.textFile("D:\\resoures\\大数据\\实时业务数据\\day08_sparkStreaming 日志处理\\logs\\click_flow1.log")
    val rdd1: RDD[String] = file.flatMap(x=>x.split("\t"))

  }
}

package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

import scala.collection.Map

/**
 * Created by YuiSol on 2017/9/14.
 * 自带算法
 */
object ActionDemo {
  val sc: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[3]"))
  def main(args: Array[String]) {
    reduTest
  }
  def reduTest(): Unit ={
      val rdd: RDD[Int] = sc.parallelize(List(1,2,3,523,4,42,2,43,234,12,3345,21,312,3,12,543,5,13,21,789))
      val rdd1: RDD[String] = sc.parallelize(List("a","c","c","d","a","a","b","c","d","a","a","b","c","d","a","a","b","c","d","a"))
      //reduce计算全部
      println("一共："+rdd.collect().reduce(_+_))
      //count 统计数量
      println("一共："+rdd1.count()+"个")
      println("一共："+rdd.count()+"个")
      //first 第一个元素
      println("第一个："+rdd1.first())
      //take 任意取5个
      println("任意5个："+rdd.take(5).toBuffer)
    //takeOrdered  先排序再任意取5个
      println("任意5个："+rdd.takeOrdered(5).toBuffer)
      //saveAsTextFile 把数据保存到本地 如果文件存在则报错
     /* rdd.saveAsTextFile("D:\\ll\\demo")*/
    /**
     * countByKey 统计key出现的次数
     * countByValue 以value为主键1，key为主键2 计算这个组合出现的次数
     */
      val rdd2: RDD[( String , Int)] = rdd1.zip(rdd)
      val rdd3: RDD[( Int , String)] = rdd.zip(rdd1)
      println(rdd2.collect().toBuffer)
      println(rdd3.countByKey())
      println(rdd3.countByValue())
      println(rdd2.countByKey())
      println(rdd2.countByValue())
      println("=====================")
    //repartitionAndSortWithinPartitions 对rdd的每一个分区进行 key-value 的排序  hashPartitioner(1) 指的是从第一个分区开始,为0表示不操作
      val rdd4: RDD[(String, Int)] = rdd2.repartitionAndSortWithinPartitions(new HashPartitioner(1))
      println(rdd4.collect().toBuffer)
  }
}

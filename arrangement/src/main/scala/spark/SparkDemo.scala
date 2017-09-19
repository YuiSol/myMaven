package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by YuiSol on 2017/9/13.
 */
object SparkDemo {
  def main(args: Array[String]) {
    /*demo1()*/
    /*demo1_1*/
    val context: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]"))
    textParallelize(context)
  }
  def demo1(): Unit ={
    //统计单词出现次数
    //本地模式 local下可以写成local[3]，这样意思是3条线程
    val context: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]"))
    //加载文件
    val file: RDD[String] = context.textFile("D:\\3b\\part-r-00000")
    //根据指定条件切分
    val map: RDD[String] = file.flatMap(_.split("\\|"))
    //为每一条数据加载初始值1 key为数据本身
    val map1: RDD[(String, Int)] = map.map((_,1))
    //统计每一条数据出现的次数
    val key: RDD[(String, Int)] = map1.reduceByKey(_+_)
    //打印结果
    println(key.collect.toBuffer)
    /*println(file.collect().toBuffer)//注意内存*/
    context.stop()
  }
  def demo1_1(){
    val context: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]"))
    val key: RDD[(String, Int)] = context.textFile("D:\\3b\\part-r-00000").flatMap(_.split("\\|")).map((_,1)).reduceByKey(_+_)
    println(key.collect.toBuffer)
    context.stop()
  }
  def textParallelize(sc : SparkContext): Unit ={
      val rdd: RDD[Int] = sc.parallelize(Array(1,2,3,4,5,6,7,8))
      println(rdd.collect.toBuffer)
  }
}

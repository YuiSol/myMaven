package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by YuiSol on 2017/9/14.
 */
object TransformationDemo1 {
  val sc=new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]"))
  def main(args: Array[String]) {
    union
  }
  def union(): Unit ={

    val rdd1: RDD[Int] = sc.parallelize(List(1,2,3,4))
    val rdd2: RDD[Int] = sc.parallelize(List(2,3,5,5,23,32))
    val rdd4: RDD[(String, Int)] = sc.parallelize(List("one","two","three","four")).zip(rdd1)
    val rdd5: RDD[(String, Int)] = sc.parallelize(List("one","two","three","four","five","six")).zip(rdd2)
    //交集
    println(rdd1.intersection(rdd2).collect().toBuffer)
    val rdd3: RDD[Int] = rdd1.union(rdd2)
    println(rdd3.collect().toBuffer)

    println(rdd5.leftOuterJoin(rdd4).collect().toBuffer)
    println(rdd5.rightOuterJoin(rdd4).collect().toBuffer)
    println(rdd5.fullOuterJoin(rdd4).collect().toBuffer)
  }
}

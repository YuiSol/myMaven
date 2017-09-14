package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * Created by YuiSol on 2017/9/14.
 */
object SparkDemo1 {
  def main(args: Array[String]) {
      val sc: SparkContext = new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[3]"))
      /*countdemo(sc)*/
      joindemo(sc)
  }

  /**
   * join 把两个rdd  key相同部分聚合起来
   *
   * @param sc
   */
  def joindemo(sc : SparkContext) ={
      val rdd1: RDD[(String, Int)] = sc.parallelize(List("one","two","three","four")).zip(sc.parallelize(List(1,2,3,4)))
      val rdd2: RDD[(String, Int)] = sc.parallelize(List("one","two","three","four","five")).zip(sc.parallelize(List(1,2,5,4,3)))
      println(rdd1.collect().toBuffer)
      println(rdd2.collect().toBuffer)
      val rdd3: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
    val rdd4: RDD[(String, Int)] = rdd3.mapPartitionsWithIndex((partitionId: Int, iter: Iterator[(String, (Int, Int))]) => {
      iter.toList.map(x => {
        (x._1, x._2._1 + x._2._2)
      }).iterator
    }).sortBy(_._2)
    println(rdd4.collect().toBuffer)
  }
  /**
   *
   * @param sc
   */
  def countdemo(sc :SparkContext): Unit ={
      val rdd: RDD[Any] = sc.parallelize(List("one","two","three",13,2,"two"))
      val rdd1: RDD[Any] = sc.parallelize(Array("three","four","five",54,9878,1321))
      val rdd2: RDD[(Any, Any)] = rdd.zip(rdd1)
      println(rdd2.collect()toBuffer)
  }

}

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
    val rdd2: RDD[Int] = sc.parallelize(List(2,3,5,5,23,32,42,31,123,5,21,54,6))
    sc.parallelize()
    val rdd3: RDD[Int] = rdd1.union(rdd2)
    println(rdd3.collect().toBuffer)
  }
}

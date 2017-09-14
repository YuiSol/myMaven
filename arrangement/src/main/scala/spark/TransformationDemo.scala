package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by YuiSol on 2017/9/13.
 */
object TransformationDemo {
  def main(args: Array[String]) {
    val master: SparkConf = new SparkConf().setAppName(this.getClass.getName).setMaster("local[5]")
    val sc: SparkContext = new SparkContext(master)
    /*filterdemo(sc)*/
    /*flatMapDemo(sc)*/
   /*  mapPartitionDemo(sc)*/
     mapPartitionDemo1(sc)
  /*  println(rdd.collect().toBuffer)*/
  }
  def filterdemo(sc :SparkContext): Unit ={
    val rdd: RDD[Int] = sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10))
    val rdd2: RDD[Int] = rdd.map(x => x * 100)
    val rdd1: RDD[Int] = rdd2.filter(x =>x%200==0)
    println(rdd1.collect().toBuffer)
  }
  def flatMapDemo(sc : SparkContext): Unit ={
    val strings: List[Array[String]] = List(Array("a","b","c"),Array("a","c","d"),Array("d","a","n"),Array("b","a","c","n","n","n","n","n"))
    //从集合创建rdd有两种方式 parallelize 和 makeRDD
    val rdd: RDD[Array[String]] = sc.parallelize(strings)
   //flatMap有一个合并的过程
    val rdd1: RDD[String] = rdd.flatMap(x=>x)
    val rdd2: RDD[(String, Int)] = rdd1.map((_,1))
    val rdd3: RDD[(String, Int)] = rdd2.reduceByKey(_ + _).sortBy(_._1)
    println(rdd3.collect().toBuffer)
  }
  def mapPartitionDemo(sc : SparkContext)= {
    val rdd: RDD[Int] = sc.parallelize(Array(1,2,3,4,6,7,93,2,32,54,3,546,3,345,1,3))
    /**
     * index 分区数
     * iter 对应的分区的内容
     * 一个rdd下一系列的分区 弹性式分布式数据集
     * 要传入一个函数
     * 返回一个 scala.collection.Iterator[A]
     * 一个RDD  =》 多个 分区 = 》内容
     * 每一个函数作用到每个函数上  iter内的每一个分区都会作用到该函数
     *
     */
    val rdd1: RDD[Unit] = rdd.mapPartitionsWithIndex((index: Int, iter: Iterator[Int]) => {
      iter.toList.map(x => {
        println(s"index = $index"+s"partition content = $x")
        /*println(x)*/
      }).iterator
    })
    rdd1.collect()
  }
  def mapPartitionDemo1(sc : SparkContext)= {
    val rdd: RDD[Int] = sc.parallelize(Array(1,2,3,4,6,7,93,2,32,54,3,546,3,345,1,3))

    val rdd1: RDD[Int] = rdd.mapPartitions(x =>x.map(x=>x*100))

    println(rdd1.collect().toBuffer)
  }
}

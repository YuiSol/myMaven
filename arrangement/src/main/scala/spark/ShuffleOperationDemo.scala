package spark

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}
import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}

/**
 * 排序
 *
 * Created by YuiSol on 2017/9/14.
 */
object ShuffleOperationDemo {
  val sc=new SparkContext(new SparkConf().setAppName(this.getClass.getName).setMaster("local[3]"))
  val rdd1: RDD[String] = sc.parallelize(Array("a","b","c","u","k","v","a","s","d","z","c","b","a","b","c","u","k","v","a","s","d","z","c","b"))
  val path:String="D:\\4b\\实施业务测试"
  def main(args: Array[String]) {
    /*noSorted
    partitionedSorted
    partitionsSortedBy
    repartitionAndSortWithinPartitionsTest*/
    flatMapDemo
    sc.stop()
  }
  def flatMapDemo={
    val rdd: RDD[Array[String]] = sc.parallelize(List(Array("a","b"),Array("c","d"),Array("e","f")))
    val map: RDD[String] = rdd.flatMap(x=>x)
    println(map.map((_,1)).reduceByKey(_+_).sortBy(_._1).collect().toBuffer)
    //value封装到Iterable里面
    println("-------------------")
    val key: RDD[(String, Iterable[Int])] = map.map((_,1)).groupByKey()
    key.foreach(x=>println(x._1+"----"+x._2.toList))
  }
  /**
   * 没有排序，要排序必须在iter里面写好你要的逻辑
   */
  def noSorted: Unit ={
    val rdd: RDD[String] = rdd1.mapPartitionsWithIndex((partitionId: Int, iter: Iterator[String]) => {
      iter.toList.map(x => {
       /* println(s"partitionID=$partitionId,value=$x")*/
        x
      }).iterator
    })

    saveFile(path.concat("\\noSorted"),rdd)
    println("=====================")
  }

  /**
   * 可以实现分区内排序
   */
  def partitionedSorted: Unit ={
      val rdd: RDD[String] = rdd1.mapPartitions(x=>x.toList.sortBy(y => y).iterator)
      saveFile(path.concat("\\partitionedSorted"),rdd)
      println("=====================")
  }

  /**
   * 可以实现rdd的排序
   */
  def partitionsSortedBy: Unit ={
    val rdd: RDD[String] = rdd1.sortBy(x =>x,true)
    saveFile(path.concat("\\partitionsSortedBy"),rdd)
    println("=====================")
  }

  /**
   * 可以实现key-value的排序
   */
  def repartitionAndSortWithinPartitionsTest: Unit ={
    var count :Int =0;
    val rdd2: RDD[(String, Int)] = rdd1.map(x => {
      count += 1
      (x, count)
    })
    val rdd: RDD[(String, Int)] = rdd2.repartitionAndSortWithinPartitions(new HashPartitioner(1))
    val fs: FileSystem = FileSystem.get(new Configuration)
    val path1: Path = new Path(path.concat("\\repartitionAndSortWithinPartitionsTest"))
    if(fs.exists(path1)){
      fs.delete(path1,true)
      rdd.saveAsTextFile(path.concat("\\repartitionAndSortWithinPartitionsTest"))
    }else{
      rdd.saveAsTextFile(path.concat("\\repartitionAndSortWithinPartitionsTest"))
    }
  }
  def saveFile(path : String,rdd : RDD[String]): Unit ={
    val fs: FileSystem = FileSystem.get(new Configuration)
    val path1: Path = new Path(path)
    if(fs.exists(path1)){
      fs.delete(path1,true)
      rdd.saveAsTextFile(path)
    }else{
      rdd.saveAsTextFile(path)
    }
  }

}

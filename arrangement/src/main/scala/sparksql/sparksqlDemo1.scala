package sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by YuiSol on 2017/9/27.
 * 反射推断法
 */
case class Person(id :Int,name:String ,age:Int)
object sparksqlDemo1 {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("sparksql").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val lineRDD = sc.textFile("D:\\2b\\person.txt").map(_.split(" "))
    val personRDD: RDD[Person] = lineRDD.map(x=>Person(x(0).toInt,x(1),x(2).toInt))
    //用隐式转换将RDD转成DataFrame
    import sqlContext.implicits._
    val personDF: DataFrame = personRDD.toDF
    //注册成临时表
    personDF.registerTempTable("person")
    val sql = sqlContext.sql("select * from  person order by age desc limit 5")
    /*将结果以json的方式存到文件，如果文件存在，则报错*/
    /*sql.write.json("D:\\2b\\bb.log")*/
    sql.show()
    sc.stop()
  }
}

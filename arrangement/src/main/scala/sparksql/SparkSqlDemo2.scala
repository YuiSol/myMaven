package sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.sql.types.{StringType, IntegerType, StructField, StructType}
import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by YuiSol on 2017/9/27.
 * sql StructType法
 */
object SparkSqlDemo2 {
  def main(args: Array[String]) {
    //如果在集群上跑就不需要setMaster("local[4]"),不过要开启hadoop和spark，本地idea上跑则要加上
    val conf = new SparkConf().setAppName("SQL-1").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val lineRDD = sc.textFile("D:\\2b\\Person.txt").map(x=>x.split(" "))
    val structType: StructType = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("id", IntegerType, true)
      )
    )
    val personRDD: RDD[Row] = lineRDD.map(x=>Row(x(0).toInt,x(1),x(2).toInt))
    //创建DataFrame
    val personDF: DataFrame = sqlContext.createDataFrame(personRDD,structType)
    //注册临时表
    personDF.registerTempTable("person")
    val sql = sqlContext.sql("select * from person")
    sql.show()
    sc.stop()
  }
}

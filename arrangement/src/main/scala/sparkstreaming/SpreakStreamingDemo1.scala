package sparkstreaming

import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}

/**
 * Created by YuiSol on 2017/9/15.
 */
object SpreakStreamingDemo1 {
  /**
   * Seq 现在的次数
   * option 历史出现的次数
   * (Iterator[(String, Seq[Int], Option[Int])]) => Iterator[(String, Int)] 接收的String 是key  sql是当前出现的次数，option是历史出现的次数 返回的Iterator[(String, Int)] 是把 key-value 封装好返回
   */
  /*private val updateFunc: (Iterator[(String, Seq[Int], Option[Int])]) => Iterator[(String, Int)] = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap(it => Some(it._2.sum + it._3.getOrElse(0)).map(x => (it._1, x)))
  }*/
  private val updateFunc: (Seq[Int], Option[Int]) => Some[Int] = (newValue :Seq[Int],runningCount :Option[Int]) => {
    println("new Value"+newValue.toBuffer)
    println("runningcount Value"+runningCount.toBuffer)
    Some(newValue.sum + runningCount.getOrElse(0))
  }
 /* val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap(it=>Some(it._2.sum + it._3.getOrElse(0)).map(x=>(it._1,x)))
    //iter.flatMap{case(x,y,z)=>Some(y.sum + z.getOrElse(0)).map(m=>(x, m))}
  }*/
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val name: SparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val streaming: StreamingContext = new StreamingContext(conf,Seconds(5))
    streaming.checkpoint("d:\\2b\\checkpoint\\")
    val sock: ReceiverInputDStream[String] = streaming.socketTextStream("192.168.1.81",9999)
    val dstream: DStream[String] = sock.flatMap(x=>(x.split(" ")))
    val map: DStream[(String, Int)] = dstream.map(x=>(x,1))
    val partitioner: HashPartitioner = new HashPartitioner(streaming.sparkContext.defaultParallelism)
   val key: DStream[(String, Int)] = map.updateStateByKey(updateFunc)
   /* val key: DStream[(String, Int)] = map.updateStateByKey(updateFunc,partitioner,true)*/
    key.print()

    streaming.start()
    streaming.awaitTermination()
    /**
    * def updateStateByKey[S: ClassTag](
      updateFunc: (Iterator[(K, Seq[V], Option[S])]) => Iterator[(K, S)],
      partitioner: Partitioner,
      rememberPartitioner: Boolean
    ): DStream[(K, S)] = ssc.withScope {
     new StateDStream(self, ssc.sc.clean(updateFunc), partitioner, rememberPartitioner, None)
  }
*/
   /* map.updateStateByKey(((iter:Iterator[(String,Seq[Int],Option[Int])])=>{
      iter.flatMap(it=>Some(it._2.sum + it._3.getOrElse(0)).map(x=>(it._2,x)))
      /*iter.flatMap(it => some(it._2.sum + it._3.getOrElse(0)).map(x => (it._1,x)))*/
    }),new HashPartitioner(streaming.sparkContext.defaultParallelism),true)*/
  }
}

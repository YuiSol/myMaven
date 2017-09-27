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
  /*  private val updateFunc: (Iterator[(String, Seq[Int], Option[Int])]) => Iterator[(String, Int)] = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    iter.flatMap(it => Some(it._2.sum + it._3.getOrElse(0)).map(x => (it._1, x)))
  }*/
  /**
   * option 历史出现的次数
   * Seq 现在的次数
   * 返回值Some[Int] 是根据你的逻辑实现你想要的操作
   * newValue.sum 可以把当前出现的次数提取出来
   * runningCount.getOrElse(0) 可以拿到历史的结果
   */
  /*  private val updateFunc: (Seq[Int], Option[Int]) => Some[Int] = (newValue :Seq[Int],runningCount :Option[Int]) => {
    println("new Value:"+newValue.toBuffer)
    println("runningcount Value:"+runningCount.toBuffer)
    Some(newValue.sum + runningCount.getOrElse(0))
  }*/
  /**
   * (Iterator[(String, Seq[Int], Option[Int])])  这是一个迭代器 里面封装了 String : key , Seq[Int] 当前出现的次数   Option 历史出现的次数
   * => Iterator[(String, Int)] 返回值 根据 key - value 封装数据 ( String , Int )
   * it => 指的是迭代器本身
   * it._2.sum 相当于  Seq[Int].sum 可以拿到当前窗口的出现的次数
   * it._3.getOrElse(0) 相当于 Option[Int].getOrElse(0) 可以拿到历史窗口记录的次数
   * Some(it._2.sum + it._3.getOrElse(0)) 把 Seq[Int] 和 Option[Int] 中的值收集以后相加，取其结果
   * it_1 就是 key ：String
   * .map(x => (it._1, x) 最后通过一个map把 key 和上面封装好的结果通过 flatmap方法 再度封装成为一个 Iterator[(String,Int)]
   *
   * 大概思路:
   * 拿到iter: Iterator[(String, Seq[Int], Option[Int])])
   * 1 . 根据需求先把  Seq[Int], Option[Int] 这一段抽出来 实现你要的逻辑
   * 2 . 把 Key : String 和上面返回的结果 封装成(String,Int)
   * 3 . 让 flatmap 方法把 (String,Int) 自动封成 Iterator[(String,Int)] 搞定
   *
   */



  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val name: SparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val streaming: StreamingContext = new StreamingContext(conf,Seconds(5))
    streaming.checkpoint("d:\\2b\\checkpoint\\")
    val sock: ReceiverInputDStream[String] = streaming.socketTextStream("192.168.1.81",9999)
    val dstream: DStream[String] = sock.flatMap(x=>(x.split(" ")))
    val map: DStream[(String, Int)] = dstream.map(x=>(x,1))
    val partitioner: HashPartitioner = new HashPartitioner(streaming.sparkContext.defaultParallelism)
  /* val key: DStream[(String, Int)] = map.updateStateByKey(updateFunc)*/
   /* val key: DStream[(String, Int)] = map.updateStateByKey(updateFunc,partitioner,true)*/
   /* private val updateFunc: (Iterator[(String, Seq[Int], Option[Int])]) => Iterator[(String, Int)] = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
      val map: Iterator[(String, Int)] = iter.flatMap(it => Some(it._2.sum + it._3.getOrElse(0)).map(x => (it._1, x)))
      //iter.flatMap{case(x,y,z)=>Some(y.sum + z.getOrElse(0)).map(m=>(x, m))}
    }
    key.print()*/

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

package redis

import java.lang.{Long, Boolean}
import java.util

import redis.clients.jedis.{BinaryClient, Jedis}
import scala.collection.JavaConverters._
import scala.collection.mutable


/**
 * Created by YuiSol on 2017/9/13.
 */
object Redis_Demo {
  def main(args: Array[String]): Unit = {
    val jedis: Jedis = new Jedis("192.168.1.81",6379)
  /*  redis_demo1(jedis)
    redis_demo2(jedis)*/
    /*redis_demo3(jedis)*/
   /* redis_demo4(jedis)*/
    /*redis_demo5(jedis)*/
    /*redis_demo6(jedis)*/
    redis_demo7(jedis)
   /* val scala: mutable.Set[String] = jedis.keys("*").asScala*/
   /* scala.foreach(x=>if(x=="s")jedis.del(x))*/
   /* scala.foreach(x=>println(x))

    jedis.append("keys","hello")
    println("-----------------")

    jedis.mset("name","yuisol","age","18")
    print( jedis.mget("name","yuisol"))
    jedis.lpush("buff","mytest")
    jedis.flushAll()
    jedis.keys("*").asScala.foreach(x=>println(x))*/
//    jedis.linsert("mykey",Client.LIST_POSITION.AFTER,"hello","spark")

  }
  def redis_demo1(jedis : Jedis): Unit ={
    jedis.append("hello","hi")
    val exists: Boolean = jedis.exists("hello")
    println("hello存在吗"+exists)
    println(jedis.get("hello"))
    jedis.keys("*").asScala.foreach(x=>print(x+"\t"))
    println()
    println("----------------------------")
  }
  def redis_demo2(jedis : Jedis): Unit ={
      jedis.set("hello","hi")
      println(jedis.get("hello"))
      jedis.mset("name","asd","asd","sad","hello","dsf")
      jedis.append("number","1")
     /* jedis.incr("number")*/
      jedis.incrBy("number",64)
      jedis.decrBy("number",10000)
      println(jedis.get("number"))
      jedis.keys("*").asScala.foreach(x=>print(x+"\t"))
  }
  def redis_demo3(jedis : Jedis): Unit ={
      println(jedis.getSet("hello","bye"))
      jedis.setex("myde",5,"daf")
      println(jedis.get("myde"))
      Thread.sleep(5001)
      println(jedis.get("myde"))
  }
  def redis_demo4(jedis : Jedis): Unit ={
    jedis.del("mylist")
    jedis.lrange("mylist",-1,0).asScala.foreach(x=>print(x+"\t"))
    println("\n ----------------------------------------")
    jedis.rpush("mylist","1","2","3","4","5")
    jedis.rpush("mylist","6","7","8","9","10")
   /* jedis.keys("mylist").asScala.foreach(x=>print(x+"\t"))*/
    jedis.lpush("mylist","0","-1")
   /* println("lpop="+jedis.lpop("mylist"))
    println("rpop="+jedis.rpop("mylist"))*/
    println(jedis.lindex("mylist",5))
    jedis.lrange("mylist",0,-1).asScala.foreach(x=>print(x+"\t"))
    println("\n-----------------------------------")
    jedis.linsert("mylist",BinaryClient.LIST_POSITION.BEFORE,"-1","-2")
    jedis.linsert("mylist",BinaryClient.LIST_POSITION.AFTER,"10","11")
    jedis.lrange("mylist",0,-1).asScala.foreach(x=>print(x+"\t"))
    println("\n+-----------------------------------")
    jedis.ltrim("mylist",3,jedis.llen("mylist"))
    jedis.lrange("mylist",0,-1).asScala.foreach(x=>print(x+"\t"))
    println("\n+-----------------------------------")
    println(jedis.brpop(1,"asdads","mylist"))
  }
  def redis_demo5(jedis : Jedis): Unit ={
    jedis.del("myhash")
    val hset: Long = jedis.hset("myhash","one","!11")
    println(hset)
    println(jedis.hget("myhash","fist"))
    println(jedis.hget("myhash","china"))
    println(jedis.hget("xxx","aas"))
    val map: util.HashMap[String, String] = new util.HashMap[String,String]()
    map.put("two","dsf")
    map.put("three","tds")
    map.put("four","50")
    jedis.hmset("myhash",map)
    val scala: mutable.Buffer[String] = jedis.hmget("myhash","one","two").asScala
    println(scala.toBuffer)
    println(jedis.hkeys("myhash").asScala.toBuffer)
    println(jedis.hvals("myhash").asScala.toBuffer)
    println(jedis.hexists("myhash","one"))
    println(jedis.hdel("myhash","one"))
    println(jedis.hexists("myhash","one"))
    jedis.hincrBy("myhash","four",234)
    jedis.hincrByFloat("myhash","four",100)
    println(jedis.hsetnx("myhash","four","bi"))
    println(jedis.hgetAll("myhash").asScala.toBuffer)
  }
  def redis_demo6(jedis : Jedis): Unit ={
    jedis.sadd("myset","one","four","five")
    println(jedis.smembers("myset").asScala.toBuffer)
    jedis.sadd("myset1","two","three","one","four","zero")
    jedis.sadd("myset2","six","three","one","four","zero")
   /* println(jedis.sdiff("myset","myset1").asScala.toBuffer)*/
    jedis.sdiffstore("myset3","myset","myset1","myset2")
    println(jedis.smembers("myset").asScala.toBuffer)
    println(jedis.smembers("myset3").asScala.toBuffer)
  }
  def redis_demo7(jedis : Jedis): Unit ={
      println(jedis.get("hello"))
      jedis.zadd("hello",1,"hello")
      println(jedis.get("hello"))
  }
}

import java.util

import redis.clients.jedis.Jedis

/**
 * Created by YuiSol on 2017/9/12.
 */
object Redis {
  def main(args: Array[String]) {
    val jedis: Jedis = new Jedis("192.168.1.81",6379)
    println(jedis.ping())
    val keys: util.Set[String] = jedis.keys("*")
    print(keys)
  }
}

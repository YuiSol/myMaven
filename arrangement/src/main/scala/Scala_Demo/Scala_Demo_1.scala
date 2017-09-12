package Scala_Demo

import java.lang.String

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, ArrayBuffer}
import scala.util.control.Breaks



/**
 * Created by YuiSol on 2017/9/8.
 */
/*class Scala_Demo_1 {
  println("这是一个先运行的方法")
  def test1(): Unit ={
    println("这是方法")
  }

  def main(args: Array[String]) {
      println("demo")
  }*/
 object Scala_Demo_1 {
  val name = "yuisol"
  private val age = 13
  println("这是一个先运行的方法")

  def test1(): Unit = {
    println("这是方法")
  }

  def start(): Unit = {
    println("这是main方法")
    test1()
    println("这是一个可变数组")
    for (i <- test2()) {
      println(i)
    }
    println("这是一个不可变数组")
    val test: Array[String] = test3()
    test.foreach(println(_))
    println("遍历数组的方式3")
    for (i <- 0 to test.length if (i != test.length)) {
      print(test(i) + "\t")
    }
    println()
    println("遍历数组的方式4")
    for (i <- 0 until test.length) {
      print(test(i) + "\t")
    }
    println()
    println("数组反转")
    for (i <- (0 until test.length).reverse) {
      print(test(i) + "\t")
    }
    println()
    println("这是yield重组后的数组")
    println(test4(test).toBuffer)
    println("map:")
    println(test.map(_ + ":map").toBuffer)
    println("filter:")
    println(test.filter(_.hashCode % 2 == 0).map(x => x + ": map" + "filter").toBuffer)
    test5()
    test6()
    test7()
    test8()
    test9()
    test10()
    test11()
    test12()
    test13()
    test14()
    test15()
  }
  def test16(): Unit ={
    val person: Person1 = new Person1("zhangsan",12)
    println(person.name)
  }
  def main(args: Array[String]) {
    start()
  }

  def test2(): ArrayBuffer[Int] = {
    val arr = ArrayBuffer[Int]()

    arr += 1;
    arr += 2;
    arr +=(3, 4, 5, 6)
    arr ++= Array(7, 8, 9, 10)
    arr ++= ArrayBuffer(11, 12, 13)
    arr.insert(0, -1, 0)
    arr.insert(5, 56, 6481, 545, 848, 848)
    arr.+=:(-2)
    arr.remove(5, 9)
    arr
  }

  def test3(): Array[String] = {
    val arr = Array[String]("one","two","three")
    println("array.tobuffer=" + arr.toBuffer)
    println("array.tostring" + arr.toString)
    arr
  }

  def test4(arr: Array[String]): Array[String] = {
    val demo = for (i <- arr) yield i + ":yield"
    demo
  }

  def test5(): Unit = {
    val arr = Array[Int](11,12,12,31,53,32,2,12321,21,345,3)
    println("数组最大值为:" + arr.max)
    println("数组最小值为:" + arr.min)
    println("数组总数为:" + arr.sum)
    val sorted: Array[Int] = arr.sorted
    println("排序后为:")
    sorted.foreach(X => print(X.asInstanceOf[Float] + "\t"))
  }

  def test6(): Unit = {
    val breaks: Breaks = new Breaks
    for (i <- 1 to 10) {
      print(i)
      /* if (i==8) breaks.break()*/
    }
  }

  def test7(): Unit = {
    /**
     * 在Scala中，有两种Map，一个是immutable包下的Map，该Map中的内容不可变；另一个是mutable包下的Map，该Map中的内容可变
     */
    val intToString: Map[Int, String] = Map((1, "one"), (2, "two"), (3, "three"))

    intToString.-(2)
    intToString + (4 -> "four")
    println()
    println(intToString(1))
    println(intToString.getOrElse(23, "null"))
    println(intToString.getOrElse(1, "null"))
    val tuples: Iterator[(Int, String)] = intToString.iterator
    while (tuples.hasNext) {
      val next: (Int, String) = tuples.next()
      println(next._1 + "+++++++++++" + next._2)
    }
  }

  def test8(): Unit = {
    val tuple: (String, Int, Double) = ("hadoop", 345, 234.24)
    println(tuple._3)
    var arr: Array[(String, Int)] = Array(("demo", 123), ("demo1", 234))
    val toMap: Map[String, Int] = arr.toMap
    println("==============")
    tomap(toMap).foreach(println(_))
  }

  val tomap = (arr: Map[String, Int]) => Iterator {
    val tuples: Iterator[(String, Int)] = arr.iterator
    while (tuples.hasNext) println(tuples.next())
  }

  def test9(): Unit = {
    println("====================")
    val ints: Array[Int] = Array(1, 2, 1, 41, 3, 34, 5)
    val ints1: Array[Int] = Array(23, 234, 23, 41, 32, 3452, 234)
    val strings: Array[String] = Array("dfas", "asdf", "adfa", "asdfs", "dasf")
    val zip: Array[((Int, String), Int)] = ints.zip(strings).zip(ints1)
    zip.foreach(x => println(x._1._2))
  }

  def test10(): Unit = {
    val ints: List[Int] = List(1, 2, 3)
    val ints1: List[Int] = 0 :: ints
    val ints2 = ints1 :: ints
    println(ints2.size)
    val strings: List[String] = List("a", "b", "c")
    val list: List[Any] = strings :: ints1
    //加不了
    list.++("d")
    for (i <- list) {
      print(i + "\t")
    }
    println()
  }

  def test11(): Unit = {
    val ints: ListBuffer[Int] = ListBuffer[Int](1,2,3,4,5)
    ints += 6
    ints.append(7, 8)
    ints.foreach(x => print(x + "\t"))
    val ints1: ListBuffer[Int] = ints.++=(ListBuffer(9, 10))
    ints1.map(x => print(x + "\t"))
    val ints2: ListBuffer[Int] = ints1.:+(11)
    println()
    println("==============")
  }

  def test12(): Unit = {
    val set: mutable.HashSet[Int] = new mutable.HashSet[Int]()
    set.+(2)
    set.+(2)
    set.add(2)
    val unit = set ++= (Set(1, 5, 3, 4))
    unit -= 1;
    unit.remove(5)
    unit.map(println(_))
  }

  def test13(): Unit = {
    val intToString: mutable.HashMap[Int, String] = mutable.HashMap[Int,String]((1,"a"),(2,"b"))
    intToString.+=((3, "c"))
    intToString(4) = "d"
    intToString.put(5, "e")
    println(intToString.get(5))
    intToString.foreach(x => print(x._1 + "----" + x._2 + "\t"))
  }

  def test14(): Unit = {
    val mydemo: mydemo = new mydemo("kitty", 18)
    //mydemo.name="a"
    mydemo.age = 12
    println(mydemo.name + mydemo.age)
    println("---------------")
    println(new PerSon().toString)
    println("---------------")
    val son: PerSon = new PerSon("YuiSol", 18)
    println(son.toString)
  }

  def test15(): Unit = {

    try {
      println(1 / 0)
    }
    catch {
      case e : ArithmeticException => println("被除数不能为0")
    }
  }

  class mydemo(val name: String, var age: Int) {
    println("这是测试类")
  }

  class PerSon() {
    var name: String = null
    var age: Int = 0
    private[this] var person: String = "person"
    println("这是主构造器")

    def this(name: String, age: Int) {
      this()
      this.name = name
      this.age = age
      println("这是副构造器")
    }

    override def toString: String = {
      this.person + "\t" + this.name + "\t" + this.age
    }

    println(person.toBuffer)
  }

  class Scala_Demo_1 {
    val sex = "男"
    private val contory = "广州"
  }
  case class Person1(name:String,age:Int){

  }
  def test10(): Unit ={
    
  }
}


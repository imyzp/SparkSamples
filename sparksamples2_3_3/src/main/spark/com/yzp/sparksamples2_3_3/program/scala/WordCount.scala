package com.yzp.sparksamples2_3_3.program.scala

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object WordCount {
  def main(args: Array[String]): Unit = {
    // 源文件路径
    val filePath = args(0)
//    val filePath = "sparksamples2_3_3/src/main/spark/com/yzp/sparksamples2_3_3/program/scala/word.txt"
    // 处理后保存路径
    val savePath = args(1)
//    val savePath = "sparksamples2_3_3/src/main/spark/com/yzp/sparksamples2_3_3/program/scala/out"


    // 1、构建sparkConf对象，设置application名称和master地址
    // master地址可在运行jar时指定
    val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[2]")

    // 2、构建sparkContext对象，该对象非常重要，它是所有spark程序的执行入口
    // 它内部会构建 DAGScheduler 和 TaskScheduler 对象
    val sc = new SparkContext(sparkConf)

    // 设置日志输出级别
    sc.setLogLevel("warn")

    // 3、读取数据文件
    val data: RDD[String] = sc.textFile(filePath)

    // 4、切分每一行，获取所有单词
    val words: RDD[String] = data.flatMap(x => x.split(" "))

    // 5、每个单词计为1
    val wordAndOne: RDD[(String,Int)] = words.map(x => (x,1))

    // 6、相同单词出现的1累加
    val result: RDD[(String,Int)] = wordAndOne.reduceByKey((x,y) =>x+y)

    // 按照单词出现的次数降序排列 第二人参数默认是true表示升序，设置为false表示降序
    val sortedRDD: RDD[(String,Int)] = result.sortBy(x => x._2,false)

    // 7、收集数据打印
    val finalResult: Array[(String,Int)] = sortedRDD.collect()
    finalResult.foreach(println)

    // 把计算结果保存
    sortedRDD.saveAsTextFile(savePath)

    // 8、关闭sc
    sc.stop()

  }
}

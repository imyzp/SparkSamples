## spark-shell --master local[N]

- local 表示程序在本地进行计算，跟spark集群目前没有任何关系
- N  它是一个正整数，表示使用N个线程参与任务计算
- local[N] 表示本地采用N个线程计算任务

- 默认会产生一个SparkSubmit进程



## spark-shell --master spark://node01:7077 --executor-memory 1g  --total-executor-cores 4

- --master spark://node01:7077
  - 指定活着的master地址
- --executor-memory 1g
  - 指定每一个executor进程的内存大小
- --total-executor-cores 4
  - 指定总的executor进程cpu核数



本地磁盘文件

```
sc.textFile("file:///yzp/yzpwork/sparkshell/word.txt").flatMap(x=>x.split(" ")).map(x=>(x,1)).reduceByKey((x,y)=>x+y).collect

sc.textFile("file:///yzp/yzpwork/sparkshell/word.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect
```



hadoop上文件

```
sc.textFile("/sparkwork/word.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect

sc.textFile("hdfs://node01:8020/sparkwork/word.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect

//实现读取hdfs上文件之后，需要把计算的结果保存到hdfs上
sc.textFile("/sparkwork/word.txt").flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).saveAsTextFile("/sparkwork/out/wordout")


```


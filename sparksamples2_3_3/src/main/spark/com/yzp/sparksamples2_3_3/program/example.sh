#!/bin/bash

$SPARK_HOME/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://node01:7077 \
--executor-memory 1G \
--total-executor-cores 2 \
$SPARK_HOME/examples/jars/spark-examples_2.11-2.3.3.jar \
10



# 参数说明，参数之间顺序没有强制要求
# --class  指定包含main方法的主类

# --master  指定spark集群master地址，可配置多个即高可用模式提交 (集群有很多个master），会轮训找到活着的master,向它申请计算资源，运行程序，
# 如# --master spark://node01:7077,node02:7077,node03:7077 \

# --executor-memory 指定任务在运行的时候需要的每一个executor内存大小

# --total-executor-cores 指定任务在运行的时候需要总的cpu核数

# 包含main方法的jar包路径

# main方法参数


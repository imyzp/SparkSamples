#!/usr/bin/env bash
spark-submit \
--master spark://node01:7077 \
--class com.yzp.sparksamples2_3_3.program.scala.WordCount \
--excutor-memory 1g \
--total-executor-cores 4 \

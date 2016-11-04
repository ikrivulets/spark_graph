package edu.gatech.cse6242

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object Task2 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Task2"))
    //read and map
    val file = sc.textFile("hdfs://localhost:8020" + args(0))
    val counts = file.map(line => (line.split("\t")(1), line.split("\t")(2).toInt)).reduceByKey(_ + _)
    
    //output
    counts.map{tuple => tuple.productIterator.mkString("\t")}.saveAsTextFile("hdfs://localhost:8020" + args(1))
  }
}

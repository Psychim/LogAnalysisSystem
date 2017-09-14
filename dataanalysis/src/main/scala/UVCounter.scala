package main.scala
import org.apache.spark.sql.SparkSession
import main.scala.util.UrlUtil

/**
  * Created by root on 9/12/17.
  */
object UVCounter {
  def run(inputPath:String,outputPath:String,num: Int): Unit ={
    val spark= SparkSession.builder().appName("MostUrl").master("local").enableHiveSupport().getOrCreate()
    val sc=spark.sparkContext

    val bcfields=sc.broadcast(Global.fields)
    val data=sc.textFile(inputPath).map(_.split("\t"))
    //counts the UV of each hosts and sorts by it, then take the top [num] (host,UV) pairs
    val uvcounts=data.map{m=>
      val url=m(bcfields.value.indexOf("Url"))
      val mac=m(bcfields.value.indexOf("Devmac"))
      (UrlUtil.getHostName(url),mac)
    }.distinct().map(m=> (m._1,1)).reduceByKey(_+_).map(m=>(m._2,m._1)).sortByKey(ascending=false).take(num)
    //save on hdfs
    sc.makeRDD(uvcounts).map(m=> m._2+"\t"+m._1).repartition(1).saveAsTextFile(outputPath)
  }
  def main(args:Array[String]): Unit ={
    run("hdfs://scm001:9000/user/hive/warehouse/loganalysis.db/log",
      "hdfs://scm001:9000/LogAnalysisSystem/UVOutput",20)
  }
}

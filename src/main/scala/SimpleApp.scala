/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession

/*
* SimpleApp follow example on Apache Spark quick start documentation.
* Original URL: https://spark.apache.org/docs/latest/quick-start.html
*/
object SimpleApp {
  def main(args: Array[String]) {
    var mySparkHome = "/zserver/spark/spark-3.0.1-bin-hadoop2.7"
    val logFile = mySparkHome + "/README.md" // Should be some file on your system
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"log file dir is $logFile")
    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}
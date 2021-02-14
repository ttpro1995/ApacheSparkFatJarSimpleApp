/* SimpleApp.scala */
import org.apache.spark.sql.SparkSession
import org.joda.money.Money
import org.joda.money.CurrencyUnit


/*
* We include joda time in this spark application 
*/
object JodaSimpleApp {
  def main(args: Array[String]) {
    var mySparkHome = "/zserver/spark/spark-3.0.1-bin-hadoop2.7"
    val logFile = mySparkHome + "/README.md" // Should be some file on your system
    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println(s"log file dir is $logFile")
    println(s"Lines with a: $numAs, Lines with b: $numBs")

    // create a monetary value
    val m1 = Money.parse("USD 23.87");
    val usd = CurrencyUnit.of("USD");
    val m2 = m1.plus(Money.of(usd, 12.43d));
    println(s"Money m1 $m1")
    println(s"Money m2 $m2")

    // // use joda time 
    // val dt = new DateTime();  // current time
    // val month = dt.month().get();     // gets the current month

    // // print the month
    // println(s"JodaTime current month is $month")

    spark.stop()
  }
}
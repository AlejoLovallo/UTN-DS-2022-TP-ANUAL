package Server;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Server {
  public static void main(String[]args){
    SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]");
    JavaSparkContext sparkContext = new JavaSparkContext(conf);
  }
}

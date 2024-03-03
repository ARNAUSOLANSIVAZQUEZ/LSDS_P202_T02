package edu.upf;

import edu.upf.model.ExtendedSimplifiedTweet;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public class MostRetweetedApp {
    public static void main(String[] args) throws IOException {
        // Initialize Spark configuration and context
        SparkConf conf = new SparkConf().setAppName("MostRetweeted App");
        conf.set("spark.hadoop.validateOutputSpecs", "false");
        SparkContext scc = SparkContext.getOrCreate(conf);
        // Initialize Java Spark context to handle with Java
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(scc);
        // Initialize timer
        LocalTime start = LocalTime.now();
        // Read input
        List<String> argsList = Arrays.asList(args);
        String outputFile = argsList.get(0);

        System.out.println("Output file: " + outputFile);
        // Retrieve tweets from input
        JavaRDD<String> tweets = sc.textFile(argsList.get(1));
        // Pipelining transformations to obtain retweeted tweets
        JavaRDD<ExtendedSimplifiedTweet> retweeted = tweets
                .flatMap(t -> Arrays.asList(t.split("\n")).iterator())
                .map(s -> ExtendedSimplifiedTweet.fromJson(s))
                .filter(extended -> extended.isPresent())
                .map(present -> present.get())
                .filter(filtered -> filtered.getIsRetweeted());

        // Pipelining transformations to obtain most retweeted users
        JavaRDD<Long> users = retweeted
                .mapToPair(t -> new Tuple2<>(t.getRetweetedUserId(), 1))
                .reduceByKey((a,b) -> a + b)
                .mapToPair(f -> f.swap())
                .sortByKey(false)
                .map(u -> u._2);

        // Retrieve top 10 users
        List<Long> top_users = users.take(10);

        // Pipelining transformations to obtain most retweeted tweets of the most retweeted users
        JavaRDD<Long> most_rt = retweeted
                .filter(t -> top_users.contains(t.getRetweetedUserId()))
                .mapToPair(t -> new Tuple2<>(t.getRetweetedTweetId(), 1))
                .reduceByKey((a,b) -> a+b)
                .mapToPair(f -> f.swap())
                .sortByKey(false)
                .map(r -> r._2);

        // Retrieve top 10 tweets
        List<Long> top_rts = most_rt.take(10);

        // Pipeline transformations to obtain most retweeted tweets of top 10 retweeted users
        JavaRDD<String> filtered = retweeted
                .filter(t -> top_users.contains(t.getRetweetedUserId()))
                .filter(t -> top_rts.contains(t.getRetweetedTweetId()))
                .mapToPair(t -> new Tuple2<>(t.getRetweetedTweetId(), t))
                .reduceByKey((a,b) -> a)
                .map(t -> t._2.getText());


        // Persist the sorted RDD
        filtered.saveAsTextFile(outputFile);

        List<String> top_tweets = filtered.take(10);

        for(int i = 0; i<top_users.size(); i++){
            System.out.println("\nTop retweeted user: " + (i+1) + "\n" + top_users.get(i) + "\n");
        }
        for(int i = 0; i<top_rts.size(); i++){
            System.out.println("\nTop retweeted Tweet: " + (i+1) + "\n" + top_rts.get(i) + "\n");
        }
        for(int i = 0; i<top_tweets.size(); i++){
            System.out.println("\nTweet: " + (i+1) + "\n" + top_tweets.get(i) + "\n");
        }
        // Finalize timer
        LocalTime end = LocalTime.now();
        Duration elapsed = Duration.between(start, end);
        long secondsElapsed = elapsed.getSeconds();
        secondsElapsed = Math.abs(secondsElapsed);
        System.out.println(String.format("\nTotal time elapsed retrieving BiGrams:  %d h %02d m %02d s" , secondsElapsed / 3600, (secondsElapsed % 3600) / 60, (secondsElapsed % 60)));


    }

}


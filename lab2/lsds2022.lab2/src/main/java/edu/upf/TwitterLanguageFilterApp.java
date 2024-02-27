package edu.upf;

import edu.upf.model.SimplifiedTweet;
import edu.upf.uploader.S3Uploader;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TwitterLanguageFilterApp {
    public static void main( String[] args ) throws IOException {
        // Initialize Spark configuration and context
        SparkConf conf = new SparkConf().setAppName("Twitter Language Filter");
        conf.set("spark.hadoop.validateOutputSpecs", "false");
        SparkContext scc = SparkContext.getOrCreate(conf);
        // Initialize Java Spark context to handle with Java
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(scc);
        // Initialize timer
        LocalTime start = LocalTime.now();
        // Read input
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);


        System.out.println("Language: " + language + ". Output file: " + outputFile);
        // Retrieve tweets from input
        JavaRDD<String> tweets = sc.textFile(argsList.get(2));
        // Pipeline transformations to filter tweets
        JavaRDD<SimplifiedTweet> filtered = tweets
                .map(x-> SimplifiedTweet.fromJson(x))
                .filter(simplified-> simplified.isPresent())
                .filter(present_tweet-> present_tweet.get().getLanguage().equals(language))
                .map(filtered_tweet-> filtered_tweet.get());
        // Persist the filtered tweets
        filtered.saveAsTextFile(outputFile);
        // Count the filtered tweets
        long count = filtered.count();
        // Finalize timer
        LocalTime end = LocalTime.now();
        Duration elapsed = Duration.between(start, end);
        long secondsElapsed = elapsed.getSeconds();
        secondsElapsed = Math.abs(secondsElapsed);
        System.out.println("\nLanguage filtered: " + language+ " || Total tweets filtered: " + count);
        System.out.println(String.format("\nTotal time elapsed Twitter Filtering:  %d h %02d m %02d s" , secondsElapsed / 3600, (secondsElapsed % 3600) / 60, (secondsElapsed % 60)));

    }
}

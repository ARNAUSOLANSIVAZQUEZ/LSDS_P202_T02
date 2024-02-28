package edu.upf;

import edu.upf.model.ExtendedSimplifiedTweet;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class BiGramsApp {
    private static class BiGram{
        String element1;
        String element2;
    }
    public static void main(String[] args) throws IOException {
        // Initialize Spark configuration and context
        SparkConf conf = new SparkConf().setAppName("BiGrams App");
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
        // Pipelining transformations to obtain original tweets
        JavaRDD<ExtendedSimplifiedTweet> original = tweets
                .map(s -> ExtendedSimplifiedTweet.fromJson(s))
                .filter(extended -> extended.isPresent())
                .filter(present_tweet-> present_tweet.get().getLanguage().equals(language))
                .filter(filtered -> filtered.get().getIsRetweeted())
                .map(org_tweet -> org_tweet.get());

    }
    public boolean equals(BiGram bg1, BiGram bg2){
        return bg1.element1 == bg2.element1 && bg1.element2 == bg2.element2
                || bg1.element1 == bg2.element2 && bg1.element2 == bg2.element1;
    }
}

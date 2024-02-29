package edu.upf;

import edu.upf.model.ExtendedSimplifiedTweet;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BiGramsApp {
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
                .flatMap(t -> Arrays.asList(t.split("\n")).iterator())
                .map(s -> ExtendedSimplifiedTweet.fromJson(s))
                .filter(extended -> extended.isPresent())
                .map(present -> present.get())
                .filter(mixed -> mixed.getLanguage().equals(language))
                .filter(filtered -> !(filtered.getIsRetweeted()));
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nCOMPLETED ORIGINALS\n\n\n\n\n\n");
        // Pipelining transformations to obtain tweets as bigrams
        JavaRDD<BiGram> bigrams = original
                .flatMap(t -> BiGram.fromExtendedSimplifiedTweet(t).iterator());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nCOMPLETED BIGRAMS\n\n\n\n\n\n");
        // Pipelining transformations to obtain counts of bigrams
        JavaPairRDD<BiGram, Integer> bigram_freq = bigrams
                .mapToPair(bigram -> new Tuple2<>(bigram, 1))
                .reduceByKey((a,b) -> a+b);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nCOMPLETED BIGRAM COUNT\n\n\n\n\n\n");
        // Persist the bigram-frequency rdd
        bigram_freq.saveAsTextFile(outputFile);
        // Count total different bigrams
        long count = bigram_freq.count();
        // Finalize timer
        LocalTime end = LocalTime.now();
        Duration elapsed = Duration.between(start, end);
        long secondsElapsed = elapsed.getSeconds();
        secondsElapsed = Math.abs(secondsElapsed);
        System.out.println("\nLanguage filtered: " + language+ " || Total different BiGrams: " + count);
        System.out.println(String.format("\nTotal time elapsed retrieving BiGrams:  %d h %02d m %02d s" , secondsElapsed / 3600, (secondsElapsed % 3600) / 60, (secondsElapsed % 60)));


    }

}

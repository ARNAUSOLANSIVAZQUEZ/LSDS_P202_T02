package edu.upf;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import com.github.tukaaa.MastodonDStream;
import com.github.tukaaa.config.AppConfig;
import com.github.tukaaa.model.SimplifiedTweetWithHashtags;


import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.api.java.function.Function;

import edu.upf.util.LanguageMapUtils;
import scala.Tuple2;



public class MastodonWindows {


        /**
         * This function takes a JavaPairRDD containing key-value pairs of type <String, Long> and performs the following operations:
         * 1. Extracts the values from the input RDD for sorting.
         * 2. Sorts the extracted values in descending order.
         * 3. Swaps the key-value pairs back to their original form after sorting.
         * 
         * @param rdd The input JavaPairRDD containing key-value pairs of type <String, Long>.
         * @return A new JavaPairRDD with the same key-value pairs as the input RDD, sorted by values in descending order.
         * @throws Exception If any exception occurs during the execution.
         */
        private static Function<JavaPairRDD<String, Long>, JavaPairRDD<String, Long>> reduceAndsortRDD =
        new Function<JavaPairRDD<String, Long>, JavaPairRDD<String, Long>>() {
        
            @Override 
            public JavaPairRDD<String, Long> call(JavaPairRDD<String, Long> rdd) throws Exception {
                // Extract the values for sorting
                JavaPairRDD<Long, String> swapped = rdd.mapToPair(Tuple2::swap);

                // Sort by values
                JavaPairRDD<Long, String> sorted = swapped.sortByKey(false);

                // Swap back to the original key-value pairs
                return sorted.mapToPair(Tuple2::swap);
            }
        };













        public static void main(String[] args) {
                String input = args[0];

                SparkConf conf = new SparkConf().setAppName("Real-time Mastodon Stateful with Windows Exercise");
                AppConfig appConfig = AppConfig.getConfig();

                StreamingContext sc = new StreamingContext(conf, Durations.seconds(20));
                JavaStreamingContext jsc = new JavaStreamingContext(sc);
                jsc.checkpoint("/tmp/checkpoint");

                JavaDStream<SimplifiedTweetWithHashtags> stream = new MastodonDStream(sc, appConfig).asJStream();


                
                // TODO IMPLEMENT ME
                JavaSparkContext sparkContext = jsc.sparkContext();
                
                
                // open file
                JavaRDD<String> fileMapping = sparkContext.textFile(input);
                JavaPairRDD<String, String> languageMap = LanguageMapUtils.buildLanguageMap(fileMapping);
                
                //stream creation
                JavaPairDStream<String, Long> language_count = stream
                    .mapToPair(toot -> new Tuple2<String,Long>(toot.getLanguage(), 1L))
                    .reduceByKey((a, b) -> a + b)
                    .transformToPair(
                        new Function<JavaPairRDD<String, Long>, JavaPairRDD<String, Long>>() {
                            @Override 
                            public JavaPairRDD<String, Long> call(JavaPairRDD<String, Long> rdd) throws Exception {
                                return languageMap
                                    .join(rdd)
                                    .mapToPair(pair -> pair._2())
                                    .distinct();
                            }
                        }
                    ).transformToPair(reduceAndsortRDD);           
                
                // window creation
                final JavaPairDStream<String, Long> windowedStream = language_count.window(Durations.seconds(60));
                
                // 15 languages with the most toots in the past 20 seconds
                language_count.foreachRDD( rdd -> {
                    System.out.println("\n\n\n\n\n\n\n\nMICRO BATCH:");
                });
                language_count.print(15);

                // 15 languages with the most toots in the past 60 seconds
                windowedStream.foreachRDD( rdd -> {
                    System.out.println("\n\n\n\n\n\n\n\nWINDOW:");
                });
                windowedStream
                    .reduceByKey((a,b) -> a + b)
                    .transformToPair(reduceAndsortRDD)
                    .print(15);



                // Start the application and wait for termination signal
                sc.start();
                sc.awaitTermination();
        }

}
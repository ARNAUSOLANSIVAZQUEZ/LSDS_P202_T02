package edu.upf;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;


import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaPairRDD;
import scala.Tuple2;
import java.util.List;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.Optional;
import org.apache.spark.streaming.api.java.JavaPairDStream;


import com.github.tukaaa.MastodonDStream;
import com.github.tukaaa.config.AppConfig;
import com.github.tukaaa.model.SimplifiedTweetWithHashtags;








public class MastodonWithState {


    /**
     * This function counts the total number of toots by user.
     * 
     * @param numbers The input list of Long values.
     * @param currentState An optional Long representing the current count state. If absent, defaults to 0L.
     * @return An Optional<Long> containing the updated count after adding the number of elements in the input list.
     * @throws Exception If any exception occurs during the execution.
     */
    private static Function2<List<Long>, Optional<Long>, Optional<Long>> countTootsByUser = new Function2<List<Long>, Optional<Long>, Optional<Long>>() {
        @Override
        public Optional<Long> call(List<Long> numbers, Optional<Long> currentState) throws Exception {
            // Extract the current count from the optional state, defaulting to 0 if absent
            Long count = currentState.or(0L);

            // Increment the count by the number of elements in the input list
            count += numbers.size();

            // Wrap the updated count in an Optional and return
            return Optional.of(count);
        }
    };





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










    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setAppName("Real-time Mastodon With State");
        AppConfig appConfig = AppConfig.getConfig();

        StreamingContext sc = new StreamingContext(conf, Durations.seconds(10));
        JavaStreamingContext jsc = new JavaStreamingContext(sc);
        jsc.checkpoint("/tmp/checkpoint");

        JavaDStream<SimplifiedTweetWithHashtags> stream = new MastodonDStream(sc, appConfig).asJStream();

        // TODO IMPLEMENT ME

        String lang = args[0];

        JavaPairDStream<String, Long> tootsPerUserInLanguage = stream
                // first we filter toots with unknown language and languages different from what we want
                .filter(toot -> toot.getLanguage() != null)
                .filter(toot -> toot.getLanguage().equals(lang))
                // asign a pair of username and value 1 to later count
                .mapToPair(toot -> new Tuple2<String, Long>(toot.getUserName(), 1L))
                // count new toots and sort them
                .updateStateByKey(countTootsByUser)
                .transformToPair(reduceAndsortRDD);
                
        // print the contents of the stream
        tootsPerUserInLanguage.print(20);

        



        // Start the application and wait for termination signal
        jsc.start();
        jsc.awaitTermination();
    }

}
package edu.upf;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;


import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import edu.upf.util.LanguageMapUtils;
import scala.Tuple2;


import com.github.tukaaa.MastodonDStream;
import com.github.tukaaa.config.AppConfig;
import com.github.tukaaa.model.SimplifiedTweetWithHashtags;

public class MastodonStateless {
        public static void main(String[] args) {
                String input = args[0];

                SparkConf conf = new SparkConf().setAppName("Real-time Twitter Stateless Exercise");
                AppConfig appConfig = AppConfig.getConfig();

                StreamingContext sc = new StreamingContext(conf, Durations.seconds(20)); 
                // With an interval of 20 seconds, we want to display the number of tweets for each language 
                JavaStreamingContext jsc = new JavaStreamingContext(sc);
                jsc.checkpoint("/tmp/checkpoint");

                JavaDStream<SimplifiedTweetWithHashtags> stream = new MastodonDStream(sc, appConfig).asJStream();

                // TODO IMPLEMENT ME
                JavaSparkContext sparkContext = jsc.sparkContext();

                // open the file
                JavaRDD<String> fileMapper = sparkContext.textFile(input);
                JavaPairRDD<String, String> languageMap = LanguageMapUtils.buildLanguageMap(fileMapper);

                // stream with the count per language
                JavaPairDStream<String, Long> lang_count = stream
                        .mapToPair(toot -> new Tuple2<String, Long>(toot.getLanguage(), 1L))
                        .reduceByKey((a, b) -> a + b) // reduce by key to get number of toots by language
                        .transformToPair(
                                new Function<JavaPairRDD<String, Long>, JavaPairRDD<String, Long>>() {
                                    @Override
                                    public JavaPairRDD<String, Long> call(JavaPairRDD<String, Long> rdd) throws Exception {
                                        return languageMap
                                                .join(rdd) // join to get names of languages in english
                                                .mapToPair(pair -> pair._2()) // yields (abbrev., (name, 1)) so we keep the second elem
                                                .distinct() // to avoid duplicates
                                                // sorting by the number of toots
                                                .mapToPair(pair -> pair.swap())
                                                .sortByKey(false)
                                                .mapToPair(pair -> pair.swap());
                                    }
                                });

                lang_count.print();



                //------------------------

                // Start the application and wait for termination signal
                sc.start();
                sc.awaitTermination();
        }
}




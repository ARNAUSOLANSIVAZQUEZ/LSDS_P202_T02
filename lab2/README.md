This submission is for group P202-02 formed by:
- Muñoz, Mario U172951
- Solans, Arnau U161668
- Villarino, Jorge U172789

# 2.1 
## User's Manual
To execute our submission, one must run the jar, with the json archives to be processed in the project directory, with the following commands:

- To filter tweets in English:

```
spark-submit --class edu.upf.TwitterLanguageFilterApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar en ./_output-folder/filtered_tweets_en ./tweets/twitter-data-from-2018-eurovision-final
```

- To filter tweets in Spanish:

```
spark-submit --class edu.upf.TwitterLanguageFilterApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar es ./_output-folder/filtered_tweets_es ./tweets/twitter-data-from-2018-eurovision-final
```

- To filter tweets in Catalan:

```
spark-submit --class edu.upf.TwitterLanguageFilterApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar ca ./_output-folder/filtered_tweets_ca ./tweets/twitter-data-from-2018-eurovision-final
```
- In general:

```
spark-submit --class edu.upf.TwitterLanguageFilterApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar <language> ./_output-folder/filtered_tweets_<language> <tweets directory path>

```


### Results

Results obtained are:
- English: 446603 tweets filtered.
- Spanish: 509435 tweets filtered
- Catalan: 4583 tweets filtered.


### Benchmark

Benchmarks are done with the library java.time.LocalTime, the benchmark has been performed for the whole process using Spark locally.


Specs for U161668:
-	RAM: MemTotal: 32516032 kB ~ 31GB
-	CPU: 12th Gen Intel(R) Core(TM) i7-12700H , cpu MHz: 892.349, cpu cores: 14
Benchmark:
- English: 0 h 00 m 39 s
- Spanish: 0 h 00 m 41 s
- Catalan: 0 h 00 m 37 s

Specs for U172951:
-	RAM: MemTotal: 16GB
-	CPU: 12th Gen Intel(R) Core(TM) i7-1255U , cpu MHz: 1700, cpu cores: 10

Specs for U172789:
-	RAM: MemTotal: 2x4GB DDR4 3200Mhz
-	CPU: 11th Gen Intel Core i5-1135G7 , frequency: 2.4 Mhz, cpu cores: 4, threads: 8

This submission is for group P202-02 formed by:
- Muñoz, Mario U172951
- Solans, Arnau U161668
- Villarino, Jorge U172789

# 2
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

Benchmark:
- English: 0 h 00 m 39 s
- Spanish: 0 h 00 m 41 s
- Catalan: 0 h 00 m 37 s
  

# 4 
## User's Manual
To execute our submission, one must run the jar, with the json archives to be processed in the project directory, with the following commands:

- To obtain bigrams from tweets in English:

```
spark-submit --class edu.upf.BiGramsApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar en ./_output-folder/bigrams_en ./tweets/twitter-data-from-2018-eurovision-final
```

- To obtain bigrams from tweets in Spanish:

```
spark-submit --class edu.upf.BiGramsApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar es ./_output-folder/bigrams_es ./tweets/twitter-data-from-2018-eurovision-final
```

- To obtain bigrams tweets in Catalan:

```
spark-submit --class edu.upf.BiGramsApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar ca ./_output-folder/bigrams_ca ./tweets/twitter-data-from-2018-eurovision-final
```
- In general:

```
spark-submit --class edu.upf.BiGramsApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar <language> ./_output-folder/bigrams_<language> <tweets directory path>

```


### Results

Results obtained are:


#### English

 Top 1 BiGram: (5798,<this,is>)

 Top 2 BiGram: (5786,<of,the>)

 Top 3 BiGram: (5218,<in,the>)

 Top 4 BiGram: (4365,<for,the>)

 Top 5 BiGram: (4243,<the,eurovision>)

 Top 6 BiGram: (3296,<eurovision,is>)

 Top 7 BiGram: (3140,<eurovision,song>)

 Top 8 BiGram: (3056,<i,love>)

 Top 9 BiGram: (2918,<is,the>)

 Top 10 BiGram: (2669,<to,be>)
 

#### Spanish

 Top 1 BiGram: (3417,<de,la>)

 Top 2 BiGram: (2879,<#eurovision,#finaleurovision>)

 Top 3 BiGram: (2412,<que,no>)

 Top 4 BiGram: (2323,<la,canción>)

 Top 5 BiGram: (2231,<de,#eurovision>)

 Top 6 BiGram: (2182,<en,el>)

 Top 7 BiGram: (2024,<lo,que>)

 Top 8 BiGram: (1838,<a,la>)

 Top 9 BiGram: (1822,<en,la>)

 Top 10 BiGram: (1786,<en,#eurovision>)


#### Catalan


 Top 1 BiGram: (70,<de,la>)

 Top 2 BiGram: (49,<#eurovision,#finaleurovision>)

 Top 3 BiGram: (46,<#eurovision,[]>)

 Top 4 BiGram: (46,<#thevoice,[]>)

 Top 5 BiGram: (46,<[],#thevoice>)

 Top 6 BiGram: (45,<up,–>)

 Top 7 BiGram: (45,<al-barakah,🎥🎥>)

 Top 8 BiGram: (45,<#france,blew>)

 Top 9 BiGram: (45,<–,wilāyat>)

 Top 10 BiGram: (45,<[],#eurovision>)


### Benchmark

Benchmarks are done with the library java.time.LocalTime, the benchmark has been performed for the whole process using Spark locally.

Benchmark:
- English: 0 h 00 m 26 s
- Spanish: 0 h 00 m 24 s
- Catalan: 0 h 00 m 18 s

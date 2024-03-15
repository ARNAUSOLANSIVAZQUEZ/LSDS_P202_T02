This submission is for group P202-02 formed by:
- Muñoz, Mario U172951
- Solans, Arnau U161668
- Villarino, Jorge U172789

# Section 2
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
  

# Section 3
## User's Manual
To execute our submission, one must run a spark job in the emr cluster built as close as possible to the instructions provided in slides:

- To filter tweets in English:

```
jar location: s3://lsds2024.lab2.output.u161668.u172789.u172951/jars/twitter-filter-1.0-SNAPSHOT.jar

spark-submit options: --class edu.upf.TwitterLanguageFilterApp

arguments: en s3://lsds2024.lab2.output.u161668.u172789.u172951/output/benchmark/en s3://lsds2024.lab2.output.u161668.u172789.u172951/input/tweets/twitter-data-from-2018-eurovision-final
```

- To filter tweets in Spanish:

```
jar location: s3://lsds2024.lab2.output.u161668.u172789.u172951/jars/twitter-filter-1.0-SNAPSHOT.jar

spark-submit options: --class edu.upf.TwitterLanguageFilterApp

arguments: es s3://lsds2024.lab2.output.u161668.u172789.u172951/output/benchmark/es s3://lsds2024.lab2.output.u161668.u172789.u172951/input/tweets/twitter-data-from-2018-eurovision-final
```

- To filter tweets in Catalan:

```
jar location: s3://lsds2024.lab2.output.u161668.u172789.u172951/jars/twitter-filter-1.0-SNAPSHOT.jar

spark-submit options: --class edu.upf.TwitterLanguageFilterApp

arguments: ca s3://lsds2024.lab2.output.u161668.u172789.u172951/output/benchmark/ca s3://lsds2024.lab2.output.u161668.u172789.u172951/input/tweets/twitter-data-from-2018-eurovision-final
```
- In general:

```
jar location: <bucket path>/jars/twitter-filter-1.0-SNAPSHOT.jar

spark-submit options: --class edu.upf.TwitterLanguageFilterApp

arguments: <language> <bucket path>/output/benchmark/<language> <bucket path>/input/tweets/twitter-data-from-2018-eurovision-final
```


### Results

Results obtained are:
- English: 446603 tweets filtered.
- Spanish: 509435 tweets filtered
- Catalan: 4583 tweets filtered.


### Benchmark

Benchmarks are done with the metrics provided by EMR. (Time elapsed in each step)

Benchmark:
- English: 11 minutes, 36 seconds
- Spanish: 12 minutes, 56 seconds
- Catalan: 10 minutes, 54 seconds



# Section 4 
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


# Section 5 
## User's Manual
To execute our submission, one must run the jar, with the json archives to be processed in the project directory, with the following commands:

- To obtain most retweeted tweets of top 10 retweeted users:

```
spark-submit --class edu.upf.MostRetweetedApp --master local[2] target/twitter-filter-1.0-SNAPSHOT.jar ./_output-folder/most_retweeted ./tweets/twitter-data-from-2018-eurovision-final
```

### Results

Results obtained are:

#### Top 10 Retweeted users

Top retweeted user: 1
3143260474

Top retweeted user: 2
24679473

Top retweeted user: 3
15584187

Top retweeted user: 4
437025093

Top retweeted user: 5
39538010

Top retweeted user: 6
38381308

Top retweeted user: 7
739812492310896640

Top retweeted user: 8
1501434991

Top retweeted user: 9
29056256

Top retweeted user: 10
2754746065

#### Most retweeted tweets for top 10 retweeted users

Tweet: 1
RT @ManelNMusic: Así que el año pasado quedo último con un gallo y este año gana una gallina... #Eurovision https://t.co/EfvXQbb8jp

Tweet: 2
RT @PaquitaSalas: Qué guasa tiene la niña, ¿eh? #Eurovision https://t.co/Iv1yottkvQ

Tweet: 3
RT @Uznare: eurovision rules https://t.co/I8cG3D5tCh

Tweet: 4
RT @auronplay: Muy bien Alemania secuestrando a Ed Sheeran y poniéndole una peluca. #Eurovision

Tweet: 5
RT @bbceurovision: See what he did there? #Eurovision #CzechRepublic  #CZE https://t.co/DwdfXmTqXg

Tweet: 6
RT @NetflixES: Ella está al mando. Con @PaquitaSalas nada malo puede pasar, ¿no? #Eurovision https://t.co/5HeUDCqxX6

Tweet: 7
RT @LVPibai: Rodolfo Chikilicuatre, un actor disfrazado con una guitarra de plástico quedó siete puestos por encima que la ganadora de un c…

Tweet: 8
RT @ManelNMusic: Oye Israel, que esto lo hice yo antes que tu... #Eurovision #FinalEurovision https://t.co/bBnDjB9s4z

Tweet: 9
RT @elmundotoday: Puigdemont ha logrado aparecerse durante unos segundos en el vestido de la participante de Estonia y ha proclamado la ind…

Tweet: 10
RT @Eurovision: The Winner of the 2018 #Eurovision Song Contest is ISRAEL! #ESC2018 #AllAboard https://t.co/Myre7yh3YV

### Benchmark

Benchmarks are done with the library java.time.LocalTime, the benchmark has been performed for the whole process using Spark locally.

Benchmark: 0 h 00 m 52 s



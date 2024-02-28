This submission is for group P202-02 formed by:
- Muñoz, Mario U172951
- Solans, Arnau U161668
- Villarino, Jorge U172789

# User's Manual

To execute our submission, one must run the jar, with the json archives to be processed in the same directory, with the following commands:

- To filter tweets in English across all files:

```
java -cp lab1-1.0-SNAPSHOT.jar edu.upf.TwitterFilter en /tmp/output-en.txt <s3-bucket-name> Eurovision3.json Eurovision4.json Eurovision5.json Eurovision6.json Eurovision7.json Eurovision8.json Eurovision9.json Eurovision10.json
```

- To filter tweets in Spanish across all files:

```
java -cp lab1-1.0-SNAPSHOT.jar edu.upf.TwitterFilter es /tmp/output-es.txt <s3-bucket-name> Eurovision3.json Eurovision4.json Eurovision5.json Eurovision6.json Eurovision7.json Eurovision8.json Eurovision9.json Eurovision10.json
```

- To filter tweets in Catalan across all files:

```
java -cp lab1-1.0-SNAPSHOT.jar edu.upf.TwitterFilter ca /tmp/output-ca.txt <s3-bucket-name> Eurovision3.json Eurovision4.json Eurovision5.json Eurovision6.json Eurovision7.json Eurovision8.json Eurovision9.json Eurovision10.json
```
- In general:

```
java -cp lab1-1.0-SNAPSHOT.jar edu.upf.TwitterFilter <language> <output-directory>.txt <s3-bucket-name> <files-to-process>
```


# Results

Results obtained are:
- English: 446603 tweets filtered.
- Spanish: 509435 tweets filtered
- Catalan: 4583 tweets filtered.


# Benchmark

Benchmarks are done with the library java.time.LocalTime, the benchmark has been performed for the whole process and for each individual file processed.

The tables presented follow the format:

File processed || Language filtered || Number of tweets filtered || Time elapsed

Total processing time (filter + upload)


Specs for U161668:
-	RAM: MemTotal: 32516032 kB ~ 31GB
-	CPU: 12th Gen Intel(R) Core(TM) i7-12700H , cpu MHz: 892.349, cpu cores: 14

```
         ____________________________________________________
        |BENCHMARK - ENGLISH - U161668			     |
        |----------------------------------------------------|
        |Eurovision3.json || en || 24346 || 0 h 00 m 01 s    |
        |Eurovision4.json || en || 96430 || 0 h 00 m 04 s    |
        |Eurovision5.json || en || 50545 || 0 h 00 m 02 s    |
        |Eurovision6.json || en || 66596 || 0 h 00 m 03 s    |
        |Eurovision7.json || en || 39794 || 0 h 00 m 02 s    |
        |Eurovision8.json || en || 35569 || 0 h 00 m 02 s    |
        |Eurovision9.json || en || 18048 || 0 h 00 m 01 s    |
        |Eurovision10.json || en || 115275 || 0 h 00 m 08 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 02 m 40 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - SPANISH - U161668			     |
        |----------------------------------------------------|
        |Eurovision3.json || es || 23848 || 0 h 00 m 02 s    |
        |Eurovision4.json || es || 78433 || 0 h 00 m 04 s    |
        |Eurovision5.json || es || 45800 || 0 h 00 m 02 s    |
        |Eurovision6.json || es || 71677 || 0 h 00 m 03 s    |
        |Eurovision7.json || es || 54969 || 0 h 00 m 02 s    |
        |Eurovision8.json || es || 38805 || 0 h 00 m 02 s    |
        |Eurovision9.json || es || 26244 || 0 h 00 m 01 s    |
        |Eurovision10.json || es || 169659 || 0 h 00 m 09 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 03 m 53 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - CATALAN - U161668			     |
        |----------------------------------------------------|
        |Eurovision3.json || ca || 242 || 0 h 00 m 01 s      |
        |Eurovision4.json || ca || 983 || 0 h 00 m 05 s      |
        |Eurovision5.json || ca || 581 || 0 h 00 m 02 s      |
        |Eurovision6.json || ca || 717 || 0 h 00 m 03 s      |
        |Eurovision7.json || ca || 398 || 0 h 00 m 02 s      |
        |Eurovision8.json || ca || 404 || 0 h 00 m 02 s      |
        |Eurovision9.json || ca || 193 || 0 h 00 m 01 s      |
        |Eurovision10.json || ca || 1065 || 0 h 00 m 09 s    |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 00 m 33 s|
        |____________________________________________________|
```

Specs for U172951:
-	RAM: MemTotal: 16GB
-	CPU: 12th Gen Intel(R) Core(TM) i7-1255U , cpu MHz: 1700, cpu cores: 10

```
         ____________________________________________________
        |BENCHMARK - ENGLISH - U172951			     |
        |----------------------------------------------------|
        |Eurovision3.json || en || 24346 || 0 h 00 m 03 s    |
        |Eurovision4.json || en || 96430 || 0 h 00 m 09 s    |
        |Eurovision5.json || en || 50545 || 0 h 00 m 05 s    |
        |Eurovision6.json || en || 66596 || 0 h 00 m 07 s    |
        |Eurovision7.json || en || 39794 || 0 h 00 m 05 s    |
        |Eurovision8.json || en || 35569 || 0 h 00 m 04 s    |
        |Eurovision9.json || en || 18048 || 0 h 00 m 02 s    |
        |Eurovision10.json || en || 115275 || 0 h 00 m 18 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 02 m 44 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - SPANISH - U172951			     |
        |----------------------------------------------------|
        |Eurovision3.json || es || 23848 || 0 h 00 m 03 s    |
        |Eurovision4.json || es || 78433 || 0 h 00 m 08 s    |
        |Eurovision5.json || es || 45800 || 0 h 00 m 05 s    |
        |Eurovision6.json || es || 71677 || 0 h 00 m 08 s    |
        |Eurovision7.json || es || 54969 || 0 h 00 m 04 s    |
        |Eurovision8.json || es || 38805 || 0 h 00 m 03 s    |
        |Eurovision9.json || es || 26244 || 0 h 00 m 02 s    |
        |Eurovision10.json || es || 169659 || 0 h 00 m 16 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 03 m 47 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - CATALAN - U172951			     |
        |----------------------------------------------------|
        |Eurovision3.json || ca || 242 || 0 h 00 m 02 s      |
        |Eurovision4.json || ca || 983 || 0 h 00 m 07 s      |
        |Eurovision5.json || ca || 581 || 0 h 00 m 04 s      |
        |Eurovision6.json || ca || 717 || 0 h 00 m 06 s      |
        |Eurovision7.json || ca || 398 || 0 h 00 m 04 s      |
        |Eurovision8.json || ca || 404 || 0 h 00 m 03 s      |
        |Eurovision9.json || ca || 193 || 0 h 00 m 01 s      |
        |Eurovision10.json || ca || 1065 || 0 h 00 m 13 s    |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 00 m 48 s|
        |____________________________________________________|
```

Specs for U172789:
-	RAM: MemTotal: 2x4GB DDR4 3200Mhz
-	CPU: 11th Gen Intel Core i5-1135G7 , frequency: 2.4 Mhz, cpu cores: 4, threads: 8

```
         ____________________________________________________
        |BENCHMARK - ENGLISH - U172789			     |
        |----------------------------------------------------|
        |Eurovision3.json || en || 24346 || 0 h 00 m 02 s    |
        |Eurovision4.json || en || 96430 || 0 h 00 m 06 s    |
        |Eurovision5.json || en || 50545 || 0 h 00 m 03 s    |
        |Eurovision6.json || en || 66596 || 0 h 00 m 05 s    |
        |Eurovision7.json || en || 39794 || 0 h 00 m 03 s    |
        |Eurovision8.json || en || 35569 || 0 h 00 m 03 s    |
        |Eurovision9.json || en || 18048 || 0 h 00 m 02 s    |
        |Eurovision10.json || en || 115275 || 0 h 00 m 13 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 06 m 28 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - SPANISH - U172789			     |
        |----------------------------------------------------|
        |Eurovision3.json || es || 23848 || 0 h 00 m 02 s    |
        |Eurovision4.json || es || 78433 || 0 h 00 m 06 s    |
        |Eurovision5.json || es || 45800 || 0 h 00 m 03 s    |
        |Eurovision6.json || es || 71677 || 0 h 00 m 05 s    |
        |Eurovision7.json || es || 54969 || 0 h 00 m 04 s    |
        |Eurovision8.json || es || 38805 || 0 h 00 m 03 s    |
        |Eurovision9.json || es || 26244 || 0 h 00 m 02 s    |
        |Eurovision10.json || es || 169659 || 0 h 00 m 14 s  |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 12 m 08 s|
        |____________________________________________________|
         ____________________________________________________
        |BENCHMARK - CATALAN - U172789			     |
        |----------------------------------------------------|
        |Eurovision3.json || ca || 242 || 0 h 00 m 02 s      |
        |Eurovision4.json || ca || 983 || 0 h 00 m 06 s      |
        |Eurovision5.json || ca || 581 || 0 h 00 m 03 s      |
        |Eurovision6.json || ca || 717 || 0 h 00 m 05 s      |
        |Eurovision7.json || ca || 398 || 0 h 00 m 03 s      |
        |Eurovision8.json || ca || 404 || 0 h 00 m 03 s      |
        |Eurovision9.json || ca || 193 || 0 h 00 m 02 s      |
        |Eurovision10.json || ca || 1065 || 0 h 00 m 13 s    |
        |----------------------------------------------------|
        |Total time elapsed Twitter Filtering:  0 h 00 m 41 s|
        |____________________________________________________|
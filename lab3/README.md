This submission is for group P202-02 formed by:
- Muñoz, Mario U172951
- Solans, Arnau U161668
- Villarino, Jorge U172789

# EXECUTION GUIDELINES

To execute this code you need have apache spark already installed.

[How to install Apache Spark](https://www.linkedin.com/pulse/how-install-apache-spark-version-350-ubuntu-2304-youssef-kabir-e5lqe/)


## 2 Running example application locally


```bash
mvn clean validate compile package
```

```bash
spark-submit --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///log4j.properties --class edu.upf.MastodonStreamingExample target/lab3-mastodon-1.0-SNAPSHOT.jar src/main/resources/map.tsv
```

if you’re having troubles running the application locally, with errors similar to ”cannot find method
methodName()”, it might be due to jar conflicts between spark and the dependencies of your applica-
tion. Find your Spark installation, move to the jar directory (in downloaded spark, the jars directory;
in brew spark, the libexec/jars directory, etc.) and remove the following files: gson-2.2.4.jar (or
equivalent versions), okhttp-3.12.12.jar (or equivalent versions), okio-1.14.0.jar (or equivalent
versions)

## 3 Stateless: joining a static RDD with a real time stream

```bash
mvn clean validate compile package
```

```bash
spark-submit --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///log4j.properties --class edu.upf.MastodonStateless target/lab3-mastodon-1.0-SNAPSHOT.jar src/main/resources/map.tsv
```




## 4 Spark Stateful transformations with windows

```bash
mvn clean validate compile package
```

```bash
spark-submit --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///log4j.properties --class edu.upf.MastodonWindows target/lab3-mastodon-1.0-SNAPSHOT.jar src/main/resources/map.tsv
```



## 5 Spark Stateful transformations with state variables

```bash
mvn clean validate compile package
```

```bash
spark-submit --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=file:///log4j.properties --class edu.upf.MastodonWithState target/lab3-mastodon-1.0-SNAPSHOT.jar en
```





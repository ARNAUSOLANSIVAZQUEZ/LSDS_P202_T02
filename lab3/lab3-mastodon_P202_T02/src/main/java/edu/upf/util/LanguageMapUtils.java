package edu.upf.util;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import scala.Tuple2;

public class LanguageMapUtils {

    public static JavaPairRDD<String, String> buildLanguageMap(JavaRDD<String> lines) {
    
        // Define a JavaPairRDD to store language mappings
        
        JavaPairRDD<String, String> languageMapping = lines
                
                // Step 1: Split each line into an array of strings using the tab character as delimiter
                .map(line -> line.split("\t"))
                // Step 2: Transform each array into a key-value pair (Tuple2) where the key is the second element
                // and the value is the third element from the array
                .mapToPair(fields -> new Tuple2<String,String>(fields[1], fields[2]))
                // Step 3: Filter out any key-value pairs where the key is an empty string
                .filter(fields -> !(fields._1.equals("")))
                // Step 4: Remove duplicate key-value pairs
                .distinct();
    
        // Print the count of unique language mappings
        System.out.print(languageMapping.count());
        
        // Return the resulting language mapping RDD
        return languageMapping;
    }
}




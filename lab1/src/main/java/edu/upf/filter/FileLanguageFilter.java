package edu.upf.filter;

import edu.upf.parser.SimplifiedTweet;
import jdk.vm.ci.meta.Local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;


public class FileLanguageFilter implements LanguageFilter {
    private String inputFile;
    private String outputFile;

    // Constructor of FileLanguageFilter, takes input file path and output file path as parameters
    public FileLanguageFilter(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    // Implementation of filterLanguage method
    @Override
    public void filterLanguage(String language) throws IOException {
        int tweetcount = 0;
        LocalTime filterStart = LocalTime.now();
        // We use a reader buffer to read lines from input file and a writer buffer to write to the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Each line is parsed into a optional SimplifiedTweet, using the fromJson method of SimplifiedTweet class
                Optional<SimplifiedTweet> optionalTweet = SimplifiedTweet.fromJson(line);
                // Check if a tweet is present in the line
                if (optionalTweet.isPresent()) {
                    SimplifiedTweet tweet = optionalTweet.get();
                    // Check if the tweet language matchs the language passed as parameter
                    if (tweet.getLanguage().equals(language)) {
                        writer.write(line);
                        writer.newLine();
                        tweetcount += 1;
                    }
                }
            }
            // If an error occur during the reading/writing process, throws an exception
        } catch (IOException e) {
            throw new IOException("Error reading/writing files", e);
        }
        LocalTime filterEnd = LocalTime.now();
        Duration filterElapsed = Duration.between(filterStart, filterEnd);
        long secondsElapsedFilter = filterElapsed.getSeconds();
        secondsElapsedFilter = Math.abs(secondsElapsedFilter);
        System.out.println("\nFile processed: "+ inputFile +"\nLanguage filtered: " +language + "\nNumber of tweets filtered: " + tweetcount);
        System.out.println(String.format("Time elapsed: %d h %02d m %02d s\n", secondsElapsedFilter / 3600, (secondsElapsedFilter % 3600) / 60, (secondsElapsedFilter % 60)));
    }
}

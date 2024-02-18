package edu.upf;

import edu.upf.filter.FileLanguageFilter;
import edu.upf.uploader.S3Uploader;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TwitterFilter {
    public static void main( String[] args ) throws IOException {
        LocalTime start = LocalTime.now();
        List<String> argsList = Arrays.asList(args);
        String language = argsList.get(0);
        String outputFile = argsList.get(1);
        String bucket = argsList.get(2);
        System.out.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);
        for(String inputFile: argsList.subList(3, argsList.size())) {
            System.out.println("Processing: " + inputFile);
            final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
            filter.filterLanguage(language);
        }
        System.out.println("Uploading processed file.");
        final S3Uploader uploader = new S3Uploader(bucket, "tweets", "default");
        uploader.upload(Arrays.asList(outputFile));
        LocalTime end = LocalTime.now();
        Duration elapsed = Duration.between(start, end);
        long secondsElapsed = elapsed.getSeconds();
        secondsElapsed = Math.abs(secondsElapsed);
        System.out.println(String.format("\n Total time elapsed Twitter Filtering:  %d h %02d m %02d s" , secondsElapsed / 3600, (secondsElapsed % 3600) / 60, (secondsElapsed % 60)));

    }
}

package edu.upf;

import edu.upf.model.ExtendedSimplifiedTweet;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class BiGram implements Serializable {
    private String element1;
    private String element2;

    // Constructor
    public BiGram(String element1, String element2){
        this.element1 = element1;
        this.element2 = element2;
    }

    // Method to transform list of strings into list of bigrams
    public static List<BiGram> fromExtendedSimplifiedTweet(ExtendedSimplifiedTweet tweet){
        List<BiGram> bigrams = new ArrayList<BiGram>();
        List<String> words = Arrays.stream(tweet.getText().split(" ")).map(BiGram::normalize).collect(Collectors.toList());
        for(int i = 0; i < words.size() - 1; i++){
            if(!words.get(i).isEmpty() && !words.get(i + 1).isEmpty()){
                bigrams.add(new BiGram(words.get(i), words.get(i+1)));
            }
        }
        return bigrams;
    }

    // Method to normalize words given the body of tweets
    private static String normalize(String word){
        return word.trim().toLowerCase();
    }

    // Override comparator
    @Override
    public boolean equals(Object obj) {
        if((obj == null) || (obj.getClass() != getClass())){
            return (false);
        }
        BiGram bg = (BiGram) obj;
        return ((this.element1.equals(bg.element1) &&  this.element2.equals(bg.element2))
                ||(this.element2.equals(bg.element1) && this.element1.equals(bg.element2)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(element1, element2);
    }
    // Override to string method
    @Override
    public String toString() {
        return ("<" + element1 + "," + element2 + ">");
    }
}

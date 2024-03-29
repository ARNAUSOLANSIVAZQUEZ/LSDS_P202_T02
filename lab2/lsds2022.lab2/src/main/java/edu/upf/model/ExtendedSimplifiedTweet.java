package edu.upf.model;

import java.io.Serializable;
import java.util.Optional;
import com.google.gson.Gson;
public class ExtendedSimplifiedTweet implements Serializable {
    private final long tweetId; // the id of the tweet (’id’)
    private final String text; // the content of the tweet (’text’)
    private final long userId; // the user id (’user->id’)
    private final String userName; // the user name (’user’->’name’)
    private final long followersCount; // the number of followers (’user’->’followers_count’)
    private final String language; // the language of a tweet (’lang’)
    private final boolean isRetweeted; // is it a retweet? (the object ’retweeted_status’ exists?)
    private final Long retweetedUserId; // [if retweeted] (’retweeted_status’->’user’->’id’)
    private final Long retweetedTweetId; // [if retweeted] (’retweeted_status’->’id’)
    private final long timestampMs; // seconds from epoch (’timestamp_ms’)

    public ExtendedSimplifiedTweet(long tweetId, String text, long userId, String userName,
                                   long followersCount, String language, boolean isRetweeted,
                                   long retweetedUserId, long retweetedTweetId, long timestampMs) {
        // Initialize ExtendedSimplifiedTweet
        this.tweetId = tweetId;
        this.text = text;
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
        this.language = language;
        this.isRetweeted = isRetweeted;
        this.retweetedUserId = retweetedUserId;
        this.retweetedTweetId = retweetedTweetId;
        this.timestampMs = timestampMs;
    }

    /**
     * Returns a {@link ExtendedSimplifiedTweet} from a JSON String.
     * If parsing fails, for any reason, return an {@link Optional#empty()}
     *
     * @param jsonStr
     * @return an {@link Optional} of a {@link ExtendedSimplifiedTweet}
     */
    public static Optional<ExtendedSimplifiedTweet> fromJson(String jsonStr) {

        try {
            // Parse JSON string into Tweet object with GSon
            Gson gson = new Gson();
            ExtendedSimplifiedTweet.Tweet tweet = gson.fromJson(jsonStr, ExtendedSimplifiedTweet.Tweet.class);
            // Check if tweet is null before going further
            if(tweet != null) {
                boolean isRetweeted = false;
                Long retweet_tweet_id = 0L;
                Long retweet_user_id = 0L;
                // Check necessary fields for a tweet
                if(tweet.id != 0 && tweet.text != null &&
                        tweet.user != null && tweet.user.id != 0 && tweet.user.name != null  &&
                        tweet.lang != null  && tweet.timestamp_ms != 0){
                    // Check if tweet is retweeted
                    if(tweet.retweeted_status != null){
                        isRetweeted = true;
                        retweet_user_id = tweet.retweeted_status.user.id;
                        retweet_tweet_id = tweet.retweeted_status.id;
                    }
                    return Optional.of(
                            new ExtendedSimplifiedTweet(
                                    tweet.id,
                                    tweet.text,
                                    tweet.user.id,
                                    tweet.user.name,
                                    tweet.user.followers_count,
                                    tweet.lang,
                                    isRetweeted,
                                    retweet_user_id,
                                    retweet_tweet_id,
                                    tweet.timestamp_ms)
                    );
                }
            }
            return Optional.empty(); // Return empty optional if any mandatory field is missing
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // JSON structure of tweet
    private static class Tweet {
        long id;
        String text;
        ExtendedSimplifiedTweet.User user;
        String lang;
        ExtendedSimplifiedTweet.retweetedStatus retweeted_status;
        long timestamp_ms;
    }

    // JSON structure of a user
    private static class User {
        long id;
        String name;
        long followers_count;
    }
    // JSON structure of retweet status
    private static class retweetedStatus{
        ExtendedSimplifiedTweet.User user;
        long id;
    }


    // Getter methods
    public long getTweetId() {
        return tweetId;
    }

    public String getText() {
        return text;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
    public long getUserFollowersCount() {
        return followersCount;
    }

    public String getLanguage() {
        return language;
    }

    public boolean getIsRetweeted(){
        return isRetweeted;
    }

    public long getRetweetedUserId(){
        return retweetedUserId;
    }

    public long getRetweetedTweetId(){
        return retweetedTweetId;
    }

    public long getTimestampMs() {
        return timestampMs;
    }
    @Override
    public String toString() {
        // Overriding how SimplifiedTweets are printed in console or the output file
        // The following line produces valid JSON as output
        return new Gson().toJson(this);
    }
}
package edu.upf.model;

import java.util.Optional;
import com.google.gson.Gson;

public class SimplifiedTweet {


  private final long tweetId;              // the id of the tweet ('id')
  private final String text;              // the content of the tweet ('text')
  private final long userId;              // the user id ('user->id')
  private final String userName;          // the user name ('user'->'name')
  private final String language;          // the language of a tweet ('lang')
  private final long timestampMs;          // seconduserIds from epoch ('timestamp_ms')

  public SimplifiedTweet(long tweetId, String text, long userId, String userName,
                         String language, long timestampMs) {
    // Initialize SimplifiedTweet
    this.tweetId = tweetId;
    this.text = text;
    this.userId = userId;
    this.userName = userName;
    this.language = language;
    this.timestampMs = timestampMs;

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

  public String getLanguage() {
    return language;
  }

  public long getTimestampMs() {
    return timestampMs;
  }

  /**
   * Returns a {@link SimplifiedTweet} from a JSON String.
   * If parsing fails, for any reason, return an {@link Optional#empty()}
   *
   * @param jsonStr
   * @return an {@link Optional} of a {@link SimplifiedTweet}
   */
  public static Optional<SimplifiedTweet> fromJson(String jsonStr) {

    try {
      // Parse JSON string into Tweet object with GSon
      Gson gson = new Gson();
      Tweet tweet = gson.fromJson(jsonStr, Tweet.class);

      // Check if all fields of tweet object are present
      if (tweet != null && tweet.id != 0 && tweet.text != null && tweet.user != null && tweet.user.id != 0
              && tweet.user.name != null && tweet.lang != null && tweet.timestamp_ms != 0) {
        return Optional.of(new SimplifiedTweet(tweet.id, tweet.text, tweet.user.id, tweet.user.name, tweet.lang,
                tweet.timestamp_ms));
      } else {
        return Optional.empty(); // Return empty optional if any mandatory field is missing
      }
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  // JSON structure of tweet
  private static class Tweet {
    long id;
    String text;
    User user;
    String lang;
    long timestamp_ms;
  }

  // JSON structure of a user
  private static class User {
    long id;
    String name;
  }


  @Override
  public String toString() {
    // Overriding how SimplifiedTweets are printed in console or the output file
    // The following line produces valid JSON as output
    return new Gson().toJson(this);
  }
}


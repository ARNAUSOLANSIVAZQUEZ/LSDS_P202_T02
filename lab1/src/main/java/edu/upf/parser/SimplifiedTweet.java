package edu.upf.parser;

import java.util.Optional;
import com.google.gson.Gson;

public class SimplifiedTweet {

  // All classes use the same instance
  private static JsonParser parser = new JsonParser();



  private final long tweetId;			  // the id of the tweet ('id')
  private final String text;  		      // the content of the tweet ('text')
  private final long userId;			  // the user id ('user->id')
  private final String userName;		  // the user name ('user'->'name')
  private final String language;          // the language of a tweet ('lang')
  private final long timestampMs;		  // seconduserIds from epoch ('timestamp_ms')

  public SimplifiedTweet(long tweetId, String text, long userId, String userName,
                         String language, long timestampMs) {
    this.tweetId = tweetId;
    this.text = text;
    this.userId = userId;
    this.userName = userName;
    this.language = language;
    this.timestampMs = timestampMs;

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
      Gson gson = new Gson();
      TweetObject tweetObject = gson.fromJson(jsonStr, TweetObject.class);
      if (tweetObject != null && tweetObject.getId() != null &&
              tweetObject.getUser() != null && tweetObject.getUser().getId() != null) {
        return Optional.of(new SimplifiedTweet(
                tweetObject.getId(),
                tweetObject.getText(),
                tweetObject.getUser().getId(),
                tweetObject.getUser().getName(),
                tweetObject.getLang(),
                tweetObject.getTimestampMs()
        ));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }


  @Override
  public String toString() {
    return "";
  }
  //Lo de abajo es lo que pone en el código de la práctica no se cual de los dos es el bueno.
  // Overriding how SimplifiedTweets are printed in console or the output file
  // The following line produces valid JSON as output
  //return new Gson().toJson(this);
  //}
}

import java.util.*;
import java.io.*;

// This class simulates a Twitter platform. Its functionalities include taking in a collection
// of tweets and allowing users to manage them, as well as being able to analyze aspects of the
// tweets and present the data generated.  
public class TwitterMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("tweets.txt")); // Make Scanner over tweet file
        List<String> tweets = new ArrayList<>();
        while (input.hasNextLine()) { // Add each tweet in file to List
            tweets.add(input.nextLine());
        }

        TweetBot bot = new TweetBot(tweets); // Create TweetBot object with list of tweets
        TwitterTrends trends = new TwitterTrends(bot); // Create TwitterTrends object

        // Calls getMostFrequentWord
        System.out.println("Most frequently tweeted word: ");
        System.out.println("> " + trends.getMostFrequentWord());
        System.out.println();

        // Calls containsFlag
        System.out.println("Your Twitter feed: ");
        System.out.println();
        for(int i = 0; i < bot.numTweets(); i++){ // Iterates through tweets
            String currentTweet = bot.nextTweet();
            System.out.println("- " + currentTweet);
            if(trends.containsFlag(currentTweet)){ // Checks if tweet contains sensitive word
                System.out.println("  *This Tweet may be misleading. Learn the facts about COVID-19:");
                System.out.println("  https://www.cdc.gov/");
            }
            System.out.println();
        }
    }
}
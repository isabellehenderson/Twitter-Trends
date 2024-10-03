import java.util.*;
import java.io.*;

// This class uses the Twitter Bot to analyze a collection of tweets. It can take in the collection
// as a whole or individual tweets to draw conclusions about the collection or individual items.
public class TwitterTrends {
    private TweetBot bot; 

    // Initializes this TwitterTrends object with the given TweetBot for analysis.
    // Parameters:
    // - TweetBot bot: The TweetBot instance to analyze tweets from.
    public TwitterTrends(TweetBot bot){
        this.bot = bot;
    }

    // Return the most frequent word among all tweets in the TweetBot we are analyzing.
    // Returns:
    // - String: the word that appears most frequently across the tweet list
    public String getMostFrequentWord(){
        Map<String, Integer> counts = new HashMap<>();

        for(int i = 0; i < bot.numTweets(); i++){
            String tweet = bot.nextTweet();

            Scanner scan = new Scanner(tweet);
            while(scan.hasNext()){
                String token = scan.next().toLowerCase();
                if(!counts.containsKey(token)){ // Checks if map already contains String key
                    counts.put(token, 1);
                } else {
                    counts.put(token, counts.get(token) + 1);
                }
            }
        } 
        
        String mostFrequent = "";
        int maxCount = 0;
        for(String key : counts.keySet()){ // Iterates through each unique word
            int count = counts.get(key);
            if(count > maxCount){ // Checks if count of word is greater than current max
                maxCount = count;
                mostFrequent = key;
            }
        }
        
        return mostFrequent;
    }

    // Searches for whether or not each tweet in the tweet list contains a sensitive word
    // and returns whether or not it is flagged.
    // Parameters:
    // - String currentTweet: the tweet being checked for sensitive words
    // Returns:
    // - boolean: returns true if tweet contains a sensitive word, false if not
    public boolean containsFlag(String currentTweet){
        List<String> sensitive = new ArrayList<>();
        sensitive.add("COVID-19");
        sensitive.add("Mask");
        sensitive.add("Vaccine");
        sensitive.add("Pandemic");

        Scanner scan = new Scanner(currentTweet);
        while(scan.hasNext()){
            String token = scan.next().toLowerCase();
            for(int j = 0; j < sensitive.size(); j++){ // Iterates through sensitive words list
                if(token.equals(sensitive.get(j).toLowerCase())){ // Checks if word is sensitive
                    return true;
                }
            }  
        }
        return false; // Returns false if no words from the sensitive list are found
    }
}
import java.util.*;
import java.io.*;

// This class is a Twitter Bot that maintains a collection of tweets. It can modify the collection
// and also return information about it while cycling through the tweets.
public class TweetBot {
    private List<String> tweets;
    private int currentIndex;

    // Constructs a new TweetBot object with the provided list of tweets
    // Parameters:
    // - List<String> tweets: the list of tweets being used to manage
    // Exceptions:
    // - tweets.size() < 1: throws an IllegalArgumentException if tweet list size is less than 1
    public TweetBot(List<String> tweets){
        if(tweets.size() < 1){
            throw new IllegalArgumentException();
        }

        this.tweets = new ArrayList<>(tweets);
    } 
    
    // Returns the number of tweets currently in the tweet list.
    // Returns:
    // - int: the size of the tweet list
    public int numTweets(){
        return tweets.size();
    } 

    // Adds the given tweet to the end of the tweet list.
    // Parameters:
    // - String tweet: the tweet being added to the tweet list
    public void addTweet(String tweet){
        this.tweets.add(tweet);
    }

    // Returns the next tweet from the tweet list. If all the tweets in the list are exhausted,
    // cycle around to the start of the list.
    // Returns:
    // - String: the next tweet in the tweet list
    public String nextTweet() {
        if (currentIndex == tweets.size()) { // Checks if positioned outside end of tweets
            this.currentIndex = 0; // Updates currentIndex to point to index 0
        }

        String currentTweet = tweets.get(currentIndex); // Get the current tweet
        this.currentIndex++; // Increment - shifts pointer to next tweet

        return currentTweet;
    }

    // Removes the given tweet from the tweet list if it is present.
    // Parameters:
    // - String tweet: the tweet being removed from the tweet list
    public void removeTweet(String tweet){
        if(tweets.contains(tweet)){
            for(int i = 0; i < tweets.size(); i++){
                if(tweets.get(i).equals(tweet)){
                    if(currentIndex > i){ // Checks if removing from before the pointer
                        this.currentIndex--; // Decrement - shifts pointer back to correct tweet
                        this.tweets.remove(i);
                    } else{
                        this.tweets.remove(i);
                    }
                }
            }
        }       
    }

    // Resets the pointer of the bot such that the next calls to cycle through the tweets
    // start at the beginning.
    public void reset(){
        this.currentIndex = 0;
    }

}   
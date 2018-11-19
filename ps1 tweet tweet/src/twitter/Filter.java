/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        final String regexUsername = "^(\\w){1,15}$";
        
        if (!username.matches(regexUsername)) {
        	throw new RuntimeException("non-valid Twitter username");
        }
        
        // reference: https://stackoverflow.com/a/18508956
        // use functional programming techniques
        return tweets
        		.stream()
        		.filter(tweet -> tweet.getAuthor() == username)
        		.collect(Collectors.toList());
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {	
        return tweets
        		.stream()
        		.filter(tweet -> checkIfTweetInTimeSpan(tweet, timespan))
        		.collect(Collectors.toList());
    }

    /**
     * Check if tweet is within given timespan(start/end endpoint included)
     * @param tweet
     * @param timespan
     * @return boolean, true if tweet is within given timespan, and vice versa
     */
    private static Boolean checkIfTweetInTimeSpan(Tweet tweet, Timespan timespan) {
    	final Instant tweetTimeStamp = tweet.getTimestamp();
    	final Instant timespanStart = timespan.getStart();
    	final Instant timespanEnd = timespan.getEnd();
    	Boolean result;
    	
    	if (tweetTimeStamp.compareTo(timespanStart) > 0 && tweetTimeStamp.compareTo(timespanEnd) < 0) {
        	// tweetTimeStamp is within timespan
    		result = true;
    	} else if (tweetTimeStamp.equals(timespanStart) || tweetTimeStamp.equals(timespanEnd)) {
    		// tweetTimeStamp may be at timespan start or timespan end
    		result = true;
    	} else {
    		// tweetTimeStamp is not within timespan
    		result = false;
    	}
    	
    	return result;
    }
    
    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        Set<String> textSet = new HashSet<>();
        
        // transfer words into set for further comparison usage
        for (String word : words) {
        	textSet.add(word.toLowerCase());
        }
        
        return tweets
        		.stream()
        		.filter(tweet -> checkIfTweetContainsText(tweet, textSet))
        		.collect(Collectors.toList());
    }
    
    /**
     * check if tweet message contains text in given textSet
     * @param tweet
     * @param textSet
     * @return boolean, true if tweet message contains text in given textSet, and vice versa
     */
    private static Boolean checkIfTweetContainsText(Tweet tweet, Set<String> textSet) {
    	Boolean result = false;
    	final String tweetMessage = tweet.getText().toLowerCase();
    	final String[] tweetMessageWordArray = tweetMessage.split(" ");
    	
    	for (String tweetMessageWord : tweetMessageWordArray) {
    		if (textSet.contains(tweetMessageWord)) {
    			result = true;
    			break;
    		}
    	}
    	
    	return result;
    }
}

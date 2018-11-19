/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	final Tweet tweetWithLargestTimeStamp = Collections.max(tweets, (a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
    	final Tweet tweetWithSmallestTimeStamp = Collections.min(tweets, (a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        final Instant tweetsTimeStampStart = tweetWithSmallestTimeStamp.getTimestamp();
        final Instant tweetsTimeStampEnd = tweetWithLargestTimeStamp.getTimestamp();
        
        return new Timespan(tweetsTimeStampStart, tweetsTimeStampEnd);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	// regular expression for twitter usernames
    	// reference: https://help.twitter.com/en/managing-your-account/twitter-username-rules
    	final String regexUsernameMention = "^@(\\w){1,15}$";
    	final Set<String> userNames = new HashSet<>();
    	Set<String> mensionedUsers = new HashSet<>();
    	String[] tweetMessageWordArray;
    	int tweetMessageWordArrayLength;
    
    	// add authors to a set for further searching usage
    	for (int i = 0; i < tweets.size(); i++) {
    		userNames.add(tweets.get(i).getAuthor());
    	}
    	
    	// search for tweets and collect all mentioned users
    	for (int i = 0; i < tweets.size(); i++) {
    		tweetMessageWordArray = tweets.get(i).getText().split(" ");
    		tweetMessageWordArrayLength = tweetMessageWordArray.length;
    		
    		if (tweetMessageWordArrayLength > 0) {
    			for (int j = 0; j < tweetMessageWordArrayLength; j++) {
    				// check if username-mention is legal and belongs to userNames
    				if (tweetMessageWordArray[j].matches(regexUsernameMention) && 
    					userNames.contains(tweetMessageWordArray[j])) {
    					mensionedUsers.add(tweetMessageWordArray[j]);
    				}
    			}
    		}
    	}
    	
    	return mensionedUsers;
    }

}

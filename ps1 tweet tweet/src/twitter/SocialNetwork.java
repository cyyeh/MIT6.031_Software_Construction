/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import twitter.Filter;
import twitter.Extract;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	final Set<String> authors = new HashSet<>();
    	List<Tweet> tweetsWithSameAuthor;
    	Set<String> tweetfollowers;
    	Map<String, Set<String>> followersGraph = new HashMap<>();
    	
    	for (Tweet tweet : tweets) {
    		authors.add(tweet.getAuthor().toLowerCase());
    	}
    	    	
    	for (String author : authors) {
    		tweetsWithSameAuthor = Filter.writtenBy(tweets, author);
    		tweetfollowers = Extract.getMentionedUsers(tweetsWithSameAuthor)
    							.stream()
    							.filter(userName -> !userName.equals(author))
    							.collect(Collectors.toSet());
    		
    		if (!tweetfollowers.isEmpty()) {
    			followersGraph.put(author, tweetfollowers);    			
    		} else {
    			followersGraph.put(author, new HashSet<>());
    		}
    	}
    	
    	return followersGraph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> influencingMap = new TreeMap<>();
        
        if (!followsGraph.isEmpty()) {
        	// reference: https://stackoverflow.com/a/35558955
        	followsGraph.forEach((user, followers) -> {
        		followers.forEach(followee -> {
        			if (influencingMap.containsKey(followee)) {
        				influencingMap.put(followee, influencingMap.get(followee).intValue() + 1);
        			} else {
        				influencingMap.put(followee, 1);
        			}
        		});
        		
        		// put empty-follower users in map
        		if (!influencingMap.containsKey(user)) {
        			influencingMap.put(user, 0);
        		}
        	});
        }
        
        return influencingMap.entrySet().stream()
            	.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            	.map(user -> user.getKey())
            	.collect(Collectors.toList());
    }

}

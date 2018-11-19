/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {
	
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T14:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to TALK about rivest so much? @alyssa", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype @alyssa", d2);
    private static final Tweet tweet3 = new Tweet(3, "john", "@ALYSSA again: rivest talk in 30 minutes #hype", d3);
    private static final Tweet tweet4 = new Tweet(4, "john", "@bbitdiddle again: rivest talk in 30 minutes #hype", d4);
    private static final Tweet tweet5 = new Tweet(5, "jam", "@bbitdiddle again: rivest talk in 30 minutes #hype", d5);

    
	/*
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    */
    
	/*
	 * Testing Strategy
	 * 
	 * Partition the input as follows:
	 * 1. empty followers
	 * 2. followers exist(case insensitive)
	 */
	
	// covers empty followers
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    // covers followers exist(case insensitive)
    @Test
    public void testGuessFollowsGraphNonEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        
        assertTrue("expected non-empty graph", !followsGraph.isEmpty());
        assertEquals("expected empty element set", 0, followsGraph.get("alyssa").size());
        assertEquals("expected single element set", 1, followsGraph.get("bbitdiddle").size());
        assertEquals("expected two element set", 2, followsGraph.get("john").size());
    }
    
    /*
     * Testing Strategy
     * 
     * Partition the input as follows:
     * 1. followsGraph empty
     * 2. followsGraph with different follower count
     * 3. followsGraph with some having the same follower count
     */
    
    
    // covers followsGraph empty
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    // covers followsGraph with different follower count
    @Test
    public void testInfluencersWithDifferentFollowerCount() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected non empty list", !influencers.isEmpty());
        assertEquals("biggest influencer", influencers.get(0), "alyssa");
        assertEquals("smallest influencer", influencers.get(influencers.size() - 1), "john");
    }
    
    // covers followsGraph with some having the same follower count
    @Test
    public void testInfluencersWithSameFollowerCount() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected non empty list", !influencers.isEmpty());
        assertTrue("biggest influencer", 
	        		influencers.get(0).equals("alyssa") || 
	        		influencers.get(0).equals("bbitdiddle"));
        assertTrue("smallest influencer", 
        			influencers.get(influencers.size() - 1).equals("john") || 
        			influencers.get(influencers.size() - 1).equals("jam"));
    }
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}

/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.sql.Time;
import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "jimmy", "hello world chihyu.jimmy@gmail.com", d3);
    private static final Tweet tweet4 = new Tweet(4, "jam", "hello @jimmy", d3);
    private static final Tweet tweet5 = new Tweet(5, "jerry", "hello @JERRY", d3);
    
    /*
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    */
    
	/*
	 * Testing Strategy
	 * 
	 * Partition the inputs as follows:
	 * 1. time stamp of tweet1 = time stamp of tweet 2
	 * 2. time stamp of tweet1 < or > time stamp of tweet 2
	 */
    
    @Test
    public void testGetTimespanTwoTweets() {
        // d1 < d2
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
    	// d2 = d3
    	Timespan timespan2 = Extract.getTimespan(Arrays.asList(tweet2, tweet3));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        assertEquals("expected start", d2, timespan2.getStart());
        assertEquals("expected end", d2, timespan2.getEnd());
        assertEquals("expected start", d3, timespan2.getStart());
        assertEquals("expected end", d3, timespan2.getEnd());
    }
    
	/*
	 * Testing Strategy
	 * 
	 * Partition the inputs as follows:
	 * 1. [x] username-mention in tweet messages
	 * 2. [v] username-mention in tweet messages [x] legal
	 * 3. [v] username-mention in tweet messages [v] legal [v] not belong to any author in tweets
	 * 4. [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(author himself)
	 * 5. [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(other)
	 * 6. [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(username-mention in uppercase)
	 */
    
    // covers [x] username-mention in tweet messages
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    // covers [v] username-mention in tweet messages [x] legal
    @Test
    public void testGetMentionedUserIllegal() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    // covers [v] username-mention in tweet messages [v] legal [v] not belong to any author in tweets
    @Test
    public void testGetMentionedUserNoAuthorsIncluded() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        
        assertTrue("expected non empty set", !mentionedUsers.isEmpty());
    }
    
    // [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(authors himself)
    @Test
    public void testGetMentionedUserAuthorsHimSelf() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
        
        assertTrue("expected non empty set", !mentionedUsers.isEmpty());    	
    }
    
    // [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(other)
    @Test
    public void testGetMentionedUserAuthorsOthers() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3, tweet4));
        
        assertTrue("expected non empty set", !mentionedUsers.isEmpty());    	
    }
    
    // [v] username-mention in tweet messages [v] legal [v] belong to any author in tweets(username-mention in uppercase)
    @Test
    public void testGetMentionedUserAuthorsWithUppercase() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4, tweet5));
        
        assertTrue("expected non empty set", !mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}

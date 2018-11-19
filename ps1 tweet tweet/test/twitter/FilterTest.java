/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to TALK about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(2, "alyssa", "again: rivest talk in 30 minutes #hype", d3);
    
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
     * 1. no tweets meets requirement of author name
     * 2. one tweet meets requirement of author name
     * 3. more than one tweet meet requirement of author name
     */
    
    // covers one tweet
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    // covers no tweet
    @Test
    public void testWrittenByMultipleTweetsEmptyResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "jimmy");
        
        assertEquals("expected empty list", 0, writtenBy.size());
    }
    
    // covers more than one tweet(and in the same order as in the input list)
    @Test
    public void testWrittenByMultipleTweetsMoreThanOneResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3), "alyssa");
        
        assertEquals("expected two-elements list", 2, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet3));
        assertEquals("expected order of writtenBy is the same as the input list", tweet1, writtenBy.get(0));
    }
    
    /*
     * Testing Strategy
     * 
     * Partition the inputs as follows:
     * 1. no tweet in time span
     * 2. one tweet in time span
     * 3. more than one tweet in time span
     * 4. there is tweet in time span start
     * 5. there is tweet in time span end
     */
    
    // covers more than one tweet in time span
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
    }
    
    // covers no tweet in time span
    @Test
    public void testInTimespanMultipleTweetsEmptyResult() {
        Instant testStart = Instant.parse("2016-02-17T08:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T09:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }
    
    // covers single tweet in time span
    @Test
    public void testInTimespanMultipleTweetsSingleResult() {
        Instant testStart = Instant.parse("2016-02-17T08:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:10:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected a single-element list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.contains(tweet1));
    }
    
    // covers single tweet in time span end
    @Test
    public void testInTimespanMultipleTweetsSingleResultAtTimeStampEnd() {
        Instant testStart = Instant.parse("2016-02-17T08:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected a single-element list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.contains(tweet1));
    }
    
    // covers single tweet in time span start
    @Test
    public void testInTimespanMultipleTweetsSingleResultAtTimeStampStart() {
        Instant testStart = Instant.parse("2016-02-17T10:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:10:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected a single-element list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.contains(tweet1));
    }
    
    /*
     * Testing Strategy
     * 
     * Partition the inputs as follows:
     * 1. contains text(case insensitive)
     * 2. contains no text
     */
    
    // covers containing text(case insensitive)
    @Test
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }
    
    // covers no text
    @Test
    public void testContainingNoText() {
    	List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("asfjdslkjifd"));
    	
    	assertTrue("expected empty list", containing.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}

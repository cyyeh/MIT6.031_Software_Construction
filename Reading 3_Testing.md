# Reading 3: Testing

reference: http://web.mit.edu/6.031/www/sp18/classes/03-testing/

**Main Topics**

- understand the value of testing, and know the process of test-first programming;
- be able to design a test suite for a method by partitioning its input and output space and choosing good test cases;
- be able to judge a test suite by measuring its code coverage; and
- understand and know when to use black box vs. glass box testing, unit tests vs. integration tests, and automated regression testing.

### Validation

- Formal reasoning
- Code review
- Testing

### Why Software Testing is Hard

Here are some approaches that unfortunately don’t work well in the world of software.

- Exhaustive testing
- Haphazard testing (“just try it and see if it works”)
- Random or statistical testing

### Test-first Programming

In test-first-programming, you write tests before you even write any code. The development of a single function proceeds in this order:

- Write a specification for the function.
- Write tests that exercise the specification.
- Write the actual code. Once your code passes the tests you wrote, you’re done.

### Choosing Test Cases by Partitioning

Creating a good test suite is a challenging and interesting design problem. We want to pick a set of test cases that is small enough to run quickly, yet large enough to validate the program.

partitioning a function's input space
To do this, we divide the input space into subdomains, each consisting of a set of inputs. Taken together the subdomains completely cover the input space, so that every input lies in at least one subdomain. Then we choose one test case from each subdomain, and that’s our test suite.

The idea behind subdomains is to partition the input space into sets of similar inputs on which the program has similar behavior. Then we use one representative of each set. This approach makes the best use of limited testing resources by choosing dissimilar test cases, and forcing the testing to explore parts of the input space that random testing might not reach.

### Automated Unit Testing with JUnit

```java

@Test
public void testALessThanB() {
    assertEquals(2, Math.max(1, 2));
}

@Test
public void testBothEqual() {
    assertEquals(9, Math.max(9, 9));
}

@Test
public void testAGreaterThanB() {
    assertEquals(-5, Math.max(-5, -6));
}

```

### Documenting Your Testing Strategy

Document the strategy at the top of the test class:

```java
/*
 * Testing strategy
 *
 * Partition the inputs as follows:
 * text.length(): 0, 1, > 1
 * start:         0, 1, 1 < start < text.length(),
 *                text.length() - 1, text.length()
 * text.length()-start: 0, 1, even > 1, odd > 1
 *
 * Include even- and odd-length reversals because
 * only odd has a middle element that doesn't move.
 *
 * Exhaustive Cartesian coverage of partitions.
 */
```

Each test method should have a comment above it saying how its test case was chosen, i.e. which parts of the partitions it covers:

```java
// covers test.length() = 0,
//        start = 0 = text.length(),
//        text.length()-start = 0
@Test public void testEmpty() {
    assertEquals("", reverseEnd("", 0));
}
```

### Black Box and Glass Box Testing

- Black box testing
- Glass box testing means choosing test cases with knowledge of how the function is actually implemented. For example, if the implementation selects different algorithms depending on the input, then you should partition according to those domains. If the implementation keeps an internal cache that remembers the answers to previous inputs, then you should test repeated inputs.

### Coverage

- Statement coverage
- Branch coverage
- Path coverage

A good code coverage tool for Eclipse is EclEmma, shown on the right. In EclEmma, a line that has been executed by the test suite is colored green, and a line not yet covered is red. A line containing a branch that has been executed in only one direction – always true but never false, or vice versa – is colored yellow. 

### Unit Testing vs. Integration Testing, and Stubs

- Unit Testing: Testing modules in isolation leads to much easier debugging. When a unit test for a module fails, you can be more confident that the bug is found in that module, rather than anywhere in the program.
- Integration Testing: tests a combination of modules, or even the entire program. 
    - stub(mock object): Isolating a higher-level module like makeIndex() is possible if we write stub versions of the modules that it calls. 

### Automated Testing and Regression Testing

- Automated testing: Running the tests and checking their results automatically.
- Regression Testing: Running all your tests after every change
    - Whenever you find and fix a bug, take the input that elicited the bug and add it to your automated test suite as a test case. This kind of test case is called a regression test. This helps to populate your test suite with good test cases.

### Summary

In this reading, we saw these ideas:

- Test-first programming. Write tests before you write code.
- Partitioning and boundaries for choosing test cases systematically.
- Glass box testing and statement coverage for filling out a test suite.
- Unit-testing each module, in isolation as much as possible.
- Automated regression testing to keep bugs from coming back.

The topics of today’s reading connect to our three key properties of good software as follows:

- Safe from bugs. Testing is about finding bugs in your code, and test-first programming is about finding them as early as possible, immediately after you introduced them.

- Easy to understand. Testing doesn’t help with this as much as code review does.

- Ready for change. Readiness for change was considered by writing tests that only depend on behavior in the spec. We also talked about automated regression testing, which helps keep bugs from coming back when changes are made to code.
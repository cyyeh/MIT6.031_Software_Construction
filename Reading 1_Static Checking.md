# Reading 1: Static Checking

reference: http://web.mit.edu/6.031/www/fa18/classes/01-static-checking/

**Two Main Topics**

- static typing(what Java differs from Python)
- the big three properties of good software
    - safe from bugs
    - easy to understand
    - ready for change

### Comparing Syntactic Differences between Java and Python from Example of Computing Hailstones

```java
// Java
int n = 3;
while (n != 1) {
    System.out.println(n);
    if (n % 2 == 0) {
        n = n / 2;
    } else {
        n = 3 * n + 1;
    }
}
System.out.println(n);
```

```python
# Python
n = 3
while n != 1:
    print(n)
    if n % 2 == 0:
        n = n / 2
    else:
        n = 3 * n + 1
print(n)
```

### The Biggest Difference: In Java, Declaring Type before Use

### Static Typing

Java is a statically-typed language. The types of variables are known at compile time, therefore the compiler can duduce the types of all expressions as well.

### Static Checking, Dynamic Checking, No Checking

- Static checking: the bug is found automatically before the program even runs.
- Dynamic checking: the bug is found automatically when the code is executed.
- No checking: the language doesn’t help you find the error at all. You have to watch for it yourself, or end up with wrong answers.

Static checking can catch:

- syntax errors, like extra punctuation or spurious words. Even dynamically-typed languages like Python do this kind of static checking. If you have an indentation error in your Python program, you’ll find out before the program starts running.
- wrong names, like Math.sine(2). (The right name is sin.)
- wrong number of arguments, like Math.sin(30, 20).
- wrong argument types, like Math.sin("30").
- wrong return types, like return "30"; from a function that’s declared to return an int.

Dynamic checking can catch:

- illegal argument values. For example, the integer expression x/y is only erroneous when y is actually zero; otherwise it works. So in this expression, divide-by-zero is not a static error, but a dynamic error.
- unrepresentable return values, i.e., when the specific return value can’t be represented in the type.
- out-of-range indexes, e.g., using a negative or too-large index on a string.
- calling a method on a null object reference (null is like Python None).

Static checking tends to be about type errors, errors that are independent of the specific value that a variable has. A type is a set of values. Static typing guarantees that a variable will have some value from that set, but we don’t know until runtime exactly which value it has. So if the error would be caused only by certain values, like divide-by-zero or index-out-of-range then the compiler won’t raise a static error about it.

Dynamic checking, by contrast, tends to be about errors caused by specific values.

### Documenting Assumptions

- type declaration
- `final` keyword

### Hacking vs. Engineering

We’ve written some hacky code in this class. Hacking is often marked by unbridled optimism:

- Bad: writing lots of code before testing any of it
- Bad: keeping all the details in your head, assuming you’ll remember them forever, instead of writing them down in your code
- Bad: assuming that bugs will be nonexistent or else easy to find and fix

But software engineering is not hacking. Engineers are pessimists:

- Good: write a little bit at a time, testing as you go. In a future class, we’ll talk about test-first programming.
- Good: document the assumptions that your code depends on
- Good: defend your code against stupidity – especially your own! Static checking helps with that.

### The Goal of 6.031

- Safe from bugs. Correctness (correct behavior right now) and defensiveness (correct behavior in the future) are required in any software we build.
- Easy to understand. The code has to communicate to future programmers who need to understand it and make changes in it (fixing bugs or adding new features). That future programmer might be you, months or years from now. You’ll be surprised how much you forget if you don’t write it down, and how much it helps your own future self to have a good design.
- Ready for change. Software always changes. Some designs make it easy to make changes; others require throwing away and rewriting a lot of code.

There are other important properties of software (like performance, usability, security), and they may trade off against these three. But these are the Big Three that we care about in 6.031, and that software developers generally put foremost in the practice of building software. It’s worth considering every language feature, every programming practice, every design pattern that we study in this course, and understanding how they relate to the Big Three.
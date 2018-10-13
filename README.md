# MIT6.031 Software Construction Notes and Projects

This repository consists of notes taken from class readings and personal/team-based projects.

## General Information for this course

*Keywords(you will learn concepts and practices behind these BIG BUZZWORDS in this course)*:
design patterns for OOP(object-oriented programming), concurrent programming, functional programming, testing, specification, version control, Git, debugging, abstract data types...

### What is this course about?

Our primary goal in this course is learning how to produce software that is:

- *Safe from bugs*. Correctness (correct behavior right now), and defensiveness (correct behavior in the future).
- *Easy to understand*. Has to communicate to future programmers who need to understand it and make changes in it (fixing bugs or adding new features). That future programmer might be you, months or years from now. You’ll be surprised how much you forget if you don’t write it down, and how much it helps your own future self to have a good design.
- *Ready for change*. Software always changes. Some designs make it easy to make changes; others require throwing away and rewriting a lot of code. 

There are other important properties of software (like performance, usability, security), and they may trade off against these three. But these are the Big Three that we care about in 6.031, and that software developers generally put foremost in the practice of building software. It’s worth considering every language feature, every programming practice, every design pattern that we study in this course, and understanding how they relate to the Big Three.

### Why we use Java in this course?

Since you’ve had 6.009, we’re assuming that you’re comfortable with Python. So why aren’t we using Python in this course? Why do we use Java in 6.031?

*Safety* is the first reason. Java has static checking (primarily type checking, but other kinds of static checks too, like that your code returns values from methods declared to do so). We’re studying software engineering in this course, and safety from bugs is a key tenet of that approach. Java dials safety up to 11, which makes it a good language for learning about good software engineering practices. It’s certainly possible to write safe code in dynamic languages like Python, but it’s easier to understand what you need to do if you learn how in a safe, statically-checked language.

*Ubiquity* is another reason. Java is widely used in research, education, and industry. Java runs on many platforms, not just Windows/Mac/Linux. Java can be used for web programming (both on the server and in the client), and native Android programming is done in Java. Although other programming languages are far better suited to teaching programming (Scheme and ML come to mind), regrettably these languages aren’t as widespread in the real world. Java on your resume will be recognized as a marketable skill. But don’t get us wrong: the real skills you’ll get from this course are not Java-specific, but carry over to any language that you might program in. The most important lessons from this course will survive language fads: safety, clarity, abstraction, engineering instincts.
In any case, a good programmer must be *multilingual*. Programming languages are tools, and you have to use the right tool for the job. You will certainly have to pick up other programming languages before you even finish your MIT career (JavaScript, C/C++, Scheme or Ruby or ML or Haskell), so we’re getting started now by learning a second one.

As a result of its ubiquity, Java has a wide array of interesting and useful *libraries* (both its enormous built-in library, and other libraries out on the net), and excellent free *tools* for development (IDEs like Eclipse, editors, compilers, test frameworks, profilers, code coverage, style checkers). Even Python is still behind Java in the richness of its ecosystem.

There are some reasons to regret using Java. It’s wordy, which makes it hard to write examples on the board. It’s large, having accumulated many features over the years. It’s internally inconsistent (e.g. the final keyword means different things in different contexts, and the static keyword in Java has nothing to do with static checking). It’s weighted with the baggage of older languages like C/C++ (the primitive types and the switch statement are good examples). It has no interpreter like Python’s, where you can learn by playing with small bits of code.
But on the whole, Java is a reasonable choice of language right now to learn how to write code that is safe from bugs, easy to understand, and ready for change. And that’s our goal.

### Course topics

* 01: Static Checking (http://web.mit.edu/6.031/www/sp18/classes/01-static-checking/)
* 02: Basic Java (http://web.mit.edu/6.031/www/sp18/classes/02-basic-java/)
* 03: Testing (http://web.mit.edu/6.031/www/sp18/classes/03-testing/)
* 04: Code Review (http://web.mit.edu/6.031/www/sp18/classes/04-code-review/)
* 05: Version Control (http://web.mit.edu/6.031/www/sp18/classes/05-version-control/)
* 06: Specifications (http://web.mit.edu/6.031/www/sp18/classes/06-specifications/)
* 07: Designing Specifications (http://web.mit.edu/6.031/www/sp18/classes/07-designing-specs/)
* 08: Mutability & Immutability (http://web.mit.edu/6.031/www/sp18/classes/08-immutability/)
* 09: Avoiding Debugging (http://web.mit.edu/6.031/www/sp18/classes/09-avoiding-debugging/)
* 10: Abstract Data Types (http://web.mit.edu/6.031/www/sp18/classes/10-abstract-data-types/)
* 11: Abstraction Functions & Rep Invariants (http://web.mit.edu/6.031/www/sp18/classes/11-abstraction-functions-rep-invariants/)
* 12: Interfaces & Enumerations (http://web.mit.edu/6.031/www/sp18/classes/12-interfaces-enums/)
* 13: Debugging (http://web.mit.edu/6.031/www/sp18/classes/13-debugging/)
* 14: Recursion (http://web.mit.edu/6.031/www/sp18/classes/14-recursion/)
* 15: Equality (http://web.mit.edu/6.031/www/sp18/classes/15-equality/)
* 16: Recursive Data Types (http://web.mit.edu/6.031/www/sp18/classes/16-recursive-data-types/)
* 17: Regular Expressions & Grammars (http://web.mit.edu/6.031/www/sp18/classes/17-regex-grammars/)
* 18: Parsers (http://web.mit.edu/6.031/www/sp18/classes/18-parsers/)
* 19: Concurrency (http://web.mit.edu/6.031/www/sp18/classes/19-concurrency/)
* 20: Thread Safety (http://web.mit.edu/6.031/www/sp18/classes/20-thread-safety/)
* 21: Locks & Synchronization (http://web.mit.edu/6.031/www/sp18/classes/21-locks/)
* 22: Queues & Message-Passing (http://web.mit.edu/6.031/www/sp18/classes/22-queues/)
* 23: Sockets & Networking (http://web.mit.edu/6.031/www/sp18/classes/23-sockets-networking/)
* 24: Callbacks (http://web.mit.edu/6.031/www/sp18/classes/24-callbacks/)
* 25: Map, Filter, Reduce (http://web.mit.edu/6.031/www/sp18/classes/25-map-filter-reduce/)
* 26: Little Languages I (http://web.mit.edu/6.031/www/sp18/classes/26-little-languages-1/)
* 27: Little Languages II (http://web.mit.edu/6.031/www/sp18/classes/27-little-languages-2/)
* 28: Team Version Control (http://web.mit.edu/6.031/www/sp18/classes/28-team-version-control/)

### Problem Sets (These are personal programming projects)

* 0: Turtle Graphics (https://ocw.mit.edu/ans7870/6/6.005/s16/psets/ps0/)
* 1: Tweet Tweet (https://ocw.mit.edu/ans7870/6/6.005/s16/psets/ps1/)
* 2: Poetic Walks (https://ocw.mit.edu/ans7870/6/6.005/s16/psets/ps2/)
* 3: Expressivo (https://ocw.mit.edu/ans7870/6/6.005/s16/psets/ps3/)
* 4: Multiplayer Minesweeper (https://ocw.mit.edu/ans7870/6/6.005/s16/psets/ps4/)

Project (This is a team based project)

* ABC Music Player(https://ocw.mit.edu/ans7870/6/6.005/s16/projects/abcplayer/)

### Quizzes (For testing how well you understand the course material)

* Quiz 1 (http://web.mit.edu/6.031/www/sp18/quizzes/archive/quiz1.pdf) and Quiz 1 solutions (http://web.mit.edu/6.031/www/sp18/quizzes/archive/quiz1_soln.pdf) (first half of lectures)
* Quiz 2 (http://web.mit.edu/6.031/www/sp18/quizzes/archive/quiz2.pdf) and Quiz 2 solutions (http://web.mit.edu/6.031/www/sp18/quizzes/archive/quiz2_soln.pdf) (second half of lectures)


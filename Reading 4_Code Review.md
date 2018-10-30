# Reading 4: Code Review

reference: http://web.mit.edu/6.031/www/sp18/classes/04-code-review/

**Main Topics**

- code review: reading and discussing code written by somebody else
- general principles of good coding: things you can look for in every code review, regardless of programming language or program purpose

### Code Review

- Improving the code
- Improving the programmer

### Style Standards

- [Google Java Style](http://google.github.io/styleguide/javaguide.html)

### Summary

Code review is a widely-used technique for improving software quality by human inspection. Code review can detect many kinds of problems in code, but as a starter, this reading talked about these general principles of good code:

- Don’t Repeat Yourself (DRY)
- Comments where needed
- Fail fast
- Avoid magic numbers
- One purpose for each variable
- Use good names
- No global variables
- Return results, don’t print them
- Use whitespace for readability

The topics of today’s reading connect to our three key properties of good software as follows:

- Safe from bugs. 
    - In general, code review uses human reviewers to find bugs. DRY code lets you fix a bug in only one place, without fear that it has propagated elsewhere. Commenting your assumptions clearly makes it less likely that another programmer will introduce a bug. The Fail Fast principle detects bugs as early as possible. Avoiding global variables makes it easier to localize bugs related to variable values, since non-global variables can be changed in only limited places in the code.

- Easy to understand. 
    - Code review is really the only way to find obscure or confusing code, because other people are reading it and trying to understand it. Using judicious comments, avoiding magic numbers, keeping one purpose for each variable, using good names, and using whitespace well can all improve the understandability of code.

- Ready for change. 
    - Code review helps here when it’s done by experienced software developers who can anticipate what might change and suggest ways to guard against it. DRY code is more ready for change, because a change only needs to be made in one place. Returning results instead of printing them makes it easier to adapt the code to a new purpose.
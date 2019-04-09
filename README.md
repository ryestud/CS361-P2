# Project 2: CS361-P2

* Author: Ryley Studer | Tucker Ferguson
* Class: CS361 Section [ 2 (Ryley) | 3 (Tucker) ]
* Semester: Spring 2019

## Overview

This program creates an NFA object model and converts it to a DFA,
by implementing two classes, NFA.java and NFAState.java. With these
two classes, there will also be a required method to create an
equivalent DFA, from within NFA.java.

## Compiling and Using

This program is ran by compiling the driver class NFADriver.java:
```
javac fa/nfa/NFADriver.java
```
Then to run the program, choose a text file "X" from within /tests:
```
java fa/nfa/NFADriver ./tests/p2tc<X>.txt
```

## Discussion

Discuss the issues and successes you encountered during programming
(development). Here are some questions to get you started. You don't
have to answer all of them in order - they are a guideline to get you
thinking.
  * What problems did you have? What went well?
  * What process did you go through to create the program?
  * What did you have to research and learn on your own?
  * What kinds of errors did you get? How did you fix them?
  * What parts of the project did you find challenging?
  * Is there anything that finally "clicked" for you in the process
  of working on this project?
  * Is there anything that you would change about the project?
  * Can you apply what you learned in this project to future projects?

Initially, we had sought out implementing the NFAState.java in a way
that we had done with P1. We followed the conventions of the provided
DFAState, and made sure to use the correct data structure to contain
each of the states as well as transitions between them.

The first part of the project was filling in all of the interface
methods. After this was done, we had issues with the states simply being
null, so we had to request assistance on clarification with the getDFA
method in NFA.java.

This involved messing around with the eClosure transition method, as it
was not performing a DFS correctly. I had to look back through some old
notes from my CS321 course to reimplement it correctly again.






## Testing

In this section, tell us how you tested your project.

You are expected to test your projects before submitting them for
final review. Pretend that your instructor is your manager or
customer at work. How would you ensure that you are delivering a
working solution to their requirements?


## Sources used

Help with DFS:
```
* Class notes from CS321
* [How to implement DFS](https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/)
```

----------
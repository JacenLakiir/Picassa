=========================
README: Picassa Design 2
=========================

Name: Eric Mercer (ewm10)

Started:  2/2/12
Finished: 2/5/12

Project Length:
- 2.5 hours [Actual]
- 2 hours [Estimated]

Estimate evaluation:
- My estimate was fairly accurate. Refactoring and formatting my code to meet the stricter design standards of Design 2 took a bit of time, but was fairly manageable.

Discussed with:
- UTA Tanner Schmidt (assigned UTA)

Resources:
- http://stackoverflow.com/questions/4140065/java-isinstance-vs-instanceof-operator (for type-checking)
- spring12_cps108_02_picassa_animated (refactoring ideas for ParserTest)

Files Used to Start and Test:
- Picassa Code Complete 2
- tested with ParserTest

Data / Resource Files Required:
- none

Impressions:

Taking a long second look at my design pointed out some glaring weak spots not addressed by Design 1.

With my UTA's advice, I overhauled parsing for ParenExpressions by refactoring some methods that were previously implemented only within the (usually one) subclass that needed it so that my design is more extensible and parsing is completely done with ParenExpression rather than sometimes being continued at the subclass-level (thereby eliminating feature envy). The two main suspects for this refactoring were checks for the number of operands parsed and the type of operands parsed.

I also changed some instance variable declarations across many Expression subclasses by fixing erroneous static declarations and declaring some more complicated instance variables as final by initializing them through a function call. This improved efficiency and cut down on unnecessary function calls.

Finally I cleaned up and reformatted my code to follow a standard set of guidelines for formatting.

Overall, I appreciate the challenges that Design 2 threw at me because they helped me reevaluate what I had previously deemed good design and update my design based on the new ideas I've learned in class since my last design submission.
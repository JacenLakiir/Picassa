================================
README: Picassa Code Complete 2
================================

Name: Eric Mercer (ewm10)

Started:  1/29/12
Finished: 2/1/12

Project Length:
- 1 hour [Actual]
- 1 hour [Estimated]

Estimate evaluation:
- My estimate was very accurate. My design for Design 1 was very flexible for quickly
and easily adding new types of expressions.

Discussed with:
- no one

Resources:
- http://stackoverflow.com/questions/4140065/java-isinstance-vs-instanceof-operator

Files Used to Start and Test:
- Picassa Design 1
- spring12_cps108_02_picassa_animated (refactoring ideas for ParserTest)
- tested with ParserTest

Data / Resource Files Required:
- none

Impressions:

Adding the features for Code Complete 2 was a very quick and easy process thanks to how much my
design for Design 1 adhered to the Open/Closed Principle. I was able to quickly add the new
expressions as new subclasses of ParenExpression while only adding about 40-50 new lines of code
to pre-existing classes in order to connect my current Picassa model to the new features and to
add a few new methods (e.g. ceiling() in ColorTransformations, removeMapping() in Model, etc.)
for the new expression types. Practically no old code was modified or deleted to accomplish all
of this.
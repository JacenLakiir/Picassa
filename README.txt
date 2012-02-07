===============================
README: Picassa Design 3
===============================

Name: Eric Mercer (ewm10)

Started:  2/7/12
Finished: 2/7/12

Project Length:
- 20 minutes [Actual]
- 30 minutes [Estimated]

Estimate evaluation:
- The few changes I needed to make went even quicker than I expected.

Discussed with:
- UTA Tanner Schmidt (assigned UTA)

Resources:
- none 

Files Used to Start and Test:
- Picassa Code Complete 3
- tested with ParserTest

Data / Resource Files Required:
- none

Impressions:

This redesign was a simple matter of fixing a few pieces of inefficient/duplicated code and expanding behavior for infinite expressions.

The special variable t was being set/updated once per pixel during evaluate() in Model even though myCurrentTime does not change within a single call to evaluate(). I moved the one-line set/update statement to nextFrame() so that t's value is only set/updated when myCurrentTime has actually changed.

I've also loosened the restriction on the minimum number of operands allowed for infinite expressions (new minimum of 1 operand for average, min, and max and no operands necessary for sum and product). My elimination of duplicated code in the evaluate() methods for sum and product coincided with my implementation of default behavior for these two expressions' new minimum of 0 operands: the base sumColor is now defined as GRAY (RGBColor(0)) and the base productColor is now defined as WHITE (RGBColor(1)). This allows for evaluate() calls for sum and product expressions with 0 operands to return a valid color while not interfering with the mathematical operation performed in the loop in evaluate() for sum and product commands with 1 or more operands.
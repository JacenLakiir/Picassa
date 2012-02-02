package model;

import java.util.ArrayList;
import java.util.List;
import model.expressions.*;


/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser 
 * is used 
 *   http://en.wikipedia.org/wiki/Recursive_descent_parser
 *   
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{

    // single instance of parser
    private static Parser myParser;
    
    // state of the parser
    private static int myCurrentPosition;
    private static String myInput;
    
    private Parser () { }
    
    public static Parser getInstance ()
    {
        if (myParser == null)
            myParser = new Parser();
        return myParser;
    }

    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input)
    {
        initializeParser(input);
        Expression result = getAndParseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                      myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }
    
    public Expression getAndParseExpression ()
    {
        String parseableString = myInput;
        List<ExpressionFactory> typesOfExpressions = initializeExpressionFactory();        
        for (ExpressionFactory expressionType : typesOfExpressions)
        {
            if (expressionType.isThisTypeOfExpression(parseableString))
                return expressionType.parseExpression(parseableString); 
        }
        throw new ParserException("Unexpected expression type", ParserException.Type.UNKNOWN_COMMAND);
    }

    public void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }

    public char currentCharacter ()
    {
        if (notAtEndOfString()) return myInput.charAt(myCurrentPosition);
        else                    throw new ParserException("Expression not terminated correctly.");
    }
    
    public String getInput ()
    {
        return myInput;
    }
    
    public int getCurrentPosition ()
    {
        return myCurrentPosition;
    }
    
    public void moveParser (int newPosition)
    {
        if (newPosition >= 0)
        {
            myCurrentPosition = newPosition;
            resetParser();
        }
    }
    
    public void incrementCurrentPosition ()
    {
        myCurrentPosition++;
    }
    
    private void initializeParser (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
        resetParser();
    }
    
    private void resetParser ()
    {
        skipWhiteSpace();
        myInput = myInput.substring(myCurrentPosition);
        myCurrentPosition = 0;
    }
    
    private boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }
    
    private List<ExpressionFactory> initializeExpressionFactory ()
    {
        List<ExpressionFactory> typesOfExpressions = new ArrayList<ExpressionFactory>();
        typesOfExpressions.add(NumberExpression.getFactory());
        typesOfExpressions.add(VariableExpression.getFactory());
        typesOfExpressions.add(ParenExpression.getFactory());
        return typesOfExpressions;
    }
}
package model.expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Model;
import model.ParserException;
import model.RGBColor;

public class ParenExpression extends Expression
{

    // expression begins with a left parenthesis followed by the command name, 
    // which is a sequence of alphabetic characters
    private static final Pattern myRegex = Pattern.compile("\\(([A-z]+)");
    private static final String myType = "ParenExpression";
    private static List<ExpressionFactory> typesOfParenExpressions;
    protected static int myMinNumberOfOperands = -1;
    protected static int myMaxNumberOfOperands = -1;
    protected List<Expression> myOperands;
    
    public ParenExpression (List<Expression> operands) 
    {
        myOperands = operands;
    }
     
    public boolean isThisTypeOfExpression (String parseableString)
    {
        Matcher expMatcher = myRegex.matcher(parseableString);
        return expMatcher.lookingAt();
    }
    
    public Expression parseExpression (String parseableString)
    {
        String command = parseCommand(parseableString);
        List<Expression> operands = parseOperands();

        initializeParenExpressionFactory();        
        for (ExpressionFactory parenExpressionType : typesOfParenExpressions)
        {
            if (parenExpressionType.matchesCommand(command))
                return parenExpressionType.createThisTypeOfParenExpression(operands); 
        }
        throw new ParserException("Unexpected expression type", ParserException.Type.UNKNOWN_COMMAND);
    }
    
    public RGBColor evaluate ()
    {
        return null;
    }
       
    /**
     * @return string representation of expression
     */
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(" " + myType + " with command " + getType() + " ");
        if (myOperands != null)
        {
            for (Expression expression : myOperands)
                result.append(" " + expression.toString() + " ");
        }
        result.append(")");
        return result.toString();
    }
    
    protected boolean matchesCommand (String command)
    {
        return false;
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return null;
    }
    
    protected String getType ()
    {
        return null;
    }
    
    protected List<Expression> getOperands ()
    {
        return Collections.unmodifiableList(myOperands);
    }
 
    protected int getMinNumberOfOperands ()
    {
        return -1;
    }

    protected int getMaxNumberOfOperands ()
    {
        return -1;
    }
    
    protected boolean isBeingEvaluatedForFirstPixel ()
    {
        return (Model.getValue("x").equals(new RGBColor(-1))
                && Model.getValue("y").equals((new RGBColor(-1))));
    }
    
    protected void checkNumberOfOperands (int numOperandsParsed)
    {
        if (numOperandsParsed < getMinNumberOfOperands())
            throw new ParserException("Not enough operands: at least " +
                                       getMinNumberOfOperands() + " operand(s) required for " +
                                       getType() + " expression");
        if (numOperandsParsed > getMaxNumberOfOperands())
            throw new ParserException("Too many operands: no more than " + 
                                       getMaxNumberOfOperands() + " operand(s) allowed for " +
                                       getType() + " expression");
    }
    
    protected <T> boolean isCorrectInstance (Object parsedObject, Class<T> expectedObjectType)
    {
        return expectedObjectType.isInstance(parsedObject);
    }
    
    private String parseCommand (String parseableString)
    {
        Matcher expMatcher = myRegex.matcher(parseableString);
        expMatcher.find(parser.getCurrentPosition());
        String command = expMatcher.group(1);
        parser.moveParser(expMatcher.end());
        return command;
    }
    
    private List<Expression> parseOperands ()
    {
        List<Expression> operands = new ArrayList<Expression>();
        while (parser.currentCharacter() != ')')
        {
            operands.add(parser.getAndParseExpression());
            parser.skipWhiteSpace();
        }
        parser.incrementCurrentPosition();
        parser.moveParser(parser.getCurrentPosition());
        return operands;
    }
    
    private static void initializeParenExpressionFactory ()
    {
        if (typesOfParenExpressions == null)
        {
            // new types for Part 1
            typesOfParenExpressions = new ArrayList<ExpressionFactory>();
            typesOfParenExpressions.add(PlusParenExpression.getParenFactory());
            typesOfParenExpressions.add(MinusParenExpression.getParenFactory());
            typesOfParenExpressions.add(MulParenExpression.getParenFactory());
            typesOfParenExpressions.add(DivParenExpression.getParenFactory());
            typesOfParenExpressions.add(ModParenExpression.getParenFactory());
            typesOfParenExpressions.add(ExpParenExpression.getParenFactory());
            typesOfParenExpressions.add(NegParenExpression.getParenFactory());
            typesOfParenExpressions.add(ColorParenExpression.getParenFactory());
            // new types for Part 2
            typesOfParenExpressions.add(LetParenExpression.getParenFactory());
            typesOfParenExpressions.add(RandomParenExpression.getParenFactory());
            typesOfParenExpressions.add(FloorParenExpression.getParenFactory());
            typesOfParenExpressions.add(CeilParenExpression.getParenFactory());
            typesOfParenExpressions.add(AbsParenExpression.getParenFactory());
            typesOfParenExpressions.add(ClampParenExpression.getParenFactory());
            typesOfParenExpressions.add(WrapParenExpression.getParenFactory());
            typesOfParenExpressions.add(SinParenExpression.getParenFactory());
            typesOfParenExpressions.add(CosParenExpression.getParenFactory());
            typesOfParenExpressions.add(TanParenExpression.getParenFactory());
            typesOfParenExpressions.add(AtanParenExpression.getParenFactory());
            typesOfParenExpressions.add(LogParenExpression.getParenFactory());            
            typesOfParenExpressions.add(RGBtoYUVParenExpression.getParenFactory());
            typesOfParenExpressions.add(YUVtoRGBParenExpression.getParenFactory());
            typesOfParenExpressions.add(PerlinColorParenExpression.getParenFactory());
            typesOfParenExpressions.add(PerlinBWParenExpression.getParenFactory());
        }
    }
            
    protected ParenExpression () { }
    
    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ParenExpression());
    }
    
}
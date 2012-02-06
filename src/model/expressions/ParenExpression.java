package model.expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.ParserException;
import model.RGBColor;


public class ParenExpression extends Expression
{

    // expression begins with a left parenthesis followed by the command name,
    // which is a sequence of alphabetic characters, or the command symbol,
    // which is one of several special symbols
    private static final String commandRegex = "[A-z]+";
    private static final String symbolRegex = "[\\+\\-\\*/%\\^!]";
    private static final Pattern myRegex = Pattern.compile("\\((" + commandRegex + "|" + symbolRegex + ")");
    
    private static final String myType = "ParenExpression";
    private static final List<ExpressionFactory> typesOfParenExpressions =
        initializeParenExpressionFactory();
    private static final int myMinNumberOfOperands = -1;
    private static final int myMaxNumberOfOperands = -1;
    private static List<Class<?>> myOperandTypes;
    protected List<Expression> myOperands;

    public ParenExpression (List<Expression> operands)
    {
        myOperands = operands;
    }

    @Override
    public boolean isThisTypeOfExpression (String parseableString)
    {
        Matcher expMatcher = myRegex.matcher(parseableString);
        return expMatcher.lookingAt();
    }

    @Override
    public Expression parseExpression (String parseableString)
    {
        String command = parseCommand(parseableString);
        List<Expression> operands = parseOperands();

        for (ExpressionFactory parenExpressionType : typesOfParenExpressions)
        {
            if (parenExpressionType.matchesCommand(command))
            {
                parenExpressionType.checkNumberOfOperands(operands.size());
                parenExpressionType.checkTypeOfOperands(operands);
                return parenExpressionType.createThisTypeOfParenExpression(operands);
            }
        }
        throw new ParserException("Unexpected paren expression type: " + parseableString,
                                  ParserException.Type.UNKNOWN_COMMAND);
    }

    @Override
    public RGBColor evaluate ()
    {
        return null;
    }

    /**
     * @return string representation of expression
     */
    @Override
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

    protected void checkNumberOfOperands (int numOperandsParsed)
    {
        if (numOperandsParsed < getMinNumberOfOperands())
             throw new ParserException("Not enough operands: at least " + getMinNumberOfOperands() +
                                       " operand(s) required for " + getType() + " expression");
        if (numOperandsParsed > getMaxNumberOfOperands())
             throw new ParserException("Too many operands: no more than " + getMaxNumberOfOperands() +
                                       " operand(s) allowed for " + getType() + " expression");
    }

    protected void checkTypeOfOperands (List<Expression> operands)
    {
        List<Class<?>> expectedOperandTypes = getOperandTypes();
        if (expectedOperandTypes == null) return;
        boolean statusCheck = true;
        for (int i = 0; i < operands.size(); i++)
        {
            statusCheck =
                statusCheck && isCorrectInstance(operands.get(i), expectedOperandTypes.get(i));
            if (statusCheck == false)
                 throw new ParserException("Incorrect type of operand(s). Should be " +
                                           expectedOperandTypes.toString());
        }
    }

    protected String getType ()
    {
        return myType;
    }
    
    protected String getSymbol ()
    {
        return null;
    }

    protected List<Expression> getOperands ()
    {
        return Collections.unmodifiableList(myOperands);
    }

    protected int getMinNumberOfOperands ()
    {
        return myMinNumberOfOperands;
    }

    protected int getMaxNumberOfOperands ()
    {
        return myMaxNumberOfOperands;
    }

    protected <T> List<Class<?>> getOperandTypes ()
    {
        return myOperandTypes;
    }

    private String parseCommand (String parseableString)
    {
        Matcher expMatcher = myRegex.matcher(parseableString);
        expMatcher.find(myParser.getCurrentPosition());
        String command = expMatcher.group(1);
        myParser.moveParser(expMatcher.end());
        return command;
    }

    private List<Expression> parseOperands ()
    {
        List<Expression> operands = new ArrayList<Expression>();
        while (myParser.currentCharacter() != ')')
        {
            operands.add(myParser.getAndParseExpression());
            myParser.skipWhiteSpace();
        }
        myParser.incrementCurrentPosition();
        myParser.moveParser(myParser.getCurrentPosition());
        return operands;
    }

    private <T> boolean isCorrectInstance (Object parsedObject, Class<T> expectedObjectType)
    {
        return expectedObjectType.isInstance(parsedObject);
    }

    private static List<ExpressionFactory> initializeParenExpressionFactory ()
    {
        List<ExpressionFactory> parenExpressionTypes = new ArrayList<ExpressionFactory>();
        // new types for Part 1
        parenExpressionTypes.add(PlusParenExpression.getParenFactory());
        parenExpressionTypes.add(MinusParenExpression.getParenFactory());
        parenExpressionTypes.add(MulParenExpression.getParenFactory());
        parenExpressionTypes.add(DivParenExpression.getParenFactory());
        parenExpressionTypes.add(ModParenExpression.getParenFactory());
        parenExpressionTypes.add(ExpParenExpression.getParenFactory());
        parenExpressionTypes.add(NegParenExpression.getParenFactory());
        parenExpressionTypes.add(ColorParenExpression.getParenFactory());
        // new types for Part 2
        parenExpressionTypes.add(LetParenExpression.getParenFactory());
        parenExpressionTypes.add(RandomParenExpression.getParenFactory());
        parenExpressionTypes.add(FloorParenExpression.getParenFactory());
        parenExpressionTypes.add(CeilParenExpression.getParenFactory());
        parenExpressionTypes.add(AbsParenExpression.getParenFactory());
        parenExpressionTypes.add(ClampParenExpression.getParenFactory());
        parenExpressionTypes.add(WrapParenExpression.getParenFactory());
        parenExpressionTypes.add(SinParenExpression.getParenFactory());
        parenExpressionTypes.add(CosParenExpression.getParenFactory());
        parenExpressionTypes.add(TanParenExpression.getParenFactory());
        parenExpressionTypes.add(AtanParenExpression.getParenFactory());
        parenExpressionTypes.add(LogParenExpression.getParenFactory());
        parenExpressionTypes.add(RGBtoYUVParenExpression.getParenFactory());
        parenExpressionTypes.add(YUVtoRGBParenExpression.getParenFactory());
        parenExpressionTypes.add(PerlinColorParenExpression.getParenFactory());
        parenExpressionTypes.add(PerlinBWParenExpression.getParenFactory());
        // new types for Part 3
        parenExpressionTypes.add(SumParenExpression.getParenFactory());
        parenExpressionTypes.add(ProductParenExpression.getParenFactory());
        parenExpressionTypes.add(AverageParenExpression.getParenFactory());
        parenExpressionTypes.add(MinParenExpression.getParenFactory());
        parenExpressionTypes.add(MaxParenExpression.getParenFactory());
        parenExpressionTypes.add(ConditionalParenExpression.getParenFactory());
        return parenExpressionTypes;
    }

    protected ParenExpression ()
    {}

    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new ParenExpression());
    }

}

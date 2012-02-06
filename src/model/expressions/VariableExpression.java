package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.ParserException;
import model.ParserException.Type;
import model.RGBColor;


public class VariableExpression extends Expression
{

    // variable represents a sequence of alphabetic characters
    private static final Pattern myRegex = Pattern.compile("[A-z]+");
    private static final String myType = "VariableExpression";
    private String myVariable;

    public VariableExpression (String variable)
    {
        myVariable = variable;
    }

    @Override
    public boolean isThisTypeOfExpression (String parseableString)
    {
        Matcher varMatcher = myRegex.matcher(parseableString);
        return varMatcher.lookingAt();
    }

    @Override
    public Expression parseExpression (String parseableString)
    {
        Matcher varMatcher = myRegex.matcher(parseableString);
        varMatcher.find(myParser.getCurrentPosition());
        String varMatch = parseableString.substring(varMatcher.start(), varMatcher.end());
        myParser.moveParser(varMatcher.end());
        return new VariableExpression(varMatch);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor value = myModel.getValue(myVariable);
        if (value != null)
            return value;
        else throw new ParserException("Undefined variable: " + myVariable, Type.UNDEFINED_VARIABLE);
    }

    /**
     * @return string representation of expression
     */
    @Override
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append(myType + " with variable " + myVariable);
        return result.toString();
    }

    public String getType ()
    {
        return myType;
    }

    public String getVariable ()
    {
        return myVariable;
    }

    private VariableExpression ()
    {}

    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new VariableExpression());
    }

}

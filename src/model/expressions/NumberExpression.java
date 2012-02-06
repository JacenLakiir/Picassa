package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.RGBColor;


public class NumberExpression extends Expression
{

    // double is made up of an optional negative sign, followed by a sequence
    // of one or more digits, optionally followed by a period, then possibly
    // another sequence of digits
    private static final Pattern myRegex = Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    private static final String myType = "NumberExpression";
    private double myValue;

    public NumberExpression (double value)
    {
        myValue = value;
    }

    @Override
    public boolean isThisTypeOfExpression (String parseableString)
    {
        Matcher doubleMatcher = myRegex.matcher(parseableString);
        return doubleMatcher.lookingAt();
    }

    @Override
    public Expression parseExpression (String parseableString)
    {
        Matcher doubleMatcher = myRegex.matcher(parseableString);
        doubleMatcher.find(myParser.getCurrentPosition());
        String numberMatch = parseableString.substring(doubleMatcher.start(), doubleMatcher.end());
        myParser.moveParser(doubleMatcher.end());
        return new NumberExpression(Double.parseDouble(numberMatch));
    }

    @Override
    public RGBColor evaluate ()
    {
        return new RGBColor(getValue());
    }

    /**
     * @return string representation of expression
     */
    @Override
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append(myType + " with value " + myValue);
        return result.toString();
    }

    public double getValue ()
    {
        return myValue;
    }

    private NumberExpression ()
    {}

    public static ExpressionFactory getFactory ()
    {
        return new ExpressionFactory(new NumberExpression());
    }

}

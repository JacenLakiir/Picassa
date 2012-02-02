package model.expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Model;
import model.ParserException;
import model.RGBColor;
import model.ParserException.Type;

public class VariableExpression extends Expression
{
    
    // variable represents one of the coordinates of a pixel (x or y)
    private static final Pattern myRegex = Pattern.compile("[A-z]+");
    private static final String myType = "VariableExpression";
    private String myVariable;
    
    public VariableExpression (String variable)
    {
        myVariable = variable;
    }

    public boolean isThisTypeOfExpression (String parseableString)
    {
        Matcher varMatcher = myRegex.matcher(parseableString);
        return varMatcher.lookingAt();
    }

    public Expression parseExpression (String parseableString)
    {
        Matcher varMatcher = myRegex.matcher(parseableString);
        varMatcher.find(parser.getCurrentPosition());
        String varMatch =
            parseableString.substring(varMatcher.start(), varMatcher.end());
        parser.moveParser(varMatcher.end());
        return new VariableExpression(varMatch);
    }
    
    public RGBColor evaluate()
    {
        if (isValidCoordinate())
            return coordinateColor();
        return tempVarColor();
    }
    
    /**
     * @return string representation of expression
     */
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
    
    /**
     * Assigns a color based on one of the pixel's coordinates (x or y).
     */
    private RGBColor coordinateColor ()
    {
        return new RGBColor(getPixelCoordinate());
    }
    
    private RGBColor tempVarColor ()
    {
        RGBColor value = Model.getValue(myVariable);
        if (value != null)
            return new RGBColor(value);
        else
            throw new ParserException("Undefined variable: " + myVariable, Type.UNDEFINED_VARIABLE);
    }
    
    private RGBColor getPixelCoordinate ()
    {
        return Model.getValue(myVariable);
    }
    
    private boolean isValidCoordinate ()
    {
        return (myVariable.equals("x") || myVariable.equals("y"));
    }
    
    private VariableExpression () { }
    
    public static ExpressionFactory getFactory()
    {
        return new ExpressionFactory(new VariableExpression());
    }

}

package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;

public class ColorParenExpression extends ParenExpression
{
    
    private static final String myType = "color";
    private static final int myMinNumberOfOperands = 3;
    private static final int myMaxNumberOfOperands = 3;
    
    public ColorParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        checkNumberOfOperands(operands.size());
        return new ColorParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor redComponent = myOperands.get(0).evaluate();
        RGBColor greenComponent = myOperands.get(1).evaluate();
        RGBColor blueComponent = myOperands.get(2).evaluate();
        return ColorCombinations.color(redComponent, greenComponent, blueComponent);
    }
    
    protected String getType ()
    {
        return myType;
    }
    
    protected int getMinNumberOfOperands ()
    {
        return myMinNumberOfOperands;
    }
    
    protected int getMaxNumberOfOperands ()
    {
        return myMaxNumberOfOperands;
    }
    
    private ColorParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new ColorParenExpression());
    }
    
}

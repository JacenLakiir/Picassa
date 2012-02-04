package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorTransformations;

public class FloorParenExpression extends ParenExpression
{
    
    private static final String myType = "floor";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = 1;
   
    
    public FloorParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new FloorParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor color = myOperands.get(0).evaluate();
        return ColorTransformations.floor(color);
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
    
    private FloorParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new FloorParenExpression());
    }
    
}

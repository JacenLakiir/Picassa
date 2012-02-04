package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;

public class RandomParenExpression extends ParenExpression
{
    
    private static final String myType = "random";
    private static final int myMinNumberOfOperands = 0;
    private static final int myMaxNumberOfOperands = 0;
    private static RGBColor myRandomColor;
   
    
    public RandomParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new RandomParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        if (isBeingEvaluatedForFirstPixel())
            myRandomColor = ColorCombinations.random();
        return myRandomColor;
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
    
    private RandomParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new RandomParenExpression());
    }
    
}

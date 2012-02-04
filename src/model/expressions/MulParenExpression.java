package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;

public class MulParenExpression extends ParenExpression
{
    
    private static final String myType = "mul";
    private static final int myMinNumberOfOperands = 2;
    private static final int myMaxNumberOfOperands = 2;
    
    
    public MulParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new MulParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor left = myOperands.get(0).evaluate();
        RGBColor right = myOperands.get(1).evaluate();
        return ColorCombinations.multiply(left, right);
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
    
    private MulParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new MulParenExpression());
    }
    
}

package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;

public class ModParenExpression extends ParenExpression
{
    
    private static final String myType = "mod";
    private static final int myMinNumberOfOperands = 2;
    private static final int myMaxNumberOfOperands = 2;
    
    public ModParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new ModParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor left = myOperands.get(0).evaluate();
        RGBColor right = myOperands.get(1).evaluate();
        return ColorCombinations.modulus(left, right);
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
    
    private ModParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new ModParenExpression());
    }
    
}

package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;


public class MulParenExpression extends ParenExpression
{

    private static final String myType = "mul";
    private static final String mySymbol = "*";
    private static final int myMinNumberOfOperands = 2;
    private static final int myMaxNumberOfOperands = 2;

    public MulParenExpression (List<Expression> operands)
    {
        super(operands);
    }

    @Override
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType) || command.equals(mySymbol));
    }

    @Override
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new MulParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor left = myOperands.get(0).evaluate();
        RGBColor right = myOperands.get(1).evaluate();
        return ColorCombinations.multiply(left, right);
    }

    @Override
    protected String getType ()
    {
        return myType;
    }
    
    @Override
    protected String getSymbol ()
    {
        return mySymbol;
    }

    @Override
    protected int getMinNumberOfOperands ()
    {
        return myMinNumberOfOperands;
    }

    @Override
    protected int getMaxNumberOfOperands ()
    {
        return myMaxNumberOfOperands;
    }

    private MulParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new MulParenExpression());
    }

}

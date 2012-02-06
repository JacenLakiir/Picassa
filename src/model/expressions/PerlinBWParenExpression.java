package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.PerlinNoise;


public class PerlinBWParenExpression extends ParenExpression
{

    private static final String myType = "perlinBW";
    private static final int myMinNumberOfOperands = 2;
    private static final int myMaxNumberOfOperands = 2;

    public PerlinBWParenExpression (List<Expression> operands)
    {
        super(operands);
    }

    @Override
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }

    @Override
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new PerlinBWParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor left = myOperands.get(0).evaluate();
        RGBColor right = myOperands.get(1).evaluate();
        return PerlinNoise.greyNoise(left, right);
    }

    @Override
    protected String getType ()
    {
        return myType;
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

    private PerlinBWParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new PerlinBWParenExpression());
    }

}

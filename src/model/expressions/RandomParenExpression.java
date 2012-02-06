package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;


public class RandomParenExpression extends ParenExpression
{

    private static final String myType = "random";
    private static final int myMinNumberOfOperands = 0;
    private static final int myMaxNumberOfOperands = 0;
    private RGBColor myRandomColor;

    public RandomParenExpression (List<Expression> operands)
    {
        super(operands);
        myRandomColor = ColorCombinations.random();
    }

    @Override
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }

    @Override
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new RandomParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        return myRandomColor;
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

    private RandomParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new RandomParenExpression());
    }

}

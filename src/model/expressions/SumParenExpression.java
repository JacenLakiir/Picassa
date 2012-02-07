package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;


public class SumParenExpression extends ParenExpression
{

    private static final String myType = "sum";
    private static final int myMinNumberOfOperands = 0;
    private static final int myMaxNumberOfOperands = Integer.MAX_VALUE;

    public SumParenExpression (List<Expression> operands)
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
        return new SumParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor sumColor = new RGBColor(0);
        for (int i = 0; i < myOperands.size(); i++)
            sumColor = ColorCombinations.add(sumColor, myOperands.get(i).evaluate());
        return sumColor;
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

    private SumParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new SumParenExpression());
    }

}

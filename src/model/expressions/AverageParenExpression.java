package model.expressions;

import java.util.ArrayList;
import java.util.List;
import model.RGBColor;


public class AverageParenExpression extends ParenExpression
{

    private static final String myType = "average";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = Integer.MAX_VALUE;

    public AverageParenExpression (List<Expression> operands)
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
        return new AverageParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        List<RGBColor> colors = new ArrayList<RGBColor>();
        for (int i = 0; i < myOperands.size(); i++)
            colors.add(myOperands.get(i).evaluate());
        return RGBColor.average(colors);
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

    private AverageParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new AverageParenExpression());
    }

}

package model.expressions;

import java.util.ArrayList;
import java.util.List;
import model.RGBColor;


public class MinParenExpression extends ParenExpression
{

    private static final String myType = "min";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = Integer.MAX_VALUE;

    public MinParenExpression (List<Expression> operands)
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
        return new MinParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        List<RGBColor> colors = new ArrayList<RGBColor>();
        for (int i = 0; i < myOperands.size(); i++)
            colors.add(myOperands.get(i).evaluate());
        return RGBColor.minimum(colors);
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

    private MinParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new MinParenExpression());
    }

}

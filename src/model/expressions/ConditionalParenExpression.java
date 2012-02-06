package model.expressions;

import java.util.List;
import model.RGBColor;


public class ConditionalParenExpression extends ParenExpression
{

    private static final String myType = "if";
    private static final int myMinNumberOfOperands = 3;
    private static final int myMaxNumberOfOperands = 3;

    public ConditionalParenExpression (List<Expression> operands)
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
        return new ConditionalParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor testCondition = myOperands.get(0).evaluate();
        if (RGBColor.computeValue(testCondition) > 0)
            return myOperands.get(1).evaluate();
        else return myOperands.get(2).evaluate();
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

    private ConditionalParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new ConditionalParenExpression());
    }

}

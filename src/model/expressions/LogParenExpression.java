package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorTransformations;


public class LogParenExpression extends ParenExpression
{

    private static final String myType = "log";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = 1;

    public LogParenExpression (List<Expression> operands)
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
        return new LogParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor color = myOperands.get(0).evaluate();
        return ColorTransformations.naturalLog(color);
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

    private LogParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new LogParenExpression());
    }

}

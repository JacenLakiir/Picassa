package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorTransformations;


public class NegParenExpression extends ParenExpression
{

    private static final String myType = "neg";
    private static final String mySymbol = "!";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = 1;

    public NegParenExpression (List<Expression> operands)
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
        return new NegParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor color = myOperands.get(0).evaluate();
        return ColorTransformations.negate(color);
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

    private NegParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new NegParenExpression());
    }

}

package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;


public class ProductParenExpression extends ParenExpression
{

    private static final String myType = "product";
    private static final int myMinNumberOfOperands = 2;
    private static final int myMaxNumberOfOperands = Integer.MAX_VALUE;

    public ProductParenExpression (List<Expression> operands)
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
        return new ProductParenExpression(operands);
    }

    @Override
    public RGBColor evaluate ()
    {
        RGBColor color1 = myOperands.get(0).evaluate();
        RGBColor color2 = myOperands.get(1).evaluate();
        RGBColor sumColor = ColorCombinations.multiply(color1, color2);
        for (int i = 2; i < myOperands.size(); i++)
            sumColor = ColorCombinations.multiply(sumColor, myOperands.get(i).evaluate());
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

    private ProductParenExpression ()
    {}

    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new ProductParenExpression());
    }

}

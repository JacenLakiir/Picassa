package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorCombinations;


public class ProductParenExpression extends ParenExpression
{

    private static final String myType = "product";
    private static final int myMinNumberOfOperands = 0;
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
        RGBColor productColor = new RGBColor(1);
        for (int i = 0; i < myOperands.size(); i++)
            productColor = ColorCombinations.multiply(productColor, myOperands.get(i).evaluate());
        return productColor;
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

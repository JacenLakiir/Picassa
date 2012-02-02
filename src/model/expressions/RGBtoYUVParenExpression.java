package model.expressions;

import java.util.List;
import model.RGBColor;
import model.util.ColorModel;

public class RGBtoYUVParenExpression extends ParenExpression
{
    
    private static final String myType = "rgbToYCrCb";
    private static final int myMinNumberOfOperands = 1;
    private static final int myMaxNumberOfOperands = 1;
   
    
    public RGBtoYUVParenExpression (List<Expression> operands)
    {
        super(operands);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        checkNumberOfOperands(operands.size());
        return new RGBtoYUVParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor color = myOperands.get(0).evaluate();
        return ColorModel.rgb2ycrcb(color);
    }
    
    protected String getType ()
    {
        return myType;
    }
    
    protected int getMinNumberOfOperands ()
    {
        return myMinNumberOfOperands;
    }
    
    protected int getMaxNumberOfOperands ()
    {
        return myMaxNumberOfOperands;
    }
    
    private RGBtoYUVParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new RGBtoYUVParenExpression());
    }
    
}

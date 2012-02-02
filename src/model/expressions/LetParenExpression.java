package model.expressions;

import java.util.List;
import model.Model;
import model.ParserException;
import model.ParserException.Type;
import model.RGBColor;

public class LetParenExpression extends ParenExpression
{
    
    private static final String myType = "let";
    private static final int myMinNumberOfOperands = 3;
    private static final int myMaxNumberOfOperands = 3;
    private static VariableExpression myVariableExpression;
    private static RGBColor myColor;
   
    
    public LetParenExpression (List<Expression> operands)
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
        return new LetParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        if (isBeingEvaluatedForFirstPixel())
            parseTemporaryVariable();
        evaluateColor();
        return myColor;
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
    
    private void parseTemporaryVariable ()
    {
        if (isCorrectInstance(myOperands.get(0), VariableExpression.class))
            myVariableExpression = (VariableExpression) myOperands.get(0);
        else
            throw new ParserException("Invalid variable name given", Type.BAD_SYNTAX);
    }
    
    private void evaluateColor ()
    {
        RGBColor tempVarColor = myOperands.get(1).evaluate();
        Model.storeMapping(myVariableExpression.getVariable(), tempVarColor);  
        myColor = myOperands.get(2).evaluate();
        Model.removeMapping(myVariableExpression.getVariable());
    }
    
    private LetParenExpression () { }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new LetParenExpression());
    }
    
}

package model.expressions;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.RGBColor;

public class LetParenExpression extends ParenExpression
{
    
    private static final String myType = "let";
    private static final int myMinNumberOfOperands = 3;
    private static final int myMaxNumberOfOperands = 3;
    private static final List<Class<?>> myOperandTypes = initializeOperandTypes();
    private VariableExpression myVariableExpression;
    private RGBColor myColor;
    
    public LetParenExpression (List<Expression> operands)
    {
        super(operands);
        myVariableExpression = (VariableExpression) myOperands.get(0);
    }
    
    protected boolean matchesCommand (String command)
    {
        return (command.equals(myType));
    }
    
    protected ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return new LetParenExpression(operands);
    }
    
    public RGBColor evaluate ()
    {
        RGBColor tempVarColor = myOperands.get(1).evaluate();
        Model.storeMapping(myVariableExpression.getVariable(), tempVarColor);  
        myColor = myOperands.get(2).evaluate();
        Model.removeMapping(myVariableExpression.getVariable());
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
    
    protected <T> List<Class<?>> getOperandTypes ()
    {
        return myOperandTypes;
    }

    private LetParenExpression () { }

    private static <T> List<Class<?>> initializeOperandTypes ()
    {
        List<Class<?>> operandTypes = new ArrayList<Class<?>>();
        operandTypes.add(VariableExpression.class);
        operandTypes.add(Expression.class);
        operandTypes.add(Expression.class);
        return operandTypes;
    }
    
    protected static ExpressionFactory getParenFactory ()
    {
        return new ExpressionFactory(new LetParenExpression());
    }
    
}

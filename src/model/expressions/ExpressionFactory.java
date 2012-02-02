package model.expressions;

import java.util.List;


public class ExpressionFactory
{
    public Expression myExpression;
    public ParenExpression myParenExpression;

    public ExpressionFactory () { }
    
    public ExpressionFactory (Expression expression)
    {
        myExpression = expression;
    }
    
    public ExpressionFactory (ParenExpression parenExpression)
    {
        myExpression = parenExpression;
        myParenExpression = parenExpression;
    }
    
    public boolean isThisTypeOfExpression (String parseableString)
    {
        return myExpression.isThisTypeOfExpression(parseableString);
    }
    
    public Expression parseExpression (String parseableString)
    {
        return myExpression.parseExpression(parseableString);
    }
    
    public boolean matchesCommand (String command)
    {
        return myParenExpression.matchesCommand(command);
    }
    
    public ParenExpression createThisTypeOfParenExpression (List<Expression> operands)
    {
        return myParenExpression.createThisTypeOfParenExpression(operands);
    }
    
}

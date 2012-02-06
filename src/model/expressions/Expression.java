package model.expressions;

import model.Model;
import model.Parser;
import model.RGBColor;


public abstract class Expression
{
    public static final String myType = "Expression";
    protected static Parser myParser = Parser.getInstance();
    protected static Model myModel = Model.getInstance();

    public Expression ()
    {}

    public abstract boolean isThisTypeOfExpression (String parseableString);

    public abstract Expression parseExpression (String parseableString);

    public abstract RGBColor evaluate ();

}

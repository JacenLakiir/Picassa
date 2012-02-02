package model;

import java.awt.Dimension;
import java.util.TreeMap;
import model.ParserException.Type;
import model.expressions.Expression;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
    // single instance of model
    private static Model myModel;

    // state of the model
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;    
    private static TreeMap<String, RGBColor> variableMap;
    
    private Model () { }
    
    public static Model getInstance ()
    {
        if (myModel == null)
        {
            myModel = new Model();
            variableMap = new TreeMap<String, RGBColor>();
        }
        return myModel;
    }

    /**
     * Evaluate an expression for each point in the image.
     */
    public Pixmap evaluate (String input, Dimension size)
    {
        Pixmap result = new Pixmap(size);
        // create expression to evaluate just once
        Expression toEval = Parser.getInstance().makeExpression(input);
        // evaluate at each pixel
        for (int imageY = 0; imageY < size.height; imageY++)
        {
            variableMap.put("y", new RGBColor(imageToDomainScale(imageY, size.height)));
            for (int imageX = 0; imageX < size.width; imageX++)
            {
                variableMap.put("x", new RGBColor(imageToDomainScale(imageX, size.width)));
                result.setColor(imageX, imageY,
                                toEval.evaluate().toJavaColor());
            }
        }
        return result;
    }


    public static RGBColor getValue (String variable)
    {
        RGBColor value = variableMap.get(variable);
        if (value == null)
            throw new ParserException("Undefined variable: " + variable, Type.UNDEFINED_VARIABLE);
        return value;
    }
    
    public static void storeMapping (String variable, RGBColor color)
    {
        variableMap.put(variable, color);
    }
    
    public static void removeMapping (String variable)
    {
        variableMap.remove(variable);
    }
    
    /**
     * Convert from image space to domain space.
     */
    private double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double)value / bounds) * range + DOMAIN_MIN;
    }
}

package model.util;

import model.RGBColor;

/**
 * Simple utility class for transforming colors through various functions.
 */
public class ColorTransformations
{
    /**
     * Negates a given color.
     */
    public static RGBColor negate (RGBColor color)
    {
        return new RGBColor(-1 * color.getRed(),
                            -1 * color.getGreen(),
                            -1 * color.getBlue());
    }
    
    /**
     * Applies the floor function to the RGB components
     * of a color.
     */
    public static RGBColor floor (RGBColor color)
    {
        return new RGBColor(Math.floor(color.getRed()),
                            Math.floor(color.getGreen()),
                            Math.floor(color.getBlue()));
    }
    
    /**
     * Applies the ceiling function to the RGB components
     * of a color.
     */
    public static RGBColor ceiling (RGBColor color)
    {
        return new RGBColor(Math.ceil(color.getRed()),
                            Math.ceil(color.getGreen()),
                            Math.ceil(color.getBlue()));
    }
    
    /**
     * Takes the absolute value of the RGB components
     * of a color.
     */
    public static RGBColor absoluteValue (RGBColor color)
    {
        return new RGBColor(Math.abs(color.getRed()),
                            Math.abs(color.getGreen()),
                            Math.abs(color.getBlue()));
    }
    
    /**
     * Applies the sine function to the RGB components
     * of a color.
     */
    public static RGBColor sine (RGBColor color)
    {
        return new RGBColor(Math.sin(color.getRed()),
                            Math.sin(color.getGreen()),
                            Math.sin(color.getBlue()));
    }
    
    /**
     * Applies the cosine function to the RGB components
     * of a color.
     */
    public static RGBColor cosine (RGBColor color)
    {
        return new RGBColor(Math.cos(color.getRed()),
                            Math.cos(color.getGreen()),
                            Math.cos(color.getBlue()));
    }
    
    /**
     * Applies the tangent function to the RGB components
     * of a color.
     */
    public static RGBColor tangent (RGBColor color)
    {
        return new RGBColor(Math.tan(color.getRed()),
                            Math.tan(color.getGreen()),
                            Math.tan(color.getBlue()));
    }
    
    /**
     * Applies the arctangent function to the RGB components
     * of a color.
     */
    public static RGBColor arctangent (RGBColor color)
    {
        return new RGBColor(Math.atan(color.getRed()),
                            Math.atan(color.getGreen()),
                            Math.atan(color.getBlue()));
    }
    
    /**
     * Applies the natural logarithm function to the RGB
     * components of a color.
     */
    public static RGBColor naturalLog (RGBColor color)
    {
        return new RGBColor(Math.log(color.getRed()),
                            Math.log(color.getGreen()),
                            Math.log(color.getBlue()));
    }
}

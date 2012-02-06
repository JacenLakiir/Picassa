package model.util;

import model.RGBColor;


/**
 * Combine two colors by combining their components. This is a separate class
 * from color since it is just one set of ways to combine colors, many may exist
 * and we do not want to keep modifying the RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations
{
    /**
     * Combine two colors by adding their components.
     */
    public static RGBColor add (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() + right.getRed(),
                            left.getGreen() + right.getGreen(),
                            left.getBlue() + right.getBlue());
    }

    /**
     * Combine two colors by subtracting their components.
     */
    public static RGBColor subtract (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() - right.getRed(),
                            left.getGreen() - right.getGreen(),
                            left.getBlue() - right.getBlue());
    }

    /**
     * Combine two colors by multiplying their components.
     */
    public static RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(),
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }

    /**
     * Combine two colors by dividing their components.
     */
    public static RGBColor divide (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() / right.getRed(),
                            left.getGreen() / right.getGreen(),
                            left.getBlue() / right.getBlue());
    }

    /**
     * Combine two colors by taking the modulus of their components.
     */
    public static RGBColor modulus (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() % right.getRed(),
                            left.getGreen() % right.getGreen(),
                            left.getBlue() % right.getBlue());
    }

    /**
     * Combine two colors by taking the first color's components (bases) and
     * raising them to the corresponding components of the second color
     * (powers).
     */
    public static RGBColor exponent (RGBColor left, RGBColor right)
    {
        return new RGBColor(Math.pow(left.getRed(), right.getRed()),
                            Math.pow(left.getGreen(), right.getGreen()),
                            Math.pow(left.getBlue(), right.getBlue()));
    }

    /**
     * Creates a color using the RGB components of colors 1, 2, and 3
     * respectively.
     */
    public static RGBColor color (RGBColor color1, RGBColor color2, RGBColor color3)
    {
        return new RGBColor(color1.getRed(), color2.getBlue(), color3.getGreen());
    }

}

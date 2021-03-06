package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.awt.Color;
import model.Model;
import model.Parser;
import model.ParserException;
import model.RGBColor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(0);
    private static final RGBColor GRAYSCALE = new RGBColor(0.5);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);

    private Parser myParser;
    private Model myModel;

    @Before
    public void setUp ()
    {
        // initialize stuff here
        myParser = Parser.getInstance();
        myModel = Model.getInstance();
        myModel.storeMapping("x", new RGBColor(-1));
        myModel.storeMapping("y", new RGBColor(-1));
        myModel.storeMapping("t", new RGBColor(-1));
    }

    @Test
    public void testConstant ()
    {
        // extreme color values
        runTest(WHITE, "  1");
        runTest(BLACK, " -1");
        // simple color examples
        runTest(GRAYSCALE, "0.5");
        runTest(GRAYSCALE, " .5");
        runTest(GRAYSCALE, "0.50000");
    }

    @Test
    public void testUnaryOps ()
    {
        runTest(WHITE, "(neg -1)");
        runTest(BLACK, "(! 1)");
        runTest(WHITE, "(abs -1)");
        runTest(BLACK, "(clamp -100.5  )");
        runTest(WHITE, "(wrap   3)");
    }

    @Test
    public void testBinaryOps ()
    {
        runTest(WHITE, "(plus .1 .9)");
        runTest(WHITE, "(plus (plus 0.01 0.09) (plus 0.4 0.5))");
        runTest(WHITE, "    (plus(plus 0.01 0.09)(plus 0.4 0.5   ))    ");
        runTest(WHITE, "    (+(plus 0.01 0.09)(+ 0.4 0.5   ))    ");
        runTest(WHITE, "(minus (div 1.8 2) (mul -10 0.01))");
        runTest(WHITE, "(- (/ 1.8 2) (* -10 0.01))");
        runTest(WHITE, "(mod 4 3)");
        runTest(WHITE, "(% 4 3)");
        runTest(WHITE, "(exp -1 0)");
        runTest(WHITE, "(^ -1 0)");
    }

    @Test
    public void testTrinaryOps ()
    {
        runTest(BLACK, "(color (color -1 -1 -1) -1 (color (color -1 -1 -1) -1 -1)) ");
        runTest(GRAYSCALE, "(color 0.5 (color 0.5 0.5 (color .5 0.5 .5) ) .5)");
        runTest(WHITE, "(let myVar -1 (mul myVar myVar))");
        runTest(GRAYSCALE, "(let foo .6 (let bar -0.1 (plus foo bar)))");
        runTest(WHITE, "(let foo 0.2 (plus foo (let foo 0.8 foo)))");
        runTest(GRAYSCALE, "(if 0 x 0.5)");
        runTest(WHITE, "(if (color 0.5 (color 0.5 0.5 (color .5 0.5 .5) ) .5) 1 y)");
        runTest(BLACK, "(if (clamp (! 100.5 ) ) (product y x 2) -1)");
    }

    @Test
    public void testInfiniteOps ()
    {
        runTest(GRAYSCALE, "(sum 0.2 0.2 0.05 .03 0.01 .01)");
        runTest(GRAYSCALE, "(product 1 1 0.5 -1 (! 1))");
        runTest(GRAYSCALE,
                "(average (color 1 1 1) (color (! 1) (neg 1) -1) (color 1 1 1) (color 1 1 1))");
        runTest(BLACK, "(min (mul x x) y 0 (plus .1 0.9) -1)");
        runTest(WHITE, "(max (mul x x) y 0 (plus .1 0.9) -1)");
        runTest(BLACK, "(average (mul -1 1))");
        runTest(WHITE, "(min   1)");
        runTest(GRAYSCALE, "(max .5  )");
        runTest(GRAY, "(sum )");
        runTest(WHITE, "(sum 1)");
        runTest(WHITE, "(product)");
        runTest(GRAY, "(product 0    )");
    }

    @Test
    public void testExceptions ()
    {
        // runExceptionalTest(ParserException.Type.BAD_SYNTAX, "--1");
        runExceptionalTest(ParserException.Type.BAD_SYNTAX, "(+plus 0.1 0.9)");
        runExceptionalTest(ParserException.Type.EXTRA_CHARACTERS, "0.5 0.5");
        runExceptionalTest(ParserException.Type.UNKNOWN_COMMAND, "(fooo 0.1 0.9)");
        runExceptionalTest(ParserException.Type.UNDEFINED_VARIABLE,
                           "(plus (let foo .5 (plus bar bar)) .5)");
        runExceptionalTest(ParserException.Type.BAD_SYNTAX, "(average)");
    }

    @After
    public void tearDown ()
    {
        myModel.removeMapping("x");
        myModel.removeMapping("y");
        myModel.removeMapping("t");
    }

    private void runExceptionalTest (ParserException.Type type, String expression)
    {
        try
        {
            myParser.makeExpression(expression).evaluate();
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            assertEquals(type, e.getType());
        }
    }

    private void runTest (RGBColor expected, String expression)
    {
        RGBColor actual = myParser.makeExpression(expression).evaluate();
        assertTrue(actual.equals(expected));
    }
}

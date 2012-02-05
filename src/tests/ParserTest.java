package tests;

import java.awt.Color;
import model.Model;
import model.Parser;
import model.ParserException;
import model.RGBColor;
import static org.junit.Assert.*;
import org.junit.*;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(0.5);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);

    
    private Parser myParser;
    @SuppressWarnings("unused")
    private Model myModel;


    @Before
    public void setUp ()
    {
        // initialize stuff here
        myParser = Parser.getInstance();
        myModel = Model.getInstance();
        Model.storeMapping("x", new RGBColor(-1));
        Model.storeMapping("y", new RGBColor(-1));
    }

    @Test
    public void testConstant ()
    {
        // extreme color values
        runTest(WHITE, "  1");
        runTest(BLACK, " -1");
        // simple color examples
        runTest(GRAY,  "0.5");
        runTest(GRAY,  " .5");
        runTest(GRAY,  "0.50000");
    }

    @Test
    public void testUnaryOps ()
    {
        runTest(WHITE, "(neg -1)");
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
        runTest(WHITE, "(minus (div 1.8 2) (mul -10 0.01))");
        runTest(WHITE, "(mod 4 3)");
        runTest(WHITE, "(exp -1 0)");
    }
    
    @Test
    public void testTrinaryOps ()
    {
        runTest(BLACK, "(color (color -1 -1 -1) -1 (color (color -1 -1 -1) -1 -1)) ");
        runTest(GRAY, "(color 0.5 (color 0.5 0.5 (color .5 0.5 .5) ) .5)");
        runTest(WHITE, "(let myVar -1 (mul myVar myVar))");
        runTest(GRAY, "(let foo .6 (let bar -0.1 (plus foo bar)))");
        runTest(WHITE, "(let foo 0.2 (plus foo (let foo 0.8 foo)))");   
    }
    
    @Test
    public void testExceptions ()
    {
//        runExceptionalTest(ParserException.Type.BAD_SYNTAX, "--1");
        runExceptionalTest(ParserException.Type.EXTRA_CHARACTERS, "0.5 0.5");
        runExceptionalTest(ParserException.Type.UNKNOWN_COMMAND,  "(fooo 0.1 0.9)");
        runExceptionalTest(ParserException.Type.UNDEFINED_VARIABLE, "(plus (let foo .5 (plus bar bar)) .5)");
    }
    
    @After
    public void tearDown ()
    {
        Model.removeMapping("x");
        Model.removeMapping("y");
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

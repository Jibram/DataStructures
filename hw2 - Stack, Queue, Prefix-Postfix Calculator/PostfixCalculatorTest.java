/*
 * Feel free to add your own tests here.
 *
 * You can find a tutorial on junit at
 *     https://www.tutorialspoint.com/junit/index.htm
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.AfterClass;

public class PostfixCalculatorTest
{
    @Test
    public void testBasic()
    {
        PostfixCalculator calc = new PostfixCalculator();

        String exp = "15 7 1 1 + - x 3 x 2 1 1 + + -";
        assertEquals("Didn't evaluate correctly", 221, calc.evaluate(exp));
    }
}

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

public class PrefixCalculatorTest
{
    @Test
    public void testBasic()
    {
        PrefixCalculator calc = new PrefixCalculator();

        String exp = "- x x 15 - 7 + 1 1 3 + 2 + 1 1";
        assertEquals("Did not evaluate correctly", 221, calc.evaluate(exp));
    }
}

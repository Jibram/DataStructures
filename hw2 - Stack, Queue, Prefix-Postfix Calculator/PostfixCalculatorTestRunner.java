/*
 * You should not edit this file
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class PostfixCalculatorTestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(PostfixCalculatorTest.class);

        for(Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.exit(0);
    }
}

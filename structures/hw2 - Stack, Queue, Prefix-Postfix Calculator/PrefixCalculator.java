/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class PrefixCalculator
{

   /*
    * Instance variables
    *
    * You should not add anything here or change the existing ones.
    *
    * You do not need to use all of these
    */
    Queue<String> sq;
    Queue<Integer> iq;
    Stack<String> ss;
    Stack<Integer> is;

    /*
     * Constructor for the QueueSolver
     *
     * Initialize the instance variables to their default values
     * You do not have to initialize variables you will not use
     */
    public PrefixCalculator()
    {
        ss = new Stack<String>();
        is = new Stack<Integer>();
    }

    /*
     * solve
     *
     * Evaluate the expression and return the result
     *
     * exp - String; the string representation of the prefix expression
     *
     * Note: We guarantee exp will always be a valid prefix expression of
     *       length >= 1. Only operators +, -, and x will be used and only
     *       non-negative integers will be used as terms. Terms and operators
     *       will be separated by spaces, for example "+ 5 3". At no point in
     *       the evaluation will an intermediate value be large enough to
     *       overflow the Java int type. However, it can go below 0.
     */

    public int evaluate(String exp)
    {
        char tempChar;
        String tempString, tempString2;
        int tempInteger;
        int x, y;
        for (int i = exp.length() - 1; i > -1; i--)
        {
            tempChar = exp.charAt(i);
            if (tempChar == '+')
            {
                x = is.pop();
                y = is.pop();
                is.push(x + y);
            }
            
            else if (tempChar == '-')
            {
                x = is.pop();
                y = is.pop();
                is.push(x - y);
            }
                        
            else if (tempChar == 'x')
            {
                x = is.pop();
                y = is.pop();
                is.push(x * y);
            }
            
            else if (tempChar == ' ')
            {
                continue;
            }

            else
            {
                tempString = "";
                while (tempChar != ' ')
                {
                    tempString = tempChar + tempString;
                    i--;
                    tempChar = exp.charAt(i);
                }
                tempInteger = Integer.valueOf(tempString);
                System.out.println(tempInteger);
                is.push(tempInteger);
            }
        }  
        return is.pop();
    }
    // public int evaluate(String exp)
    // {
    //     boolean prevWasOperand = false;
    //     char tempChar;
    //     char tempOperator;
    //     String tempString;
    //     int tempInteger;
    //     int tempIntegerFromStack;

    //     for (int i = 0; i < exp.length(); i++)
    //     {
    //         tempChar = exp.charAt(i);
    //         if (tempChar == '+' || tempChar == '-' || tempChar == 'x')
    //         {
    //             ss.push(String.valueOf(tempChar));
    //             prevWasOperand = false;
    //         }

    //         else if (tempChar == ' ')
    //         {
    //             continue;
    //         }

    //         else
    //         {
    //             //Gets my a full number into a string.
    //             tempString = "";
    //             while (tempChar != ' ')
    //             {
    //                 tempString += tempChar;
    //                 i++;
    //                 if (i < exp.length()) {tempChar = exp.charAt(i);}
    //                 else {break;}
    //             }
    //             tempInteger = Integer.valueOf(tempString);
    //             System.out.println(tempInteger);
                
    //             if (prevWasOperand)
    //             {
    //                 while (!ss.isEmpty())
    //                 {
    //                     tempIntegerFromStack = is.pop();
    //                     tempOperator = ss.pop().charAt(0);
                        
    //                     if (tempOperator == '+')
    //                     {
    //                         System.out.println(tempIntegerFromStack + " + " + tempInteger);
    //                         tempInteger = tempIntegerFromStack + tempInteger;
    //                         System.out.println(tempInteger);
    //                     }
    
    //                     else if (tempOperator == '-')
    //                     {
    //                         System.out.println(tempIntegerFromStack + " - " + tempInteger);
    //                         tempInteger = tempIntegerFromStack - tempInteger;
    //                         System.out.println(tempInteger);
    //                     }
                        
    //                     else if (tempOperator == 'x')
    //                     {
    //                         System.out.println(tempIntegerFromStack + " * " + tempInteger);
    //                         tempInteger = tempIntegerFromStack * tempInteger;
    //                         System.out.println(tempInteger);
    //                     }
    //                 }
    //             }
    //             is.push(tempInteger);
    //             prevWasOperand = true;
    //         }
    //     }
    //     return is.pop();
    // }
}
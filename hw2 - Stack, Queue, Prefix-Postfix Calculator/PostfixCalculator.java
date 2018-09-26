/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

public class PostfixCalculator
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
     * Constructor for the StackSolver
     *
     * Initialize the instance variables to their default values
     * You do not have to initialize variables you will not use
     */
    public PostfixCalculator()
    {
        is = new Stack<Integer>();
    }


    /*
     * evaluate
     *
     * Evaluate the expression and return the result
     *
     * exp - String; the string representation of the postfix expression
     *
     * Note: We guarantee exp will always be a valid postfix expression of
     *       length >= 1. Only operators +, -, and * will be used and only
     *       non-negative integers will be used as terms. Terms and operators
     *       will be separated by spaces, for example "5 3 x". At no point in
     *       the evaluation will an intermediate value be large enough to
     *       overflow the Java int type. However, it can go below 0.
     */
    public int evaluate(String exp)
    {
        //Makes runtime faster to assign the variable once rather than checking the string's first member 3 seperate times
        char tempChar;
        String tempString;
        int x, y;
        for (int i = 0; i < exp.length(); i++)
        {
            tempChar = exp.charAt(i);
            if (tempChar == '+')
            {
                y = is.pop();
                x = is.pop();
                is.push(x + y);
            }
            
            else if (tempChar == '-')
            {
                y = is.pop();
                x = is.pop();
                is.push(x - y);
            }
                        
            else if (tempChar == 'x')
            {
                y = is.pop();
                x = is.pop();
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
                    tempString += tempChar;
                    i++;
                    tempChar = exp.charAt(i);
                }
                is.push(Integer.valueOf(tempString));
            }
        }  
        return is.pop();
    }
}
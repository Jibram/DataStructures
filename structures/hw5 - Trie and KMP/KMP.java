/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

import java.util.ArrayList;

import javax.net.ssl.ExtendedSSLSession;
/*
 * You should NOT import anything else.
 * 
 * For a quick summary of ArrayLists, see https://www.tutorialspoint.com/java/util/java_util_arraylist.htm
 */
public class KMP
{
    /*
    * Instance variables
    * You should not add any instance variables here
    *
    * failure - int[] - holds the failure function for the KMP algorithm
    */
    private int[] failure;
    private String pattern;

    /*
    * Constructor
    *
    * Initalize the instance variables here
    */
    public KMP(String pattern)
    {
        setFailure(pattern);
    }

    /*
    * getFailure
    *
    * return the failure function array
    */
    public int[] getFailure()
    {
        return failure;
    }

    /*
    * getPattern
    *
    * return the current pattern
    */
    public String getPattern()
    {
	    return pattern;
    }

    /*
    * setFailure
    *
    * Make a new failure function for the new pattern
    *
    * pattern - string - the new pattern for the KMP algorithm
    */
    public void setFailure(String pattern)
    {
        this.pattern = pattern;
        failure = new int[pattern.length()];
        for (int a = 0; a < failure.length; a++) {failure[a] = 0;}
        int charValue = 0;
        int i = 1;
        int j = 0;
        while (i < pattern.length())
        {
            charValue = 0 + pattern.charAt(i);
            if (pattern.charAt(i) == pattern.charAt(j) || pattern.charAt(i) == '*' || ((65 <= charValue && charValue <= 90) && (int)pattern.charAt(i) != charValue + 32))
            {
                failure[i] = j + 1;
                i++;
                j++;
            }
            else if (j > 0)
            {
                j = failure[j - 1];
            }
            else
            {
                failure[i] = 0;
                i++;
            }
        }
    }

    /*
    * match
    *
    * output whether or not the input string matches the pattern exactly
    *
    * input - string - the string to test
    */
    public boolean match(String input)
    {
        int n = input.length();
        int m = pattern.length();
        int charValue = 0;
        if (m == 0 || n != m) {return false;}
        int inputIndex = 0;
        int patternIndex = 0;
        while (inputIndex < n)
        {
            charValue = 0 + pattern.charAt(patternIndex);
            if (input.charAt(inputIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '*' || (65 <= charValue && charValue <= 90) && (int)input.charAt(inputIndex) != charValue + 32)
            {
                if (patternIndex == m - 1) {return true;}
                inputIndex++;
                patternIndex++;
            }
            else if (patternIndex > 0)
            {
                patternIndex = failure[patternIndex - 1];
            }   
        }
        return false;
    }

    /*
    * getMatchesInString
    *
    * return an arraylist of all indices in input where a substring
    * that matches our pattern ends
    *
    * input - string - the string to test
    */
    public ArrayList<Integer> getMatchesInString(String input)
    {
        ArrayList<Integer> listOfIndices = new ArrayList<Integer>();
        int n = input.length();
        int m = pattern.length();
        int charValue = 0;
        if (m == 0 || n == 0) {return listOfIndices;}
        int inputIndex = 0;
        int patternIndex = 0;
        while (inputIndex < n)
        {
            charValue = 0 + pattern.charAt(patternIndex);
            if (input.charAt(inputIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '*' || (65 <= charValue && charValue <= 90) && ((int)input.charAt(inputIndex) != charValue + 32))
            {
                if (patternIndex == m - 1) {listOfIndices.add(inputIndex); patternIndex = failure[patternIndex - 1];}
                inputIndex++;
                patternIndex++;
            }
            else if (patternIndex > 0)
            {
                patternIndex = failure[patternIndex - 1];
            }   
            else
            {
                inputIndex++;
            }
        }
        return listOfIndices;
    }

    /*
    *  countMatchesInString
    *
    * return the number of substrings of the input string that match
    * our pattern
    *
    * input - string - the string to test
    */
    public int countMatchesInString(String input)
    {
        return getMatchesInString(input).size();
    }
}

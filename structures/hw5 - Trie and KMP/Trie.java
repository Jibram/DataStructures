/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
/*
 * You should NOT import anything else.
 * 
 * For a quick summary of ArrayLists, see https://www.tutorialspoint.com/java/util/java_util_arraylist.htm
 * For a summary of HashMaps, Iterators, and HashSets please see the handout.
 */

public class Trie
{

    /*
    * Instance variables
    * You should not add any instance variables here
    *
    * root - TrieNode - holds the root of the trie
    * numWords - int - the number of words currently stored in the trie
    */
    private TrieNode root;
    private int numWords;


    /*
    * Constructor
    * Initalize the instance variables here
    *
    * root should always exist (and is not an output node by default)
    */
    public Trie()
    {
        root = new TrieNode(false);
        numWords = 0;
    }

    /*
    * insert
    *
    * Insert a string into the trie.
    * 
    * s - string - the string to be inserted
    */
    public void insert(String s)
    {
        TrieNode tempNode = root;
        for (int i = 0; i < s.length(); i++)
        {
            tempNode.addTransition(s.charAt(i));
            tempNode = tempNode.getTransition(s.charAt(i));
        }
        tempNode.setOutput(true);
        numWords++;
    }

    /*
    * insertMany
    *
    * Insert all strings from a list into the trie.
    * 
    * strings - String[] - the strings to be inserted
    */
    public void insertMany(String[] strings)
    {
        for (int i = 0; i < strings.length; i++)
        {
            insert(strings[i]);
        }
    }

    /*
    * contains
    *
    * Return whether or not a given string is stored in the trie
    * 
    * s - string - the string to be checked
    */
    public boolean contains(String s)
    {
        TrieNode tempNode = root;
        for (int i = 0; i < s.length(); i++)
        {
            if (tempNode.getTransition(s.charAt(i)) != null)
            {
                tempNode = tempNode.getTransition(s.charAt(i));
            }
            else
            {
                return false;
            }
        }
        return tempNode.getOutput();
    }

    /*
    * remove
    *
    * Remove a given string from the trie (if it is contained in the trie)
    * 
    * s - string - the string to be removed
    */
    public void remove(String s)
    {
        TrieNode tempNode = root;
        for (int i = 0; i < s.length(); i++)
        {
            if (tempNode.getTransition(s.charAt(i)) != null)
            {
                tempNode = tempNode.getTransition(s.charAt(i));
            }
            else
            {
                return;
            }
        }
        tempNode.setOutput(false);
        numWords--;
    }

    /*
    * getMatchesInString
    *
    * Return an arraylist containing all indices of the input string where a substring
    * contained in the trie ends
    * 
    * input - string - the string to be tested
    */
    public ArrayList<Integer> getMatchesInString(String input)
    {
        ArrayList<Integer> matches = new ArrayList<Integer>();
        // get every substring and check if it exists
        for (int i = 0; i < input.length(); i++)
        {
            for (int j = i; j > -1; j--)
            {
                if (contains(input.substring(i, j)))
                {
                    matches.add(i);
                    j = -1;
                }
            }
        }
        
        return matches;
    }

    /*
    * countMatchesInString
    *
    * Return the number of substrings in the input string that are contained in the trie
    * 
    * input - string - the string to be tested
    */
    public int countMatchesInString(String input)
    {
        int matches = 0;
        // get every substring and check if it exists
        for (int startIndex = 0; startIndex < input.length(); startIndex++)
        {
            for (int endIndex = 0; endIndex < input.length(); endIndex++)
            {
                if (endIndex >= startIndex)
                {
                    if (contains(input.substring(startIndex, endIndex)))
                    {
                        matches++;
                    }
                }
            }
        }
        
        return matches;
    }


    /*
    * getRoot
    *
    * Return the root of the trie
    */
    public TrieNode getRoot()
    {
        return root;
    }

    public class TrieNode
    {
        /*
        * TrieNode instance variables. You should not add any instance variables.
        *
        * children - HashMap<Character, TrieNode> - the outgoing transitions (mapped by character) of the node
        * output - boolean - whether or not the node is an output node
        */
        public HashMap<Character, TrieNode> children;
        public boolean output;

        /*
        * TrieNode constructor
        *
        * output - boolean - whether or not the node is an output node
        */
        public TrieNode(boolean output)
        {
            children = new HashMap<Character, TrieNode>();
            output = false;
        }

        /*
        * getOutput
        *
        * return the output instance variable
        */
        public boolean getOutput()
        {
            return output;
        }

        /*
        * getChildren
        *
        * return the children instance variable
        */
        public HashMap<Character, TrieNode> getChildren()
        {
            return children;
        }

        /*
        * setOutput
        *
        * set the output variable
        */
        public void setOutput(boolean output)
        {
            this.output = output;
        }

        /*
        * addTransition
        *
        * construct a new TrieNode and add a transition to it from the current node
        *
        * if the transition label already exists in children, you should just return null
        * without changing anything
        *
        * c - char - the transition label to add
        */
        public TrieNode addTransition(char c)
        {
            if (children.get(c) == null)
            {
                TrieNode newNode = new TrieNode(false);
                children.put(c, newNode);
                return newNode;
            }
            else
            {
                return null;
            }
        }

        /*
        * getTransition
        *
        * Return the TrieNode in children mapped to by c (or null if it does not exist).
        */
        public TrieNode getTransition(char c)
        {
            if (children.get(c) == null)
            {
                return null;
            }
            else
            {
                return children.get(c);
            }
        }
    }
}
/*
 * Feel free to add your own tests here.
 *
 * You can find a tutorial on junit at
 *     https://www.tutorialspoint.com/junit/index.htm
 */
import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import java.lang.Math;
import java.util.ArrayList;

import org.junit.AfterClass;

public class TreeTest
{

    private static int score;
    private static int max_score;

    @BeforeClass
    public static void init()
    {
        score = 0;
        max_score = 0;
    }

    @Before
    public void maxScore()
    {
        max_score += 1;
    }

    @AfterClass
    public static void after()
    {
        System.out.println("Tree Score: " + String.format("%d/%d", score, max_score));
        try{
            PrintWriter writer = new PrintWriter("Tree_score.txt", "UTF-8");
            writer.println(score);
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Something went wrong here");
        }
    }

    @Test(timeout=1000)
    public void testInit()
    {
        Tree<Integer> t = new Tree<Integer>();
        assertEquals("Size is wrong (1)", 0, t.getSize());
        assertNull("root is not set to null (1)", t.getRoot());
        
        score += 1;
    }

    @Test(timeout=1000)
    public void testkInit()
    {
        Tree<Integer> t = new Tree<Integer>(5);
        assertEquals("Size is wrong (2)", 0, t.getSize());
        assertNull("root is not set to null (2)", t.getRoot());
        
        score += 1;
    }


    @Test(timeout=1000)
    public void testBasic()
    {
        Tree<Integer> t = new Tree<Integer>();

        assertEquals("Size is wrong (1)", 0, t.getSize());
        assertNull("root is not set to null (1)", t.getRoot());

        for(int i = 1; i <= 10; i++)
        {
            t.insert(i);
            assertEquals("Size is wrong (2)", i, t.getSize());
            assertEquals("root is wrong (2)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (2)", Integer.valueOf(i), t.getLast().getData());
        }
        score += 1;
    }

    @Test(timeout=1000)
    public void testkBasic()
    {
        Tree<Integer> t = new Tree<Integer>(5);

        assertEquals("Size is wrong (1)", 0, t.getSize());
        assertNull("root is not set to null (1)", t.getRoot());

        for(int i = 1; i <= 10; i++)
        {
            t.insert(i);
            assertEquals("Size is wrong (2)", i, t.getSize());
            assertEquals("root is wrong (2)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (2)", Integer.valueOf(i), t.getLast().getData());
        }
        score += 1;
    }

    @Test(timeout=1000)
    public void testRemove()
    {
        Tree<Integer> t = new Tree<Integer>();

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 20; i >= 1; i--)
        {
            assertEquals("Size is wrong (1)", i, t.getSize());
            assertEquals("root is wrong (1)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (1)", Integer.valueOf(i), t.getLast().getData());
            t.remove();
        }

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 20; i >= 1; i--)
        {
            assertEquals("Size is wrong (1)", i, t.getSize());
            assertEquals("root is wrong (1)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (1)", Integer.valueOf(i), t.getLast().getData());
            t.remove();
        }
        score += 1;
    }

    @Test(timeout=1000)
    public void testkRemove()
    {
        Tree<Integer> t = new Tree<Integer>();

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 20; i >= 1; i--)
        {
            assertEquals("Size is wrong (1)", i, t.getSize());
            assertEquals("root is wrong (1)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (1)", Integer.valueOf(i), t.getLast().getData());
            t.remove();
        }

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 20; i >= 1; i--)
        {
            assertEquals("Size is wrong (1)", i, t.getSize());
            assertEquals("root is wrong (1)", Integer.valueOf(1), t.getRoot().getData());
            assertEquals("last is wrong (1)", Integer.valueOf(i), t.getLast().getData());
            t.remove();
        }
        score += 1;
    }

    @Test(timeout=1000)
    public void testPreorder()
    {
        Tree<Integer> t = new Tree<Integer>();

        int[] sol = {1,2,4,8,9,5,10,3,6,7};

        for(int i = 1; i <= 10; i++)
        {
            t.insert(i);
        }

        ArrayList<Tree<Integer>.Node<Integer>> res = t.preorder();

        for(int i = 0; i < sol.length; i++)
        {
            assertEquals("preorder is wrong", Integer.valueOf(sol[i]), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testInorder()
    {
        Tree<Integer> t = new Tree<Integer>();

        int[] sol = {8,4,9,2,10,5,1,6,3,7};

        for(int i = 1; i <= 10; i++)
        {
            t.insert(i);
        }

        ArrayList<Tree<Integer>.Node<Integer>> res = t.inorder();

        for(int i = 0; i < sol.length; i++)
        {
            assertEquals("inorder is wrong", Integer.valueOf(sol[i]), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testPostorder()
    {
        Tree<Integer> t = new Tree<Integer>();

        int[] sol = {8,9,4,10,5,2,6,7,3,1};

        for(int i = 1; i <= 10; i++)
        {
            t.insert(i);
        }

        ArrayList<Tree<Integer>.Node<Integer>> res = t.postorder();

        for(int i = 0; i < sol.length; i++)
        {
            assertEquals("postorder is wrong", Integer.valueOf(sol[i]), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testkPreorder()
    {
        Tree<Integer> t = new Tree<Integer>(3);

        int[] sol = {1,2,5,14,15,6,7,3,8,9,10,4,11,12,13};

        for(int i = 1; i <= 15; i++)
        {
            t.insert(i);
        }

        ArrayList<Tree<Integer>.Node<Integer>> res = t.preorder();

        for(int i = 0; i < sol.length; i++)
        {
            assertEquals("preorder is wrong", Integer.valueOf(sol[i]), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testkPostorder()
    {
        Tree<Integer> t = new Tree<Integer>(4);

        int[] sol = {22,23,24,25,6,26,27,28,29,7,30,8,9,2,10,11,12,13,3,14,15,16,17,4,18,19,20,21,5,1};
        

        for(int i = 1; i <= 30; i++)
        {
            t.insert(i);
        }

        ArrayList<Tree<Integer>.Node<Integer>> res = t.postorder();

        for(int i = 0; i < sol.length; i++)
        {
            assertEquals("postorder is wrong", Integer.valueOf(sol[i]), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testArrayRepresentation()
    {
        Tree<Character> t = new Tree<Character>();

        String sol = "abcdefghijklmnopqrstuvwxyz";

        for(int i = 0; i < 26; i++)
        {
            t.insert(sol.charAt(i));
        }

        ArrayList<Tree<Character>.Node<Character>> res = t.convertToArrayList();

        for(int i = 0; i < sol.length(); i++)
        {
            assertEquals("array representation is wrong", Character.valueOf(sol.charAt(i)), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=1000)
    public void testkArrayRepresentation()
    {
        Tree<Character> t = new Tree<Character>(6);

        String sol = "abcdefghijklmnopqrstuvwxyz";

        for(int i = 0; i < 26; i++)
        {
            t.insert(sol.charAt(i));
        }

        ArrayList<Tree<Character>.Node<Character>> res = t.convertToArrayList   ();

        for(int i = 0; i < sol.length(); i++)
        {
            assertEquals("array representation is wrong", Character.valueOf(sol.charAt(i)), res.get(i).getData());
        }

        score += 1;
    }

    @Test(timeout=5000)
    public void testIsPerfect()
    {
        Tree<Integer> t = new Tree<Integer>();
        int[] sol =  {1,3,7,15,31,63,123};
        int sol_counter = 0;
        for(int i = 1; i <= 100; i++)
        {
            t.insert(i);
            if(i == sol[sol_counter])
            {
                assertTrue("isPerfect is wrong (1)", t.isPerfect());
                sol_counter++;
            }
            
            else
            {
                assertFalse("isPerfect is wrong (2)", t.isPerfect());
            }
        }
        score += 1;
    }

    @Test(timeout=5000)
    public void testkIsPerfect()
    {
        Tree<Integer> t = new Tree<Integer>(4);
        int[] sol =  {1,5,21,85,341};
        int sol_counter = 0;
        for(int i = 1; i <= 150; i++)
        {
            t.insert(i);
            if(i == sol[sol_counter])
            {
                assertTrue("isPerfect is wrong (1)", t.isPerfect());
                sol_counter++;
            }
            
            else
            {
                assertFalse("isPerfect is wrong (2)", t.isPerfect());
            }
        }
        score += 1;
    }

    @Test(timeout=1000)
    public void testClear()
    {
        Tree<Integer> t = new Tree<Integer>();

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        assertEquals("size is wrong (1)", 20, t.getSize());

        t.clear();

        assertEquals("size is wrong (2)", 0, t.getSize());
        assertNull("root is wrong (1)", t.getRoot());

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }
        assertEquals("size is wrong (3)", 20, t.getSize());
        
        score += 1;
    }

    @Test(timeout=1000)
    public void testkClear()
    {
        Tree<Integer> t = new Tree<Integer>(6);

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        assertEquals("size is wrong (1)", 20, t.getSize());

        t.clear();

        assertEquals("size is wrong (2)", 0, t.getSize());
        assertNull("root is wrong (1)", t.getRoot());

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }
        assertEquals("size is wrong (3)", 20, t.getSize());
        
        score += 1;
    }

    @Test(timeout=5000)
    public void testContains()
    {
        Tree<Integer> t = new Tree<Integer>();

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 1; i <= 20; i++)
        {
           assertTrue("contains is wrong (1)", t.contains(i));
        }

        for(int i = 21; i < 100; i++)
        {
            assertFalse("contains is wrong (2)", t.contains(i));
        }

        for(int i = 20; i >= 1; i--)
        {
            assertTrue("contains is wrong (3)", t.contains(i));
            t.remove();
            assertFalse("contains is wrong (4)", t.contains(i));
        }

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        t.clear();
        for(int i = 1; i <= 20; i++)
        {
           assertFalse("contains is wrong (5)", t.contains(i));
        }
        
        score += 1;
    }

    @Test(timeout=5000)
    public void testkContains()
    {
        Tree<Integer> t = new Tree<Integer>(3);

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        for(int i = 1; i <= 20; i++)
        {
           assertTrue("contains is wrong (1)", t.contains(i));
        }

        for(int i = 21; i < 100; i++)
        {
            assertFalse("contains is wrong (2)", t.contains(i));
        }

        for(int i = 20; i >= 1; i--)
        {
            assertTrue("contains is wrong (3)", t.contains(i));
            t.remove();
            assertFalse("contains is wrong (4)", t.contains(i));
        }

        for(int i = 1; i <= 20; i++)
        {
            t.insert(i);
        }

        t.clear();
        for(int i = 1; i <= 20; i++)
        {
           assertFalse("contains is wrong (5)", t.contains(i));
        }
        
        score += 1;
    }

    @Test(timeout=1000)
    public void testDepth()
    {
        score += 1; //free point
        Tree<Integer> t = new Tree<Integer>();

        for(int i = 1; i <= 100; i++)
        {
            t.insert(i);
        }
        int res = t.getDepth();
        Tree<Integer>.Node<Integer> cur = t.getLast();
        int count = 1;
        while(cur != t.getRoot())
        {
            count++;
            cur = cur.getParent();
        }
        assertEquals("getDepth() is wrong (you will not lose points for this) (1)", count, res);
    
        t = new Tree<Integer>(5);
        
        for(int i = 1; i <= 150; i++)
        {
            t.insert(i);
        }
        res = t.getDepth();
        cur = t.getLast();
        count = 1;
        while(cur != t.getRoot())
        {
            count++;
            cur = cur.getParent();
        }
        assertEquals("getDepth() is wrong (you will not lose points for this) (2)", count, res);
    }
}

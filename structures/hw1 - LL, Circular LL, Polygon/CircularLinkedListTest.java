import java.io.*;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class CircularLinkedListTest
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
        System.out.println("CircularLinkedList Score: " + String.format("%d/%d", score, max_score));
        try{
            PrintWriter writer = new PrintWriter("CircularLinkedList_score.txt", "UTF-8");
            writer.println(score);
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Something went wrong here");
        }
    }

    @Test(timeout=1000)
    public void testCompile()
    {
        CircularLinkedList<Integer> cll;
        cll = new CircularLinkedList<Integer>();
        assertEquals("Initialization (size) is wrong", 0, cll.getSize());
        assertNull("Initialization (head) is wrong", cll.getHead());
        
        score += 1;
    }


    @Test(timeout=1000)
    public void testBasic()
    {
        CircularLinkedList<String> cll = new CircularLinkedList<String>();
        
        cll.insertAtHead("Hello");
        cll.insertAtHead("Data");
        cll.insertAtHead("Structures");

        assertEquals("size is wrong", 3, cll.getSize());
        assertEquals("head is wrong", String.valueOf("Structures"), cll.getHead().getData());

        score += 1;
    }

    @Test(timeout=1000)
    public void testInsert()
    {
        CircularLinkedList<String> cll = new CircularLinkedList<String>();

        cll.insert(0, "a");

        assertEquals("size is wrong (1)", 1, cll.getSize());
        assertNotNull("head.next incorrectly set (1)", cll.getHead().getNext());
        assertNotNull("head.prev incorrectly set (1)", cll.getHead().getPrev());
        assertTrue("head.next points to the wrong node (1)", cll.getHead().getNext() == cll.getHead());
        assertTrue("head.prev points to the wrong node (1)", cll.getHead().getPrev() == cll.getHead());


        cll.insert(0, "b");
        
        assertEquals("size is wrong (2)", 2, cll.getSize());
        assertEquals("head is incorrectly set (1)", String.valueOf("b"), cll.getHead().getData());
        assertNotNull("head.next incorrectly set (2)", cll.getHead().getNext());
        assertNotNull("head.prev incorrectly set (2)", cll.getHead().getPrev());
        assertTrue("head.next points to the wrong node (2)", cll.getHead().getNext() == cll.getHead().getPrev());
        assertTrue("head.prev points to the wrong node (2)", cll.getHead().getPrev() == cll.getHead().getNext());
        
        cll.insert(1, "c");
        
        assertEquals("size is wrong (3)", 3, cll.getSize());
        assertEquals("insert added at the wrong spot (1)", String.valueOf("b"), cll.getHead().getData());
        assertEquals("insert added at the wrong spot (2)", String.valueOf("a"), cll.getHead().getPrev().getData());

        cll.insert(100, "d");
        assertEquals("size is wrong (4)", 3, cll.getSize());
        assertEquals("insert added at the wrong spot (3)", String.valueOf("b"), cll.getHead().getData());
        assertEquals("insert added at the wrong spot (4)", String.valueOf("a"), cll.getHead().getPrev().getData());

        cll.insert(-20, "f");
        assertEquals("size is wrong (5)", 3, cll.getSize());
        assertEquals("insert added at the wrong spot (5)", String.valueOf("b"), cll.getHead().getData());
        assertEquals("insert added at the wrong spot (6)", String.valueOf("a"), cll.getHead().getPrev().getData());

        cll.insert(cll.getSize(), "test");
        assertEquals("size is wrong (6)", 4, cll.getSize());
        assertEquals("head is incorrectly set (2)", String.valueOf("b"), cll.getHead().getData());
        assertEquals("head.prev is incorrectly set (3)", String.valueOf("test"), cll.getHead().getPrev().getData());

        score += 1;
    }

    @Test(timeout=1000)
    public void testInsertHeadAndTail()
    {
        CircularLinkedList<Integer> cll = new CircularLinkedList<Integer>();

        cll.insertAtHead(5);

        assertEquals("size is wrong (1)", 1, cll.getSize());
        assertNotNull("head.next incorrectly set (1)", cll.getHead().getNext());
        assertNotNull("head.prev incorrectly set (1)", cll.getHead().getPrev());

        cll = new CircularLinkedList<Integer>();

        cll.insertAtHead(2);
        assertEquals("size is wrong (2)", 1, cll.getSize());
        assertNotNull("head not set properly (1)", cll.getHead());
        assertNotNull("head.next incorrectly set (2)", cll.getHead().getNext());
        assertNotNull("head.prev incorrectly set (2)", cll.getHead().getPrev());

        cll = new CircularLinkedList<Integer>();

        cll.insertAtHead(5);
        assertEquals("size is wrong (3)", 1, cll.getSize());
        assertEquals("Item inserted at the wrong place (1)", Integer.valueOf(5), cll.getHead().getData());

        cll.insertAtHead(2);
        assertEquals("size is wrong (4)", 2, cll.getSize());
        assertNotNull("head not set properly (2)", cll.getHead());
        assertNotNull("head.next incorrectly set (3)", cll.getHead().getNext());
        assertNotNull("head.prev incorrectly set (3)", cll.getHead().getPrev());
        assertEquals("Item inserted at the wrong place (2)", Integer.valueOf(2), cll.getHead().getData());
        assertEquals("head.next pointing to the wrong thing (1)", Integer.valueOf(5), cll.getHead().getNext().getData());
        assertEquals("head.prev pointing to the wrong thing (1)", Integer.valueOf(5), cll.getHead().getPrev().getData());

        cll.insertAtTail(10);

        assertEquals("size is wrong (5)", 3, cll.getSize());
        assertEquals("Item inserted at the wrong place (3)", Integer.valueOf(2), cll.getHead().getData());
        assertEquals("head.next pointing to the wrong thing (2)", Integer.valueOf(5), cll.getHead().getNext().getData());
        assertEquals("head.prev pointing to the wrong thing (2)", Integer.valueOf(10), cll.getHead().getPrev().getData());

        cll.insertAtTail(15);
    
        assertEquals("size is wrong (6)", 4, cll.getSize());
        assertEquals("Item inserted at the wrong place (4)", Integer.valueOf(2), cll.getHead().getData());
        assertEquals("head.next pointing to the wrong thing (3)", Integer.valueOf(5), cll.getHead().getNext().getData());
        assertEquals("head.prev pointing to the wrong thing (3)", Integer.valueOf(15), cll.getHead().getPrev().getData());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemove()
    {
        CircularLinkedList<String> cll = new CircularLinkedList<String>();
        
        cll.insertAtHead("Hello");
        cll.insertAtHead("Data");
        cll.insertAtHead("Structures");

        assertEquals("size is wrong (1)", 3, cll.getSize());

        cll.remove("Structures");
        assertEquals("size is wrong (2)", 2, cll.getSize());
        assertEquals("Removed the wrong node (1)", String.valueOf("Data"), cll.getHead().getData());
        assertEquals("head.next is wrong (1)", String.valueOf("Hello"), cll.getHead().getNext().getData());
        assertEquals("head.prev is wrong (1)", String.valueOf("Hello"), cll.getHead().getPrev().getData());

        cll = new CircularLinkedList<String>();

        cll.insertAtHead("Hello");
        cll.insertAtHead("Data");
        cll.insertAtHead("Structures");

        cll.remove("Hello");
        assertEquals("size is wrong (3)", 2, cll.getSize());
        assertEquals("Removed the wrong node (2)", String.valueOf("Structures"), cll.getHead().getData());
        assertEquals("head.next is wrong (2)", String.valueOf("Data"), cll.getHead().getNext().getData());
        assertEquals("head.prev is wrong (2)", String.valueOf("Data"), cll.getHead().getPrev().getData());

        cll = new CircularLinkedList<String>();

        cll.insertAtHead("Hello");
        cll.insertAtHead("Data");
        cll.insertAtHead("Structures");

        cll.remove("Data");
        assertEquals("size is wrong (4)", 2, cll.getSize());
        assertEquals("Removed the wrong node (3)", String.valueOf("Structures"), cll.getHead().getData());
        assertEquals("head.next is wrong (3)", String.valueOf("Hello"), cll.getHead().getNext().getData());
        assertEquals("head.prev is wrong (3)", String.valueOf("Hello"), cll.getHead().getPrev().getData());

        cll.remove("Hello");
        assertEquals("size is wrong (5)", 1, cll.getSize());
        assertEquals("Removed the wrong node (4)", String.valueOf("Structures"), cll.getHead().getData());
        assertEquals("head.next is wrong (4)", String.valueOf("Structures"), cll.getHead().getNext().getData());
        assertEquals("head.prev is wrong (4)", String.valueOf("Structures"), cll.getHead().getPrev().getData());

        cll.remove("Hello");
        assertEquals("size is wrong (6)", 1, cll.getSize());
        assertNotNull("head is null in non-empty list (1)", cll.getHead());
        assertEquals("head was changed when nothing was deleted (1)", String.valueOf("Structures"), cll.getHead().getData());
        assertEquals("head.next is wrong (5)", cll.getHead(), cll.getHead().getNext());
        assertEquals("head.prev is wrong (5)", cll.getHead(), cll.getHead().getPrev());

        cll.remove("Structures");
        assertEquals("size is wrong (7)", 0, cll.getSize());
        assertNull("head is not set to null (1)", cll.getHead());

        cll = new CircularLinkedList<String>();
        
        cll.insertAtHead("Hello");
        cll.insertAtHead("Data");
        cll.insertAtHead("Structures");

        cll.remove("Korea university");
        assertEquals("size is wrong (8)", 3, cll.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemoveMultiple()
    {
        
        CircularLinkedList<String> cll = new CircularLinkedList<String>();

        cll.insertAtHead("a");
        cll.insertAtHead("b");
        cll.insertAtHead("b");
        cll.insertAtHead("b");
        cll.insertAtHead("b");
        cll.insertAtHead("a");

        cll.remove("b");
        assertEquals("size is wrong (1)", 5, cll.getSize());

        cll.remove("a");
        assertEquals("size is wrong (2)", 4, cll.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemoveFromHeadAndTail()
    {
        
        CircularLinkedList<String> cll = new CircularLinkedList<String>();

        cll.insertAtTail("a");
        cll.insertAtTail("b");
        cll.insertAtTail("c");
        cll.insertAtTail("d");
        cll.insertAtTail("e");
        cll.insertAtTail("f");

        cll.removeFromTail();
        assertEquals("size is wrong (1)", 5, cll.getSize());
        assertEquals("Head is wrong (1)", String.valueOf("a"), cll.getHead().getData());
        assertEquals("head.prev is wrong (1)", String.valueOf("e"), cll.getHead().getPrev().getData());
        assertEquals("New tail is wrong (1)", String.valueOf("a"), cll.getHead().getPrev().getNext().getData());

        cll.removeFromHead();        
        assertEquals("size is wrong (2)", 4, cll.getSize());
        assertEquals("Head is wrong (2)", String.valueOf("b"), cll.getHead().getData());
        assertEquals("head.prev is wrong (2)", String.valueOf("e"), cll.getHead().getPrev().getData());
        assertTrue("tail.next is wrong (1)", cll.getHead().getPrev().getNext() == cll.getHead());

        cll = new CircularLinkedList<String>();
        cll.insertAtTail("a");
        cll.insertAtTail("b");

        cll.removeFromHead();
        assertEquals("size is wrong (3)", 1, cll.getSize());
        assertEquals("Head is wrong (2)", String.valueOf("b"), cll.getHead().getData());
        assertTrue("head.next is wrong (1)", cll.getHead() == cll.getHead().getNext());
        assertTrue("head.prev is wrong (3)", cll.getHead() == cll.getHead().getPrev());


        cll = new CircularLinkedList<String>();
        cll.insertAtTail("a");
        cll.insertAtTail("b");

        cll.removeFromTail();
        assertEquals("size is wrong (4)", 1, cll.getSize());
        assertEquals("Head is wrong (2)", String.valueOf("a"), cll.getHead().getData());
        assertTrue("head.next is wrong (2)", cll.getHead() == cll.getHead().getNext());
        assertTrue("head.prev is wrong (4)", cll.getHead() == cll.getHead().getPrev());

        cll.removeFromTail();
        assertEquals("size is wrong (5)", 0, cll.getSize());
        assertNull("head not null in empty list (1)", cll.getHead());

        cll = new CircularLinkedList<String>();
        cll.insertAtTail("a");
        cll.insertAtTail("b");

        cll.removeFromHead();
        cll.removeFromHead();
        assertEquals("size is wrong (6)", 0, cll.getSize());
        assertNull("head not null in empty list (2)", cll.getHead());

        score += 1;
    }

    @Test(timeout=1000)
    public void testContainsAndGetIndex()
    {
        
        CircularLinkedList<String> cll = new CircularLinkedList<String>();

        cll.insertAtHead("a");
        cll.insertAtHead("b");
        cll.insertAtHead("c");

        assertTrue("Contains is wrong (1)", cll.contains("a"));
        assertTrue("Contains is wrong (2)", cll.contains("b"));
        assertTrue("Contains is wrong (3)", cll.contains("c"));
        assertFalse("Contains is wrong (4)", cll.contains("Korea University"));

        assertEquals("getIndex is wrong (1)", 0, cll.getIndex("c"));
        assertEquals("getIndex is wrong (2)", 1, cll.getIndex("b"));
        assertEquals("getIndex is wrong (3)", 2, cll.getIndex("a"));
        assertEquals("getIndex is wrong (4)", -1, cll.getIndex("Korea University"));

        cll.remove("c");

        assertTrue("Contains is wrong (5)", cll.contains("a"));
        assertTrue("Contains is wrong (6)", cll.contains("b"));
        assertFalse("Contains is wrong (7)", cll.contains("c"));
        assertFalse("Contains is wrong (8)", cll.contains("Korea University"));

        assertEquals("getIndex is wrong (5)", -1, cll.getIndex("c"));
        assertEquals("getIndex is wrong (6)", 0, cll.getIndex("b"));
        assertEquals("getIndex is wrong (7)", 1, cll.getIndex("a"));
        assertEquals("getIndex is wrong (8)", -1, cll.getIndex("Korea University"));

        cll.remove("a");

        assertFalse("Contains is wrong (9)", cll.contains("a"));
        assertTrue("Contains is wrong (10)", cll.contains("b"));
        assertFalse("Contains is wrong (11)", cll.contains("c"));
        assertFalse("Contains is wrong (12)", cll.contains("Korea University"));

        assertEquals("getIndex is wrong (9)", -1, cll.getIndex("c"));
        assertEquals("getIndex is wrong (10)", 0, cll.getIndex("b"));
        assertEquals("getIndex is wrong (11)", -1, cll.getIndex("a"));
        assertEquals("getIndex is wrong (12)", -1, cll.getIndex("Korea University"));

        cll.remove("b");

        assertFalse("Contains is wrong (13)", cll.contains("a"));
        assertFalse("Contains is wrong (14)", cll.contains("b"));
        assertFalse("Contains is wrong (15)", cll.contains("c"));
        assertFalse("Contains is wrong (16)", cll.contains("Korea University"));

        assertEquals("getIndex is wrong (13)", -1, cll.getIndex("c"));
        assertEquals("getIndex is wrong (14)", -1, cll.getIndex("b"));
        assertEquals("getIndex is wrong (15)", -1, cll.getIndex("a"));
        assertEquals("getIndex is wrong (16)", -1, cll.getIndex("Korea University"));

        score += 1;
    }

    @Test(timeout=1000)
    public void testClearandIsEmpty()
    {

        CircularLinkedList<String> cll = new CircularLinkedList<String>();
        
        cll.insertAtHead("a");
        cll.insertAtHead("b");
        cll.insertAtHead("c");

        cll.clear();

        assertTrue("isEmpty is wrong (1)", cll.isEmpty());
        assertEquals("size is wrong (1)", 0, cll.getSize());
        assertNull("head is not null (1)", cll.getHead());

        score += 1;
    }


    @Test(timeout=2000)
    public void testAddAndRemoveMany()
    {

        //insertion/deletion should be O(1)
        CircularLinkedList<Integer> cll = new CircularLinkedList<Integer>();
        int big_num = 10000;

        for(int i = 0; i < big_num; i++)
        {
            if(i%4 == 0)
            {
                cll.insertAtHead(i);
            }
            
            else if(i%4 == 1)
            {
                cll.insert(0, i);
            }

            else if(i%4 == 2)
            {
                cll.insertAtTail(i);
            }

            else
            {
                cll.insert(cll.getSize(), i);
            }
        }

        assertEquals("size is wrong (1)", cll.getSize(), big_num);

        for(int i = 0; i < big_num; i++)
        {
            if(i%4 == 0)
            {
                cll.removeFromHead();
            }
            
            else if(i%4 == 1)
            {
                cll.removeIndex(0);
            }

            else if(i%4 == 2)
            {
                cll.removeFromTail();
            }

            else
            {
                cll.removeIndex(cll.getSize()-1);
            }
        } 

        assertEquals("size is wrong (2)", 0, cll.getSize());
        score += 1;
    }
    
    @Test(timeout=1000)
    public void testGetElementAtIndex()
    {

        CircularLinkedList<String> cll = new CircularLinkedList<String>();
        
        cll.insertAtHead("a");
        cll.insertAtTail("b");
        cll.insert(1,"c");

        assertEquals("getElementAtIndex is wrong (1)", String.valueOf("c"), cll.getElementAtIndex(1).getData());
    
        score += 1;
    }

}

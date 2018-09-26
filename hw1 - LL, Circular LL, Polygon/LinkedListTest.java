import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class LinkedListTest
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
        System.out.println("LinkedList Score: " + String.format("%d/%d", score, max_score));
        try{
            PrintWriter writer = new PrintWriter("LinkedList_score.txt", "UTF-8");
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
        LinkedList<Integer> ll;
        ll = new LinkedList<Integer>();
        assertEquals("Initialization (size) is wrong", 0, ll.getSize());
        assertNull("Initialization (head) is wrong", ll.getHead());
        assertNull("Initialization (tail) is wrong", ll.getTail());
        
        score += 1;
    }

    @Test(timeout=1000)
    public void testBasic()
    {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.insertAtHead("Data");
        ll.insertAtTail("Structures");
        ll.insert(2, "is the best class");

        score += 1;
    }

    @Test(timeout=1000)
    public void testInsertAtHead()
    {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.insertAtHead("hello");
        assertNotNull("Head is null after insert", ll.getHead());
        assertNotNull("Tail is null after insert", ll.getTail());
        assertEquals("Size is wrong after insert (1)", 1, ll.getSize());
        
        ll.insertAtHead("goodbye");
        assertFalse("Insert at head didn't work (1)", ll.getHead() == ll.getTail());
        assertEquals("Size is wrong after insert (2)", 2,  ll.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testInsertAtTail()
    {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.insertAtTail("hello");
        assertNotNull("Head is null after insert", ll.getHead());
        assertNotNull("Tail is null after insert", ll.getTail());
        assertEquals("Size is wrong after insert (1)", 1, ll.getSize());
        
        ll.insertAtTail("goodbye");
        assertFalse("Insert at head didn't work (1)", ll.getHead() == ll.getTail());
        assertEquals("Size is wrong after insert (2)", 2, ll.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemove()
    {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.insertAtHead("a");
        ll.insertAtHead("b");
        
        ll.remove("b");

        assertNotNull("Tail is null in non-empty list (1)", ll.getTail());
        assertNotNull("Head is null in non-empty list (1)", ll.getHead());
        assertTrue("Wrong thing was removed (1)", "a".equals(ll.getHead().getData()));
        assertEquals("Size is wrong after remove (1)", 1, ll.getSize());
        
        ll = new LinkedList<String>();
        
        ll.insertAtHead("a");
        ll.insertAtHead("b");
        
        ll.remove("a");

        assertNotNull("Tail is null in non-empty list (2)", ll.getTail());
        assertNotNull("Head is null in non-empty list (2)", ll.getHead());
        assertEquals("Wrong thing was removed (2)", "b", ll.getHead().getData());
        assertEquals("Size is wrong after remove (2)", 1, ll.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemoveMultiple()
    {
        LinkedList<String> ll = new LinkedList<String>();
        
        ll.insertAtHead("a");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("a");
        
        
        ll.remove("b");

        assertEquals("Size is wrong after remove (1)", 5, ll.getSize());

        ll.remove("b");
        
        assertEquals("Size is wrong after remove (2)", 4, ll.getSize());

        ll.remove("b");
        
        assertEquals("Size is wrong after remove (3)", 3, ll.getSize());

        ll.remove("a");
        
        assertEquals("Size is wrong after remove (4)", 2, ll.getSize());

        ll.remove("a");
        
        assertEquals("Size is wrong after remove (5)", 1, ll.getSize());

        ll.remove("b");
        
        assertEquals("Size is wrong after remove (6)", 0, ll.getSize());
        assertNull("Head is not null in empty list", ll.getHead());
        assertNull("Tail is not null in empty list", ll.getTail());

        score += 1;
    }

    @Test(timeout=1000)
    public void testClearAndEmpty()
    {
        LinkedList<String> ll = new LinkedList<String>();

        ll.insertAtHead("a");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("b");
        ll.insertAtHead("a");

        assertFalse("isEmpty is wrong (1)", ll.isEmpty());

        ll.clear();
        assertEquals("Size is wrong after clear", 0, ll.getSize());
        assertNull("Head is not null after clear", ll.getHead());
        assertNull("Tail is not null after clear", ll.getTail());
        assertTrue("isEmpty is wrong (2)", ll.isEmpty());

        score += 1;
    }

    @Test(timeout=1000) 
    public void testJoinLists()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        LinkedList<Integer> kk = new LinkedList<Integer>();

        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        LinkedList<Integer>.Node<Integer> lltempTail = ll.getTail();

        kk.insertAtHead(5);
        kk.insertAtHead(6);
        kk.insertAtHead(7);

        LinkedList<Integer>.Node<Integer> kktempHead = kk.getHead();

        ll.joinLists(kk);

        assertEquals("Tail not set properly", Integer.valueOf(5), ll.getTail().getData());
        assertEquals("Size is incorrect after join", 6, ll.getSize());
        assertEquals("ll tail not connected to kk head", kktempHead, lltempTail.getNext());
        
        score += 1;
    }

    @Test(timeout=1000) 
    public void testJoinListsEdgeCases1()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        LinkedList<Integer> kk = new LinkedList<Integer>();

        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        ll.joinLists(kk);

        assertNotNull("ll head is null after join", ll.getHead());
        assertNotNull("ll tail is null after join", ll.getTail());
        assertNull("ll tail points to non null", ll.getTail().getNext());
        assertEquals("size is wrong", 3, ll.getSize());
        
        score += 1;
    }

    @Test(timeout=1000) 
    public void testJoinListsEdgeCases2()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        LinkedList<Integer> kk = new LinkedList<Integer>();

        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        LinkedList<Integer>.Node<Integer> lltempTail = ll.getTail();

        kk.joinLists(ll);

        assertNull("kk tail points to non null", kk.getTail().getNext());
        assertEquals("size is wrong", 3, kk.getSize());

        score += 1;
    }

    @Test(timeout=1000) 
    public void testJoinListsEdgeCases3()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        LinkedList<Integer> kk = new LinkedList<Integer>();


        ll.joinLists(kk);

        assertNull("ll head is not null after empty joins", ll.getHead());
        assertNull("ll tail not is null after empty joins", ll.getTail());
        assertEquals("size is wrong", 0, ll.getSize());
        
        score += 1;
    }

    @Test(timeout=1000)
    public void testContainsAndGetIndex()
    {
        LinkedList<String> ll = new LinkedList<String>();

        ll.insertAtHead("x");
        ll.insertAtHead("y");
        ll.insertAtHead("z");
        ll.insertAtTail("Yonsei");

        assertEquals("getIndex failed (1)", 0, ll.getIndex("z"));
        assertEquals("getElementAtIndex failed (1)", String.valueOf("z"), ll.getElementAtIndex(0).getData());
        assertEquals("getIndex failed (2)", 1, ll.getIndex("y"));
        assertEquals("getElementAtIndex failed (2)", String.valueOf("y"), ll.getElementAtIndex(1).getData());
        assertEquals("getIndex failed (3)", 2, ll.getIndex("x"));
        assertEquals("getElementAtIndex failed (3)", String.valueOf("x"), ll.getElementAtIndex(2).getData());
        assertEquals("getIndex failed (4)", -1, ll.getIndex("Korea University"));

        assertTrue("Contains failed (1)", ll.contains("x"));
        assertTrue("Contains failed (2)", ll.contains("y"));
        assertTrue("Contains failed (3)", ll.contains("z"));
        assertTrue("Contains failed (4)", ll.contains("Yonsei"));
        assertFalse("Contains failed (5)", ll.contains("Korea University"));

        assertEquals("Size failed (1)", 4, ll.getSize());

        ll.remove("y");

        assertEquals("getIndex failed (5)", 0, ll.getIndex("z"));
        assertEquals("getElementAtIndex failed (4)", String.valueOf("z"), ll.getElementAtIndex(0).getData());
        assertEquals("getIndex failed (6)", 1, ll.getIndex("x"));
        assertEquals("getElementAtIndex failed (5)", String.valueOf("x"), ll.getElementAtIndex(1).getData());
        assertEquals("getIndex failed (7)", 2, ll.getIndex("Yonsei"));
        assertEquals("getElementAtIndex failed (6)", String.valueOf("Yonsei"), ll.getElementAtIndex(2).getData());
        assertEquals("getIndex failed (8)", -1, ll.getIndex("y"));
        assertEquals("getIndex failed (9)", -1, ll.getIndex("Korea University"));

        assertTrue("Contains failed (6)", ll.contains("z"));
        assertTrue("Contains failed (7)", ll.contains("x"));
        assertFalse("Contains failed (8)", ll.contains("y"));
        assertTrue("Contains failed (9)", ll.contains("Yonsei"));
        assertFalse("Contains failed (10)", ll.contains("Korea University"));

        assertEquals("Size failed (2)", 3, ll.getSize());

        ll.remove("Korea University");

        assertEquals("getIndex failed (10)", 0, ll.getIndex("z"));
        assertEquals("getElementAtIndex failed (7)", String.valueOf("z"), ll.getElementAtIndex(0).getData());
        assertEquals("getIndex failed (11)", 1, ll.getIndex("x"));
        assertEquals("getElementAtIndex failed (8)", String.valueOf("x"), ll.getElementAtIndex(1).getData());
        assertEquals("getIndex failed (12)", 2, ll.getIndex("Yonsei"));
        assertEquals("getElementAtIndex failed (9)", String.valueOf("Yonsei"), ll.getElementAtIndex(2).getData());
        assertEquals("getIndex failed (13)", -1, ll.getIndex("y"));
        assertEquals("getIndex failed (14)", -1, ll.getIndex("Korea University"));

        assertTrue("Contains failed (11)", ll.contains("z"));
        assertTrue("Contains failed (12)", ll.contains("x"));
        assertFalse("Contains failed (13)", ll.contains("y"));
        assertTrue("Contains failed (14)", ll.contains("Yonsei"));
        assertFalse("Contains failed (15)", ll.contains("Korea University"));

        assertEquals("Size failed (3)", 3, ll.getSize());

        ll.remove("Yonsei");

        assertEquals("getIndex failed (15)", 0, ll.getIndex("z"));
        assertEquals("getElementAtIndex failed (10)", String.valueOf("z"), ll.getElementAtIndex(0).getData());
        assertEquals("getIndex failed (16)", 1, ll.getIndex("x"));
        assertEquals("getElementAtIndex failed (11)", String.valueOf("x"), ll.getElementAtIndex(1).getData());
        assertEquals("getIndex failed (17)", -1, ll.getIndex("y"));
        assertEquals("getIndex failed (18)", -1, ll.getIndex("Yonsei"));
        assertEquals("getIndex failed (19)", -1, ll.getIndex("Korea University"));

        assertTrue("Contains failed (16)", ll.contains("z"));
        assertTrue("Contains failed (17)", ll.contains("x"));
        assertFalse("Contains failed (18)", ll.contains("y"));
        assertFalse("Contains failed (19)", ll.contains("Yonsei"));
        assertFalse("Contains failed (20)", ll.contains("Korea University"));

        assertEquals("Size failed (4)", 2, ll.getSize());

        ll.remove("z");

        assertEquals("getIndex failed (21)", 0, ll.getIndex("x"));
        assertEquals("getElementAtIndex failed (12)", String.valueOf("x"), ll.getElementAtIndex(0).getData());
        assertEquals("getIndex failed (22)", -1, ll.getIndex("z"));
        assertEquals("getIndex failed (23)", -1, ll.getIndex("y"));
        assertEquals("getIndex failed (24)", -1, ll.getIndex("Yonsei"));
        assertEquals("getIndex failed (25)", -1, ll.getIndex("Korea University"));

        assertFalse("Contains failed (21)", ll.contains("z"));
        assertFalse("Contains failed (22)", ll.contains("Yonsei"));
        assertTrue("Contains failed (23)", ll.contains("x"));
        assertFalse("Contains failed (24)", ll.contains("y"));
        assertFalse("Contains failed (25)", ll.contains("Korea University"));
        
        assertEquals("Size failed (5)", 1, ll.getSize());

        ll.remove("x");

        assertEquals("getIndex failed (26)", -1, ll.getIndex("x"));
        assertEquals("getIndex failed (27)", -1, ll.getIndex("z"));
        assertEquals("getIndex failed (28)", -1, ll.getIndex("y"));
        assertEquals("getIndex failed (29)", -1, ll.getIndex("Yonsei"));
        assertEquals("getIndex failed (30)", -1, ll.getIndex("Korea University"));

        assertFalse("Contains failed (26)", ll.contains("z"));
        assertFalse("Contains failed (27)", ll.contains("Yonsei"));
        assertFalse("Contains failed (28)", ll.contains("x"));
        assertFalse("Contains failed (29)", ll.contains("y"));
        assertFalse("Contains failed (30)", ll.contains("Korea University"));

        assertEquals("Size failed (6)", 0, ll.getSize());
        assertTrue("isEmpty failed", ll.isEmpty());

        score += 1;
    }

    @Test(timeout=1000) 
    public void testRemoveAtIndex()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        ll.removeIndex(0);
        assertEquals("removeIndex failed (1)", Integer.valueOf(1), ll.getHead().getData());
        assertEquals("size failed (1)", 2, ll.getSize());
        assertTrue("contains failed (1)", ll.contains(0));
        assertTrue("contains failed (2)", ll.contains(1));
        assertFalse("contains failed (3)", ll.contains(2));

        ll = new LinkedList<Integer>();
        
        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        ll.removeIndex(2);
        assertEquals("removeIndex failed (2)", Integer.valueOf(2), ll.getHead().getData());
        assertEquals("size failed (2)", 2, ll.getSize());
        assertFalse("contains failed (4)", ll.contains(0));
        assertTrue("contains failed (5)", ll.contains(1));
        assertTrue("contains failed (6)", ll.contains(2));
        assertNull("new tail.next doesn't point to null after removing tail (1)", ll.getTail().getNext());

        ll = new LinkedList<Integer>();
        
        ll.insertAtHead(0);
        ll.insertAtHead(1);
        ll.insertAtHead(2);

        ll.removeIndex(1);
        assertEquals("removeIndex failed (3)", Integer.valueOf(2), ll.getHead().getData());
        assertEquals("size failed (3)", 2, ll.getSize());
        assertTrue("contains failed (7)", ll.contains(0));
        assertFalse("contains failed (8)", ll.contains(1));
        assertTrue("contains failed (9)", ll.contains(2));
        
        ll.removeIndex(1);
        assertEquals("removeIndex failed (4)", Integer.valueOf(2), ll.getHead().getData());
        assertEquals("size failed (4)", 1, ll.getSize());
        assertFalse("contains failed (10)", ll.contains(0));
        assertFalse("contains failed (11)", ll.contains(1));
        assertTrue("contains failed (12)", ll.contains(2));
        assertNull("new tail.next doesn't point to null after removing tail (2)", ll.getTail().getNext());

        ll.removeIndex(3);
        assertEquals("removeIndex failed (5)", Integer.valueOf(2), ll.getHead().getData());
        assertEquals("size failed (5)", 1, ll.getSize());
        assertFalse("contains failed (13)", ll.contains(0));
        assertFalse("contains failed (14)", ll.contains(1));
        assertTrue("contains failed (15)", ll.contains(2));

        ll.removeIndex(0);
        assertEquals("size failed (6)", 0, ll.getSize());
        assertFalse("contains failed (16)", ll.contains(0));
        assertFalse("contains failed (17)", ll.contains(1));
        assertFalse("contains failed (18)", ll.contains(2));
        assertTrue("isEmpty failed", ll.isEmpty());
    
        score += 1;
    }

    @Test(timeout=1000)
    public void testRemoveFromHead()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();

        for(int i = 0; i < 100; i++)
        {
            ll.insertAtTail(i);
        }

        for(int i = 0; i < 10; i++)
        {
            ll.removeFromHead();
        }

        assertEquals("size is wrong", 90, ll.getSize());
        assertFalse("contains failed (1)", ll.contains(3));
        assertTrue("contains failed (2)", ll.contains(66));
        assertEquals("head is incorrectly set", Integer.valueOf(10), ll.getHead().getData());

        score += 1;
    }

    @Test(timeout=1000)
    public void testRemoveFromTail()
    {
        LinkedList<Integer> ll = new LinkedList<Integer>();

        for(int i = 0; i < 100; i++)
        {
            ll.insertAtTail(i);
        }

        for(int i = 0; i < 10; i++)
        {
            ll.removeFromTail();
        }

        assertEquals("size is wrong", 90, ll.getSize());
        assertFalse("contains failed (1)", ll.contains(98));
        assertTrue("contains failed (2)", ll.contains(8));
        assertEquals("tail is incorrectly set", Integer.valueOf(89), ll.getTail().getData());

        score += 1;
    }

    @Test(timeout=1000)
    public void testAddMany()
    {
        //adding and size should be done in O(1) time

        LinkedList<Integer> ll = new LinkedList<Integer>();

        for(int i = 0; i < 100000; i++)
        {
            if(i%2 == 0)
                ll.insertAtTail(i);
            else
                ll.insert(ll.getSize(), i);
        }

        assertEquals("size is wrong", 100000, ll.getSize());

        score += 1;
    }
    
}

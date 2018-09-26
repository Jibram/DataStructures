import java.io.*;
 
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class PolygonTest
{

    public static int score;
    public static int max_score;

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
        System.out.println("Polygon Score: " + String.format("%d/%d", score, max_score));
        try{
            PrintWriter writer = new PrintWriter("Polygon_score.txt", "UTF-8");
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
        Polygon p = new Polygon();
        p.addPoint(new Point(0,0));
        p.addPoint(new Point(1,0));
        p.addPoint(new Point(1,1));
        p.addPoint(new Point(0,1));

        assertEquals("Size is wrong", 4, p.getSize());

        score += 1;
    }

    @Test(timeout=1000)
    public void testInclusion1()
    {
        Polygon p = new Polygon();
        p.addPoint(new Point(0,0));
        p.addPoint(new Point(1,0));
        p.addPoint(new Point(1,1));
        p.addPoint(new Point(0,1));

        assertFalse("Inclusion failed (1)", p.pointInPolygon(new Point(-3, 2)));
        assertFalse("Inclusion failed (2)", p.pointInPolygon(new Point(1, 1.5)));
        assertFalse("Inclusion failed (3)", p.pointInPolygon(new Point(8,1.5)));
        assertFalse("Inclusion failed (4)", p.pointInPolygon(new Point(-1,.5)));
        assertTrue("Inclusion failed (5)", p.pointInPolygon(new Point(.5,.5)));
        assertFalse("Inclusion failed (6)", p.pointInPolygon(new Point(2,.5)));
        assertFalse("Inclusion failed (7)", p.pointInPolygon(new Point(-1,.5)));

        score += 1;
    }

    @Test(timeout=1000)
    public void testInclusion2()
    {
        Polygon p = new Polygon();
        p.addPoint(new Point(0,0));
        p.addPoint(new Point(1,2));
        p.addPoint(new Point(2,1));
        p.addPoint(new Point(3,2));
        p.addPoint(new Point(4,0));
        p.addPoint(new Point(3,-2));
        p.addPoint(new Point(2,-1));
        p.addPoint(new Point(1,-2));

        assertFalse("Inclusion failed (1)", p.pointInPolygon(new Point(.5,2.5)));
        assertFalse("Inclusion failed (2)", p.pointInPolygon(new Point(.5,1.5)));
        assertFalse("Inclusion failed (3)", p.pointInPolygon(new Point(.5,-1.5)));
        assertFalse("Inclusion failed (4)", p.pointInPolygon(new Point(.5,-2.5)));

        assertFalse("Inclusion failed (5)", p.pointInPolygon(new Point(2,2.5)));
        assertFalse("Inclusion failed (6)", p.pointInPolygon(new Point(2,1.5)));
        assertFalse("Inclusion failed (7)", p.pointInPolygon(new Point(2,-2.5)));
        assertFalse("Inclusion failed (8)", p.pointInPolygon(new Point(2,-1.5)));

        assertFalse("Inclusion failed (9)", p.pointInPolygon(new Point(3.5,2.5)));
        assertFalse("Inclusion failed (10)", p.pointInPolygon(new Point(3.5,1.5)));
        assertFalse("Inclusion failed (11)", p.pointInPolygon(new Point(3.5,-1.5)));
        assertFalse("Inclusion failed (12)", p.pointInPolygon(new Point(3.5,-2.5)));

        assertFalse("Inclusion failed (13)", p.pointInPolygon(new Point(-20,100.4)));
        assertFalse("Inclusion failed (14)", p.pointInPolygon(new Point(2017.5,-1.2)));

        assertTrue("Inclusion failed (15)", p.pointInPolygon(new Point(1.5,.5)));
        assertTrue("Inclusion failed (16)", p.pointInPolygon(new Point(3.5,.1)));
        assertTrue("Inclusion failed (17)", p.pointInPolygon(new Point(3.5,-.1)));
        assertTrue("Inclusion failed (18)", p.pointInPolygon(new Point(3,1.5)));
        assertTrue("Inclusion failed (19)", p.pointInPolygon(new Point(3,-1.5)));
        
        assertTrue("Inclusion failed (20)", p.pointInPolygon(new Point(1.5,.1)));
        assertTrue("Inclusion failed (21)", p.pointInPolygon(new Point(1.5,-.1)));
        assertTrue("Inclusion failed (22)", p.pointInPolygon(new Point(1,1.5)));
        assertTrue("Inclusion failed (23)", p.pointInPolygon(new Point(1,-1.5)));

        score += 1;
    }

    @Test(timeout=1000)
    public void testInclusion3()
    {
        Polygon p = new Polygon();
        p.addPoint(new Point(0,1));
        p.addPoint(new Point(2,0));
        p.addPoint(new Point(4,1));

        assertFalse("Inclusion failed (1)", p.pointInPolygon(new Point(.5,2.5)));
        assertFalse("Inclusion failed (2)", p.pointInPolygon(new Point(.5,-4.5)));
        assertFalse("Inclusion failed (3)", p.pointInPolygon(new Point(2.5,-4.5)));
        assertFalse("Inclusion failed (4)", p.pointInPolygon(new Point(18,.5)));
        assertFalse("Inclusion failed (5)", p.pointInPolygon(new Point(.5,.5)));
        assertFalse("Inclusion failed (6)", p.pointInPolygon(new Point(3.5,.1)));

        assertTrue("Inclusion failed (7)", p.pointInPolygon(new Point(2,.5)));
        assertTrue("Inclusion failed (8)", p.pointInPolygon(new Point(1.5,.9)));
        assertTrue("Inclusion failed (9)", p.pointInPolygon(new Point(2.5,.9)));

        score += 1;
    }
}

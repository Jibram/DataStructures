import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class KMPTest
{
    private static int score, max_score;

    private static String[] states = {"alabama","alaska","arizona","arkansas","california","colorado",
                           "connecticut","delaware","districtofcolumbia","florida","georgia",
                           "hawaii","idaho","illinois","indiana","iowa","kansas","kentucky",
                           "louisiana","maine","maryland","massachusetts","michigan","minnesota",
                           "mississippi","missouri","montana","nebraska","nevada","newhampshire",
                           "newjersey","newmexico","newyork","northcarolina","northdakota","ohio",
                           "oklahoma","oregon","pennsylvania","rhodeisland","southcarolina","southdakota",
                           "tennessee","texas","utah","vermont","virginia","washington","westvirginia",
                           "wisconsin","wyoming"};
    
    private static String[] capitals = {"albany","annapolis","atlanta","augusta","austin","batonrouge","bismarck",
                             "boise","boston","carsoncity","charleston","cheyenne","columbia","columbus",
                             "concord","denver","desmoines","dover","frankfort","harrisburg","hartford",
                             "helena","honolulu","indianapolis","jackson","jeffersoncity","juneau",
                             "lansing","lincoln","littlerock","madison","montgomery","montpelier","nashville",
                             "oklahomacity","olympia","phoenix","pierre","providence","raleigh","richmond",
                             "sacramento","saintpaul","salem","saltlakecity","santafe","springfield","tallahassee",
                             "topeka","trenton"};

    private static String[] commonEnglishWords = {"the","name","of","very","to","through","and","just","a","form","in","much","is","great","it","think","you","say","that","help","he","low","was","line","for","before","on","turn","are","cause","with","same","as","mean","i","differ","his","move","they","right","be","boy","at","old","one","too","have","does","this","tell","from","sentence","or","set","had","three","by","want","hot","air","but","well","some","also","what","play","there","small","we","end","can","put","out","home","other","read","were","hand","all","port","your","large","when","spell","up","add","use","even","word","land","how","here","said","must","an","big","each","high","she","such","which","follow","do","act","their","why","time","ask","if","men","will","change","way","went","about","light","many","kind","then","off","them","need","would","house","write","picture","like","try","so","us","these","again","her","animal","long","point","make","mother","thing","world","see","near","him","build","two","self","has","earth","look","father","more","head","day","stand","could","own","go","page","come","should","did","country","my","found","sound","answer","no","school","most","grow","number","study","who","still","over","learn","know","plant","water","cover","than","food","call","sun","first","four","people","thought","may","let","down","keep","side","eye","been","never","now","last","find","door","any","between","new","city","work","tree","part","cross","take","since","get","hard","place","start","made","might","live","story","where","saw","after","far","back","sea","little","draw","only","left","round","late","man","run","year","dont","came","while","show","press","every","close","good","night","me","real","give","life","our","few","under","stop"};

    private static String[] loremIpsumWords = {"lorem","ipsum","dolor","sit","amet","consectetur","adipiscing","elit","sed","do","eiusmod","tempor","incididunt","ut","labore","et","dolore","magna","aliqua","ut","enim","ad","minim","veniam","quis","nostrud","exercitation","ullamco","laboris","nisi","ut","aliquip","ex","ea","commodo","consequat","duis","aute","irure","dolor","in","reprehenderit","in","voluptate","velit","esse","cillum","dolore","eu","fugiat","nulla","pariatur","excepteur","sint","occaecat","cupidatat","non","proident","sunt","in","culpa","qui","officia","deserunt","mollit","anim","id","est","laborum"};

    private static String loremIpsumText = "loremipsumdolorsitametconsecteturadipiscingelitseddoeiusmodtemporincididuntutlaboreetdoloremagnaaliquautenimadminimveniamquisnostrudexercitationullamcolaborisnisiutaliquipexeacommodoconsequatduisauteiruredolorinreprehenderitinvoluptatevelitessecillumdoloreeufugiatnullapariaturexcepteursintoccaecatcupidatatnonproidentsuntinculpaquiofficiadeseruntmollitanimidestlaborum";

    private static ArrayList<Integer> testMediumSol;

    private static String mobyDickText, beeMovieScript;
    
    @BeforeClass
    public static void init()
    {
        score = 0;
        max_score = 0;
        testMediumSol = new ArrayList<Integer>();
        try
        {
            mobyDickText = String.join("\n", Files.readAllLines(Paths.get("mobydick.txt")));
            beeMovieScript = String.join("\n", Files.readAllLines(Paths.get("beemovie.txt")));
            Scanner scanner = new Scanner(new File("testMediumSol.txt"));
            while(scanner.hasNextInt())
                testMediumSol.add(scanner.nextInt());
        }
        catch (IOException e) 
        {
            System.out.println("Something went wrong here");
        }
    }

    @AfterClass
    public static void after()
    {
        System.out.println("KMP Score: " + String.format("%d/%d", score, max_score));
        try{
            PrintWriter writer = new PrintWriter("KMP_score.txt", "UTF-8");
            writer.println(score);
            writer.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Something went wrong here");
        }
    }

    @Before
    public void maxScore()
    {
        max_score += 1;
    }

    @Test(timeout=1000)
    public void testBasic()
    {
        KMP k = new KMP("nano");
        assertTrue("match is wrong (1)", k.match("nano"));

        k.setFailure("nAno");
        assertFalse("match is wrong (2)", k.match("nano"));
        assertTrue("match is wrong (3)", k.match("nbno"));
        assertTrue("match is wrong (4)", k.match("ncno"));
        assertTrue("match is wrong (5)", k.match("ndno"));
        assertTrue("match is wrong (6)", k.match("neno"));
        assertTrue("match is wrong (7)", k.match("nfno"));

        score++;
    }

    @Test(timeout=1000)
    public void testWildCards()
    {
        KMP k = new KMP("*******");
        assertEquals("countMatchesInString is wrong (1)", loremIpsumText.length()-7+1, k.countMatchesInString(loremIpsumText));
        
        k.setFailure("YONSEIUNIVERSItY");
        assertTrue("match is wrong (1)", k.match("knuthmorrispratt"));

        score++;
    }

    @Test(timeout=1000)
    public void testCountMatches()
    {
        KMP k = new KMP("animal");
        assertEquals("countMatchesInString is wrong (1)", 16, k.countMatchesInString(mobyDickText));
        score++;
    }

    @Test(timeout=1000)
    public void testMedium()
    {
        ArrayList<Integer> sol = new ArrayList<Integer>();
        sol.add(6263);
        sol.add(6267);
        sol.add(6271);
        sol.add(6275);
        sol.add(6279);
        sol.add(6283);
        sol.add(6287);
        sol.add(6291);
        sol.add(6295);
        sol.add(6299);
        sol.add(6303);

        KMP k = new KMP("buzzbuzz");

        ArrayList<Integer> result = k.getMatchesInString(beeMovieScript);
        Collections.sort(result);

        assertEquals("getMatchesInString is wrong (1)", sol.size(), result.size());

        for(int i = 0; i < sol.size(); i++)
        {
            assertEquals("getMatchesInString is wrong (2)", sol.get(i), result.get(i));
        }
        score++;
    }

    @Test(timeout=2000)
    public void testLong()
    {
        KMP k = new KMP(loremIpsumText);
        assertEquals("countMatchesInString is wrong (1)", 0, k.countMatchesInString(mobyDickText));
        score++;
    }
}
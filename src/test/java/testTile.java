import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class testTile {
    int testContens = 0;
    GridSquare testTile;

    @Test
    void testInitialSetup()
    {
        testTile = new GridSquare(testContens);
        Assertions.assertEquals(testContens, testTile.GetContents(), "Incorrect setup");
        Assertions.assertEquals(false, testTile.GetReveled(), "Initialised as true");
        Assertions.assertEquals(false,testTile.GetFlag(), "Initialised as true");
    }

    @Test
    void testCheckSquareTest()
    {
        testTile = new GridSquare(testContens);

        testTile.RevelSquare();
        Assertions.assertEquals(true, testTile.GetReveled(), "Has not changed");
        testTile.RevelSquare();
        Assertions.assertEquals(true, testTile.GetReveled(), "Has changed agan");

    }

    @Test
    void testFlagSquare()
    {
        testTile = new GridSquare(testContens);

        testTile.FlagSquare();
        Assertions.assertEquals(true, testTile.GetFlag(),"Doesn't change the flag");
        testTile.FlagSquare();
        Assertions.assertEquals(false, testTile.GetFlag(), "Not changed the flag");
    }

    @Test
    void  testFlagCheckSequence()
    {
        testTile = new GridSquare(testContens);

        testTile.FlagSquare();
        testTile.RevelSquare();
        Assertions.assertEquals(true, testTile.GetFlag(), "The tile is unflagged");
        Assertions.assertEquals(false, testTile.GetReveled(), "The tile has been reveled");
        testTile.FlagSquare();
        testTile.RevelSquare();
        Assertions.assertEquals(false, testTile.GetFlag(), "not showing flag");
        Assertions.assertEquals(true, testTile.GetReveled(), "Not been reveled after being unflagged flagged");
        testTile.FlagSquare();
        testTile.RevelSquare();
        Assertions.assertEquals(false, testTile.GetFlag(), "The square is flagged when checked");
        Assertions.assertEquals(true, testTile.GetReveled(), "The squarer is hidden after being checked");

    }

    @Test
    void testForceRevel()
    {
        testTile = new GridSquare(testContens);

        testTile.FlagSquare();
        testTile.ForceRevel();

        Assertions.assertEquals(true, testTile.GetReveled(), "The square as not been reveled");
        Assertions.assertEquals(false, testTile.GetFlag(), "The tile is still flagged");
    }

    @Test
    void testRevele()
    {
        for (int i = -1; i <= 9; i++)
        {
            testTile = new GridSquare(i);
            System.out.println("\ni: " + i);
            System.out.println("Unflaged, Unreveled");

            Assertions.assertEquals("\u25A0", testTile.CheckSquare(), "The squarer is not showing the correct symbol or assert doesn't work with unicode characters");
            System.out.println(testTile.CheckSquare());

            System.out.println("Flaged, Unreveled");
            testTile.FlagSquare();
            Assertions.assertEquals("\033[0;31m\u22A0\033[0;37m", testTile.CheckSquare());
            System.out.println(testTile.CheckSquare());

            testTile.FlagSquare();
            testTile.RevelSquare();
            System.out.println("Unflaged, Reveled");

            if(i < 0)
            {

                Assertions.assertEquals("\033[0;31m\u00D7\033[0;37m", testTile.CheckSquare(), "Mine not displaying correctly");

            } else if(i == 0)
            {

                Assertions.assertEquals("0",testTile.CheckSquare(), "Blank Not displaying");

            }else
            {
                String testStr;
                testStr = "\033[0;33m" + String.valueOf(i) + "\033[0;37m";
                Assertions.assertEquals(testStr, testTile.CheckSquare(),"Numbers not displaying correctly for " + i);
            }
            System.out.println(testTile.CheckSquare());

        }
    }

}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class testTile {
    int testContens = 0;
    GridSquare testTile;

    @Test
    public void testInitialSetup()
    {
        testTile = new GridSquare(testContens);
        Assertions.assertEquals(testContens, testTile.GetContents(), "Incorrect setup");
        Assertions.assertEquals(false, testTile.GetReveled(), "Initialised as true");
        Assertions.assertEquals(false,testTile.GetFlag(), "Initialised as true");
    }

    @Test
    public void testRevelSquare()
    {
        testTile = new GridSquare(testContens);

        testTile.RevelSquare();
        Assertions.assertEquals(true, testTile.GetReveled(), "Has not changed");
        testTile.RevelSquare();
        Assertions.assertEquals(true, testTile.GetReveled(), "Has changed agan");

    }

    @Test
    public void testFlagSquare()
    {
        testTile = new GridSquare(testContens);

        testTile.FlagSquare();
        Assertions.assertEquals(true, testTile.GetFlag(),"Doesn't change the flag");
        testTile.FlagSquare();
        Assertions.assertEquals(false, testTile.GetFlag(), "Not changed the flag");
    }

    @Test
    public void testFlagRevelSequence()
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
    public void testForceRevel()
    {
        testTile = new GridSquare(testContens);

        testTile.FlagSquare();
        testTile.ForceRevel();

        Assertions.assertEquals(true, testTile.GetReveled(), "The square as not been reveled");
    }

    @Test
    public void testShowSquareImage()
    {
        for (int i = -1; i <= 9; i++)
        {
            testTile = new GridSquare(i);
            System.out.println("\ni: " + i);
            System.out.println("Unflaged, Unreveled");

            Assertions.assertEquals("\u25A0", testTile.ShowSquareImage(), "The squarer is not showing the correct symbol or assert doesn't work with unicode characters");
            System.out.println(testTile.ShowSquareImage());

            System.out.println("Flaged, Unreveled");
            testTile.FlagSquare();
            Assertions.assertEquals("\033[0;31m\u22A0\033[0;37m", testTile.ShowSquareImage());
            System.out.println(testTile.ShowSquareImage());

            testTile.FlagSquare();
            testTile.RevelSquare();
            System.out.println("Unflaged, Reveled");

            if(i < 0)
            {

                Assertions.assertEquals("\033[0;31m\u00D7\033[0;37m", testTile.ShowSquareImage(), "Mine not displaying correctly");

            } else if(i == 0)
            {

                Assertions.assertEquals("0",testTile.ShowSquareImage(), "Blank Not displaying");

            }else
            {
                String testStr;
                testStr = "\033[0;33m" + String.valueOf(i) + "\033[0;37m";
                Assertions.assertEquals(testStr, testTile.ShowSquareImage(),"Numbers not displaying correctly for " + i);
            }
            System.out.println(testTile.ShowSquareImage());

        }
    }

    @Test
    public void testShowForcedReveledImagesMines()
    {
        testTile = new GridSquare(-1);

        testTile.ForceRevel();
        Assertions.assertEquals("\033[0;31m\u00D7\033[0;37m", testTile.ShowSquareImage(), "The mine tile doesn't display correctly when unflagged");
        System.out.println("Not flagged: " + testTile.ShowSquareImage());

        testTile = new GridSquare(-1);
        testTile.FlagSquare();
        testTile.ForceRevel();
        Assertions.assertEquals("\033[0;32m\u00D7\033[0;37m", testTile.ShowSquareImage(), "The mine tile doesn't display correctly when flagged ");
        System.out.println("Flagged: " + testTile.ShowSquareImage());
    }

    @Test
    public void testShowForcedReveledImagesNumbers()
    {
        String testString = "";
        for (int i = 0; i < 10; i++)
        {
            testTile = new GridSquare(i);
            testTile.ForceRevel();
            if (i == 0)
            {
                Assertions.assertEquals(String.valueOf(i), testTile.ShowSquareImage(), "unflagged " + i + " tile not displaying correctly for force revel");
            }else
            {
                testString = "\033[0;33m" + String.valueOf(i) + "\033[0;37m";
                Assertions.assertEquals(testString, testTile.ShowSquareImage(), "Unflagged " + i + " tile not displaying correctly for force revel");
            }
            System.out.println("Unflaged " + i + " tile: " + testTile.ShowSquareImage());

            testTile = new GridSquare(i);
            testTile.FlagSquare();
            testTile.ForceRevel();
            if (i == 0)
            {
                Assertions.assertEquals(String.valueOf(i), testTile.ShowSquareImage(), "Flagged " + i + " tile not displaying correctly for force revel");
            }else
            {
                testString = "\033[0;33m" + String.valueOf(i) + "\033[0;37m";
                Assertions.assertEquals(testString, testTile.ShowSquareImage(), "Flagged " + i + " tile not displaying correctly for force revel");
            }
            System.out.println("Flagged " + i + " tile: " + testTile.ShowSquareImage());
        }
    }

}

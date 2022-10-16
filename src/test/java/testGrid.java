import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testGrid {

    Mode[] testPresets = {new Mode(9, 9, 10),       //Beginner
                          new Mode(16, 16, 40),     //Intermediate
                          new Mode(30, 16, 99)};    //Expert
    Mode testMode;
    Grid testGrid;

    @Test
    public void testGridGenPresets()
    {
        int testXSize;
        int testYSize;
        int testMineCount;

        for (int i = 0; i < 3; i++)
        {
            testGrid = new Grid(testPresets[i].height, testPresets[i].width, testPresets[i].mines);
            testYSize = testGrid.GetYMax();
            testXSize = testGrid.GetXMax();

            // Tests the x and y size
            if (i == 0)
            {
                Assertions.assertEquals(9, testXSize, "Incorrect x size for Beginner");
                Assertions.assertEquals(9, testYSize, "Incorrect y size for Beginner");
            } else if (i == 1) {

                Assertions.assertEquals(16, testXSize, "Incorrect x size for Intermediate");
                Assertions.assertEquals(16, testYSize, "Incorrect y size for Intermediate");

            } else {

                Assertions.assertEquals(30, testXSize, "Incorrect x size for Expert");
                Assertions.assertEquals(16, testYSize, "Incorrect y size for Expert");

            }

            testMineCount = 0;

            for (int y = 0; y <testYSize; y++)
            {
                for (int x = 0; x < testXSize; x++)
                {
                    if (testGrid.GetSquareContent(x, y) == -1)
                        testMineCount++;
                }
            }

            if (i == 0)
            {
                Assertions.assertEquals(10, testMineCount, "Incorrect number of mines for Beginner");

            } else if (i == 1) {

                Assertions.assertEquals(40, testMineCount, "Incorrect number of mines for Intermediate");

            } else {

                Assertions.assertEquals(99, testMineCount, "Incorrect number of mines for Expert");

            }
        }
    }

    @Test
    public void testGridGenCustom()
    {
        int testXSize;
        int testYSize;
        int testMineCount;

        // Sensible low
        testMode = new Mode(7, 12, 11);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(7, testXSize, "Incorrect x size for Sensational low");
        Assertions.assertEquals(12, testYSize, "Incorrect y size for Sensational low");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(11, testMineCount, "Incorrect number of mines for Sensational low");

        // Sensible high
        testMode = new Mode(123, 231, 7064);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(123, testXSize, "Incorrect x size for Sensational high");
        Assertions.assertEquals(231, testYSize, "Incorrect y size for Sensational high");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(7064, testMineCount, "Incorrect number of mines for Sensational high");

        //region Best to leve commented out Extreme High

        //Extreme High
//        testMode = new Mode(827, 2272, 293585);
//
//        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
//        testYSize = testGrid.GetYMax();
//        testXSize = testGrid.GetXMax();
//
//        Assertions.assertEquals(827, testXSize, "Incorrect x size for Sensational high");
//        Assertions.assertEquals(2272, testYSize, "Incorrect y size for Sensational high");
//
//        testMineCount = 0;
//        for (int y = 0; y < testYSize; y++)
//        {
//            for (int x = 0; x < testXSize; x++)
//            {
//                if (testGrid.GetSquareContent(x, y) == -1)
//                    testMineCount++;
//            }
//        }
//
//        Assertions.assertEquals(293585, testMineCount, "Incorrect number of mines for Sensational high");
        //endregion

        // 0 input
        testMode = new Mode(0, 0, 0);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(2, testXSize, "Incorrect x size for 0 input");
        Assertions.assertEquals(2, testYSize, "Incorrect y size for 0 input");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(1, testMineCount, "Incorrect number of mines for 0 input");

        // negative input
        testMode = new Mode(-50, -20, -30);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(2, testXSize, "Incorrect x size for negative input");
        Assertions.assertEquals(2, testYSize, "Incorrect y size for negative input");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(1, testMineCount, "Incorrect number of mines for negative input");


        // too many mines
        testMode = new Mode(16, 13, 245);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(16, testXSize, "Incorrect x size for too many mines");
        Assertions.assertEquals(13, testYSize, "Incorrect y size for too many mines");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(42, testMineCount, "Incorrect number of mines for too many mines");

        // exact mines
        testMode = new Mode(16, 13, 208);

        testGrid = new Grid(testMode.height, testMode.width, testMode.mines);
        testYSize = testGrid.GetYMax();
        testXSize = testGrid.GetXMax();

        Assertions.assertEquals(16, testXSize, "Incorrect x size for too many mines");
        Assertions.assertEquals(13, testYSize, "Incorrect y size for too many mines");

        testMineCount = 0;
        for (int y = 0; y < testYSize; y++)
        {
            for (int x = 0; x < testXSize; x++)
            {
                if (testGrid.GetSquareContent(x, y) == -1)
                    testMineCount++;
            }
        }

        Assertions.assertEquals(42, testMineCount, "Incorrect number of mines for too many mines");
    }

    @Test
    public void testDrawGrid()
    {
        int[][] testManualNumberGrid;
        int toSet;
        String checkString;
        String testString;
        String blueColour = "\033[0;36m";
        String greenColour = "\033[0;32m";
        String redColour = "\033[0;31m";
        String yellowColour ="\033[0;33m";
        String whiteColour = "\033[0;37m";

        // Small test
        testGrid = new Grid(10, 6, 4);
        testManualNumberGrid = new int[10][6];

        for (int y = 9; y >= 0; y--)
        {
            for (int x = 0; x < 6; x++)
            {
                if(y == 5 && x ==4)
                {
                    toSet = -1;
                } else if (y >= 7 && y <=8 && x == 2) {
                    toSet = -1;
                } else if (y == 0 && x == 0) {
                    toSet = -1;
                }else
                {
                    toSet = 0;
                }
                testManualNumberGrid[y][x] = toSet;
            }
        }

        testManualNumberGrid = testGrid.ManualNumberGrid(testManualNumberGrid);
        testGrid.ManualSetGameBord(testManualNumberGrid);

        checkString = blueColour + "y\n" + whiteColour;

        for (int y = 9; y >= 0; y--)
        {
            checkString = checkString + blueColour + y + whiteColour;

            for (int x = 0; x < 6; x++)
            {
                checkString = checkString + " " + whiteColour + "\u25A0" + whiteColour;
            }
            checkString += "\n";
        }

        checkString = checkString + "  " + blueColour;

        for (int x = 0; x < 6; x++)
        {
            checkString = checkString + x + " ";
        }

        checkString = checkString + "x" + whiteColour;

        System.out.println(checkString);
        System.out.println(testGrid.DrawGrid());

        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid 6 by 10");

        //region Best leave commented out but has passed

//        // large test
//        testGrid = new Grid(250, 100, 1);
//        testManualNumberGrid = new int[250][100];
//
//        for (int y = 249; y >= 0; y--)
//        {
//            for (int x = 0; x < 100; x++)
//            {
//                toSet = 0;
//
//                if (x == y && x == 0)
//                {
//                    toSet = -1;
//                }
//                testManualNumberGrid[y][x] = toSet;
//            }
//        }
//
//        testManualNumberGrid = testGrid.ManualNumberGrid(testManualNumberGrid);
//        testGrid.ManualSetGameBord(testManualNumberGrid);
//
//        checkString = blueColour + "y\n" + whiteColour;
//
//        for (int y = 249; y >= 0; y--)
//        {
//            checkString += blueColour;
//            if (y < 100)
//            {
//                checkString += "0";
//            }
//
//            if(y < 10)
//            {
//                checkString += "0";
//            }
//
//            checkString = checkString + y + whiteColour;
//
//            for (int x = 0; x < 100; x++)
//            {
//                checkString = checkString + "  " + whiteColour + "\u25A0" + whiteColour;
//            }
//            checkString += "\n";
//        }
//
//        checkString = checkString + "     " + blueColour;
//
//        for (int x = 0; x < 100; x++)
//        {
//            if(x < 10)
//                checkString += "0";
//
//            checkString = checkString + x + " ";
//        }
//
//        checkString = checkString + "x" + whiteColour;
//
//        System.out.println(checkString);
//        System.out.println(testGrid.DrawGrid());
//
//        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid 100 by 250");

        //endregion
    }

    @Test
    public void testGridFlagRevelAndForceRevel()
    {
        int[][] testManualNumberGrid;
        int toSet;
        String checkString;
        String blueColour = "\033[0;36m";
        String greenColour = "\033[0;32m";
        String redColour = "\033[0;31m";
        String yellowColour ="\033[0;33m";
        String whiteColour = "\033[0;37m";

        String flagged = redColour + "\u22A0" + whiteColour;
        String hidden = whiteColour + "\u25A0" + whiteColour;
        String zero = whiteColour + "0" + whiteColour;
        String one = yellowColour + "1" + whiteColour;
        String two = yellowColour + "2" + whiteColour;

        // Small test
        testGrid = new Grid(10, 6, 4);
        testManualNumberGrid = new int[10][6];

        // Generate mine grid
        for (int y = 9; y >= 0; y--)
        {
            for (int x = 0; x < 6; x++)
            {
                if(y == 5 && x ==4)
                {
                    toSet = -1;
                } else if (y >= 7 && y <=8 && x == 2) {
                    toSet = -1;
                } else if (y == 0 && x == 0) {
                    toSet = -1;
                }else
                {
                    toSet = 0;
                }
                testManualNumberGrid[y][x] = toSet;
            }
        }

        testManualNumberGrid = testGrid.ManualNumberGrid(testManualNumberGrid);
        testGrid.ManualSetGameBord(testManualNumberGrid);

        Assertions.assertEquals(4, testGrid.MinesLeft(), "Incorrect mine number before flagging");

        //Flagging
        testGrid.FlagMines(0, 0);
        testGrid.FlagMines(1, 0);
        testGrid.FlagMines(1, 2);
        testGrid.FlagMines(2, 2);
        testGrid.FlagMines(5, 4);
        testGrid.FlagMines(2, 7);

        Assertions.assertEquals(-2, testGrid.MinesLeft(), "Incorrect mine number after flagging");


        //Draw grid after flagged
        checkString = blueColour + "y\n" + whiteColour;

        for (int y = 9; y >= 0; y--)
        {
            checkString = checkString + blueColour + y + whiteColour;

            for (int x = 0; x < 6; x++)
            {
                checkString += " ";

                if (x >= 0 && x <=1 && y==0) {
                    checkString += flagged;
                } else if (x >=1 && x <=2 && y ==2) {
                    checkString += flagged;
                } else if (x == 5 && y == 4) {
                    checkString += flagged;
                } else if (x == 2 && y == 7) {
                    checkString += flagged;
                } else {
                    checkString += hidden;
                }
            }
            checkString += "\n";
        }

        checkString = checkString + "  " + blueColour;

        for (int x = 0; x < 6; x++)
        {
            checkString = checkString + x + " ";
        }

        checkString = checkString + "x" + whiteColour;

        System.out.println("\n Check String");
        System.out.println(checkString);
        System.out.println("\n testGrid.Draw()");
        System.out.println(testGrid.DrawGrid());

        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid after being flagged");

        //revel
        testGrid.Revel(0, 3);

        //Draw grid after reveled
        //Draw grid after flagged
        checkString = blueColour + "y\n" + whiteColour;

        for (int y = 9; y >= 0; y--)
        {
            checkString = checkString + blueColour + y + whiteColour;

            for (int x = 0; x < 6; x++)
            {
                checkString += " ";

                if(x >= 4 && y >= 5) {
                    checkString += hidden;
                } else if (x == 3 && y >=7) {
                    checkString += hidden;
                } else if (x == 2 && y >=8) {
                    checkString += hidden;
                } else if(x == 1 && y == 9) {
                    checkString += one;
                } else if (x >=1 && x <=2 && y == 6) {
                    checkString += one;
                } else if(x >=3 && x <=4 && y==4) {
                    checkString += one;
                }else if(x >= 0 && x <=1 && y ==1){
                    checkString += one;
                } else if(x == 3 && y == 5){
                    checkString += one;
                } else if (x == 1 && y >=7 && y <= 8){
                    checkString += two;
                } else if (x == 3 && y == 6){
                    checkString += two;
                } else if (x == 2 && y == 7){
                    checkString += flagged;
                } else if (x == 5 && y ==4){
                    checkString += flagged;
                } else if (x >= 1 && x <=2 && y == 2){
                    checkString += flagged;
                } else if (x >= 0 && x <= 1 && y == 0) {
                    checkString += flagged;
                } else {
                    checkString += zero;
                }

            }
            checkString += "\n";
        }

        checkString = checkString + "  " + blueColour;

        for (int x = 0; x < 6; x++)
        {
            checkString = checkString + x + " ";
        }

        checkString = checkString + "x" + whiteColour;

        System.out.println(checkString);
        System.out.println(testGrid.DrawGrid());

        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid after being reveled");

        //Unflagg some
        testGrid.FlagMines(0 , 0);
        testGrid.FlagMines(1, 0);
        testGrid.FlagMines(1, 2);
        testGrid.FlagMines(4, 1);

        //Draw grid after reveled
        //Draw grid after flagged
        checkString = blueColour + "y\n" + whiteColour;

        for (int y = 9; y >= 0; y--)
        {
            checkString = checkString + blueColour + y + whiteColour;

            for (int x = 0; x < 6; x++)
            {
                checkString += " ";

                if(x >= 4 && y >= 5) {
                    checkString += hidden;
                } else if (x == 3 && y >=7) {
                    checkString += hidden;
                } else if (x == 2 && y >=8) {
                    checkString += hidden;
                } else if(x == 1 && y == 9) {
                    checkString += one;
                } else if (x >=1 && x <=2 && y == 6) {
                    checkString += one;
                } else if(x >=3 && x <=4 && y==4) {
                    checkString += one;
                }else if(x >= 0 && x <=1 && y ==1){
                    checkString += one;
                } else if(x == 3 && y == 5){
                    checkString += one;
                } else if (x == 1 && y >=7 && y <= 8){
                    checkString += two;
                } else if (x == 3 && y == 6){
                    checkString += two;
                } else if (x == 2 && y == 7){
                    checkString += flagged;
                } else if (x == 5 && y ==4){
                    checkString += flagged;
                } else if (x == 2 && y ==2){
                    checkString += flagged;
                } else if (x == 1 && y ==2) {
                    checkString += hidden;
                } else if (x >= 0 && x <= 1 && y == 0) {
                    checkString += hidden;
                } else {
                    checkString += zero;
                }

            }
            checkString += "\n";
        }

        checkString = checkString + "  " + blueColour;

        for (int x = 0; x < 6; x++)
        {
            checkString = checkString + x + " ";
        }

        checkString = checkString + "x" + whiteColour;

        System.out.println(checkString);
        System.out.println(testGrid.DrawGrid());

        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid after unflagging some");

        //Force revele
        testGrid.ForceRevealAll();

        //Draw grid after force revel
        checkString = blueColour + "y\n" + whiteColour;

        for (int y = 9; y >= 0; y--)
        {
            checkString = checkString + blueColour + y + whiteColour;

            for (int x = 0; x < 6; x++)
            {
                checkString += " ";

                if(x >= 1 && x <=3 && y == 9) {
                    checkString += one;
                } else if(x >= 3 && y == 4){
                    checkString += one;
                } else if(x >= 1 && x <= 2 && y == 6){
                    checkString += one;
                }else if(x >= 4 && y == 6){
                    checkString += one;
                } else if(x >= 0 && x <= 1 && y == 1){
                    checkString += one;
                } else if((x == 1 && y == 0)||(x == 3 && y == 5)||(x == 5 && y == 5))
                {
                    checkString += one;
                }else if (x == 1 && y >= 7 && y <= 8)
                {
                    checkString += two;
                }else if (x == 3 && y >= 6 && y <=8)
                {
                    checkString += two;
                } else if ((x == 0 && y == 0) || (x == 2 && y == 8) || (x == 4 && y == 5)){
                    checkString = checkString + redColour + "\u00D7" + whiteColour;
                } else if (x == 2 && y == 7) {
                    checkString = checkString + greenColour + "\u00D7" + whiteColour;
                } else {
                    checkString += zero;
                }

            }
            checkString += "\n";
        }

        checkString = checkString + "  " + blueColour;

        for (int x = 0; x < 6; x++)
        {
            checkString = checkString + x + " ";
        }

        checkString = checkString + "x" + whiteColour;

        System.out.println(checkString);
        System.out.println(testGrid.DrawGrid());

        Assertions.assertEquals(checkString, testGrid.DrawGrid(), "Failed to draw the grid after being force reveled");

    }

    @Test
    public void testGridNumberPositioning()
    {
        testGrid = new Grid(3, 3, 1);

        int[][] testMineGrid = {{-1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] checkMineGrid = {{-1, 1, 0}, {1, 1, 0}, {0, 0, 0}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 1");
            }
        }

        testMineGrid = new int[][] {{-1, 0, -1}, {-1, -1, 0}, {0, 0, 0}};
        checkMineGrid = new int[][]  {{-1, 4, -1}, {-1, -1, 2}, {2, 2, 1}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 4");
            }
        }

        testMineGrid = new int[][] {{0, 0, 0}, {-1, 0, -1}, {-1, -1, -1}};
        checkMineGrid = new  int[][] {{1, 2, 1},{-1, 5, -1},{-1, -1, -1}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 5");
            }
        }

        testMineGrid = new int[][] {{-1, -1, -1}, {-1, 0, -1}, {0, 0, -1}};
        checkMineGrid = new  int[][] {{-1, -1, -1}, {-1, 6, -1}, {1, 3, -1}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 6");
            }
        }

        testMineGrid = new int[][] {{-1, -1, -1}, {-1, 0, -1}, {-1, 0, -1}};
        checkMineGrid = new  int[][] {{-1, -1, -1}, {-1, 7, -1}, {-1, 4, -1}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 7");
            }
        }

        testMineGrid = new int[][] {{-1, -1, -1}, {-1, 0, -1}, {-1, -1, -1}};
        checkMineGrid = new  int[][] {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

        testMineGrid = testGrid.ManualNumberGrid(testMineGrid);

        for (int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                Assertions.assertEquals(checkMineGrid[y][x],testMineGrid[y][x], "number mismatch at (" + x + ", " + y +") max 7");
            }
        }
    }
}

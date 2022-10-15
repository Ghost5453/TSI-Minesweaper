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

            } else if (i == 2) {

                Assertions.assertEquals(30, testXSize, "Incorrect x size for Expert");
                Assertions.assertEquals(16, testYSize, "Incorrect y size for Expert");

            } else {
                System.out.println("Loop overflow");
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

            } else if (i == 2) {

                Assertions.assertEquals(99, testMineCount, "Incorrect number of mines for Expert");

            } else {
                System.out.println("Loop overflow");
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

//    @Test
//    public void testDrawGrid()
//    {
//       // int[] testNumsGrid = {{},{},{},{},{},{},{},{},{},{},{},{}};
//        testGrid = new Grid(10, 4, 8);
//
//        // y: 09, 11, 01, 06, 10, 07, 01, 12
//        // x: 04, 02, 01, 04, 03, 01, 03, 02
//
//    }

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

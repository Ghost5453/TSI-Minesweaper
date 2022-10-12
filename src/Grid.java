public class Grid {

    private int mines, time, xSize, ySize, newY;
    private GridSquare[][] grid;

    Grid(int myHeight, int myWidth, int mines){

        this.xSize = myWidth;
        this.ySize = myHeight;
        this.mines = mines;
        AssignGridValues();
        //RenderGrid();
    }

    public String RenderGrid()
    {
        String gridStr;
        gridStr ="y\n";

        for (int y = ySize - 1; y >= 0; y--)
        {
            gridStr += y;
            gridStr += "   ";
            for (int x = 0; x < xSize; x++)
            {
                gridStr += grid[y][x].CheckSquare();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }

            gridStr += "\n";
        }

        gridStr += "\n";

        gridStr += "    ";

        for (int x = 0; x < xSize; x++)
        {
            gridStr = gridStr + x + " ";
        }

        gridStr += "x";

        return gridStr;
    }

    public int GetScore()
    {
        return time;
    }

    private void AssignGridValues()
    {
        int[][] numberGrid = new int[ySize][xSize];
        int minesLeft;

        minesLeft = mines;

        for (int y = 0; y < ySize; y++ )
        {
            for (int x = 0; x < xSize; x++)
            {
                numberGrid[y][x] = 0;
            }
        }

        while (minesLeft > 0)
        {
            int x, y;

            x = (int) (Math.random() * xSize);
            y = (int) (Math.random() * ySize);

            System.out.println("x: " + x + ", y: " + y);

            if(numberGrid[y][x] == 0)
            {
                numberGrid[y][x] = -1;
                minesLeft--;
            }
        }

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                if (numberGrid[y][x] == -1)
                {
                    for (int localY = -1; localY <= 1; localY++)
                    {
                        for (int localX = -1; localX <= 1; localX++)
                        {
                            int yTest, xTest;
                            boolean yOver =true, xOver = true;

                            if (localY == 0 && localX == 0)
                                continue;

                            yTest = localY + y;
                            xTest = localX + x;

                            if (yTest < ySize && yTest >= 0)
                            {
                                yOver = false;
                            }

                            if (xTest < xSize && xTest >=0)
                            {
                                xOver = false;

                            }

                            if (xOver || yOver)
                                continue;

                            if (numberGrid[yTest][xTest] >= 0)
                            {
                                numberGrid[yTest][xTest] += 1;
                            }

                        }
                    }
                }
            }
        }

        GenerateGrid(numberGrid);

    }

    private void GenerateGrid(int[][] myMineMask)
    {
        System.out.println("");
        grid = new GridSquare[ySize][xSize];
        for(int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                grid[y][x] = new GridSquare(myMineMask[y][x]);
            }
        }
    }

}

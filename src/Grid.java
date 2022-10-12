public class Grid {

    private int mines, time, xSize, ySize, newY, flagedMines;
    private GridSquare[][] grid;

    Grid(int myHeight, int myWidth, int mines){

        this.xSize = myWidth;
        this.ySize = myHeight;
        this.mines = mines;
        AssignGridValues();
        //RenderGrid();
    }

    public void Flag(int myX, int myY)
    {
        grid[myY][myX].FlagSquare();

        if (grid[myY][myX].GetFlag())
        {
            flagedMines++;
        }
        else
        {
            flagedMines--;
        }
        RenderGrid();
    }

    public void Check(int myX, int myY)
    {
        if (grid[myY][myX].GetReveled())
        {
            System.out.println("This tile has already been reveled");
        }else {
            if (grid[myY][myX].GetFlag())
            {
                System.out.println("You cannot check flagged tiles");
            }else
            {
                RecursiveRevel(myX, myY);
            }
        }
        RenderGrid();
    }

    public String RenderGrid()
    {
        String gridStr;
        gridStr ="y\n";

        for (int y = ySize - 1; y >= 0; y--)
        {
            if (y < 100 && ySize > 100)
            {
                gridStr += "0";
            }
            if (y < 10 && ySize > 10)
            {
                gridStr += "0";
            }

            gridStr += y;
            gridStr += "   ";
            for (int x = 0; x < xSize; x++)
            {
                if (xSize > 100)
                {
                    gridStr += " ";
                }
                if (xSize > 10)
                {
                    gridStr += " ";
                }
                gridStr += grid[y][x].CheckSquare();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }

            gridStr += "\n";
        }

        gridStr += "\n";

        if (ySize > 100)
        {
            gridStr += " ";
        }

        if (ySize > 10)
        {
            gridStr += " ";
        }

        gridStr += "    ";

        for (int x = 0; x < xSize; x++)
        {
            if (x < 100 && xSize > 100)
            {
                gridStr += "0";
            }
            if (x < 10 && xSize > 10)
            {
                gridStr += "0";
            }
            gridStr = gridStr + x + " ";
        }

        gridStr += "x";

        return gridStr;
    }

    public int MinesLeft()
    {
        return mines - flagedMines;
    }

    private void RecursiveRevel(int myX, int myY)
    {
        int newX, newY;
        boolean yInRange = false, xInRange = false, checked;

        checked = grid[myY][myX].GetReveled();
        grid[myY][myX].RevelSquare();

        if (grid[myY][myX].GetContents() == 0 && !checked)
        {
            for (int dY = -1; dY <= 1; dY++)
            {
                newY = dY + myY;
                for (int dX = -1; dX <= 1; dX++)
                {
                    xInRange = false;
                    yInRange = false;

                    newX = dX + myX;

                    if (newX == myX && newY == myY)
                    {
                        continue;
                    }

                    if (newX >= 0 && newX < xSize)
                        xInRange = true;

                    if (newY >= 0 && newY < ySize)
                        yInRange = true;

                    if (yInRange && xInRange)
                    {
                        RecursiveRevel(newX, newY);
                    }
                }
            }
        }



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

    public int GetScore()
    {
        return time;
    }

    public int GetRemainingMines()
    {
        return mines - flagedMines;
    }

}

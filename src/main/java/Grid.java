import java.util.Random;

public class Grid {

    private int mines;
    private int xSize;
    private int ySize;
    private int flaggedMines;

    private GridSquare[][] gameGrid;

    Grid(int myHeight, int myWidth, int mines){

        this.xSize = myWidth;
        this.ySize = myHeight;
        this.mines = mines;
        AssignGridValues();
    }

    //region Inputs
    public void FlagMines(int myX, int myY)
    {
        gameGrid[myY][myX].FlagSquare();

        if (gameGrid[myY][myX].GetFlag())
        {
            flaggedMines++;
        }
        else
        {
            flaggedMines--;
        }
        CheckWin();
    }

    public void Revel(int myX, int myY)
    {
        int newX;
        int newY;

        boolean yInRange;
        boolean xInRange;
        boolean checked;

        checked = gameGrid[myY][myX].GetReveled();
        gameGrid[myY][myX].RevelSquare();

        if (gameGrid[myY][myX].GetContents() == -1)
        {
            Main.SetPlaying(false);
        } else
        {
            if (gameGrid[myY][myX].GetContents() == 0 && !checked)
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
                            continue;

                        if (newX >= 0 && newX < xSize)
                            xInRange = true;

                        if (newY >= 0 && newY < ySize)
                            yInRange = true;

                        if (yInRange && xInRange)
                            Revel(newX, newY);
                    }
                }
            }
        }
        CheckWin();
    }
    //endregion

    //region Outputs
    public String DrawGrid()
    {
        String blueColour ="\033[0;36m";
        String whiteColour = "\033[0;37m";
        String gridStr;

        gridStr = blueColour + "y\n" + whiteColour;

        for (int y = ySize - 1; y >= 0; y--)
        {
            gridStr += blueColour;
            if (y < 100 && ySize > 100)
            {
                gridStr += "0";
            }
            if (y < 10 && ySize > 10)
            {
                gridStr += "0";
            }

            gridStr = gridStr + y + whiteColour + " ";

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
                gridStr += gameGrid[y][x].ShowSquareImage();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }
            gridStr += "\n";
        }

        if (ySize > 100)
        {
            gridStr += " ";
        }

        if (ySize > 10)
        {
            gridStr += " ";
        }

        gridStr += "  ";

        gridStr += blueColour;

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
        gridStr += whiteColour;

        return gridStr;
    }

    public String ForceRevealAll()
    {
        for(int y = 0; y < ySize; y++)
        {
            for(int x = 0; x< xSize; x++)
            {
                gameGrid[y][x].ForceRevel();
            }
        }
        return DrawGrid();
    }

    public int MinesLeft()
    {
        return mines - flaggedMines;
    }

    //region Grid Getters
    public int GetXMax()
    {
        return xSize;
    }

    public int GetYMax()
    {
        return  ySize;
    }
    //endregion

    //region Tile Getters
    public boolean CheckFlagged(int x, int y)
    {
        return gameGrid[y][x].GetFlag();
    }

    public boolean CheckReveled(int x, int y)
    {
        return  gameGrid[y][x].GetReveled();
    }
    //endregion
    //endregion

    //region Private
    private void CheckWin()
    {
        boolean foundAllMinis = true;
        boolean foundAllSafe = true;

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                if (gameGrid[y][x].GetContents() >= 0)
                {
                    if (!gameGrid[y][x].GetReveled())
                    {
                        foundAllSafe = false;
                    }
                } else
                {
                    if (!gameGrid[y][x].GetFlag())
                    {
                        foundAllMinis = false;
                    }
                }
            }
        }

        if (foundAllSafe && foundAllMinis)
        {
            System.out.println(DrawGrid());
            Main.SetWin(true);
            Main.EndGame();
        }
    }

    private void AssignGridValues()
    {
        int[][] numberGrid = new int[ySize][xSize];

        numberGrid = PlaceMines();

        numberGrid = PlaceNumbers(numberGrid);

        GenerateGrid(numberGrid);
    }

    private int[][] PlaceMines()
    {
        Random random = new Random();
        int[][] mineGrid = new int[ySize][xSize];
        int minesLeft;

        minesLeft = mines;

        for (int y = 0; y < ySize; y++ )
        {
            for (int x = 0; x < xSize; x++)
            {
                mineGrid[y][x] = 0;
            }
        }

        while (minesLeft > 0)
        {
            int x;
            int y;

            x = random.nextInt(xSize);
            y = random.nextInt(ySize);

            if(mineGrid[y][x] == 0)
            {
                mineGrid[y][x] = -1;
                minesLeft--;
            }
        }
        return mineGrid;
    }

    private int[][] PlaceNumbers(int[][] myGrid)
    {
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                if (myGrid[y][x] == -1)
                {
                    for (int localY = -1; localY <= 1; localY++)
                    {
                        for (int localX = -1; localX <= 1; localX++)
                        {
                            int yTest;
                            int xTest;

                            boolean yOver =true;
                            boolean xOver = true;

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

                            if (myGrid[yTest][xTest] >= 0)
                            {
                                myGrid[yTest][xTest] += 1;
                            }
                        }
                    }
                }
            }
        }
        return myGrid;
    }

    private void GenerateGrid(int[][] myNumberGrid)
    {
        gameGrid = new GridSquare[ySize][xSize];
        for(int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                gameGrid[y][x] = new GridSquare(myNumberGrid[y][x]);
            }
        }
    }
    //endregion
}

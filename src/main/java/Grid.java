import java.util.Random;
import java.lang.Math;

public class Grid {

    private int mines;
    private int xSize;
    private int ySize;
    private int flaggedMines;

    private GridSquare[][] gameGrid;

    Grid(int myHeight, int myWidth, int myMines) //tested: preset, custom
    {
        if (myHeight < 2)
            myHeight = 2;

        if (myWidth < 2)
            myWidth = 2;

        if (myMines < 1)
            myMines=1;

        if (myMines >= (myHeight * myWidth))
        {
            myMines = (int)((myHeight * myWidth) / 4.85f);
        }

        this.xSize = myWidth;
        this.ySize = myHeight;
        this.mines = myMines;
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

    public void Revel(int myX, int myY) //tested
    {
        int newX;
        int newY;

        boolean yInRange;
        boolean xInRange;
        boolean checked;
        boolean flagged;

        checked = gameGrid[myY][myX].GetReveled();
        flagged = gameGrid[myY][myX].GetFlag();

        if (!checked && !flagged)
        {
            gameGrid[myY][myX].RevelSquare();

            if (gameGrid[myY][myX].GetContents() == -1)
            {
                Main.SetPlaying(false);
            } else
            {
                if (gameGrid[myY][myX].GetContents() == 0)
                {
                    for (int dY = -1; dY <= 1; dY++)
                    {
                        newY = dY + myY;
                        for (int dX = -1; dX <= 1; dX++)
                        {
                            xInRange = false;
                            yInRange = false;

                            newX = dX + myX;

                            if (newX >= 0 && newX < xSize)
                                xInRange = true;

                            if (newY >= 0 && newY < ySize)
                                yInRange = true;

                            if (newX == myX && newY == myY)
                            {
                                xInRange = false;
                                yInRange = false;
                            }

                            if (yInRange && xInRange)
                                Revel(newX, newY);
                        }
                    }
                }
            }
        }
        CheckWin();
    }
    //endregion

    //region Outputs
    public String DrawGrid() //tested
    {
        String blueColour ="\033[0;36m";
        String whiteColour = "\033[0;37m";
        String gridStr;
        int xPower = 1;
        int yPower = 1;
        float xSizeFloat = (float) xSize;
        float ySizeFloat = (float) ySize;

        while ((xSizeFloat / Math.pow(10, xPower)) > 1)
            xPower++;

        while ((ySizeFloat / Math.pow(10, yPower))> 1)
            yPower++;

        gridStr = blueColour + "y\n" + whiteColour;

        for (int y = ySize - 1; y >= 0; y--)
        {
            gridStr += blueColour;

            if (yPower > 1)
            {
                for (int p = yPower -1; p > 0; p--)
                {
                    if (y < Math.pow(10, p))
                    {
                        gridStr += "0";
                    }
                }
            }

            gridStr = gridStr + y + whiteColour + " ";

            for (int x = 0; x < xSize; x++)
            {
                if (xPower > 1)
                {
                    for(int p = xPower - 1; p > 0; p--)
                    {
                        gridStr += " ";
                    }
                }

                gridStr += gameGrid[y][x].ShowSquareImage();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }
            gridStr += "\n";
        }

        if (yPower > 1)
        {
            for(int p = yPower; p > 0; p--)
            {
                gridStr += " ";
            }
        }

        gridStr += "  ";

        gridStr += blueColour;

        for (int x = 0; x < xSize; x++)
        {
            if (xPower > 1)
            {
                for (int p = xPower - 1; p > 0; p--)
                {
                    if (x < Math.pow(10, p))
                    {
                        gridStr += "0";
                    }
                }
            }

            gridStr = gridStr + x + " ";
        }

        gridStr += "x";
        gridStr += whiteColour;

        return gridStr;
    }

    public String ForceRevealAll() //tested
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

    public int MinesLeft() //tested
    {
        return mines - flaggedMines;
    }

    //region Grid Getters
    public int GetXMax() //tested
    {
        return xSize;
    }

    public int GetYMax() //tested
    {
        return  ySize;
    }
    //endregion

    //region Tile Getters
    public boolean CheckFlagged(int x, int y) //tested
    {
        return gameGrid[y][x].GetFlag();
    }

    public boolean CheckReveled(int x, int y) //tested
    {
        return  gameGrid[y][x].GetReveled();
    }

    public int GetSquareContent(int x, int y) // tested
    {
        return gameGrid[y][x].GetContents();
    }
    //endregion
    //endregion

    //region Manual Overrides for Private Methods
    public void ManualSetGameBord(int[][] myNewBoard) //tested
    {
        GenerateGrid(myNewBoard);
    }

    public int[][] ManualNumberGrid(int[][] myMineGrid) //tested
    {
        return PlaceNumbers(myMineGrid);
    }

    //endregion

    //region Private
    private void AssignGridValues() // tested: presets, custom
    {
        int[][] numberGrid = new int[ySize][xSize];

        numberGrid = PlaceMines();

        numberGrid = PlaceNumbers(numberGrid);

        GenerateGrid(numberGrid);
    }

    private int[][] PlaceMines() // tested: presets, custom
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

    private int[][] PlaceNumbers(int[][] myGrid) //tested
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

    private void GenerateGrid(int[][] myNumberGrid) // tested: preset, custom
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
    //endregion
}

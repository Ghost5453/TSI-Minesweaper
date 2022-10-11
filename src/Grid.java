public class Grid {

    private int mines, time, xSize, ySize, newY;
    private GridSquare[][] grid;

    Grid(int myHeight, int myWidth, int mines){

        this.xSize = myWidth;
        this.ySize = myHeight;
        this.mines = mines;
        PlaceMines();
        //RenderGrid();
    }

    public String RenderGrid()
    {
        String gridStr;
        gridStr ="y\n";

        newY = ySize - 1;

        for (int y = 0; y < ySize; y++)
        {
            gridStr += newY;
            gridStr += "  ";
            for (int x = 0; x < xSize; x++)
            {
                gridStr += grid[newY][x].CheckSquare();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }

            newY--;

            gridStr += '\n';
        }

        gridStr += "   ";

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

    private void PlaceMines()
    {
        int[][] mineMask = new int[ySize][xSize];
        int minesLeft;

        minesLeft = mines;

        for (int y = 0; y < ySize; y++ )
        {
            for (int x = 0; x < xSize; x++)
            {
                mineMask[newY][x] = 0;
            }
        }

        while (minesLeft > 0)
        {
            int x, y;

            x = (int) (Math.random() * xSize);
            y = (int) (Math.random() * ySize);

            System.out.println("x: " + x + ", y: " + y);

            if(mineMask[y][x] == 0)
            {
                mineMask[y][x] = -1;
                minesLeft--;
            }
        }

        GenerateGrid(mineMask);

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

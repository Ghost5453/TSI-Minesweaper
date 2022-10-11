public class Grid {

    private int mines, time, xSize, ySize;
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
        gridStr ="";

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                gridStr += grid[y][x].CheckSquare();
                if (x < xSize - 1)
                {
                    gridStr += ' ';
                }
            }

            if (y < ySize - 1)
            {
                gridStr += '\n';
            }
        }

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
                mineMask[y][x] = 0;
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

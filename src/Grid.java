import java.util.ArrayList;
import java.util.List;

public class Grid {

    private int mines, time, xSize, ySize;
    private GridSquare[][] grid;

    Grid(int myXSize, int myYSize, int myMines){

        this.xSize = myXSize;
        this.ySize = myYSize;
        this.mines = myMines;
        GenerateGrid();
    }

    public String RenderGrid()
    {
        String gridStr;
        List<Character> gridCharacterList;
        char[] gridCharArr;
        int stringCounter;

        gridCharacterList = new ArrayList<Character>();
        gridStr ="";

        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                gridStr += grid[x][y].CheckSquare();
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

    private void GenerateGrid()
    {
        grid = new GridSquare[xSize][ySize];
        for(int y = 0; y < xSize; y++)
        {
            for (int x = 0; x < ySize; x++)
            {
                grid[x][y] = new GridSquare(x, y, 0);
            }
        }
    }

}

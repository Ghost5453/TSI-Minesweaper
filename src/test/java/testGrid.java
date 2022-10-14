import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testGrid {
    int height = 10;
    int width = 10;
    int mines = 20;

    Mode testMode = new Mode(width,height,mines);
    Grid testGrid = new Grid(testMode.height,testMode.width,testMode.mines);

    @Test
    public void testGridGen()
    {

    }

}

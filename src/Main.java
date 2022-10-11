import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output;

        Grid newGame = new Grid(5,5,3);
        output = newGame.RenderGrid();
        System.out.println(output);
        input.nextLine();

    }

    public static void EndGame(boolean myWin)
    {
        if (myWin)
        {
            System.out.println("Congratulations you did it :)");

        }
        else
        {
            System.out.println("Try agan :(");
        }
    }

    public static void NewGame()
    {
        Scanner input = new Scanner(System.in);

    }
}
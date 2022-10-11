import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output;
        Mode beginner = new Mode(9, 9, 10);
        Mode intermediat = new Mode(16, 16, 40);
        Mode expert = new Mode(30, 16, 99);

        System.out.println("What difficulty do you want to play:\n((B)eginner, (I)ntermediat, (E)xpert or (C)ustom");

        //TODO: input parsing
        //TODO: custom mode

        Grid newGame = new Grid(10,8,13);
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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output;

        Grid newGame = new Grid(5,5,0);
        output = newGame.RenderGrid();
        System.out.println(output);
        input.nextLine();

    }
}
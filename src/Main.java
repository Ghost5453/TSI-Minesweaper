import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output, inputString;
        String[] test;
        int conuter;
        Mode beginner = new Mode(9, 9, 10);
        Mode intermediat = new Mode(16, 16, 40);
        Mode expert = new Mode(30, 16, 99);

        System.out.println("What difficulty do you want to play:\n((B)eginner, (I)ntermediat, (E)xpert or (C)ustom");

        //TODO: input parsing
        //TODO: custom mode

        Grid newGame = new Grid(10,8,13);
        output = newGame.RenderGrid();
        System.out.println(output);

        inputString = input.nextLine();

        test = InputParsing(inputString);

        conuter = 0;


        for (String mySt:test) {

            output = test[conuter];

            System.out.println(output);
            conuter++;

        }

        input.nextLine();

    }

    public static String[] InputParsing (String input)
    {
        char[] inputCharArray;
        String[] returnStrings;
        String toAdd = "";
        int counter;
        List<String> parsesList = new ArrayList<>();

        inputCharArray = input.toCharArray();

        for (char myChar:inputCharArray) {

            if (myChar == ' ')
            {
                if (toAdd != "")
                {
                    parsesList.add(toAdd);
                    toAdd = "";
                }
            } else
            {
                toAdd += myChar;
            }
        }

        parsesList.add(toAdd);

        returnStrings =  new String[parsesList.size()];
        counter = 0;
        for (String myStr:parsesList)
        {
            returnStrings[counter] = parsesList.get(counter);
            counter++;
        }

        return returnStrings;
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
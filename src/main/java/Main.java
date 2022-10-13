import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean Playing, Win;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output, inputString, lowerString;
        char action;
        int counter, height, width, mines, xCoordinate, yCoordinate;
        boolean inGame = true, playing, validInput;

        String[] parcedInput;
        Grid game;
        Mode beginner = new Mode(9, 9, 10), intermediate = new Mode(16, 16, 40), expert = new Mode(30, 16, 99), current;

        Main.SetPlaying(true);


        do{
            do {
                validInput = true;

                System.out.println("What difficulty do you want to play:\n((B)eginner, (I)ntermediate, (E)xpert or (C)ustom");

                inputString = input.nextLine();
                parcedInput = InputParsing(inputString);

                lowerString = parcedInput[0].toLowerCase();
                action = lowerString.charAt(0);

                if (action == 'b')
                {
                    current = beginner;
                } else if(action == 'i')
                {
                    current = intermediate;
                } else if(action == 'e')
                {
                    current = expert;
                } else if(action == 'c')
                {
                    System.out.println("What format do you want to use\n(type in the form: \"height\" \"width\" \"mines\")");
                    inputString = input.nextLine();
                    parcedInput = InputParsing(inputString);

                    try {
                        height = Integer.valueOf(parcedInput[0]).intValue();
                        width = Integer.valueOf(parcedInput[1]).intValue();
                        mines = Integer.valueOf(parcedInput[2]).intValue();

                        if (mines >= (height * width))
                        {
                            int newMines;
                            System.out.println("Too many mines");
                            newMines = (int)((height*width) / 4.85f);
                            System.out.println("Will use: " + newMines + " mines");
                            current = new Mode(height, width, newMines);
                        }
                        else
                        {
                            current = new Mode(height, width, mines);
                        }

                    } catch (Exception fail)
                    {
                        System.out.println("Invalid input");
                        validInput = false;
                        current = intermediate;
                    }
                } else
                {
                    System.out.println("Invalid input");
                    validInput = false;
                    current = intermediate;
                }

            }while (!validInput);



            // In game

            game = new Grid(current.height, current.width, current.mines);
            playing = true;

            do {
                System.out.println("There are: " + game.GetRemainingMines() + " left");
                output = game.RenderGrid();
                System.out.println(output);
                System.out.println("List of commands:\nCheck \"X coordinate\" \"Y coordinator\"\nFlag \"X coordinate\" \"Y coordinator\"\nCheck revels the square and Flag makes the square");

                inputString = input.nextLine();
                parcedInput = InputParsing(inputString);

                lowerString = parcedInput[0].toLowerCase();
                action = lowerString.charAt(0);

                if(action == 'q')
                {
                    System.out.println("Do you want to quit\n\"yes\" or \"no\"");

                    inputString = input.nextLine();
                    parcedInput = InputParsing(inputString);

                    lowerString = parcedInput[0].toLowerCase();
                    action = lowerString.charAt(0);

                    if(action == 'y')
                    {
                        SetPlaying(false);
                    }else
                    {
                        System.out.println("Continuing");
                    }
                }else
                {
                    try
                    {
                        xCoordinate = Integer.valueOf(parcedInput[1]).intValue();
                        yCoordinate = Integer.valueOf(parcedInput[2]).intValue();

                    }catch (Exception fail)
                    {
                        System.out.println("Invalid input");
                        continue;
                    }

                    if (action == 'f')
                    {
                        game.Flag(xCoordinate,yCoordinate);
                    }else if(action == 'c')
                    {
                        game.Check(xCoordinate,yCoordinate);
                    } else
                    {
                        System.out.println("Invalid action");
                    }
                }



            }while (GetPlaying());

            System.out.println("Would you like to play again\n\"yes\" or \"no\"");
            inputString = input.nextLine();
            lowerString = inputString.toLowerCase();
            parcedInput = InputParsing(lowerString);
            action = parcedInput[0].charAt(0);

            if (action == 'n')
            {
                inGame = false;
            } else if (action == 'y')
            {

            } else
            {
                System.out.println("Invalid input type \"yes\" or \"no\"");
            }

        }while (inGame);
    }

    public static boolean GetWin()
    {
        return Win;
    }

    public static void SetWin(boolean myBool)
    {
        Win = myBool;
    }

    public static void SetPlaying (boolean myBool)
    {
        Playing = myBool;
    }
    public static boolean GetPlaying()
    {
        return Playing;
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
            Playing = false;
            System.out.println("You hit a mine");
            System.out.println("Try agan :(");
        }
    }
}
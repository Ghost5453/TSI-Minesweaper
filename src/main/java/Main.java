import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static boolean Playing, Win;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String output, inputString, lowerString;

        char action;
        int height, width, mines, xCoordinate, yCoordinate, extraOutputs, maxCash = 5;
        boolean inGame = true, validInput;

        String[] chasedInputStrings = new String[maxCash];
        String[] parsedInput;
        Grid game;
        Mode beginner = new Mode(9, 9, 10), intermediate = new Mode(16, 16, 40), expert = new Mode(30, 16, 99), current;

        Main.SetPlaying(true);

        do{
            do {
                int modifier;
                extraOutputs = 0;
                validInput = true;

                System.out.println("What difficulty do you want to play:\n((B)eginner, (I)ntermediate, (E)xpert or (C)ustom");

                inputString = input.nextLine();
                parsedInput = InputParsing(inputString);

                lowerString = parsedInput[0].toLowerCase();
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
                    if  (parsedInput.length > 1)
                    {
                        if (parsedInput.length >= 3)
                        {
                            extraOutputs = 1;
                        }
                        else
                        {
                            System.out.println("Incomplete secondary input");
                            extraOutputs = 0;
                        }
                    } else
                    {
                        extraOutputs = 0;
                    }

                    if (extraOutputs == 0)
                    {
                        System.out.println("What format do you want to use\n(type in the form: \"height\" \"width\" \"mines\")");
                        inputString = input.nextLine();
                        parsedInput = InputParsing(inputString);
                        modifier = 0;
                    }
                    else
                    {
                        modifier = 1;
                    }

                    try {
                        height = Integer.valueOf(parsedInput[0+modifier]).intValue();
                        width = Integer.valueOf(parsedInput[1+modifier]).intValue();
                        mines = Integer.valueOf(parsedInput[2+modifier]).intValue();

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
            extraOutputs = 0;

            do {
                System.out.println("There are: " + game.GetRemainingMines() + " left");
                output = game.RenderGrid();
                System.out.println(output);
                System.out.println("List of commands:\nCheck \"X coordinate\" \"Y coordinator\"\nFlag \"X coordinate\" \"Y coordinator\"\nCheck revels the square and Flag makes the square");

                inputString = input.nextLine();
                parsedInput = InputParsing(inputString);

                lowerString = parsedInput[0].toLowerCase();
                action = lowerString.charAt(0);

                if(action == 'q')
                {
                    System.out.println("Do you want to quit\n\"yes\" or \"no\"");

                    inputString = input.nextLine();
                    lowerString = inputString.toLowerCase();
                    parsedInput = InputParsing(lowerString);

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
                    boolean canCash = false;
                    int inputCount = 0;
                    while (canCash)
                    {
                        if (extraOutputs < maxCash)
                        {
                            for (int i = 0; i < 3; i++)
                            {
                                chasedInputStrings[extraOutputs] += parsedInput[inputCount];
                            }

                            extraOutputs++;
                            inputCount++;
                            if (inputCount >= parsedInput.length)
                            {
                                canCash = false;
                            }
                        }
                        else
                        {
                            canCash = false;
                        }
                    }

                    for (int i = 0; i < inputCount; i++)
                    {
                        try
                        {
                            parsedInput = InputParsing(chasedInputStrings[i]);

                            xCoordinate = Integer.valueOf(parsedInput[1]).intValue();
                            yCoordinate = Integer.valueOf(parsedInput[2]).intValue();

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
                        if (!GetPlaying())
                        {
                            game.ForceRevealAll();
                            System.out.println(game.RenderGrid());
                            EndGame(false);
                        }
                    }
                }
            }while (GetPlaying());

            System.out.println("Would you like to play again\n\"yes\" or \"no\"");
            inputString = input.nextLine();
            lowerString = inputString.toLowerCase();
            parsedInput = InputParsing(lowerString);
            action = parsedInput[0].charAt(0);

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

    public static String @NotNull [] InputParsing (@NotNull String input)
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
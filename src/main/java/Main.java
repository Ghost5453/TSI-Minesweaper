import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String output;
        String inputString;
        String lowerString;

        String[] parsedInput;

        char action;

        int height;
        int width;
        int mines;
        int xCoordinate;
        int yCoordinate;

        boolean inGame = true;
        boolean validInput;


        Grid game;

        Mode beginner = new Mode(9, 9, 10);
        Mode intermediate = new Mode(16, 16, 40);
        Mode expert = new Mode(30, 16, 99);
        Mode current;

        Main.SetPlaying(true);

        do{
            SetError("");
            do {
                validInput = true;

                System.out.println("What difficulty do you want to play:\n((B)eginner, (I)ntermediate, (E)xpert or (C)ustom");

                inputString = input.nextLine();

                if(inputString.equals(""))
                {
                    validInput = false;
                    current = beginner;
                    continue;
                }

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
                    System.out.println("What format do you want to use\n(type in the form: \"height\" \"width\" \"mines\")");
                    inputString = input.nextLine();
                    parsedInput = InputParsing(inputString);

                    try {
                        height = Integer.valueOf(parsedInput[0]).intValue();
                        width = Integer.valueOf(parsedInput[1]).intValue();
                        mines = Integer.valueOf(parsedInput[2]).intValue();

                        if (mines >= (height * width))
                        {
                            int newMines;
                            System.out.println("\033[0;31mToo many mines");
                            newMines = (int)((height*width) / 4.85f);
                            System.out.println("Will use: " + newMines + " mines\033[0;37m");
                            current = new Mode(height, width, newMines);
                        }
                        else
                        {
                            current = new Mode(height, width, mines);
                        }

                    } catch (Exception fail)
                    {
                        System.out.println("\033[0;31mInvalid input\033[0;37m");
                        validInput = false;
                        current = intermediate;
                    }
                } else
                {
                    System.out.println("\033[0;31mInvalid input\033[0;37m");
                    validInput = false;
                    current = intermediate;
                }

            }while (!validInput);

            // In game

            game = new Grid(current.height, current.width, current.mines);

            do {
                if (game.MinesLeft() == 1 || game.MinesLeft() == -1 )
                {
                    System.out.println("There are: " + game.MinesLeft() + " mine left");
                }
                else
                {
                    System.out.println("There are: " + game.MinesLeft() + " mines left");
                }

                output = game.DrawGrid();
                System.out.println(output);
                System.out.println("List of commands:\nCheck \"X coordinate\" \"Y coordinator\"\nFlagMines \"X coordinate\" \"Y coordinator\"\nQuit");
                System.out.println("Check revels the square; FlagMines marks / unmarks the square, stopping you form reveling it; and Quit ends the game");

                if (!GetError().equals(""))
                {
                    System.out.println(GetError());
                    SetError("");
                }

                inputString = input.nextLine();

                if(inputString.equals(""))
                {
                    SetError("Wright a command");
                    continue;
                }

                parsedInput = InputParsing(inputString);

                lowerString = parsedInput[0].toLowerCase();
                action = lowerString.charAt(0);

                if(action == 'q')
                {
                    System.out.println("Do you want to quit\n\"yes\" or \"no\"");

                    inputString = input.nextLine();
                    lowerString = inputString.toLowerCase();
                    parsedInput = InputParsing(lowerString);

                    action = parsedInput[0].charAt(0);

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
                        xCoordinate = Integer.valueOf(parsedInput[1]).intValue();
                        yCoordinate = Integer.valueOf(parsedInput[2]).intValue();

                    }catch (Exception fail)
                    {
                        SetError("Invalid X or Y input");
                        continue;
                    }

                    if (xCoordinate< 0 || xCoordinate >= game.GetXMax())
                    {
                        SetError("X is out of range");
                        continue;
                    }

                    if(yCoordinate < 0 || yCoordinate >= game.GetYMax())
                    {
                        SetError("Y is out of range");
                        continue;
                    }

                    if (action == 'f')
                    {
                        game.FlagMines(xCoordinate,yCoordinate);
                    }else if(action == 'c')
                    {
                        if (game.CheckFlagged(xCoordinate, yCoordinate))
                        {
                            SetError("Square flagged so can't be checked\nUse flag to unflag the square");
                            continue;
                        }

                        if(game.CheckReveled(xCoordinate,yCoordinate))
                        {
                            SetError("The square has already been reveled");
                            continue;
                        }

                        game.Revel(xCoordinate,yCoordinate);

                    } else
                    {
                        SetError("Invalid action");
                        continue;
                    }

                    if (!GetPlaying() && !GetWin())
                    {
                        System.out.println(game.ForceRevealAll());
                        if(!GetError().equals(""))
                        {
                            System.out.println(GetError());
                            SetError("");
                        }
                        EndGame();
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
                SetPlaying(true);
            } else
            {
                System.out.println("\033[31mInvalid input type \"yes\" or \"no\"\033[0;37m");
            }
        }while (inGame);
    }

    private static boolean _playing;
    private static boolean _win;

    private static String _errorMsg;

    private  static  void InitialSetup()
    {

    }

    private static void InGame()
    {

    }

    public static boolean GetWin()
    {
        return _win;
    }

    public static void SetWin(boolean myBool)
    {
        _win = myBool;
    }

    public static void SetError(String myError)
    {
        if (myError.equals(""))
        {
            _errorMsg = "";
        }else
        {
            _errorMsg = "\n\033[0;31m" + myError + "\033[0;37m\n";
        }
    }

    public static String GetError()
    {
        return _errorMsg;
    }

    public static void SetPlaying (boolean myBool)
    {
        _playing = myBool;
    }
    public static boolean GetPlaying()
    {
        return _playing;
    }

    public static String [] InputParsing (String input)
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
        returnStrings = new String[parsesList.size()];

        for (int i = 0; i <parsesList.size(); i++)
        {
            returnStrings[i] = parsesList.get(i);
        }

        return returnStrings;
    }

    public static void EndGame()
    {
        if (GetWin())
        {
            System.out.println("\n\033[32mCongratulations you did it :)\033[0;37m\n");
        }
        else
        {
            System.out.println("\n\033[0;31mYou hit a mine");
            System.out.println("Try agan :(\033[0;37m\n");
        }
        SetPlaying(false);
    }
}
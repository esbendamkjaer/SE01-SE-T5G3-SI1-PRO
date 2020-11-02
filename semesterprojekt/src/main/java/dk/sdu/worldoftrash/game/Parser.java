package dk.sdu.worldoftrash.game;

import java.util.Arrays;
import java.util.Scanner;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
    }

    /**
     * Reads a line from the command prompt and parses it to a Command object.
     * @return The command object corresponding to the player's input.
     */
    public Command getCommand(String inputLine)
    {
        String word1 = null;
        String word2 = null;

        inputLine.trim();

        String[] command = inputLine.split(" ");

        String[] args = null;
        if (command.length > 1) {
            args = Arrays.copyOfRange(command, 1, command.length);
        }

        if (command.length >= 1) {
            return new Command(commands.getCommandWord(command[0]), args);
        } else {
            return new Command(commands.getCommandWord(null), args);
        }
    }

    public void showCommands()
    {
        commands.showAll();
    }
}

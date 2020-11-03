package dk.sdu.worldoftrash.game;

public enum CommandWord
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    PICKUP("pickup"),
    DROP("drop"),
    INVENTORY("inventory"),
    USE("use"),
    SEARCH("search"),
    SAVE("save"), // Temporary
    UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}

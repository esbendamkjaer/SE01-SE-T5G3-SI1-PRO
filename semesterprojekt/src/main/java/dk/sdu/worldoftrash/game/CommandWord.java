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
    SCORE("score"),
    SAVE("save"), // Temporary
    TALK("talk"),
    GIVE("give"),
    UNKNOWN("?"),
    ;
    
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

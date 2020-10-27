package worldofzuul;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        //for at oprette et rum, skriv navnet på rummet efter et,
        Room start, skraldesorteringsrum, HUB,
                /*gren 1 */supermarkede, kontor, lager, parkeringsplads,
                /* gren 2 */hospital_udenfor, reception, operationsstuen, kapellet, kantine,
               /* gren 3 */ skole_udenfor, læreværelse, Kemi, gymnastik_rum, girls_lockerroom;

        // for at lave et intialisere rummet skal vi bruge *rumnavn = new Room();*
        start = new Room("at the start");
        skraldesorteringsrum = new Room(" in skraldesorteringsrum");
        HUB = new Room("in the HUB");
        supermarkede = new Room("in the supermarkede");
        kontor = new Room("in the supermarkede Kontor");
        lager = new Room("In the lager");
        parkeringsplads = new Room("At the parkeringsplads");
        hospital_udenfor = new Room("Outside the hospital");
        reception = new Room("In the hospital reception");
        operationsstuen = new Room("in the hauntingly clean operationsstue");
        kapellet = new Room("in the kapellet");
        kantine = new Room("in the kantine");
        skole_udenfor = new Room("outside the school");
        læreværelse = new Room("In the læreværelse");
        Kemi = new Room("in the Kemi");
        gymnastik_rum = new Room("in the gymnastik_rum");
        girls_lockerroom = new Room("in the girls locer room");

        
        start.setExit("east", skraldesorteringsrum);

        skraldesorteringsrum.setExit("west", start);
        skraldesorteringsrum.setExit("east", HUB);

        HUB.setExit("east", supermarkede);
        HUB.setExit("west", skraldesorteringsrum);
        HUB.setExit("south", hospital_udenfor);
        HUB.setExit("north", skole_udenfor);

        // Gren #1
        supermarkede.setExit("west", HUB);
        supermarkede.setExit("east", kontor);
        supermarkede.setExit("north", lager);
        supermarkede.setExit("south", parkeringsplads);

        kontor.setExit("west", supermarkede);
        lager.setExit("south", supermarkede);
        parkeringsplads.setExit("north", supermarkede);

        //Gren #2
        hospital_udenfor.setExit("north", HUB);
        hospital_udenfor.setExit("south", reception);

        reception.setExit("north", hospital_udenfor);
        reception.setExit("east", operationsstuen);
        reception.setExit("south", kapellet);
        reception.setExit("west", kantine);

        operationsstuen.setExit("west", reception);
        kapellet.setExit("north", reception);
        kantine.setExit("east", reception);

        //Gren #3
        skole_udenfor.setExit("south", HUB);
        skole_udenfor.setExit("north", læreværelse);
        skole_udenfor.setExit("east", Kemi);
        skole_udenfor.setExit("west", gymnastik_rum);

        læreværelse.setExit("south", skole_udenfor);
        Kemi.setExit("west", skole_udenfor);
        gymnastik_rum.setExit("east", skole_udenfor);
        gymnastik_rum.setExit("south", girls_lockerroom);
        girls_lockerroom.setExit("north", gymnastik_rum);

        currentRoom = start;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}

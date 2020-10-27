package worldofzuul;

public class Game {
    private Parser parser;
    private Room currentRoom;


    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Instantiates the rooms in the game.
     */
    private void createRooms() {
        //for at oprette et rum, skriv navnet på rummet efter et,
        Room start, sortingRoom, odense,
                /* gren 1 */Supermarket, office, storageRoom, parkinglot,
                /* gren 2 */hospitalOutside, reception, operatingRoom, morgue, canteen,
                /* gren 3 */schoolOutside, teachersLounge, chemistryRoom, gymnasticsRoom, girlsLockerRoom;

        // for at lave et intialisere rummet skal vi bruge *rumnavn = new Room();*
        start = new Room("at the start");
        sortingRoom = new Room("in sorting room");
        odense = new Room("in the city of Odense. In the east is a supermarket, in the west is the sorting room, in the south is the hospital and in the north is the school.");
        Supermarket = new Room("in the supermarket");
        office = new Room("in the supermarket office");
        storageRoom = new Room("in the storage room");
        parkinglot = new Room("at the parking lot");
        hospitalOutside = new Room("outside the hospital");
        reception = new Room("in the hospital reception");
        operatingRoom = new Room("in the hauntingly clean operations room");
        morgue = new Room("in the morgue");
        canteen = new Room("in the canteen");
        schoolOutside = new Room("outside the school");
        teachersLounge = new Room("in the teachers lounge");
        chemistryRoom = new Room("in the chemistry room");
        gymnasticsRoom = new Room("in the gymnastics room");
        girlsLockerRoom = new Room("in the girls locker room");


        start.setExit("sorting-room", sortingRoom);

        sortingRoom.setExit("start", start);
        sortingRoom.setExit("Odense", odense);

        odense.setExit("east", Supermarket);
        odense.setExit("west", sortingRoom);
        odense.setExit("south", hospitalOutside);
        odense.setExit("north", schoolOutside);

        // Gren #1
        Supermarket.setExit("Odense", odense);
        Supermarket.setExit("office", office);
        Supermarket.setExit("storage-room", storageRoom);
        Supermarket.setExit("parking-lot", parkinglot);

        office.setExit("supermarket", Supermarket);
        storageRoom.setExit("supermarket", Supermarket);
        parkinglot.setExit("supermarket", Supermarket);

        //Gren #2
        hospitalOutside.setExit("Odense", odense);
        hospitalOutside.setExit("reception", reception);

        reception.setExit("hospital", hospitalOutside);
        reception.setExit("operations-room", operatingRoom);
        reception.setExit("morgue", morgue);
        reception.setExit("canteen", canteen);

        operatingRoom.setExit("reception", reception);
        morgue.setExit("reception", reception);
        canteen.setExit("reception", reception);

        //Gren #3
        schoolOutside.setExit("Odense", odense);
        schoolOutside.setExit("teachers-lounge", teachersLounge);
        schoolOutside.setExit("chemistry-room", chemistryRoom);
        schoolOutside.setExit("gymnastics-room", gymnasticsRoom);

        teachersLounge.setExit("school", schoolOutside);
        chemistryRoom.setExit("school", schoolOutside);
        gymnasticsRoom.setExit("school", schoolOutside);
        gymnasticsRoom.setExit("girls-lockerroom", girlsLockerRoom);
        girlsLockerRoom.setExit("gymnastics-room", gymnasticsRoom);

        currentRoom = start;
    }

    /**
     * Runs the game loop.
     */
    public void play() {
        printWelcome();


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Prints out a welcome message.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Executes the logic associated with a given command.
     * @param command Command to execute.
     * @return A boolean indicating whether the player wants to quit the game.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    /**
     * Prints out a help message.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Makes the player go to the room specified by the given command.
     * @param command Command that specifies where to go.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Meant to be used for quit commands.
     * Examines if a command has a second word in which case it is assumed the player didn't mean to quit the game.
     * @param command Quit command.
     * @return Whether or not the player meant to quit the game.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}

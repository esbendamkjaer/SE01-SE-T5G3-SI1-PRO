package dk.sdu.worldoftrash.game;

import dk.sdu.worldoftrash.game.data.WasteType;
import dk.sdu.worldoftrash.game.items.*;
import dk.sdu.worldoftrash.game.items.usables.Sink;
import dk.sdu.worldoftrash.game.items.usables.Usable;
import dk.sdu.worldoftrash.game.rooms.Room;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;

    private ScoreSystem scoreSystem;

    private Player player;

    private Scanner reader;

    private Room start, sortingRoom, odense,
    /* level 1 */supermarket, office, storageRoom, parkinglot,
    /* level 2 */hospitalOutside, reception, operatingRoom, morgue, canteen,
    /* level 3 */schoolOutside, teachersLounge, chemistryRoom, gymnasticsRoom, girlsLockerRoom;

    public Game() {
        this.parser = new Parser();
        this.reader = new Scanner(System.in);
        this.player = new Player(this, "Player");
        this.scoreSystem = new ScoreSystem();
        createRooms();
    }

    /**
     * Instantiates the rooms in the game.
     */
    private void createRooms() {
        // for at lave et intialisere rummet skal vi bruge *rumnavn = new Room();*
        start = new Room(this, "start", "at the start");

        // Waste objects #level 3
        Waste papers = new Waste(this, "papers", WasteType.PAPER, "this is a paper, it goes in paper",true);
        Waste lighter = new Waste(this, "lighter", WasteType.HAZARDOUS, "lighters contains hazardous materials and therefore goes in hazardous", true);
        Waste bananas = new Waste(this, "bananas", WasteType.ORGANIC, "this is an organic material and can be used as an compost and therefore goes in organic", true);
        Waste jam_jar = new Waste(this, "jam-jar", WasteType.GLASS, "a clean jar of jam can be recycled for reuse", false);
        Waste Nutella_glass = new Waste(this, "Nutella-glass", WasteType.GLASS, "a clean glass of nutella can be recycled for reuse", false);
        Waste Coffee_grounds = new Waste(this, "coffee grounds", WasteType.ORGANIC, "coffee grounds are an organic substance and be used for compost", true);
        Waste poster = new Waste(this, "poster", WasteType.CARDBOARD, "a poster is of cardboard material and goes in cardboard", true);
        Waste spectacles_frame = new Waste(this, "spectacles-frame", WasteType.METAL, "spectacles frame is made of steel and therefore goes in metal", true);
        Waste post_it = new Waste(this, "post-it", WasteType.RESIDUAL, "Due the adhesive substance in the back of post it notes they are not to be recycled with normal paper and goes in residual", true);
        Waste milk_carton = new Waste(this, "milk-carton", WasteType.RESIDUAL, "Due to a milk carton being soaked in a organic substance and goes in residual", true);
        Waste perfume_bottle = new Waste(this,"perfume-bottle", WasteType.GLASS, "if you remove the perfume substances in the bottle, then the bottle can be recycled for reuse and therefore goes in glass", false);
        Waste water_bottle = new Waste(this, "plastic-water-bottle", WasteType.HARD_PLASTIC, "typcically water bottle are made of hard plastic and can be recycled for reuse and therefore goes in hard plastic", true);
        Waste safety_goggles = new Waste(this, "safety-goggles", WasteType.HARD_PLASTIC, "typically safety goggles are made of hard plastic and can be recycled for reuse and therefore goes in hard plastic", true);
        Waste paper_clip = new Waste(this, "paper-clips", WasteType.RESIDUAL, "unless you got a large quantity of paper clips, recycling paper clips have a larger carbon footprint rather than just sorting them to residual and therefore goes in residual", true);
        Waste paint = new Waste(this, "paint", WasteType.HAZARDOUS, "paint is an hazardous material and should be handled accordingly and placed in hazardous", true);
        sortingRoom = new Room(this, "sortingRoom", "in sorting room");

        // Trash containers
        WasteContainer organicContainer = new WasteContainer(this, "organic-container", WasteType.ORGANIC);
        WasteContainer glassContainer = new WasteContainer(this, "glass-container", WasteType.GLASS);
        WasteContainer metalContainer = new WasteContainer(this,"metal-container", WasteType.METAL);
        WasteContainer papercontainer = new WasteContainer(this, "paper-container", WasteType.PAPER);
        WasteContainer residualcontainer = new WasteContainer(this, "residual-container", WasteType.RESIDUAL);
        WasteContainer cardboardcontainer = new WasteContainer(this, "cardboard-container", WasteType.CARDBOARD);
        WasteContainer hardPlasticcontainer = new WasteContainer(this,"plastic-container", WasteType.HARD_PLASTIC);
        sortingRoom.addItem(organicContainer);
        sortingRoom.addItem(glassContainer);

        //Assigning trash to the rooms
        sortingRoom.addItem(papers);
        sortingRoom.addItem(lighter);
        sortingRoom.addItem(bananas);

        teachersLounge.addItem(jam_jar);
        teachersLounge.addItem(Nutella_glass);
        teachersLounge.addItem(Coffee_grounds);

        gymnasticsRoom.addItem(poster);
        gymnasticsRoom.addItem(spectacles_frame);
        gymnasticsRoom.addItem(post_it);

        girlsLockerRoom.addItem(milk_carton);
        girlsLockerRoom.addItem(perfume_bottle);
        girlsLockerRoom.addItem(water_bottle);

        chemistryRoom.addItem(safety_goggles);
        chemistryRoom.addItem(paper_clip);
        chemistryRoom.addItem(paint);

        //Sink
        Sink sink = new Sink(this, "Sink");
        sortingRoom.addItem(sink);

        //Rooms
        odense = new Room(this, "city", "in the city of Odense. In the east is a supermarket, in the west is the sorting room, in the south is the hospital and in the north is the school.");
        supermarket = new Room(this, "supermarket", "in the supermarket");
        office = new Room(this, "office", "in the supermarket office");
        storageRoom = new Room(this, "storageRoom", "in the storage room");
        parkinglot = new Room(this, "parkinglot", "at the parking lot");
        hospitalOutside = new Room(this, "hospitalOutside", "outside the hospital");
        reception = new Room(this, "reception", "in the hospital reception");
        operatingRoom = new Room(this, "operatingRoom", "in the hauntingly clean operations room");
        morgue = new Room(this, "morgue", "in the morgue");
        canteen = new Room(this, "canteen", "in the canteen");
        schoolOutside = new Room(this, "schoolOutside", "outside the school");
        teachersLounge = new Room(this, "teachersLounge", "in the teachers lounge");
        chemistryRoom = new Room(this, "chemistryRoom", "in the chemistry room");
        gymnasticsRoom = new Room(this, "gymnasticsRoom", "in the gymnastics room");
        girlsLockerRoom = new Room(this, "girlsLockerRoom", "in the girls locker room");

        //Exit #0
        start.setExit("sorting-room", sortingRoom);

        sortingRoom.setExit("start", start);
        sortingRoom.setExit("Odense", odense);

        odense.setExit("east", supermarket);
        odense.setExit("west", sortingRoom);
        odense.setExit("south", hospitalOutside);
        odense.setExit("north", schoolOutside);

        // Gren #1
        supermarket.setExit("Odense", odense);
        supermarket.setExit("office", office);
        supermarket.setExit("storage-room", storageRoom);
        supermarket.setExit("parking-lot", parkinglot);

        office.setExit("supermarket", supermarket);
        storageRoom.setExit("supermarket", supermarket);
        parkinglot.setExit("supermarket", supermarket);

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

        scoreSystem.getLevelHandler().addLevel(supermarket, 0);
        scoreSystem.getLevelHandler().addLevel(hospitalOutside, 1);
        scoreSystem.getLevelHandler().addLevel(schoolOutside, 2);
    }

    /**
     * Runs the game loop.
     */
    public void play() {
        printWelcome();


        boolean finished = false;
        while (!finished) {
            System.out.print("> ");

            Command command = parser.getCommand(reader.nextLine());
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

        switch (commandWord) {
            case GO -> {
                goRoom(command);
            }
            case QUIT -> {
                wantToQuit = quit(command);
            }
            case HELP -> {
                printHelp();
            }
            case PICKUP -> {
                processPickup(command);
            }
            case DROP -> {
                processDrop(command);
            }
            case INVENTORY -> {
                player.getInventory().printInv();
            }
            case USE -> {
                processUse(command);
            }
            case SEARCH -> {
                printWaste();
            }
            case SAVE -> {
                scoreSystem.uploadData();
                System.out.println("Data saved on database.");
            }
            case UNKNOWN -> {
                System.out.println("I don't know what you mean...");
            }
        }
        
        return wantToQuit;
    }

    private void printWaste() {
        List<Waste> waste = currentRoom.getWaste();

        if (waste.size() <= 0) {
            System.out.println("You look around and realize, there's no waste in this room.");
            return;
        }

        System.out.print("You look around and see the following waste in this room: ");

        for (int i = 0; i < waste.size(); i++) {
            System.out.print((i == 0 ? "" : ", ") + waste.get(i).getName());
        }
        System.out.println();
    }

    /**
     * Processes a use command.
     * @param command A drop command.
     */
    private void processUse(Command command) {

        String args[] = command.getArgs();

        if (args == null || args.length <= 0) {
            System.out.println("Use what?");
            return;
        } else if (args.length >= 1) {
            Item usableItem = currentRoom.getItemByName(args[0]);

            if (usableItem == null) {
                System.out.println("There's no such item in this room.");
                return;
            }

            if (!(usableItem instanceof Usable)) {
                System.out.println("'" + usableItem.getName() + "' is not useable.");
                return;
            }

            Usable usable = (Usable) usableItem;

            if (args.length == 1) {
                usable.use();

                return;
            } if (args.length >= 2 && args[1].equalsIgnoreCase("on")) {

                if (args.length == 2) {
                    System.out.println("Use item '" + usableItem.getName() + "' on what?");
                    return;
                } else if (args.length == 3) {
                    Item item = player.getInventory().getItemByName(args[2]);

                    if (item == null) {
                        System.out.println("No such item '" + args[2] + "' in inventory.");
                        return;
                    }

                    usable.useOn(item);

                    return;
                }
            }
        }
        System.out.println("Not sure, what you mean...");
    }

    /**
     * Processes a drop command.
     * @param command A drop command.
     */
    private void processDrop(Command command) {

        String args[] = command.getArgs();

        if (args == null || args.length <= 0) {
            System.out.println("Drop what?");
            return;
        } else if (args.length == 1) {
            Item item = player.getInventory().getItemByName(args[0]);
            if (item == null) {
                System.out.println("No such item in inventory.");
            } else {
                player.getInventory().removeItem(item);
                currentRoom.addItem(item);

                System.out.println("Dropped " + item.getName() + " on the ground.");
            }
            return;
        } else if (args.length == 3 && args[1].equalsIgnoreCase("in")) {
            Item item = player.getInventory().getItemByName(args[0]);

            if (item == null) {
                System.out.println("No such item '" + args[0] + "' in inventory.");
            } else {
                Item container = currentRoom.getItemByName(args[2]);

                if(container == null) {
                    System.out.println("There's no such waste container in this room.");
                    return;
                }

                if (!(container instanceof WasteContainer)) {
                    System.out.println("That is not a waste container.");
                    return;
                }

                if (!(item instanceof Waste)) {
                    System.out.println("You can only drop waste in a waste container");
                    return;
                }

                WasteContainer wasteContainer = (WasteContainer) container;
                Waste waste = (Waste) item;

                if (wasteContainer.giveWaste(waste)) {
                    player.getInventory().removeItem(waste);
                }
            }

            return;
        }

        System.out.println("Not sure, what you mean...");

    }

    /**
     * Processes a pickup command.
     * @param command A pickup command.
     */
    private void processPickup(Command command) {

        Item item;

        if ((item = currentRoom.getItemByName(command.getSecondWord())) == null) {
            System.out.println("There is no such item.");
            return;
        }

        if (!(item instanceof Pickupable)) {
            System.out.println("You can't pickup this item.");
            return;
        }

        currentRoom.removeItem(item);
        if (player.getInventory().storeItem(item)) {
            System.out.println("You picked up " + item.getName());
        } else {
            System.out.println("You do not have sufficient space in your inventory.");
        }

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
            if (nextRoom.isLocked()) {
                System.out.println("This room is locked.");
                return;
            }
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

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }
}

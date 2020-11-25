package dk.sdu.worldoftrash.game.domain;

import dk.sdu.worldoftrash.game.dal.data.WasteType;
import dk.sdu.worldoftrash.game.presentation.gui.ImageIO;
import dk.sdu.worldoftrash.game.domain.items.*;
import dk.sdu.worldoftrash.game.domain.items.npcs.*;
import dk.sdu.worldoftrash.game.domain.rooms.Room;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Room currentRoom;

    private ScoreSystem scoreSystem;

    private Player player;

    private IGameTextPrinter textLogArea;

    private double height;
    private double width;

    private Room start, sortingRoom, odense,
    /* level 1 */supermarket, office, storageRoom, parkinglot,
    /* level 2 */hospitalOutside, reception, operatingRoom, morgue, canteen,
    /* level 3 */schoolOutside, teachersLounge, chemistryRoom, classRooms, girlsLockerRoom;

    public Game(double width, double height) {
        this.player = new Player(this, "Player");
        this.scoreSystem = new ScoreSystem(this);

        this.height = width;
        this.width = height;

        initObjects();
    }


    /**
     * Initializes game world objects.
     */
    private void initObjects() {
        //Rooms
        start = new Room(this, "start", "in the start room. \n The beginning of this trashy world's hero... You!!! \nA man greets you and says \"Welcome to the World of Trash. My name is Trash Master Martin, but you can just call me Martin. \nYou must help us save the planet! Now follow me if you want to survive, Start by meeting me in the sorting room by using X on the arrow in the door and talk using X in the next room.\"");
        start.setBackground(ImageIO.load("/images/maps/main/start.png"));

        /*
        Waste waste = new Waste(this, "test", WasteType.GLASS, "Test", true);
        waste.setImage(ImageIO.load("/images/trash/apple.png"));
        waste.setScale(0.5f);
        waste.fitToImage();
        waste.setPosition(  waste.getWidth() * 3, waste.getHeight() * 3);
        start.addItem(waste);
        */


        // Baggrunde
        sortingRoom = new Room(this, "sortingRoom", "in sorting room. Martin follows you. This is where you sort the trash and clean it in the sink if needed be. \nThere are 8 different containers, a organic-container, a glass-container, a metal-container, a paper-container, a residual-container, a cardboard-container, a hazardous-container and a plastic-container");
        sortingRoom.setBackground(ImageIO.load("/images/maps/main/sorting_room.png"));

        odense = new Room(this, "city", "in the city of Odense. Martin follows you. The city is in shambles and filled with trash. In the distance you see mountains of trash towering over the city.\nIn the east is a supermarket, in the west is the sorting room, in the south is a hospital and in the north is a school");
        odense.setBackground(ImageIO.load("/images/maps/main/city.png"));

        supermarket = new Room(this, "supermarket", "in the supermarket");
        supermarket.setBackground(ImageIO.load("/images/maps/supermarket/supermarket.png"));

        office = new Room(this, "office", "in the supermarket office");
        office.setBackground(ImageIO.load("/images/maps/supermarket/office.png"));

        storageRoom = new Room(this, "storageRoom", "in the storage room");
        storageRoom.setBackground(ImageIO.load("/images/maps/supermarket/storage_room.png"));

        parkinglot = new Room(this, "parking-lot", "at the parking lot. There is an homeless man staring intensely at you. A nametag on his coat says Dan");
        parkinglot.setBackground(ImageIO.load("/images/maps/supermarket/parking_lot.png"));

        hospitalOutside = new Room(this, "hospital-outside", "outside the hospital. You see a man resting in front of the hospital entrance. He looks to be over his expiration date and missing an arm. He greets you and tells you to call him Mr.Zombie");
        hospitalOutside.setBackground(ImageIO.load("/images/maps/hospital/outside_hospital.png"));

        reception = new Room(this, "reception", "in the hospital reception");
        reception.setBackground(ImageIO.load("/images/maps/hospital/hospital_reception.png"));

        operatingRoom = new Room(this, "operating-room", "in the operations room");
        operatingRoom.setBackground(ImageIO.load("/images/maps/hospital/operations_room.png"));

        morgue = new Room(this, "morgue", "in the morgue");
        morgue.setBackground(ImageIO.load("/images/maps/hospital/morgue.png"));

        canteen = new Room(this, "canteen", "in the canteen");
        canteen.setBackground(ImageIO.load("/images/maps/hospital/canteen.png"));

        schoolOutside = new Room(this, "school-outside", "outside the school");
        schoolOutside.setBackground(ImageIO.load("/images/maps/school/outside_school.png"));

        teachersLounge = new Room(this, "teachers-lounge", "in the teachers lounge. A guy in an dirty lab coat is resting in a sofa. He looks like a Mad-Chemist");
        teachersLounge.setBackground(ImageIO.load("/images/maps/school/teachers_lounge.png"));

        chemistryRoom = new Room(this, "chemistry-room", "in the chemistry room");
        chemistryRoom.setBackground(ImageIO.load("/images/maps/school/chemistry_room.png"));

        classRooms = new Room(this, "class-rooms", "in the class rooms");
        classRooms.setBackground(ImageIO.load("/images/maps/school/school_gym.png"));

        girlsLockerRoom = new Room(this, "girls-locker-room", "in the girls locker room");
        girlsLockerRoom.setBackground(ImageIO.load("/images/maps/school/girls_locker_room.png"));

        //**************************************** Waste objects - Start ***********************************************

        // Waste objects #Level 1 - Supermarket
        Waste tomatoCan = new Waste(this, "tomato-can", WasteType.METAL, "cans are made of metal, and therefore goes in metals", false);
        Waste sodaCan = new Waste(this, "soda-can", WasteType.METAL, "cans are made of metal, and therefore goes in metals", false);
        Waste meatTray = new Waste(this, "meat-tray", WasteType.HARD_PLASTIC, "meat-trays are made of hard plastic, and therefore goes in hard plastic", false);
        Waste ketchupPlasticBottle = new Waste(this, "ketchup-plastic-bottle", WasteType.HARD_PLASTIC, "it is made of hard plastic, and therefore goes in hard plastic", false);
        Waste picklesGlass = new Waste(this, "pickle-glass", WasteType.GLASS, "this is a glass, and therefore goes in glass", false);
        Waste beerBottle = new Waste(this, "beer-bottle", WasteType.GLASS, "beer bottles typically are made of glass, and therefore goes in glass", false);
        Waste eatenApple = new Waste(this, "eaten-apple", WasteType.ORGANIC, "apples are organic, and therefore goes in organic", true);
        Waste pizzaSlice = new Waste(this, "pizza-slice", WasteType.ORGANIC, "pizza slice is organic, and therefore goes in organic", true);
        Waste paint1 = new Waste(this, "paint", WasteType.HAZARDOUS, "paint is an hazardous material and should be handled accordingly and placed in hazardous", true);
        Waste aaBatteries = new Waste(this, "AA-batteries", WasteType.HAZARDOUS, "batteries contain hazardous chemicals, and therefore goes in hazardous", true);
        Waste deodorant = new Waste(this, "deodorant", WasteType.HAZARDOUS, "deodorants are typically cans containing chemicals, and therefore goes in hazardous", true);
        Waste newspaper = new Waste(this, "newspaper", WasteType.PAPER, "newspapers are made of paper, and therefore goes in paper", true);
        Waste cardboardBox = new Waste(this, "cardboard-box", WasteType.CARDBOARD, "cardboard boxes are made of cardboard, and therefore goes in cardboard", true);
        Waste pizzaBox = new Waste(this, "pizza-box", WasteType.RESIDUAL, "a pizza box has been dirtied by a pizza, and therefore goes in residual", true);
        Waste receipt = new Waste(this, "receipt", WasteType.RESIDUAL, "receipts is made of a special kind of paper containing chemicals and should not be sorted in paper, therefore it goes in residual", true);
        Key bigbox = new Key(this, "biggest-box");

        // Waste objects #Level 2 - Hospital
        Waste papers1 = new Waste(this, "papers", WasteType.PAPER, "this is a paper, it goes in paper",true);
        papers1.setImage(ImageIO.load("/images/trash/paper.png"));
        papers1.setScale(0.8f);
        papers1.fitToImage();
        papers1.setPosition(535,50);

        Waste medicineBottle = new Waste (this, "glass medicine bottle", WasteType.GLASS, "it is made out of glass, and therefore goes in glass", false);
        medicineBottle.setImage(ImageIO.load("/images/trash/medicine_bottle.png"));
        medicineBottle.setScale(1);
        medicineBottle.fitToImage();
        medicineBottle.setPosition(502,55);

        Waste syringe = new Waste (this, "syringe", WasteType.HAZARDOUS, "most biochemical equipment are hazardous, so are syringes, never reuse a syringe!", true);
        syringe.setImage(ImageIO.load("/images/trash/syringe.png"));
        syringe.setScale(1);
        syringe.fitToImage();
        syringe.setPosition(469,60);

        Waste scalpel = new Waste (this, "scalpel", WasteType.METAL, "a scalpel is made of metal. This goes into metal", true);
        scalpel.setImage(ImageIO.load("/images/trash/scalpel.png"));
        scalpel.setScale(1);
        scalpel.fitToImage();
        scalpel.setPosition(436,65);

        Waste paperclip = new Waste (this, "paperclips", WasteType.RESIDUAL, "unless you got a large quantity of paper clips, recycling paper clips have a larger carbon footprint rather than just sorting them to residual, and therefore goes in residual", true);
        paperclip.setImage(ImageIO.load("/images/trash/paper_clips.png"));
        paperclip.setScale(1);
        paperclip.fitToImage();
        paperclip.setPosition(403,70);

        Waste facemask = new Waste (this, "face mask", WasteType.RESIDUAL, "this is a face mask. It goes into residual", true);
        facemask.setImage(ImageIO.load("/images/trash/maske.png"));
        facemask.setScale(1);
        facemask.fitToImage();
        facemask.setPosition(370,75);

        Waste bloodbag = new Waste (this, "blood bag", WasteType.HAZARDOUS, "blood bags are biohazardous material and goes in hazardous", true);
        bloodbag.setImage(ImageIO.load("/images/trash/bloodbag.png"));
        bloodbag.setScale(1);
        bloodbag.fitToImage();
        bloodbag.setPosition(340,80);

        Waste apple = new Waste (this, "apple",WasteType.ORGANIC, "apples usually are organics and can be dropped in organic", true);
        apple.setImage(ImageIO.load("/images/trash/apple.png"));
        apple.setScale(1);
        apple.fitToImage();
        apple.setPosition(310,85);

        Waste needle = new Waste (this, "needle", WasteType.RESIDUAL, "Needles in themselves are not worth recycling themselves and usually can be drop in residual ", true);
        needle.setImage(ImageIO.load("/images/trash/needle-kopi.png"));
        needle.setScale(1);
        needle.fitToImage();
        needle.setPosition(280, 90);

        Waste chocolateMilkBottle = new Waste (this, "chocolate milk bottle", WasteType.GLASS, "this is a glass bottle, and therefore goes into glass", false);
        chocolateMilkBottle.setImage(ImageIO.load("/images/trash/chockolade_milk.png"));
        chocolateMilkBottle.setScale(1);
        chocolateMilkBottle.fitToImage();
        chocolateMilkBottle.setPosition(250,95);

        Waste can = new Waste (this, "can", WasteType.METAL, "cans are made out of metal. It goes into metal", false);
        can.setImage(ImageIO.load("/images/trash/can-kopi.png"));
        can.setScale(1);
        can.fitToImage();
        can.setPosition(220,100);

        Waste cake = new Waste (this, "moldy cake", WasteType.ORGANIC, "this is food and therefore organic. It goes into organic", true);
        cake.setImage(ImageIO.load("/images/trash/muffin_cake-kopi.png"));
        cake.setScale(1);
        cake.fitToImage();
        cake.setPosition(190,105);

        Waste tray = new Waste(this,"tin tray", WasteType.METAL, "tin trays are made out of metal. It goes into metal", false);
        tray.setImage(ImageIO.load("/images/trash/meat_tray.png"));
        tray.setScale(1);
        tray.fitToImage();
        tray.setPosition(160,110);

        Waste ballPen = new Waste(this, "ball-pen", WasteType.RESIDUAL, "a ball pen is compromised of multiple components and hard to recycle. It goes into residual", true);
        ballPen.setImage(ImageIO.load("/images/trash/ball_pen.png"));
        ballPen.setScale(1);
        ballPen.fitToImage();
        ballPen.setPosition(130,115);

        Waste toothBrush = new Waste(this, "tooth-brush", WasteType.RESIDUAL, "a tooth brush is not recycled due to repeated contact with a persons mouth and therefore unhygienic, it goes into residual", true);
        toothBrush.setImage(ImageIO.load("/images/trash/toothbrush.png"));
        toothBrush.setScale(1);
        toothBrush.fitToImage();
        toothBrush.setPosition(100,120);
        Key arms = new Key(this, "arm");
        Key sewing_kit = new Key(this, "sewing-kit");
        sewing_kit.setImage(ImageIO.load("/images/trash/sewing_kit.png"));
        sewing_kit.setScale(1);
        sewing_kit.fitToImage();
        sewing_kit.setPosition(100,100);

        // Waste objects #Level 3 - School
        Waste papers = new Waste(this, "papers", WasteType.PAPER, "this is a paper, it goes in paper",true);
        Waste lighter = new Waste(this, "lighter", WasteType.HAZARDOUS, "lighters contains hazardous materials and therefore goes in hazardous", true);
        Waste bananas = new Waste(this, "bananas", WasteType.ORGANIC, "this is an organic material and can be used as an compost, and therefore goes in organic", true);
        Waste jam_jar = new Waste(this, "jam-jar", WasteType.GLASS, "a jam jar is made of glass, and can therefore if clean be recycled for reuse if put into glass", false);
        Waste nutella_glass = new Waste(this, "nutella-glass", WasteType.GLASS, "a nutella glass is made of glass, and can therefore if clean be recycled for reuse if put into glass", false);
        Waste coffee_grounds = new Waste(this, "coffee-grounds", WasteType.ORGANIC, "coffee grounds are an organic substance and be used for compost. It goes into organic", true);
        Waste poster = new Waste(this, "poster", WasteType.CARDBOARD, "a poster is of cardboard material and goes in cardboard", true);
        Waste spectacles_frame = new Waste(this, "spectacles-frame", WasteType.METAL, "spectacles frame is made of steel and therefore goes in metal", true);
        Waste post_it = new Waste(this, "post-it", WasteType.RESIDUAL, "due to the adhesive substance in the back of post it notes, they are not to be recycled with normal paper, and goes in residual", true);
        Waste milk_carton = new Waste(this, "milk-carton", WasteType.RESIDUAL, "due to a milk carton being soaked in a organic substance it goes in residual", true);
        Waste perfume_bottle = new Waste(this, "perfume-bottle", WasteType.GLASS, "if you remove the perfume substances in the bottle, then the bottle can be recycled for reuse and therefore goes in glass", false);
        Waste water_bottle = new Waste(this, "plastic-water-bottle", WasteType.HARD_PLASTIC, "it is made of hard plastic and can be recycled for reuse and therefore goes in hard plastic", true);
        Waste safety_goggles = new Waste(this, "safety-goggles", WasteType.HARD_PLASTIC, "typically safety goggles are made of hard plastic and can be recycled for reuse, and therefore goes in hard plastic", true);
        Waste paper_clip = new Waste(this, "paper-clips", WasteType.RESIDUAL, "unless you got a large quantity of paper clips, recycling paper clips have a larger carbon footprint rather than just sorting them to residual, and therefore goes in residual", true);
        Waste paint = new Waste(this, "paint", WasteType.HAZARDOUS, "paint is an hazardous material and should be handled accordingly, and placed in hazardous", true);
        Key fertilizer = new Key(this,"fertilizer");

        //NPCs
        ParkingLotNPC homelessDan = new ParkingLotNPC(this, "Dan");
        ZombieNPC mrZombie = new ZombieNPC(this, "Mr.Zombie");
        SchoolNPC madChemist = new SchoolNPC(this, "Mad-Chemist");
        NPC martin = new CityNPC(this, "Martin");
        start.addItem(martin);

        //************************************* Trash assigning - Start *************************************
        //Assigning trash to - Supermarket
        tomatoCan.setImage(ImageIO.load(("/images/trash/tomato_can.png")));
        tomatoCan.setScale(0.8f);
        tomatoCan.fitToImage();
        tomatoCan.setPosition(500,500);

        meatTray.setImage(ImageIO.load(("/images/trash/food_tray.png")));
        meatTray.setScale(0.6f);
        meatTray.fitToImage();
        meatTray.setPosition(150,125);

        ketchupPlasticBottle.setImage(ImageIO.load(("/images/trash/ketchup.png")));
        ketchupPlasticBottle.setScale(0.5f);
        ketchupPlasticBottle.fitToImage();
        ketchupPlasticBottle.setPosition(760,325);

        receipt.setImage(ImageIO.load(("/images/trash/receipt.png")));
        receipt.setScale(0.8f);
        receipt.fitToImage();
        receipt.setPosition(700,700);

        //Assigning trash to - Supermarket/kontor
        beerBottle.setImage(ImageIO.load(("/images/trash/beer.png")));
        beerBottle.setScale(0.6f);
        beerBottle.fitToImage();
        beerBottle.setPosition(200,200);

        pizzaSlice.setImage(ImageIO.load(("/images/trash/slice.png")));
        pizzaSlice.setScale(0.8f);
        pizzaSlice.fitToImage();
        pizzaSlice.setPosition(600,100);

        pizzaBox.setImage(ImageIO.load(("/images/trash/pizza_box.png")));
        pizzaBox.setScale(0.8f);
        pizzaBox.fitToImage();
        pizzaBox.setPosition(600,150);

        bigbox.setImage(ImageIO.load(("/images/trash/biggest-box.png")));
        bigbox.setScale(0.8f);
        bigbox.fitToImage();
        bigbox.setPosition(800,750);

        //Assigning trash to - Supermarket/lageret
        eatenApple.setImage(ImageIO.load(("/images/trash/apple_eaten.png")));
        eatenApple.setScale(0.6f);
        eatenApple.fitToImage();
        eatenApple.setPosition(300,300);

        paint1.setImage(ImageIO.load(("/images/trash/paint_2.png")));
        paint1.setScale(1f);
        paint1.fitToImage();
        paint1.setPosition(150,675);

        aaBatteries.setImage(ImageIO.load(("/images/trash/batteri.png")));
        aaBatteries.setScale(0.6f);
        aaBatteries.fitToImage();
        aaBatteries.setPosition(500,750);

        cardboardBox.setImage(ImageIO.load(("/images/trash/cardboard_box.png")));
        cardboardBox.setScale(0.8f);
        cardboardBox.fitToImage();
        cardboardBox.setPosition(700,350);

        //Assigning trash to - Supermarket/parkeringsplads
        newspaper.setImage(ImageIO.load(("/images/trash/newspaper-kopi.png")));
        newspaper.setScale(0.7f);
        newspaper.fitToImage();
        newspaper.setPosition(300,200);

        sodaCan.setImage(ImageIO.load(("/images/trash/soda can.png")));
        sodaCan.setScale(0.4f);
        sodaCan.fitToImage();
        sodaCan.setPosition(635,275);

        deodorant.setImage(ImageIO.load(("/images/trash/deo.png")));
        deodorant.setScale(0.4f);
        deodorant.fitToImage();
        deodorant.setPosition(600,550);

        picklesGlass.setImage(ImageIO.load(("/images/trash/pickles.png")));
        picklesGlass.setScale(0.8f);
        picklesGlass.fitToImage();
        picklesGlass.setPosition(200,700);

        //Assigning trash to - School/outside

        //Assigning trash to - School/gymnastiksal

        //Assigning trash to - School/omklædningsrum

        //Assigning trash to - School/kemilokale

        //Assigning trash to - School/lærerværelse


        // ********************************************* Doors - Start *********************************************
        Door startSort = new Door(this, "Start", start);
        startSort.setImage(ImageIO.load("/images/ArrowRight.png"));
        startSort.setWidth(45);
        startSort.setHeight(64);
        startSort.autoScale();
        startSort.setPosition(870, 410);

        Door sortStart = new Door(this, "Sort", sortingRoom);
        sortStart.setImage(ImageIO.load("/images/ArrowLeft.png"));
        sortStart.setWidth(45);
        sortStart.setHeight(64);
        sortStart.autoScale();
        sortStart.setPosition(0, 404);

        Door sortOdense = new Door(this, "Sort", sortingRoom);
        sortOdense.setImage(ImageIO.load("/images/ArrowRight.png"));
        sortOdense.setWidth(45);
        sortOdense.setHeight(64);
        sortOdense.autoScale();
        sortOdense.setPosition(870, 404);

        Door odenseSort = new Door(this, "Odense", odense);
        odenseSort.setImage(ImageIO.load("/images/ArrowLeft.png"));
        odenseSort.setWidth(45);
        odenseSort.setHeight(64);
        odenseSort.autoScale();
        odenseSort.setPosition(0, 410);

        Door odenseSuper = new Door(this, "Odense", odense);
        odenseSuper.setImage(ImageIO.load("/images/ArrowRight.png"));
        odenseSuper.setWidth(45);
        odenseSuper.setHeight(64);
        odenseSuper.autoScale();
        odenseSuper.setPosition(870, 410);

        Door odenseHos = new Door(this, "Odense", odense);
        odenseHos.setImage(ImageIO.load("/images/ArrowDown.png"));
        odenseHos.setWidth(64);
        odenseHos.setHeight(45);
        odenseHos.autoScale();
        odenseHos.setPosition(435, 870);

        Door odenseSchool = new Door(this, "Odense", odense);
        odenseSchool.setImage(ImageIO.load("/images/ArrowUp.png"));
        odenseSchool.setWidth(64);
        odenseSchool.setHeight(45);
        odenseSchool.autoScale();
        odenseSchool.setPosition(435, 0);

        //Door Level 1.
        Door superOdense = new Door(this, "Supermarket", supermarket);
        superOdense.setImage(ImageIO.load("/images/ArrowLeft.png"));
        superOdense.setWidth(45);
        superOdense.setHeight(64);
        superOdense.autoScale();
        superOdense.setPosition(0, 478);

        Door superOffice = new Door(this, "Supermarket", supermarket);
        superOffice.setImage(ImageIO.load("/images/ArrowUp.png"));
        superOffice.setWidth(64);
        superOffice.setHeight(45);
        superOffice.autoScale();
        superOffice.setPosition(448, 0);

        Door superStor = new Door(this, "Supermarket", supermarket);
        superStor.setImage(ImageIO.load("/images/ArrowRight.png"));
        superStor.setWidth(45);
        superStor.setHeight(64);
        superStor.autoScale();
        superStor.setPosition(870, 480);

        Door superPark = new Door(this, "Supermarket", supermarket);
        superPark.setImage(ImageIO.load("/images/ArrowDown.png"));
        superPark.setWidth(64);
        superPark.setHeight(45);
        superPark.autoScale();
        superPark.setPosition(452, 870);

        Door officeSuper = new Door(this, "Office", office);
        officeSuper.setImage(ImageIO.load("/images/ArrowDown.png"));
        officeSuper.setWidth(64);
        officeSuper.setHeight(45);
        officeSuper.autoScale();
        officeSuper.setPosition(420, 870);

        Door storSuper = new Door(this, "Storage", storageRoom);
        storSuper.setImage(ImageIO.load("/images/ArrowLeft.png"));
        storSuper.setWidth(45);
        storSuper.setHeight(64);
        storSuper.autoScale();
        storSuper.setPosition(0, 351);

        Door parkSuper = new Door(this, "Parking lot", parkinglot);
        parkSuper.setImage(ImageIO.load("/images/ArrowUp.png"));
        parkSuper.setWidth(64);
        parkSuper.setHeight(45);
        parkSuper.autoScale();
        parkSuper.setPosition(466, 120);

        //Door Level 2.
        Door hosOdense = new Door(this, "Hospital", hospitalOutside);
        hosOdense.setImage(ImageIO.load("/images/ArrowUp.png"));
        hosOdense.setWidth(64);
        hosOdense.setHeight(45);
        hosOdense.autoScale();
        hosOdense.setPosition(404, 0);

        Door hosRep = new Door(this, "Hospital", hospitalOutside);
        hosRep.setImage(ImageIO.load("/images/ArrowUp.png"));
        hosRep.setWidth(64);
        hosRep.setHeight(45);
        hosRep.autoScale();
        hosRep.setPosition(788, 570);

        Door repHos = new Door(this, "Reception", reception);
        repHos.setImage(ImageIO.load("/images/ArrowDown.png"));
        repHos.setWidth(64);
        repHos.setHeight(45);
        repHos.autoScale();
        repHos.setPosition(643, 870);

        Door repMorg = new Door(this, "Reception", reception);
        repMorg.setImage(ImageIO.load("/images/ArrowLeft.png"));
        repMorg.setWidth(45);
        repMorg.setHeight(64);
        repMorg.autoScale();
        repMorg.setPosition(24, 338);

        Door repOp = new Door(this, "Reception", reception);
        repOp.setImage(ImageIO.load("/images/ArrowRight.png"));
        repOp.setWidth(45);
        repOp.setHeight(64);
        repOp.autoScale();
        repOp.setPosition(850, 404);

        Door repCan = new Door(this, "Reception", reception);
        repCan.setImage(ImageIO.load("/images/ArrowUp.png"));
        repCan.setWidth(64);
        repCan.setHeight(45);
        repCan.autoScale();
        repCan.setPosition(676, 0);

        Door morgueRep = new Door(this, "Morgue", morgue);
        morgueRep.setImage(ImageIO.load("/images/ArrowRight.png"));
        morgueRep.setWidth(45);
        morgueRep.setHeight(64);
        morgueRep.autoScale();
        morgueRep.setPosition(870, 386);

        Door opRep = new Door(this, "Operating room", operatingRoom);
        opRep.setImage(ImageIO.load("/images/ArrowLeft.png"));
        opRep.setWidth(45);
        opRep.setHeight(64);
        opRep.autoScale();
        opRep.setPosition(0, 466);

        Door canRep = new Door(this, "Canteen", canteen);
        canRep.setImage(ImageIO.load("/images/ArrowDown.png"));
        canRep.setWidth(64);
        canRep.setHeight(45);
        canRep.autoScale();
        canRep.setPosition(420, 870);

        //Door Level 3.
        Door schoolOdense = new Door(this, "School", schoolOutside);
        schoolOdense.setImage(ImageIO.load("/images/ArrowDown.png"));
        schoolOdense.setWidth(64);
        schoolOdense.setHeight(45);
        schoolOdense.autoScale();
        schoolOdense.setPosition(435, 870);

        Door schoolChem = new Door(this, "School", schoolOutside);
        schoolChem.setImage(ImageIO.load("/images/ArrowLeft.png"));
        schoolChem.setWidth(45);
        schoolChem.setHeight(64);
        schoolChem.autoScale();
        schoolChem.setPosition(26, 243);

        Door schoolTeach = new Door(this, "School", schoolOutside);
        schoolTeach.setImage(ImageIO.load("/images/ArrowRight.png"));
        schoolTeach.setWidth(45);
        schoolTeach.setHeight(64);
        schoolTeach.autoScale();
        schoolTeach.setPosition(840, 243);

        Door schoolClass = new Door(this, "School", schoolOutside);
        schoolClass.setImage(ImageIO.load("/images/ArrowUp.png"));
        schoolClass.setWidth(64);
        schoolClass.setHeight(45);
        schoolClass.autoScale();
        schoolClass.setPosition(435, 0);

        Door chemSchool = new Door(this, "Chemistry room", chemistryRoom);
        chemSchool.setImage(ImageIO.load("/images/ArrowRight.png"));
        chemSchool.setWidth(45);
        chemSchool.setHeight(64);
        chemSchool.autoScale();
        chemSchool.setPosition(870, 448);

        Door teachSchool = new Door(this, "Teachers Lounge", teachersLounge);
        teachSchool.setImage(ImageIO.load("/images/ArrowLeft.png"));
        teachSchool.setWidth(45);
        teachSchool.setHeight(64);
        teachSchool.autoScale();
        teachSchool.setPosition(0, 339);

        Door classSchool = new Door(this, "Class rooms", classRooms);
        classSchool.setImage(ImageIO.load("/images/ArrowDown.png"));
        classSchool.setWidth(64);
        classSchool.setHeight(45);
        classSchool.autoScale();
        classSchool.setPosition(402, 870);

        Door classGirl = new Door(this, "Class rooms", classRooms);
        classGirl.setImage(ImageIO.load("/images/ArrowUp.png"));
        classGirl.setWidth(64);
        classGirl.setHeight(45);
        classGirl.autoScale();
        classGirl.setPosition(357, 52);

        Door girlClass = new Door(this, "Girls locker room", girlsLockerRoom);
        girlClass.setImage(ImageIO.load("/images/ArrowDown.png"));
        girlClass.setWidth(64);
        girlClass.setHeight(45);
        girlClass.autoScale();
        girlClass.setPosition(372, 870);

        //Connect Doors
        startSort.connect(sortStart);
        sortOdense.connect(odenseSort);
        odenseSuper.connect(superOdense);
        odenseHos.connect(hosOdense);
        odenseSchool.connect(schoolOdense);

        superOffice.connect(officeSuper);
        superStor.connect(storSuper);
        superPark.connect(parkSuper);

        hosRep.connect(repHos);
        repCan.connect(canRep);
        repMorg.connect(morgueRep);
        repOp.connect(opRep);

        schoolChem.connect(chemSchool);
        schoolTeach.connect(teachSchool);
        schoolClass.connect(classSchool);
        classGirl.connect(girlClass);

        //Adding Doors
        start.addItem(startSort);
        sortingRoom.addItem(sortStart);
        sortingRoom.addItem(sortOdense);
        odense.addItem(odenseSort);
        odense.addItem(odenseSuper);
        odense.addItem(odenseHos);
        odense.addItem(odenseSchool);

        supermarket.addItem(superOdense);
        supermarket.addItem(superOffice);
        supermarket.addItem(superStor);
        supermarket.addItem(superPark);
        office.addItem(officeSuper);
        storageRoom.addItem(storSuper);
        parkinglot.addItem(parkSuper);

        hospitalOutside.addItem(hosOdense);
        hospitalOutside.addItem(hosRep);
        reception.addItem(repCan);
        reception.addItem(repHos);
        reception.addItem(repMorg);
        reception.addItem(repOp);
        canteen.addItem(canRep);
        morgue.addItem(morgueRep);
        operatingRoom.addItem(opRep);

        schoolOutside.addItem(schoolOdense);
        schoolOutside.addItem(schoolChem);
        schoolOutside.addItem(schoolClass);
        schoolOutside.addItem(schoolTeach);
        chemistryRoom.addItem(chemSchool);
        teachersLounge.addItem(teachSchool);
        classRooms.addItem(classSchool);
        classRooms.addItem(classGirl);
        girlsLockerRoom.addItem(girlClass);


        sortingRoom.addItem(martin);
        odense.addItem(martin);
        parkinglot.addItem(homelessDan);
        homelessDan.setBigbox(bigbox);
        mrZombie.setArm(arms);
        mrZombie.setSewing_kit(sewing_kit);
        hospitalOutside.addItem(mrZombie);
        teachersLounge.addItem(madChemist);
        madChemist.setFertilizer(fertilizer);

        // ********************************************* Doors - end *********************************************
        //Creating trash container objects
        WasteContainer organicContainer = new WasteContainer(this, "organic-container", WasteType.ORGANIC);
        organicContainer.setImage(ImageIO.load("/images/wasteContainers/ORGANICS.png"));
        organicContainer.setScale(0.8f);
        organicContainer.fitToImage();
        organicContainer.setPosition(50,50);

        WasteContainer glassContainer = new WasteContainer(this, "glass-container", WasteType.GLASS);
        glassContainer.setImage(ImageIO.load("/images/wasteContainers/glass.png"));
        glassContainer.setScale(0.8f);
        glassContainer.fitToImage();
        glassContainer.setPosition(285,50);


        WasteContainer metalContainer = new WasteContainer(this, "metal-container", WasteType.METAL);
        metalContainer.setImage(ImageIO.load("/images/wasteContainers/Metal.png"));
        metalContainer.setScale(0.8f);
        metalContainer.fitToImage();
        metalContainer.setPosition(535,50);

        WasteContainer papercontainer = new WasteContainer(this, "paper-container", WasteType.PAPER);
        papercontainer.setImage(ImageIO.load("/images/wasteContainers/paper.png"));
        papercontainer.setScale(0.8f);
        papercontainer.fitToImage();
        papercontainer.setPosition(750,50);

        WasteContainer residualcontainer = new WasteContainer(this, "residual-container", WasteType.RESIDUAL);
        residualcontainer.setImage(ImageIO.load(("/images/wasteContainers/RESIDUAL.png")));
        residualcontainer.setScale(0.8f);
        residualcontainer.fitToImage();
        residualcontainer.setPosition(50,725);

        WasteContainer cardboardcontainer = new WasteContainer(this, "cardboard-container", WasteType.CARDBOARD);
        cardboardcontainer.setImage(ImageIO.load("/images/wasteContainers/CARDBOARD.png"));
        cardboardcontainer.setScale(0.8f);
        cardboardcontainer.fitToImage();
        cardboardcontainer.setPosition(285,725);

        WasteContainer hardPlasticcontainer = new WasteContainer(this, "plastic-container", WasteType.HARD_PLASTIC);
        hardPlasticcontainer.setImage(ImageIO.load("/images/wasteContainers/PLASTIC.png"));
        hardPlasticcontainer.setScale(0.8f);
        hardPlasticcontainer.fitToImage();
        hardPlasticcontainer.setPosition(535,725);

        WasteContainer hazardouscontainer = new WasteContainer(this, "hazardous-container", WasteType.HAZARDOUS);
        hazardouscontainer.setImage(ImageIO.load("/images/wasteContainers/hazardous.png"));
        hazardouscontainer.setScale(0.8f);
        hazardouscontainer.fitToImage();
        hazardouscontainer.setPosition(750,725);

        //Assigning containers to the Sorting room
        sortingRoom.addItem(organicContainer);
        sortingRoom.addItem(glassContainer);
        sortingRoom.addItem(metalContainer);
        sortingRoom.addItem(papercontainer);
        sortingRoom.addItem(residualcontainer);
        sortingRoom.addItem(cardboardcontainer);
        sortingRoom.addItem(hazardouscontainer);
        sortingRoom.addItem(hardPlasticcontainer);

        //Assigning trash for level #1 - Supermarket
        supermarket.addItem(tomatoCan);
        supermarket.addItem(ketchupPlasticBottle);
        supermarket.addItem(meatTray);
        supermarket.addItem(receipt);

        office.addItem(beerBottle);
        office.addItem(pizzaSlice);
        office.addItem(pizzaBox);
        office.addItem(bigbox);

        storageRoom.addItem(eatenApple);
        storageRoom.addItem(paint1);
        storageRoom.addItem(aaBatteries);
        storageRoom.addItem(cardboardBox);

        parkinglot.addItem(newspaper);
        parkinglot.addItem(sodaCan);
        parkinglot.addItem(deodorant);
        parkinglot.addItem(picklesGlass);

        //Assigning trash for level #2 - Hospital
        hospitalOutside.addItem(syringe);
        hospitalOutside.addItem(needle);
        hospitalOutside.addItem(apple);

        reception.addItem(papers1);
        reception.addItem(paperclip);
        reception.addItem(facemask);

        operatingRoom.addItem(bloodbag);
        operatingRoom.addItem(medicineBottle);
        operatingRoom.addItem(toothBrush);
        operatingRoom.addItem(sewing_kit);

        morgue.addItem(scalpel);
        morgue.addItem(tray);
        morgue.addItem(ballPen);
        morgue.addItem(arms);

        canteen.addItem(chocolateMilkBottle);
        canteen.addItem(can);
        canteen.addItem(cake);

        //Assigning trash for level #3 - School
        schoolOutside.addItem(papers);
        schoolOutside.addItem(lighter);
        schoolOutside.addItem(bananas);
        schoolOutside.addItem(fertilizer);

        teachersLounge.addItem(jam_jar);
        teachersLounge.addItem(nutella_glass);
        teachersLounge.addItem(coffee_grounds);

        classRooms.addItem(poster);
        classRooms.addItem(spectacles_frame);
        classRooms.addItem(post_it);

        girlsLockerRoom.addItem(milk_carton);
        girlsLockerRoom.addItem(perfume_bottle);
        girlsLockerRoom.addItem(water_bottle);

        chemistryRoom.addItem(safety_goggles);
        chemistryRoom.addItem(paper_clip);
        chemistryRoom.addItem(paint);


        //**************************************** Waste objects - end ************************************************

        //Sink
        Sink sink1 = new Sink(this, "sink");
        Sink sink2 = new Sink(this, "sink");
        sortingRoom.addItem(sink1);
        sortingRoom.addItem(sink2);
        sink1.setImage(ImageIO.load("/images/SINK90.png"));
        sink2.setImage(ImageIO.load("/images/SINK-90.png"));
        sink2.setScale(0.5f);
        sink1.setScale(0.5f);
        sink1.fitToImage();
        sink2.fitToImage();
        sink1.setPosition(45,300);
        sink2.setPosition(775,300);



        //Exits in "Start" #0
        start.setExit("sorting-room", sortingRoom);

        //Exits in "Sorting Room" #0
        sortingRoom.setExit("start", start);
        sortingRoom.setExit("Odense", odense);

        //Exits in "Odense" #0
        odense.setExit("supermarket", supermarket);
        odense.setExit("sorting-room", sortingRoom);
        odense.setExit("hospital", hospitalOutside);
        odense.setExit("school", schoolOutside);

        //Exits in Supermarket #1
        supermarket.setExit("Odense", odense);
        supermarket.setExit("office", office);
        supermarket.setExit("storage-room", storageRoom);
        supermarket.setExit("parking-lot", parkinglot);

        office.setExit("supermarket", supermarket);
        storageRoom.setExit("supermarket", supermarket);
        parkinglot.setExit("supermarket", supermarket);

        //Exits in "Hospital" #2
        hospitalOutside.setExit("Odense", odense);
        hospitalOutside.setExit("reception", reception);

        reception.setExit("hospital-outside", hospitalOutside);
        reception.setExit("operations-room", operatingRoom);
        reception.setExit("morgue", morgue);
        reception.setExit("canteen", canteen);

        operatingRoom.setExit("reception", reception);
        morgue.setExit("reception", reception);
        canteen.setExit("reception", reception);

        //Exits in "School" #3
        schoolOutside.setExit("Odense", odense);
        schoolOutside.setExit("teachers-lounge", teachersLounge);
        schoolOutside.setExit("chemistry-room", chemistryRoom);
        schoolOutside.setExit("gymnastics-room", classRooms);

        teachersLounge.setExit("school", schoolOutside);
        chemistryRoom.setExit("school", schoolOutside);
        classRooms.setExit("school", schoolOutside);
        classRooms.setExit("girls-locker-room", girlsLockerRoom);
        girlsLockerRoom.setExit("gymnastics-room", classRooms);

        // Misc.
        currentRoom = start;

        //Scores
        scoreSystem.getLevelHandler().addLevel(supermarket, 0);
        scoreSystem.getLevelHandler().addLevel(hospitalOutside, 0);
        scoreSystem.getLevelHandler().addLevel(schoolOutside, 6);
    }

    // Getters for NPC locations
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getOdense() {
        return odense;
    }

    public Room getSortingRoom() {
        return sortingRoom;
    }

    public Room getStart() {
        return start;
    }

    /*** Prints out a welcome message.***/
    public void printWelcome() {
        textLogArea.printText("Welcome to the World of Trash!!!");
        textLogArea.printText("The world has been through an apocalypse caused by massive amounts of trash and is in need of a hero. \nThis is an incredibly exciting adventure game.");
        textLogArea.printText(currentRoom.getLongDescription());
    }

    //********************************************* Commands - Start ***************************************************

    /**
     * Prints out a help message.
     */
    private void printHelp() {
        getTextLogArea().printText("You are lost. You are alone. You wander");
        getTextLogArea().printText("around the city of Odense.");
        getTextLogArea().printText("");
        getTextLogArea().printText("Your command words are:");
    }

    /**
     * Changes current room to the given room.
     * @param room Room to go to.
     * @return Whether or not the room is locked
     */
    public boolean changeRoom(Room room) {
        if (room.isLocked()) {
            getTextLogArea().printText("This room is locked.");
            return false;
        }
        currentRoom = room;
        textLogArea.printText(currentRoom.getLongDescription());
        return true;
    }

    //********************************************* Commands - End ***************************************************

    /**
     * Called before every frame render.
     * @param delta Time since last frame.
     */
    public void update(float delta) {
        getCurrentRoom().update(delta);
        player.update(delta);
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Returns a list of items in the current room whose boundary box collides with the players'.
     * @return List of colliding items.
     */
    public List<Item> getCollisionsWithPlayer() {
        List<Item> colliding = new ArrayList<>();
        for (Item item : currentRoom.getItems()) {
            if (player.getBoundaryBox().intersects(item.getBoundaryBox())) {
                colliding.add(item);
            }
        }
        return colliding;
    }

    /**
     * Returns a list of objects of given type in the current room whose boundary box collides with the players'.
     * @return List of colliding items of given type.
     */
    public <T> List<T> getCollisionsWithPlayer(Class<T> type) {
        List<T> colliding = new ArrayList<>();
        for (Item item : currentRoom.getItems()) {
            if (type.isInstance(item) && player.getBoundaryBox().intersects(item.getBoundaryBox())) {
                colliding.add(type.cast(item));
            }
        }
        return colliding;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ScoreSystem getScoreSystem () {
        return scoreSystem;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public IGameTextPrinter getTextLogArea() {return textLogArea;}

    public void setTextLogArea(TextLogArea textLogArea) {this.textLogArea = textLogArea;}

}



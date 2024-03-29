package dk.sdu.worldoftrash.game.domain;

import dk.sdu.worldoftrash.game.domain.items.*;
import dk.sdu.worldoftrash.game.domain.items.npcs.*;
import dk.sdu.worldoftrash.game.domain.scoresystem.ScoreSystem;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Room currentRoom;
    private ScoreSystem scoreSystem;
    private Player player;
    private IGameTextPrinter textPrinter;
    private double height;
    private double width;

    public Game(double width, double height) {
        this.player = new Player(this, "Player");
        this.scoreSystem = new ScoreSystem(this, 45);
        player.setPosition(528, 390);

        this.height = width;
        this.width = height;

        initObjects();
    }
    
    /**
     * Initializes game world objects.
     */
    private void initObjects() {
        Room start, sortingRoom, city,
                /* level 1 */supermarket, office, storageRoom, parkinglot,
                /* level 2 */hospitalOutside, reception, operatingRoom, morgue, canteen,
                /* level 3 */schoolOutside, teachersLounge, chemistryRoom, classRooms, girlsLockerRoom;

        //Rooms
        start = new Room(this, "start");
        start.loadWalls("/collisionMaps/main/start.json");
        start.setBackground(Img.load("/images/maps/main/start.png"));

        // Baggrunde
        sortingRoom = new Room(this, "sortingRoom");
        sortingRoom.loadWalls("/collisionMaps/main/sorting_room.json");
        sortingRoom.setBackground(Img.load("/images/maps/main/sorting_room.png"));

        city = new Room(this, "city");
        city.loadWalls("/collisionMaps/main/city.json");
        city.setBackground(Img.load("/images/maps/main/city.png"));

        supermarket = new Room(this, "supermarket");
        supermarket.loadWalls("/collisionMaps/supermarket/supermarket.json");
        supermarket.setBackground(Img.load("/images/maps/supermarket/supermarket.png"));

        office = new Room(this, "office");
        office.loadWalls("/collisionMaps/supermarket/office.json");
        office.setBackground(Img.load("/images/maps/supermarket/office.png"));

        storageRoom = new Room(this, "storageRoom");
        storageRoom.loadWalls("/collisionMaps/supermarket/storage_room.json");
        storageRoom.setBackground(Img.load("/images/maps/supermarket/storage_rom.png"));

        parkinglot = new Room(this, "parking-lot");
        parkinglot.loadWalls("/collisionMaps/supermarket/parking_lot.json");
        parkinglot.setBackground(Img.load("/images/maps/supermarket/parking_lot.png"));

        hospitalOutside = new Room(this, "hospital-outside");
        hospitalOutside.loadWalls("/collisionMaps/hospital/outside_hospital.json");
        hospitalOutside.setBackground(Img.load("/images/maps/hospital/outside_hospital.png"));

        reception = new Room(this, "reception");
        reception.loadWalls("/collisionMaps/hospital/reception.json");
        reception.setBackground(Img.load("/images/maps/hospital/hospital_reception.png"));

        operatingRoom = new Room(this, "operating-room");
        operatingRoom.loadWalls("/collisionMaps/hospital/operations_room.json");
        operatingRoom.setBackground(Img.load("/images/maps/hospital/operations_room.png"));

        morgue = new Room(this, "morgue");
        morgue.loadWalls("/collisionMaps/hospital/morgue.json");
        morgue.setBackground(Img.load("/images/maps/hospital/morgue.png"));

        canteen = new Room(this, "canteen");
        canteen.loadWalls("/collisionMaps/hospital/canteen.json");
        canteen.setBackground(Img.load("/images/maps/hospital/canteen.png"));

        schoolOutside = new Room(this, "school-outside");
        schoolOutside.loadWalls("/collisionMaps/school/outside_school.json");
        schoolOutside.setBackground(Img.load("/images/maps/school/outside_school.png"));

        teachersLounge = new Room(this, "teachers-lounge");
        teachersLounge.loadWalls("/collisionMaps/school/teachers_lounge.json");
        teachersLounge.setBackground(Img.load("/images/maps/school/teachers_lounge.png"));

        chemistryRoom = new Room(this, "chemistry-room");
        chemistryRoom.loadWalls("/collisionMaps/school/chemistry_room.json");
        chemistryRoom.setBackground(Img.load("/images/maps/school/chemistry_room.png"));

        classRooms = new Room(this, "class-rooms");
        classRooms.loadWalls("/collisionMaps/school/school_gym.json");
        classRooms.setBackground(Img.load("/images/maps/school/school_gym.png"));

        girlsLockerRoom = new Room(this, "girls-locker-room");
        girlsLockerRoom.loadWalls("/collisionMaps/school/girls_locker_room.json");
        girlsLockerRoom.setBackground(Img.load("/images/maps/school/girls_locker_room.png"));

        //**************************************** Waste objects - Creation ***********************************************
        // Waste objects #Level 1 - Supermarket
        Waste tomatoCan = new Waste(this, "Tomato can", WasteType.METAL, "cans are made of metal, and therefore goes in metals", false);
        Waste sodaCan = new Waste(this, "Soda can", WasteType.METAL, "cans are made of metal, and therefore goes in metals", false);
        Waste meatTray = new Waste(this, "Meat tray", WasteType.HARD_PLASTIC, "meat trays are made of hard plastic, and therefore goes in hard plastic", false);
        Waste ketchupPlasticBottle = new Waste(this, "Ketchup plastic bottle", WasteType.HARD_PLASTIC, "it is made of hard plastic, and therefore goes in hard plastic", false);
        Waste picklesGlass = new Waste(this, "Pickle glass", WasteType.GLASS, "this is a glass, and therefore goes in glass", false);
        Waste beerBottle = new Waste(this, "Beer bottle", WasteType.GLASS, "beer bottles are typically made of glass, and therefore goes in glass", false);
        Waste eatenApple = new Waste(this, "Eaten apple", WasteType.ORGANIC, "apples are organic, and therefore goes in organic", true);
        Waste pizzaSlice = new Waste(this, "Pizza slice", WasteType.ORGANIC, "pizza is organic, and therefore goes in organic", true);
        Waste paint1 = new Waste(this, "Paint", WasteType.HAZARDOUS, "paint is an hazardous material and should be handled accordingly and placed in hazardous", true);
        Waste aaBatteries = new Waste(this, "AA batteries", WasteType.HAZARDOUS, "batteries contain hazardous chemicals, and therefore goes in hazardous", true);
        Waste deodorant = new Waste(this, "Deodorant", WasteType.HAZARDOUS, "deodorants are typically cans containing chemicals, and therefore goes in hazardous", true);
        Waste newspaper = new Waste(this, "Newspaper", WasteType.PAPER, "newspapers are made of paper, and therefore goes in paper", true);
        Waste cardboardBox = new Waste(this, "Cardboard box", WasteType.CARDBOARD, "cardboard boxes are made of cardboard, and therefore goes in cardboard", true);
        Waste pizzaBox = new Waste(this, "Pizza box", WasteType.RESIDUAL, "a pizza box has been dirtied by a pizza, and therefore goes in residual", true);
        Waste receipt = new Waste(this, "Receipt", WasteType.RESIDUAL, "receipts is made of a special kind of paper containing chemicals and should not be sorted in paper, therefore it goes in residual", true);

        // Waste objects #Level 2 - Hospital
        Waste papers1 = new Waste(this, "Papers", WasteType.PAPER, "this is a paper. It goes in paper",true);
        Waste medicineBottle = new Waste (this, "Glass medicine bottle", WasteType.GLASS, "it is made out of glass, and therefore goes in glass", false);
        Waste syringe = new Waste (this, "Syringe", WasteType.HAZARDOUS, "most biochemical equipment are hazardous, so are syringes. Never reuse a syringe!", true);
        Waste scalpel = new Waste (this, "Scalpel", WasteType.HAZARDOUS, "a scalpel is a sharp object. This goes into hazardous", true);
        Waste paperclip = new Waste (this, "Paperclips", WasteType.RESIDUAL, "unless you got a large quantity of paper clips, recycling paper clips have a larger carbon footprint rather than just sorting them to residual. Therefore it goes in residual", true);
        Waste facemask = new Waste (this, "Face mask", WasteType.RESIDUAL, "this is a face mask. It goes into residual", true);
        Waste bloodbag = new Waste (this, "Blood bag", WasteType.HAZARDOUS, "blood bags are biohazardous material and goes in hazardous", true);
        Waste apple = new Waste (this, "Apple",WasteType.ORGANIC, "apples are organic. It goes in organic", true);
        Waste needle = new Waste (this, "Needle", WasteType.RESIDUAL, "needles in themselves are not worth recycling. It goes in residual ", true);
        Waste chocolateMilkCarton = new Waste (this, "Chocolate milk carton", WasteType.RESIDUAL, "this is a carton containing leftover organic substance, and therefore goes into residual", true);
        Waste can = new Waste (this, "Can", WasteType.METAL, "cans are made out of metal. It goes into metal", false);
        Waste cake = new Waste (this, "Moldy cake", WasteType.ORGANIC, "this is food and therefore organic. It goes into organic", true);
        Waste tray = new Waste(this,"Tin tray", WasteType.METAL, "tin trays are made out of metal. It goes into metal", false);
        Waste ballPen = new Waste(this, "Ballpoint pen", WasteType.RESIDUAL, "a ballpoint pen is compromised of multiple components and hard to recycle. It goes into residual", true);
        Waste toothBrush = new Waste(this, "Toothbrush", WasteType.RESIDUAL, "a toothbrush is not recycled due to repeated contact with a persons mouth and therefore unhygienic. It goes into residual", true);

        // Waste objects #Level 3 - School
        Waste papers = new Waste(this, "Papers", WasteType.PAPER, "this is a paper. It goes in paper",true);
        Waste lighter = new Waste(this, "Lighter", WasteType.HAZARDOUS, "lighters contains hazardous materials and therefore goes in hazardous", true);
        Waste bananas = new Waste(this, "Bananas", WasteType.ORGANIC, "this is an organic material and can be used as a compost, and therefore goes in organic", true);
        Waste jam_jar = new Waste(this, "Jam jar", WasteType.GLASS, "a jam jar is made of glass, and can therefore if clean, be recycled for reuse if put into the glass container", false);
        Waste nutella_glass = new Waste(this, "Nutella glass", WasteType.GLASS, "a nutella glass is made of glass, and can therefore if clean, be recycled for reuse if put into the glass container", false);
        Waste coffee_grounds = new Waste(this, "Coffee grounds", WasteType.ORGANIC, "coffee grounds are organic and can be used for compost. It goes into organic", true);
        Waste poster = new Waste(this, "Poster", WasteType.CARDBOARD, "a poster is made of cardboard material and goes in cardboard", true);
        Waste spectacles_frame = new Waste(this, "Spectacles frame", WasteType.METAL, "spectacle frame is made of steel and therefore goes in metal", true);
        Waste post_it = new Waste(this, "Post-it", WasteType.RESIDUAL, "due to the adhesive substance on the back of post-it notes, they are not to be recycled with normal paper, and goes in residual", true);
        Waste milk_carton = new Waste(this, "Milk carton", WasteType.RESIDUAL, "due to a milk carton being soaked in a organic substance it goes in residual", true);
        Waste perfume_bottle = new Waste(this, "Perfume bottle", WasteType.GLASS, "if you remove the perfume substances in the bottle, then the bottle can be recycled for reuse. Therefore it goes in glass", false);
        Waste water_bottle = new Waste(this, "Plastic water bottle", WasteType.HARD_PLASTIC, "it is made of hard plastic and can be recycled for reuse. Therefore it goes in hard plastic", true);
        Waste safety_goggles = new Waste(this, "Safety goggles", WasteType.HARD_PLASTIC, "typically safety goggles are made of hard plastic and can be recycled for reuse. Therefore goes in hard plastic", true);
        Waste paper_clip = new Waste(this, "Paper clips", WasteType.RESIDUAL, "unless you got a large quantity of paper clips, recycling paper clips have a larger carbon footprint rather than just sorting them to residual. Therefore it goes in residual", true);
        Waste paint = new Waste(this, "Paint", WasteType.HAZARDOUS, "paint is an hazardous material and should be handled accordingly. It goes in hazardous", true);


        //**************************************** Waste objects - Adding to rooms ***********************************************
        //Assigning trash for level #1 - Supermarket
        supermarket.addItem(tomatoCan);
        supermarket.addItem(ketchupPlasticBottle);
        supermarket.addItem(meatTray);
        supermarket.addItem(receipt);

        office.addItem(beerBottle);
        office.addItem(pizzaSlice);
        office.addItem(pizzaBox);

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

        morgue.addItem(scalpel);
        morgue.addItem(tray);
        morgue.addItem(ballPen);

        canteen.addItem(chocolateMilkCarton);
        canteen.addItem(can);
        canteen.addItem(cake);

        //Assigning trash for level #3 - School
        schoolOutside.addItem(papers);
        schoolOutside.addItem(lighter);
        schoolOutside.addItem(bananas);

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


        //************************************* Waste object - placement in rooms *************************************
        //Place waste to - Supermarket
        tomatoCan.setImage(Img.load("/images/items/trash/tomato_can.png"));
        tomatoCan.setScale(0.8f);
        tomatoCan.fitToImage();
        tomatoCan.setPosition(500,500);

        meatTray.setImage(Img.load("/images/items/trash/food_tray.png"));
        meatTray.setScale(0.6f);
        meatTray.fitToImage();
        meatTray.setPosition(150,125);

        ketchupPlasticBottle.setImage(Img.load("/images/items/trash/ketchup.png"));
        ketchupPlasticBottle.setScale(0.5f);
        ketchupPlasticBottle.fitToImage();
        ketchupPlasticBottle.setPosition(760,325);

        receipt.setImage(Img.load("/images/items/trash/receipt.png"));
        receipt.setScale(0.8f);
        receipt.fitToImage();
        receipt.setPosition(700,700);

        //Place waste to - Supermarket/kontor
        beerBottle.setImage(Img.load("/images/items/trash/beer.png"));
        beerBottle.setScale(0.6f);
        beerBottle.fitToImage();
        beerBottle.setPosition(200,200);

        pizzaSlice.setImage(Img.load("/images/items/trash/slice.png"));
        pizzaSlice.setScale(0.8f);
        pizzaSlice.fitToImage();
        pizzaSlice.setPosition(600,100);

        pizzaBox.setImage(Img.load("/images/items/trash/pizza_box.png"));
        pizzaBox.setScale(0.8f);
        pizzaBox.fitToImage();
        pizzaBox.setPosition(600,150);

        //Place waste to - Supermarket/lageret
        eatenApple.setImage(Img.load("/images/items/trash/apple_eaten.png"));
        eatenApple.setScale(0.6f);
        eatenApple.fitToImage();
        eatenApple.setPosition(300,300);

        paint1.setImage(Img.load("/images/items/trash/paint_2.png"));
        paint1.setScale(1f);
        paint1.fitToImage();
        paint1.setPosition(150,675);

        aaBatteries.setImage(Img.load("/images/items/trash/batteri.png"));
        aaBatteries.setScale(0.6f);
        aaBatteries.fitToImage();
        aaBatteries.setPosition(500,750);

        cardboardBox.setImage(Img.load("/images/items/trash/cardboard_box.png"));
        cardboardBox.setScale(0.8f);
        cardboardBox.fitToImage();
        cardboardBox.setPosition(700,350);

        //Place trash to - Supermarket/parkeringsplads
        newspaper.setImage(Img.load("/images/items/trash/newspaper-kopi.png"));
        newspaper.setScale(0.7f);
        newspaper.fitToImage();
        newspaper.setPosition(300,200);

        sodaCan.setImage(Img.load("/images/items/trash/soda can.png"));
        sodaCan.setScale(0.4f);
        sodaCan.fitToImage();
        sodaCan.setPosition(635,275);

        deodorant.setImage(Img.load("/images/items/trash/deo.png"));
        deodorant.setScale(0.4f);
        deodorant.fitToImage();
        deodorant.setPosition(600,550);

        picklesGlass.setImage(Img.load("/images/items/trash/pickles.png"));
        picklesGlass.setScale(0.8f);
        picklesGlass.fitToImage();
        picklesGlass.setPosition(200,700);

        //Place trash to - Hospital/outside
        apple.setImage(Img.load("/images/items/trash/apple.png"));
        apple.setScale(0.5f);
        apple.fitToImage();
        apple.setPosition(250,85);

        needle.setImage(Img.load("/images/items/trash/needle-kopi.png"));
        needle.setScale(0.7f);
        needle.fitToImage();
        needle.setPosition(469, 715);

        syringe.setImage(Img.load("/images/items/trash/syringe.png"));
        syringe.setScale(0.75f);
        syringe.fitToImage();
        syringe.setPosition(720,120);

        //Place trash to - Hospital/Reception
        papers1.setImage(Img.load("/images/items/trash/paper.png"));
        papers1.setScale(0.6f);
        papers1.fitToImage();
        papers1.setPosition(200,800);

        facemask.setImage(Img.load("/images/items/trash/maske.png"));
        facemask.setScale(0.5f);
        facemask.fitToImage();
        facemask.setPosition(378,378);

        paperclip.setImage(Img.load("/images/items/trash/paper_clips.png"));
        paperclip.setScale(0.5f);
        paperclip.fitToImage();
        paperclip.setPosition(238,129);

        //Place trash to - Hospital/Morgue
        scalpel.setImage(Img.load("/images/items/trash/scalpel.png"));
        scalpel.setScale(0.8f);
        scalpel.fitToImage();
        scalpel.setPosition(700,435);

        tray.setImage(Img.load("/images/items/trash/meat_tray.png"));
        tray.setScale(0.5f);
        tray.fitToImage();
        tray.setPosition(760,740);

        ballPen.setImage(Img.load("/images/items/trash/ball_pen.png"));
        ballPen.setScale(0.8f);
        ballPen.fitToImage();
        ballPen.setPosition(420,120);

        //Place trash to - Hospital/Canteen
        cake.setImage(Img.load("/images/items/trash/muffin_cake-kopi.png"));
        cake.setScale(0.5f);
        cake.fitToImage();
        cake.setPosition(190,483);

        chocolateMilkCarton.setImage(Img.load("/images/items/trash/chockolade_milk.png"));
        chocolateMilkCarton.setScale(0.5f);
        chocolateMilkCarton.fitToImage();
        chocolateMilkCarton.setPosition(500,240);

        can.setImage(Img.load("/images/items/trash/can-kopi.png"));
        can.setScale(0.5f);
        can.fitToImage();
        can.setPosition(700,469);

        //Place trash to - Hospital/Operations-room
        medicineBottle.setImage(Img.load("/images/items/trash/medicine_bottle.png"));
        medicineBottle.setScale(0.4f);
        medicineBottle.fitToImage();
        medicineBottle.setPosition(502,502);

        toothBrush.setImage(Img.load("/images/items/trash/toothbrush.png"));
        toothBrush.setScale(0.35f);
        toothBrush.fitToImage();
        toothBrush.setPosition(100,270);

        bloodbag.setImage(Img.load("/images/items/trash/bloodbag.png"));
        bloodbag.setScale(0.84f);
        bloodbag.fitToImage();
        bloodbag.setPosition(340,80);

        //Place trash to - School/outside
        papers.setImage(Img.load("/images/items/trash/paper_2.png"));
        papers.setScale(0.5f);
        papers.fitToImage();
        papers.setPosition(539,400);

        lighter.setImage(Img.load("/images/items/trash/lighter.png"));
        lighter.setScale(0.3f);
        lighter.fitToImage();
        lighter.setPosition(300,750);

        bananas.setImage(Img.load("/images/items/trash/banana.png"));
        bananas.setScale(0.7f);
        bananas.fitToImage();
        bananas.setPosition(600,200);

        //Place trash to - School/gymnastiksal
        poster.setImage(Img.load("/images/items/trash/poster.png"));
        poster.setScale(0.6f);
        poster.fitToImage();
        poster.setPosition(175,395);

        spectacles_frame.setImage(Img.load("/images/items/trash/spectacles.png"));
        spectacles_frame.setScale(0.4f);
        spectacles_frame.fitToImage();
        spectacles_frame.setPosition(200,800);

        post_it.setImage(Img.load("/images/items/trash/Post it.png"));
        post_it.setScale(0.8f);
        post_it.fitToImage();
        post_it.setPosition(400,200);

        //Place trash to - School/omklædningsrum
        milk_carton.setImage(Img.load("/images/items/trash/milk.png"));
        milk_carton.setScale(0.4f);
        milk_carton.fitToImage();
        milk_carton.setPosition(600,400);

        perfume_bottle.setImage(Img.load("/images/items/trash/perfume_bottle.png"));
        perfume_bottle.setScale(0.6f);
        perfume_bottle.fitToImage();
        perfume_bottle.setPosition(400,200);

        water_bottle.setImage(Img.load("/images/items/trash/water_bottle.png"));
        water_bottle.setScale(0.8f);
        water_bottle.fitToImage();
        water_bottle.setPosition(310,510);

        //Place trash to - School/lærerværelset
        jam_jar.setImage(Img.load("/images/items/trash/jamjar-kopi.png"));
        jam_jar.setScale(0.6f);
        jam_jar.fitToImage();
        jam_jar.setPosition(545,590);

        nutella_glass.setImage(Img.load("/images/items/trash/nutella.png"));
        nutella_glass.setScale(0.7f);
        nutella_glass.fitToImage();
        nutella_glass.setPosition(200,300);

        coffee_grounds.setImage(Img.load("/images/items/trash/coffee_grounds-kopi.png"));
        coffee_grounds.setScale(0.7f);
        coffee_grounds.fitToImage();
        coffee_grounds.setPosition(350,350);

        //Place trash to - School/kemilokale
        safety_goggles.setImage(Img.load("/images/items/trash/safety googles.png"));
        safety_goggles.setScale(0.5f);
        safety_goggles.fitToImage();
        safety_goggles.setPosition(650,200);

        paper_clip.setImage(Img.load("/images/items/trash/paper_clips.png"));
        paper_clip.setScale(0.5f);
        paper_clip.fitToImage();
        paper_clip.setPosition(600,510);

        paint.setImage(Img.load("/images/items/trash/paint.png"));
        paint.setScale(1.0f);
        paint.fitToImage();
        paint.setPosition(200,780);


        //**************************************** Key objects - Adding to rooms - Placement in rooms ****************************************
        Key bigbox = new Key(this, "BIGGEST BOX");
        office.addItem(bigbox);
        bigbox.setImage(Img.load("/images/items/trash/biggest-box.png"));
        bigbox.setScale(0.8f);
        bigbox.fitToImage();
        bigbox.setPosition(800,750);

        Key sewing_kit = new Key(this, "SEWING KIT");
        operatingRoom.addItem(sewing_kit);
        sewing_kit.setImage(Img.load("/images/items/trash/sewing_kit.png"));
        sewing_kit.setScale(0.9f);
        sewing_kit.fitToImage();
        sewing_kit.setPosition(750,90);

        Key arms = new Key(this, "ARM");
        morgue.addItem(arms);
        arms.setImage(Img.load("/images/npc/Arm.png"));
        arms.setScale(0.2f);
        arms.fitToImage();
        arms.setPosition(400,700);

        Key fertilizer = new Key(this,"FERTILIZER");
        schoolOutside.addItem(fertilizer);
        fertilizer.setImage(Img.load("/images/items/trash/fertilizer-kopi.png"));
        fertilizer.setScale(0.6f);
        fertilizer.fitToImage();
        fertilizer.setPosition(225,425);


        //**************************************** NPC - Adding to rooms - Placement in rooms ***********************************************
        //Start NPC
        NPC martin1 = new CityNPC(this, "Martin");
        martin1.setImage(Img.load("/images/npc/start_npc.png"));
        martin1.setScale(1.25f);
        martin1.fitToImage();
        martin1.setPosition(600, 600);
        start.addItem(martin1);

        NPC martin2 = new CityNPC(this, "Martin");
        martin2.setImage(Img.load("/images/npc/start_npc.png"));
        martin2.setScale(1.25f);
        martin2.fitToImage();
        martin2.setPosition(450, 400);
        sortingRoom.addItem(martin2);

        NPC martin3 = new CityNPC(this, "Martin");
        martin3.setImage(Img.load("/images/npc/start_npc.png"));
        martin3.setScale(1.25f);
        martin3.fitToImage();
        martin3.setPosition(362, 566);
        city.addItem(martin3);

        //Supermarket NPC
        ParkingLotNPC homelessDan = new ParkingLotNPC(this, "Dan");
        homelessDan.setImage(Img.load("/images/npc/supermarket_npc.png"));
        homelessDan.setScale(1.25f);
        homelessDan.fitToImage();
        homelessDan.setPosition(300, 300);
        parkinglot.addItem(homelessDan);
        homelessDan.setBigbox(bigbox);

        //Hospital NPC
        ZombieNPC mrZombie = new ZombieNPC(this, "Mr.Zombie");
        mrZombie.setImage(Img.load("/images/npc/hospital_npc.png"));
        mrZombie.setScale(1.25f);
        mrZombie.fitToImage();
        mrZombie.setPosition(720, 570);
        hospitalOutside.addItem(mrZombie);
        mrZombie.setArm(arms);
        mrZombie.setSewingKit(sewing_kit);

        //School NPC
        SchoolNPC madChemist = new SchoolNPC(this, "Mad-Chemist");
        madChemist.setImage(Img.load("/images/npc/school_npc.png"));
        madChemist.setScale(1.25f);
        madChemist.fitToImage();
        madChemist.setPosition(536, 270);
        teachersLounge.addItem(madChemist);
        madChemist.setFertilizer(fertilizer);


        // ********************************************* Doors - Adding to rooms - Placement in rooms - Connecting doors *********************************************
        Door startSort = new Door(this, "Start", start);
        startSort.setImage(Img.load("/images/items/ArrowRight.png"));
        startSort.setWidth(45);
        startSort.setHeight(64);
        startSort.autoScale();
        startSort.setPosition(870, 410);

        Door sortStart = new Door(this, "Sort", sortingRoom);
        sortStart.setImage(Img.load("/images/items/ArrowLeft.png"));
        sortStart.setWidth(45);
        sortStart.setHeight(64);
        sortStart.autoScale();
        sortStart.setPosition(0, 404);

        Door sortOdense = new Door(this, "Sort", sortingRoom);
        sortOdense.setImage(Img.load("/images/items/ArrowRight.png"));
        sortOdense.setWidth(45);
        sortOdense.setHeight(64);
        sortOdense.autoScale();
        sortOdense.setPosition(870, 404);

        Door odenseSort = new Door(this, "Odense", city);
        odenseSort.setImage(Img.load("/images/items/ArrowLeft.png"));
        odenseSort.setWidth(45);
        odenseSort.setHeight(64);
        odenseSort.autoScale();
        odenseSort.setPosition(0, 410);

        Door odenseSuper = new Door(this, "Odense", city);
        odenseSuper.setImage(Img.load("/images/items/ArrowRight.png"));
        odenseSuper.setWidth(45);
        odenseSuper.setHeight(64);
        odenseSuper.autoScale();
        odenseSuper.setPosition(870, 410);

        Door odenseHos = new Door(this, "Odense", city);
        odenseHos.setImage(Img.load("/images/items/ArrowDown.png"));
        odenseHos.setWidth(64);
        odenseHos.setHeight(45);
        odenseHos.autoScale();
        odenseHos.setPosition(435, 870);

        Door odenseSchool = new Door(this, "Odense", city);
        odenseSchool.setImage(Img.load("/images/items/ArrowUp.png"));
        odenseSchool.setWidth(64);
        odenseSchool.setHeight(45);
        odenseSchool.autoScale();
        odenseSchool.setPosition(435, 0);

        //Door Level 1.
        Door superOdense = new Door(this, "Supermarket", supermarket);
        superOdense.setImage(Img.load("/images/items/ArrowLeft.png"));
        superOdense.setWidth(45);
        superOdense.setHeight(64);
        superOdense.autoScale();
        superOdense.setPosition(0, 478);

        Door superOffice = new Door(this, "Supermarket", supermarket);
        superOffice.setImage(Img.load("/images/items/ArrowUp.png"));
        superOffice.setWidth(64);
        superOffice.setHeight(45);
        superOffice.autoScale();
        superOffice.setPosition(448, 0);

        Door superStor = new Door(this, "Supermarket", supermarket);
        superStor.setImage(Img.load("/images/items/ArrowRight.png"));
        superStor.setWidth(45);
        superStor.setHeight(64);
        superStor.autoScale();
        superStor.setPosition(870, 480);

        Door superPark = new Door(this, "Supermarket", supermarket);
        superPark.setImage(Img.load("/images/items/ArrowDown.png"));
        superPark.setWidth(64);
        superPark.setHeight(45);
        superPark.autoScale();
        superPark.setPosition(452, 870);

        Door officeSuper = new Door(this, "Office", office);
        officeSuper.setImage(Img.load("/images/items/ArrowDown.png"));
        officeSuper.setWidth(64);
        officeSuper.setHeight(45);
        officeSuper.autoScale();
        officeSuper.setPosition(420, 870);

        Door storSuper = new Door(this, "Storage", storageRoom);
        storSuper.setImage(Img.load("/images/items/ArrowLeft.png"));
        storSuper.setWidth(45);
        storSuper.setHeight(64);
        storSuper.autoScale();
        storSuper.setPosition(0, 351);

        Door parkSuper = new Door(this, "Parking lot", parkinglot);
        parkSuper.setImage(Img.load("/images/items/ArrowUp.png"));
        parkSuper.setWidth(64);
        parkSuper.setHeight(45);
        parkSuper.autoScale();
        parkSuper.setPosition(466, 120);

        //Door Level 2.
        Door hosOdense = new Door(this, "Hospital", hospitalOutside);
        hosOdense.setImage(Img.load("/images/items/ArrowUp.png"));
        hosOdense.setWidth(64);
        hosOdense.setHeight(45);
        hosOdense.autoScale();
        hosOdense.setPosition(404, 0);

        Door hosRep = new Door(this, "Hospital", hospitalOutside);
        hosRep.setImage(Img.load("/images/items/ArrowUp.png"));
        hosRep.setWidth(64);
        hosRep.setHeight(45);
        hosRep.autoScale();
        hosRep.setPosition(788, 570);

        Door repHos = new Door(this, "Reception", reception);
        repHos.setImage(Img.load("/images/items/ArrowDown.png"));
        repHos.setWidth(64);
        repHos.setHeight(45);
        repHos.autoScale();
        repHos.setPosition(643, 870);

        Door repMorg = new Door(this, "Reception", reception);
        repMorg.setImage(Img.load("/images/items/ArrowLeft.png"));
        repMorg.setWidth(45);
        repMorg.setHeight(64);
        repMorg.autoScale();
        repMorg.setPosition(24, 370);

        Door repOp = new Door(this, "Reception", reception);
        repOp.setImage(Img.load("/images/items/ArrowRight.png"));
        repOp.setWidth(45);
        repOp.setHeight(64);
        repOp.autoScale();
        repOp.setPosition(850, 404);

        Door repCan = new Door(this, "Reception", reception);
        repCan.setImage(Img.load("/images/items/ArrowUp.png"));
        repCan.setWidth(64);
        repCan.setHeight(45);
        repCan.autoScale();
        repCan.setPosition(676, 0);

        Door morgueRep = new Door(this, "Morgue", morgue);
        morgueRep.setImage(Img.load("/images/items/ArrowRight.png"));
        morgueRep.setWidth(45);
        morgueRep.setHeight(64);
        morgueRep.autoScale();
        morgueRep.setPosition(870, 386);

        Door opRep = new Door(this, "Operating room", operatingRoom);
        opRep.setImage(Img.load("/images/items/ArrowLeft.png"));
        opRep.setWidth(45);
        opRep.setHeight(64);
        opRep.autoScale();
        opRep.setPosition(0, 466);

        Door canRep = new Door(this, "Canteen", canteen);
        canRep.setImage(Img.load("/images/items/ArrowDown.png"));
        canRep.setWidth(64);
        canRep.setHeight(45);
        canRep.autoScale();
        canRep.setPosition(420, 870);

        //Door Level 3.
        Door schoolOdense = new Door(this, "School", schoolOutside);
        schoolOdense.setImage(Img.load("/images/items/ArrowDown.png"));
        schoolOdense.setWidth(64);
        schoolOdense.setHeight(45);
        schoolOdense.autoScale();
        schoolOdense.setPosition(435, 870);

        Door schoolChem = new Door(this, "School", schoolOutside);
        schoolChem.setImage(Img.load("/images/items/ArrowLeft.png"));
        schoolChem.setWidth(45);
        schoolChem.setHeight(64);
        schoolChem.autoScale();
        schoolChem.setPosition(26, 243);

        Door schoolTeach = new Door(this, "School", schoolOutside);
        schoolTeach.setImage(Img.load("/images/items/ArrowRight.png"));
        schoolTeach.setWidth(45);
        schoolTeach.setHeight(64);
        schoolTeach.autoScale();
        schoolTeach.setPosition(840, 243);

        Door schoolClass = new Door(this, "School", schoolOutside);
        schoolClass.setImage(Img.load("/images/items/ArrowUp.png"));
        schoolClass.setWidth(64);
        schoolClass.setHeight(45);
        schoolClass.autoScale();
        schoolClass.setPosition(435, 0);

        Door chemSchool = new Door(this, "Chemistry room", chemistryRoom);
        chemSchool.setImage(Img.load("/images/items/ArrowRight.png"));
        chemSchool.setWidth(45);
        chemSchool.setHeight(64);
        chemSchool.autoScale();
        chemSchool.setPosition(870, 448);

        Door teachSchool = new Door(this, "Teachers Lounge", teachersLounge);
        teachSchool.setImage(Img.load("/images/items/ArrowLeft.png"));
        teachSchool.setWidth(45);
        teachSchool.setHeight(64);
        teachSchool.autoScale();
        teachSchool.setPosition(0, 339);

        Door classSchool = new Door(this, "Class rooms", classRooms);
        classSchool.setImage(Img.load("/images/items/ArrowDown.png"));
        classSchool.setWidth(64);
        classSchool.setHeight(45);
        classSchool.autoScale();
        classSchool.setPosition(402, 870);

        Door classGirl = new Door(this, "Class rooms", classRooms);
        classGirl.setImage(Img.load("/images/items/ArrowUp.png"));
        classGirl.setWidth(64);
        classGirl.setHeight(45);
        classGirl.autoScale();
        classGirl.setPosition(357, 52);

        Door girlClass = new Door(this, "Girls locker room", girlsLockerRoom);
        girlClass.setImage(Img.load("/images/items/ArrowDown.png"));
        girlClass.setWidth(64);
        girlClass.setHeight(45);
        girlClass.autoScale();
        girlClass.setPosition(372, 870);

        //Adding Doors
        start.addItem(startSort);
        sortingRoom.addItem(sortStart);
        sortingRoom.addItem(sortOdense);
        city.addItem(odenseSort);
        city.addItem(odenseSuper);
        city.addItem(odenseHos);
        city.addItem(odenseSchool);

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


        // ********************************************* Waste Containers *********************************************
        //Creating waste container objects
        WasteContainer organicContainer = new WasteContainer(this, "organic-container", WasteType.ORGANIC);
        organicContainer.setImage(Img.load("/images/wasteContainers/ORGANICS.png"));
        organicContainer.setScale(0.8f);
        organicContainer.fitToImage();
        organicContainer.setPosition(50,50);

        WasteContainer glassContainer = new WasteContainer(this, "glass-container", WasteType.GLASS);
        glassContainer.setImage(Img.load("/images/wasteContainers/glass.png"));
        glassContainer.setScale(0.8f);
        glassContainer.fitToImage();
        glassContainer.setPosition(285,50);


        WasteContainer metalContainer = new WasteContainer(this, "metal-container", WasteType.METAL);
        metalContainer.setImage(Img.load("/images/wasteContainers/Metal.png"));
        metalContainer.setScale(0.8f);
        metalContainer.fitToImage();
        metalContainer.setPosition(535,50);

        WasteContainer papercontainer = new WasteContainer(this, "paper-container", WasteType.PAPER);
        papercontainer.setImage(Img.load("/images/wasteContainers/paper.png"));
        papercontainer.setScale(0.8f);
        papercontainer.fitToImage();
        papercontainer.setPosition(750,50);

        WasteContainer residualcontainer = new WasteContainer(this, "residual-container", WasteType.RESIDUAL);
        residualcontainer.setImage(Img.load(("/images/wasteContainers/RESIDUAL.png")));
        residualcontainer.setScale(0.8f);
        residualcontainer.fitToImage();
        residualcontainer.setPosition(50,725);

        WasteContainer cardboardcontainer = new WasteContainer(this, "cardboard-container", WasteType.CARDBOARD);
        cardboardcontainer.setImage(Img.load("/images/wasteContainers/CARDBOARD.png"));
        cardboardcontainer.setScale(0.8f);
        cardboardcontainer.fitToImage();
        cardboardcontainer.setPosition(285,725);

        WasteContainer hardPlasticcontainer = new WasteContainer(this, "plastic-container", WasteType.HARD_PLASTIC);
        hardPlasticcontainer.setImage(Img.load("/images/wasteContainers/PLASTIC.png"));
        hardPlasticcontainer.setScale(0.8f);
        hardPlasticcontainer.fitToImage();
        hardPlasticcontainer.setPosition(535,725);

        WasteContainer hazardouscontainer = new WasteContainer(this, "hazardous-container", WasteType.HAZARDOUS);
        hazardouscontainer.setImage(Img.load("/images/wasteContainers/hazardous.png"));
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


        //**************************************** Sink Object ************************************************

        //Sink
        Sink sink1 = new Sink(this, "sink");
        Sink sink2 = new Sink(this, "sink");
        sortingRoom.addItem(sink1);
        sortingRoom.addItem(sink2);
        sink1.setImage(Img.load("/images/items/SINK90.png"));
        sink2.setImage(Img.load("/images/items/SINK-90.png"));
        sink2.setScale(0.3f);
        sink1.setScale(0.3f);
        sink1.fitToImage();
        sink2.fitToImage();
        sink1.setPosition(45,300);
        sink2.setPosition(825,300);

        // Misc.
        currentRoom = start;

        //Scores
        scoreSystem.getLevelHandler().addLevel(supermarket, 0);
        scoreSystem.getLevelHandler().addLevel(hospitalOutside, 15);
        scoreSystem.getLevelHandler().addLevel(schoolOutside, 30);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /*** Prints out a welcome message.***/
    public void printWelcome() {
        textPrinter.printText("Welcome to the World of Trash!!! \n\nThe world has been through an apocalypse caused by massive amounts of trash and is in need of a hero. " +
                "This is an incredibly exciting adventure game. \n\nYou are in your home. The beginning of your trashy adventure. A man greets " +
                "you and says \"Welcome to the World of Trash. My name is Trash Master Martin, but you can just call me Martin\" \n\n\"You must help us save the " +
                "planet! Now follow me into the next room if you want to survive. " +
                "You can go to the next room by pressing X on the green arrow, and talk using X when you are near me\"");

    }

    /**
     * Changes current room to the given room.
     * @param room Room to go to.
     * @return Whether or not the room is locked
     */
    public boolean changeRoom(Room room) {
        if (room.isLocked()) {
            getTextPrinter().printText("This room is locked.");
            return false;
        }
        currentRoom = room;
        return true;
    }

    /**
     * Called before every frame render.
     * @param delta Time since last frame.
     */
    public void update(double delta) {
        getCurrentRoom().update(delta);
        player.update(delta);
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

    public Player getPlayer() {
        return player;
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

    public double getWidth() {
        return width;
    }

    public IGameTextPrinter getTextPrinter() {return textPrinter;}

    public void setTextPrinter(IGameTextPrinter textPrinter) {this.textPrinter = textPrinter;}

}
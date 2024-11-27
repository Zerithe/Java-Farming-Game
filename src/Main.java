import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Main class where all the objects will be declared and will be the driver of the program.
 * @author De Vera
 * @author Gutierrez
 *
 *
 */
public class Main {
    private static seed[] seeds = new seed[8];
    private static Tools[] tools = new Tools[6];
    private static Tile[] plots = new Tile[50];
    private static Grid menu;
    private static Player player;
    private static FarmerType[] fTypes = new FarmerType[4];

    /**
     * assignSeeds() method is for the initialization of each seed object to be used in the game.
     */
    public static void assignSeeds(){
        seeds[0] = new seed("Turnip", "Root crop",
                2, 1, 2, 0, 1,
                1, 3, 5,
                6, 5, false);
        seeds[1] = new seed("Carrot", "Root crop", 3,
                1, 2, 0, 1,
                1,2, 10, 9, 7.5, false);
        seeds[2] = new seed("Potato", "Root crop", 5, 3,
                4, 1, 2, 1, 11,
                20, 3, 12.5, false);
        seeds[3] = new seed("Rose", "Flower", 1, 1, 2,
                0, 1, 1, 2, 5, 5, 2.5, false);
        seeds[4] = new seed("Tulips", "Flower", 2, 2, 3, 0,
                1, 1, 2, 10, 9, 5, false);
        seeds[5] = new seed("Sunflower", "Flower", 3, 2, 3,
                1, 2, 1, 1, 20, 19, 7.5, false);
        seeds[6] = new seed("Mango", "Fruit tree", 10, 7, 7,
                4, 4, 5, 16, 100,
                8, 25, true);
        seeds[7] = new seed("Apple", "Fruit tree", 10, 7, 7, 5, 5,
                10, 16, 200, 5, 25,true);
    }
    /**
     * AssignTools() method is for the initialization of each seed object to be used in the game.
     */
    public static void assignTools(){
        tools[0] = new Tools("Plow", 0, 0.5);
        tools[1] = new Tools("Watering Can", 0, 0.5);
        tools[2] = new Tools("Fertilizer", 10, 4);
        tools[3] = new Tools("Pickaxe", 50, 15);
        tools[4] = new Tools("Shovel", 7, 2);
    }
    /**
     * assignFarmerTypes() method is for the initialization of each farmer type object to be used in the game.
     */
    public static void assignFarmerTypes(){
        fTypes[0] = new FarmerType("Farmer", 0, 0, 0, 0, 0, 200);
        fTypes[1] = new FarmerType("Registered Farmer", 5, 1, -1, 0, 0, 300);
        fTypes[2] = new FarmerType("Distinguished Farmer", 10, 2, -2, 1, 0, 400);
        fTypes[3] = new FarmerType("Legendary Farmer", 15, 4, -3, 2, 1, 0);
    }
    /**
     * assignEmptyPlots() method is for the initialization of each plot object to be used in the game.
     */
    public static void assignEmptyPlots(){
        for(int i = 0; i < 50;i++){
            plots[i] = new Tile();
        }
    }
    /**
     * assignEmptyPlots() method is to place the rocks from the provided location in the file
     */
    public static void assignRocks(){
        int randomamount = ThreadLocalRandom.current().nextInt(10, 31);
        //Change to file reading later;
        try{
            File rockPlacer = new File("RockPlace.txt");
            Scanner sc = new Scanner(rockPlacer);
            while(sc.hasNextInt()){
                plots[sc.nextInt()].getStatus().setRock(true);
            }
        }catch(FileNotFoundException e){
            System.out.println("Did not find rock.txt");
            for(int i = 0; i <= randomamount; i++){
                plots[ThreadLocalRandom.current().nextInt(0, 50)].getStatus().setRock(true);
            }
        }
    }
    /**
     * Main method to be used for the execution of the Java Program.
     */
    public static void main(String[] args) {
        assignSeeds();
        assignTools();
        assignEmptyPlots();
        assignRocks();
        assignFarmerTypes();
        player = new Player(tools, seeds, plots, fTypes);
        menu = new Grid(plots, player);

    }
}
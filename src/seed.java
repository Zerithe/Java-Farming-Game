import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the seeds that will be used throughout the farm.
 * Each created seed will have their own name, needs, bonuses, yields, and costs
 * that will affect the player's needed tool usage and yields every harvest time.
 * @author De Vera
 * @author Gutierrez
 */
public class seed {
    private String seedName;
    private String seedType;
    private int harvestTime;
    private int waterNeeds;
    private int waterBonus;
    private int fertilizerNeeds;
    private int fertilizerBonus;
    private int productNum;
    private int productMin;
    private int productMax;
    private int cost;
    private int sellingPrice;
    private double expGain;
    private boolean isTree;

    /**
     * Creates the seed object where it will have its own statistics that will affect
     *     the needed actions of the player.
     *
     *     @param seedName         name of the seed.
     *     @param seedType         name of the type of seed.
     *     @param harvestTime      needed time before harvest.
     *     @param waterNeeds       amount of the times the seed must be watered.
     *     @param waterBonus       amount of the times the seed can be watered to incur a bonus for harvest.
     *     @param fertilizerNeeds  amount of the times the seed must be fertilized.
     *     @param fertilizerBonus  amount of the times the seed can be fertilized to incur a bonus for harvest.
     *     @param productMin       minimum amount of products that can be produced at harvest.
     *     @param productMax       maximum amount of products that can be produced at harvest.
     *     @param cost             how much the seed cost.
     *     @param sellingPrice     how much each individual product can be sold for.
     *     @param expGain          how much experience can be gained from harvesting.
     *     @param isTree           if the seed is a plant type of tree or not.
     */
    public seed(String seedName,
                      String seedType,
                      int harvestTime,
                      int waterNeeds,
                      int waterBonus,
                      int fertilizerNeeds,
                      int fertilizerBonus,
                      int productMin,
                      int productMax,
                      int cost,
                      int sellingPrice,
                      double expGain,
                      boolean isTree) {

        this.seedName = seedName;
        this.seedType = seedType;
        this.harvestTime = harvestTime;
        this.waterNeeds = waterNeeds;
        this.waterBonus = waterBonus;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonus = fertilizerBonus;
        this.productMin = productMin;
        this.productMax = productMax;
        this.cost = cost;
        this.sellingPrice = sellingPrice;
        this.expGain = expGain;
        this.isTree = isTree;
    }

    /**
     * returns the name of the seed.
     * @return seedName
     */
    public String getName() {
        return this.seedName;
    }
    /**
     * returns the name type of the seed.
     * @return seedType
     */
    public String getSeedType(){
        return this.seedType;
    }
    /**
     * returns the harvest time of the seed
     * @return harvestTime
     */
    public int getHarvestTime() {
        return this.harvestTime;
    }
    /**
     * returns the water needs of the seed
     * @return waterNeeds
     */
    public int getWaterNeeds() {
        return this.waterNeeds;
    }
    /**
     * returns the fertilizer needs of the seed
     * @return fertilizerNeeds
     */
    public int getFertilizerNeeds() {
        return this.fertilizerNeeds;
    }
    /**
     * returns the fertilizer bonus of the seed
     * @return fertilizerBonus
     */
    public int getFertilizerBonus(){
        return this.fertilizerBonus;
    }
    /**
     * returns the water bonus of the seed
     * @return waterBonus
     */
    public int getWaterBonus() {
        return waterBonus;
    }

    /**
     * generates a random number for the final harvest amount of the seed.
     */
    public void generateProductNum(){
        productNum = ThreadLocalRandom.current().nextInt(productMin, productMax);
    }
    /**
     * returns the final harvest amount of the seed.
     * @return productNum
     */
    public int getProductNum() {
        return this.productNum;
    }
    /**
     * returns the cost of the seed
     * @return cost
     */
    public int getCost() {
        return this.cost;
    }
    /**
     * returns the selling price per piece of the seed
     * @return sellingPrice
     */
    public int getSellingPrice() {
        return this.sellingPrice;
    }
    /**
     * returns the experience gain of the seed
     * @return expGain
     */
    public double getExpGain() {
        return this.expGain;
    }
    /**
     * returns if the seed is a tree or not.
     * @return isTree
     */
    public boolean getIsTree(){
        return this.isTree;
    }

}

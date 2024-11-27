/**
 * The Tile class is used to create the tiles that can be used for the farm,
 * where the actions can be used, and where the seeds can be planted.
 * @author De Vera
 * @author Gutierrez
 */
public class Tile{
    private seed seeds;
    private Status status;
    private int finalPrice;
    private boolean isTree = false;

    /**
     * When the tile is called, it creates its status object to be manipulated in the game.
     */
    Tile(){
        status = new Status();
    }

    /**
     * returns the status of the tile.
     * @return status
     */
    public Status getStatus(){
        return status;
    }

    /**
     * assigns the given seed in the parameter to the tile object.
     * @param inputSeed given seed object
     */
    public void assignSeed(seed inputSeed){
        this.seeds = inputSeed;
        status.setDays(seeds.getHarvestTime());
        if(inputSeed.getIsTree()){
            isTree = true;
        }
    }
    /**
     * returns the seed placed in the tile.
     * @return seeds
     */
    public seed getSeed(){
        return seeds;
    }

    /**
     * Checks if the seed in the tile is ready to be harvested. Sets the necessary measures in order to set the
     * calculations of the harvest to be correct. Also checks if the seed is to be withered or not.
     * @param fType the farmer type to be used for the correct values of the status.
     */
    public void checkReady(FarmerType fType){
        if((status.getWaterCount() >= seeds.getWaterNeeds()) && (status.getFertilizeCount() >= seeds.getFertilizerNeeds())){
            if(status.getWaterCount() >= seeds.getWaterNeeds() + seeds.getWaterBonus() + fType.getWaterBonusLimit()){
                status.setWaterCount(seeds.getWaterNeeds() + seeds.getWaterBonus() + fType.getWaterBonusLimit());
            }
            if(status.getFertilizeCount() >= seeds.getFertilizerNeeds() + seeds.getFertilizerBonus() + fType.getFertilizerBonusLimit()){
                status.setWaterCount(seeds.getFertilizerNeeds() + seeds.getFertilizerBonus() + fType.getFertilizerBonusLimit());
            }
            status.setReady(true);
        }
        else if(status.getDaysLeft() == 0 && !status.isReady()){
            status.setWithered(true);
        }
        if(status.getDaysLeft() < 0 && status.isReady()){
            status.setReady(false);
            status.setWithered(true);
        }
    }

    /**
     * this function resets the seed and status of the tile.
     * @return void
     */

    public void resetTile() {
        seeds = null;
        status = new Status();
    }

    /**
     * deletes the seed from the tile.
     */
    public void resetSeed(){
        seeds = null;
    }

    /**
     * sets the final price of the harvested tile
     * @param finalPrice final price of the harvest.
     */
    public void setFinalPrice(int finalPrice){
        this.finalPrice = finalPrice;
    }

    /**
     * returns the final price
     * @return finalPrice
     */
    public int getFinalPrice(){
        return finalPrice;
    }

    /**
     * returns whether the seed placed will become a tree.
     * @return isTree
     */
    public boolean isTree(){
        return isTree;
    }
}

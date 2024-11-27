/**
 * This class represents all the actions that the player will take in the
 * program and also holds all the statistics that the player has.
 * It is responsible for updating and modifying the tile that the farm has
 * and to keep the players updated with the actions they use.
 *  @author De Vera
 *  @author Gutierrez
 */
public class Player {
    private static double exp = 450;
    private static int objectCoins = 1000;
    private static Tools[] currTool;
    private static seed[] seed;

    private static Grid menu;
    private static int level = 0;
    private static double currLevelCap = 100;
    private static int currDay = 1;
    private static FarmerType[] fTypes;
    private static FarmerType currFType;
    private static int currFTypeLvl = 0;
    /**
     * Constructor for the variables to be used in the player class.
     * @param currTool array of objects of all the tools to be used in the game.
     * @param fTypes array of objects of all the farmer types available.
     * @param plots array of objects of all the plots to be used in the game.
     * @param seed array of objects of all seeds to be used in the game.
     */
    Player(Tools[] currTool, seed[] seed, Tile[] plots, FarmerType[] fTypes){
        Player.currTool = currTool;
        Player.seed = seed;
        Player.fTypes = fTypes;
        currFType = fTypes[0];
    }

    /**
     * Checks the plot if it is available to be plowed, if it is available, it will call the function inside
     * the plot object given and set it to a plowed state. With this action, it will give the corresponding experience
     * and objectCoins that the tool related to the action provides.
     * @param plot plots from the grid of the game.
     * @param plotNum plot location
     * @return if plowing was successful.
     */
    public boolean plow(Tile[] plot, int plotNum){
        if(!plot[plotNum].getStatus().isPlowed() && !plot[plotNum].getStatus().isRock() && !plot[plotNum].getStatus().isOccupied()){
            plot[plotNum].getStatus().setPlowed(true);
            exp += currTool[0].getExpGain();
            convertExp();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks the plot if it is available to put a seed in the plot, if it is available, it will call the function inside
     * the plot object given and set a seed object within the tile. With this action, it will take the corresponding price
     * of the seed.
     * @param plot plots from the grid of the game.
     * @param plotNum plot location.
     * @param seedName name of seed being placed
     * @return if planting was successful.
     */
    public boolean plant(Tile[] plot, int plotNum, String seedName){
        if(plot[plotNum].getStatus().isPlowed() && !plot[plotNum].getStatus().isOccupied()){
            plot[plotNum].getStatus().setOccupied(true);
            for(int i = 0; i < 8; i++){
                if(seedName.equals(seed[i].getName())){
                    plot[plotNum].assignSeed(seed[i]);
                }
            }
            if(objectCoins >= plot[plotNum].getSeed().getCost() && checkZone(plot, plotNum)) {
                objectCoins -= plot[plotNum].getSeed().getCost() + currFType.getSeedCostReduction();
                return true;
            }
            else {
                plot[plotNum].resetSeed();
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * Checks whether the seed placement is valid or not
     * @param plot plots from the grid of the game.
     * @param plotNum plot location
     * @return if plot is valid for planting
     */
    public boolean checkZone(Tile[] plot, int plotNum){
        boolean isWestSides = false;
        boolean isNorthSides = false;
        boolean isEastSides = false;
        boolean isSouthSides = false;
        boolean isAdjacent = false;
        boolean isTree = plot[plotNum].isTree();
        int[] adjacentPlots = {1, 4, 5, 6};
        if(isTree){
            isWestSides = isSides(plotNum, 0, 46, 5);
            isNorthSides = isSides(plotNum, 0, 5,1);
            isEastSides = isSides(plotNum, 4, 50,5);
            isSouthSides = isSides(plotNum, 45, 50,1);
            if(!isWestSides && !isNorthSides  && !isEastSides  && !isSouthSides)
                isAdjacent = isAdjacent(plot, plotNum, adjacentPlots, true);
        }
        else{
            isAdjacent = isAdjacent(plot, plotNum, adjacentPlots, false);
        }
        if(isTree){
            if(isWestSides || isNorthSides  || isEastSides  || isSouthSides){
                return false;
            }
            else
                return !isAdjacent;
        }
        else{
            return !isAdjacent;
        }
    }

    /**
     * Checks if the plant is placed in the sides or corners of the plot grid
     * @param plotNum plot location
     * @param start starting number of the side
     * @param end end number of the side
     * @param increment how many increments for the for loop
     * @return whether it is on the side or not.
     */
    public boolean isSides(int plotNum, int start, int end, int increment) {
        boolean isSides = false;
        for (int i = start; i < end; i+=increment) {
            if (plotNum == i) {
                isSides = true;
            }
        }
        return isSides;
    }

    /**
     * Checks whether the placement of the seed is valid based on its type and adjacency
     * @param plot plots from the grid of the game.
     * @param plotNum plot location
     * @param adjacentPlots array of integers to be used to check for adjacency
     * @param isTree if the seed is a tree.
     * @return if the adjacency is valid or not
     */
    public boolean isAdjacent(Tile[] plot, int plotNum, int[] adjacentPlots, boolean isTree){
        boolean isAdjacent = false;
        int[] adjTreesCheck = {plotNum-adjacentPlots[0], plotNum-adjacentPlots[1], plotNum-adjacentPlots[2], plotNum-adjacentPlots[3],
                plotNum+adjacentPlots[0], plotNum+adjacentPlots[1], plotNum+adjacentPlots[2], plotNum+adjacentPlots[3]};
        if(isTree) {
            for(int i = 0;i < 8;i++){
                if(plot[adjTreesCheck[i]].getStatus().isOccupied() || plot[adjTreesCheck[i]].getStatus().isRock()){
                    isAdjacent = true;
                    break;
                }
            }
        }
        else{
            for(int j = 0; j < 8;j++){
                if(adjTreesCheck[j] >= 0 && adjTreesCheck[j] <= 50){
                    if(plot[adjTreesCheck[j]].isTree()){
                        isAdjacent = true;
                    }
                }
            }
        }
        return isAdjacent;
    }

    /**
     * Checks the plot if it is available to water the plot, if it is available, it will call the function inside
     * the plot object given and set it to a watered state. With this action, it will give the corresponding experience
     * and objectCoins that the tool related to the action provides.
     * @param plot plot from the grid of the game.
     * @param plotNum plot location
     * @return if watering was successful.
     */
    public boolean water(Tile[] plot, int plotNum){
        if(plot[plotNum].getStatus().isOccupied() && !plot[plotNum].getStatus().isWatered() && !plot[plotNum].getStatus().isWithered()){
            plot[plotNum].getStatus().setWatered(true);
            plot[plotNum].getStatus().updateWaterCount();
            exp += currTool[1].getExpGain();
            convertExp();
            return true;
        }
        else
            return false;
    }

    /**
     * Checks the plot if it is available to fertilize the plot, if it is available, it will call the function inside
     * the plot object given and set it to a fertilized state. With this action, it will give the corresponding experience
     * and take objectCoins that the tool related to the action provides and takes.
     * @param plot plot from the grid of the game.
     * @param plotNum plot location
     * @return if fertilizing was successful.
     */
    public boolean fertilize(Tile[] plot, int plotNum){
        if(plot[plotNum].getStatus().isOccupied() && !plot[plotNum].getStatus().isFertilized() && objectCoins >= currTool[2].getUsageCost() && !plot[plotNum].getStatus().isWithered()){
            plot[plotNum].getStatus().setFertilized(true);
            plot[plotNum].getStatus().updateFertilizeCount();
            exp += currTool[2].getExpGain();
            convertExp();
            objectCoins -= currTool[2].getUsageCost();
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks the plot if there is a rock on the plot. if there is a rock it will take away the rock and reset the tile.
     * With this action, it will give the corresponding experience and take objectCoins that the tool related to the action provides and takes.
     * @param plot plot from the grid of the game.
     * @param plotNum plot location
     * @return if mining was successful
     */
    public boolean mineRock(Tile[] plot, int plotNum){
        if(plot[plotNum].getStatus().isRock() && objectCoins >= currTool[3].getUsageCost()){
            plot[plotNum].getStatus().setRock(false);
            exp += currTool[3].getExpGain();
            convertExp();
            objectCoins -= currTool[3].getUsageCost();
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Resets the current tile if it is occupied or if it is withered. Will still take objectCoins even if plot is empty or has a rock.
     * With this action, it will give the corresponding experience and take objectCoins that the tool related to the action provides and takes.
     * @param plot plot from the grid of the game.
     * @param plotNum plot location
     * @return if mining was successful
     */
    public boolean dig(Tile[] plot, int plotNum){
        if(plot[plotNum].getStatus().isOccupied() && objectCoins >= currTool[4].getUsageCost()) {
            plot[plotNum].resetTile();
            exp += currTool[4].getExpGain();
            convertExp();
            objectCoins -= currTool[4].getUsageCost();
            return true;
        }
        else if(objectCoins >= currTool[4].getUsageCost()){
            objectCoins -= currTool[4].getUsageCost();
            return false;
        }
        else{
            return false;
        }
    }

    /**
     * Will advance the current day and will check every plot for their current states. It will ready the harvest for
     * plots with satisfied plants, will wither plants which conditions were not satisfied, and will reset some states of the plants
     * and continue its countdown towards its harvest.
     * @param plot plots to be used in the game.
     */
    public void advanceDay(Tile[] plot){
        currDay++;
        for(int i = 0;i < 50; i++){
            if(plot[i].getStatus().isOccupied() && !plot[i].getStatus().isWithered() && plot[i].getStatus().getDaysLeft() != 0){
                plot[i].getStatus().setWatered(false);
                plot[i].getStatus().setFertilized(false);
                plot[i].getStatus().updateDays();
            }
            if(plot[i].getStatus().isOccupied() && plot[i].getStatus().getDaysLeft() <= 0){
                plot[i].checkReady(currFType);
                plot[i].getStatus().updateDays();
            }
            if(plot[i].getStatus().isPlowed() && !plot[i].getStatus().isOccupied()){
                plot[i].getStatus().setPlowed(false);
            }
        }
    }
    /**
     * Checks the plot if is ready to be harvested. If it is ready to be harvested,
     * it will calculate the final harvest price with the designated formulas.
     * With this action, it will give the corresponding experience
     * and objectCoins that the seed and harvest provides.
     * @param plot plot from the grid of the game.
     * @param plotNum plot location
     * @return if harvesting was successful.
     */
    public boolean harvest(Tile[] plot, int plotNum){
        int harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
        if(plot[plotNum].getStatus().isReady()){
            exp += plot[plotNum].getSeed().getExpGain();
            convertExp();
            plot[plotNum].getSeed().generateProductNum();
            harvestTotal = plot[plotNum].getSeed().getProductNum() * (plot[plotNum].getSeed().getSellingPrice() + currFType.getBonusEarnings());
            waterBonus = (int) (harvestTotal * 0.2 *(plot[plotNum].getStatus().getWaterCount() - 1));
            fertilizerBonus = (int)(harvestTotal * 0.5 * plot[plotNum].getStatus().getFertilizeCount());
            if(plot[plotNum].getSeed().getSeedType().equals("Flower")) {
                finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            }
            else{
                finalHarvestPrice =(int) ((harvestTotal + waterBonus + fertilizerBonus) * 1.1);
            }
            objectCoins += finalHarvestPrice;
            plot[plotNum].setFinalPrice(finalHarvestPrice);
            return true;
        }
        else
            return false;
    }

    /**
     * Checks to see if the farmer is meeting the conditions of upgrading its type.
     * If it is ready to be upgraded, it will change its object to the next element.
     * @return if the upgrading was successful.
     */
    public boolean upgradeFarmer(){
        if ((level >= currFType.getLvlReq() + 5) && currFTypeLvl <= 3) {
            currFTypeLvl++;
            objectCoins -= currFType.getRegFee();
            currFType = fTypes[currFTypeLvl];
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * returns the experience points.
     * @return exp
     */
    public double getExp() {
        return exp;
    }
    /**
     * returns the objectCoins
     * @return objectCoins
     */
    public int getObjectCoins() {
        return objectCoins;
    }
    /**
     * returns the level of the player.
     * @return level
     */
    public int getLevel() {
        return level;
    }
    /**
     * returns the current day
     * @return currDay
     */
    public int getCurrDay(){
        return currDay;
    }
    /**
     * returns the asked seed.
     * @param i reference pointer to the seed
     * @return seed[i]
     */
    public seed getSeed(int i){
        return seed[i];
    }
    /**
     * returns the current farmer type
     * @return currFType
     */
    public FarmerType getCurrFType(){
        return currFType;
    }

    /**
     * checks to see if the player has leveled up.
     */
    public void convertExp(){
        if(exp >= currLevelCap) {
            level++;
            currLevelCap += 100;
        }
    }
}

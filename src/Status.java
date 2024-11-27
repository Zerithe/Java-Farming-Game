/**
 *the Status class is used to modify and keep track of the current status of the plot.
 * It tells whether the plot has been plowed, watered, fertilized, and various other conditions that
 * are needed for certain actions or events.
 * @author De Vera
 * @author Gutierrez
 */
public class Status {

    private boolean isWatered;
    private boolean isPlowed;
    private boolean isFertilized;
    private boolean isRock;
    private boolean isWithered;
    private boolean isReady;
    private boolean isOccupied;

    private int daysLeft;

    private int currDay;
    private int waterCount = 0;
    private int fertilizeCount;


    /**
     * the Status object is assigned to the player so that the actions of the player can affect the given
     *     variables of the status.
     */

    public Status(){

    }
    /**
     * This method is called whenever an action affects the watered condition of a plot.
     *
     *     @param watered      condition if the plot will be watered or not.
     */
    public void setWatered(boolean watered) {
        this.isWatered = watered;
    }
    /**
     * Method that returns whether the plot is watered or not.
     *
     *     @return condition if the plot is watered or not.
     */
    public boolean isWatered(){
        return isWatered;
    }
    /**
     * This method is called whenever an action affects the plowed condition of a plot.
     *
     *     @param plowed      condition if the plot will be plowed or not.
     */
    public void setPlowed(boolean plowed){
        this.isPlowed = plowed;
    }
    /**
     * Method that returns whether the plot is plowed or not.
     *
     *     @return condition if the plot is plowed or not.
     */
    public boolean isPlowed(){
        return this.isPlowed;
    }
    /**
     * This method is called whenever an action affects the fertilized condition of a plot.
     *
     *     @param fertilized      condition if the plot will be fertilized or not.
     */
    public void setFertilized(boolean fertilized){
        this.isFertilized = fertilized;
    }
    /**
     * Method that returns whether the plot is fertilized or not.
     *
     *     @return condition if the plot is fertilized or not.
     */
    public boolean isFertilized(){
        return this.isFertilized;
    }
    /**
     * This method is called whenever it modifies the condition of a present rock.
     *
     *     @param rock      condition if the plot has a rock or not.
     */
    public void setRock(boolean rock){
        this.isRock = rock;
    }
    /**
     * Method that returns whether the plot has a rock or not.
     *
     *     @return condition if the plot has a rock or not.
     */
    public boolean isRock(){
        return this.isRock;
    }
    /**
     * This method is called whenever it modifies the withered condition of the present plant on the plot
     *
     *     @param withered      condition if the plant on the plot is withered or not.
     */
    public void setWithered(boolean withered){
        this.isWithered = withered;
    }
    /**
     * Method that returns whether the plant is withered or not.
     *
     *     @return condition if the plant is withered or not.
     */
    public boolean isWithered(){
        return this.isWithered;
    }
    /**
     * This method is called whenever the plant is ready to be harvested or not
     *
     *     @param ready      condition if the plant is harvest ready or not.
     */
    public void setReady(boolean ready){
        this.isReady = ready;
    }
    /**
     * Method that returns whether the plant is ready to be harvested or not.
     *
     *     @return condition if the plant is ready to be harvested or not.
     */
    public boolean isReady(){
        return this.isReady;
    }
    /**
     * This method is called whenever the occupied condition of the plot is changed.
     *
     *     @param occupied     condition if the plot is occupied or not.
     */
    public void setOccupied(boolean occupied){
        this.isOccupied = occupied;
    }
    /**
     * Method that returns whether the plot is occupied or not.
     *
     *     @return condition if the plot is occupied or not.
     */
    public boolean isOccupied(){
        return this.isOccupied;
    }
    /**
     * Method that the decreases the amount of days before the plant is ready to be harvested.
     */
    public void updateDays(){
        this.daysLeft--;
    }
    /**
     * Method that sets the days before the plant is ready to be harvested.
     *
     *     @param daysLeft     the needed days before the plant can be harvested based on the seed planted.
     */
    public void setDays(int daysLeft){
        this.daysLeft = daysLeft;
    }
    /**
     * Method that returns the amount of days left before the plant can be harvested.
     *
     *     @return the amount of days left before the plant can be harvested.
     */
    public int getDaysLeft(){
        return this.daysLeft;
    }
    /**
     * Method that sets the current day
     *
     *     @param currDay      current day to be set.
     */
    public void setCurrDay(int currDay){
        this.currDay = currDay;
    }
    /**
     * Method that returns the current day.
     *
     *     @return the current day.
     */
    public int getCurrDay() {
        return this.currDay;
    }
    /**
     * Method that updates the current day to the next day.
     */
    public void updateCurrDay(){
        this.currDay++;
    }
    /**
     * Method that sets the amount of times the plant has been watered.
     *
     *     @param waterCount       amount of times the plant has been watered.
     */
    public void setWaterCount(int waterCount){
        this.waterCount = waterCount;
    }
    /**
     * Method that increases the times the plant has been watered. It checks first whether the plant has been previously
     *     watered on the same day.
     */
    public void updateWaterCount(){
        waterCount++;
    }
    /**
     * Method that returns the amount of times the plant has been watered.
     *
     *     @return the amount of times the plant has been watered.
     */
    public int getWaterCount(){
        return this.waterCount;
    }
    /**
     * Method that sets the amount of times the plant has been fertilized.
     *
     *     @param fertilizeCount       amount of times the plant has been fertilized.
     */
    public void setFertilizeCount(int fertilizeCount){
        this.fertilizeCount = fertilizeCount;
    }
    /**
     * Method that increases the times the plant has been fertilized. It checks first whether the plant has been previously
     *     fertilized on the same day.
     */
    public void updateFertilizeCount(){
        fertilizeCount++;
    }
    /**
     * Method that returns the amount of times the plant has been fertilized.
     *
     *     @return the amount of times the plant has been fertilized.
     */
    public int getFertilizeCount(){
        return this.fertilizeCount;
    }

}

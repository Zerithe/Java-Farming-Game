/**
 * The Tools class is used to give each tool used a name, the amount of objectCoins needed to use the
 * tool, and the amount of experience gained every time the player successfully uses the tool.
 * @author De Vera
 * @author Gutierrez
 */
public class Tools {
    private String name;
    private int usageCost;
    private double expGain;
    /**
     * Creates the Tool object with its needed parameters.
     *
     *     @param name         given name of the tool.
     *     @param usageCost    the cost to use the tool.
     *     @param expGain      the experience gained from the tool.
     */
    public Tools(String name, int usageCost, double expGain){
        this.name = name;
        this.usageCost = usageCost;
        this.expGain = expGain;
    }
    /**
     * Method that returns the name of the tool.
     *
     *     @return tool name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Method that returns the needed objectCoins to use the tool.
     *
     *     @return tool cost
     */
    public int getUsageCost(){
        return this.usageCost;
    }
    /**
     * Method that returns the experience gained from using the object.
     *
     *     @return gained experience
     */
    public double getExpGain(){
        return this.expGain;
    }
}

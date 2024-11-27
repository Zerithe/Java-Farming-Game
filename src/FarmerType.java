/**
 * This class creates a Farmer type object which will be given to the player if opted in to upgrading towards a better
 * farmer type. The variables in farmer type will help in increasing or reducing variables to increase profit.
 * @author De Vera
 * @author Gutierrez
 */
public class FarmerType {
    private String nameType;
    private int lvlReq;
    private int bonusEarnings;
    private int seedCostReduction;
    private int waterBonusLimit;
    private int fertilizerBonusLimit;
    private int regFee;

    FarmerType(String nameType, int lvlReq, int bonusEarnings, int seedCostReduction, int waterBonusLimit, int fertilizerBonusLimit, int regFee){
        this.nameType = nameType;
        this.lvlReq = lvlReq;
        this.bonusEarnings = bonusEarnings;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusLimit = waterBonusLimit;
        this.fertilizerBonusLimit = fertilizerBonusLimit;
        this.regFee = regFee;
    }

    public String getNameType(){
        return nameType;
    }
    public int getLvlReq(){
        return lvlReq;
    }
    public int getBonusEarnings(){
        return bonusEarnings;
    }
    public int getSeedCostReduction(){
        return seedCostReduction;
    }
    public int getWaterBonusLimit(){
        return waterBonusLimit;
    }
    public int getFertilizerBonusLimit(){
        return fertilizerBonusLimit;
    }
    public int getRegFee(){
        return regFee;
    }
}

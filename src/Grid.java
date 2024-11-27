/**
 * The Grid class is used to create the main UI and will be able to run
 * via main function. This consists of all the panels and buttons that are
 * implemented with logic and functionality.
 * @author De Vera
 * @author Gutierrez
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Grid {
    JFrame frame = new JFrame();
    private static JButton[] buttons = new JButton[50];
    private JLabel num;
    private static String toNum;
    private static int getNum = 0;
    private JLabel stats;
    private JPanel options = new JPanel();
    private JPanel playerStats = new JPanel();
    private JComboBox<String> seedPicker;
    private JLabel pStats = new JLabel();
    private boolean isGameOver;
    private JPanel seedList = new JPanel();

     Grid(Tile[] tile, Player player) {
        frame.setSize(1100, 1000);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        options.setSize(500, 500);
        options.setLayout(new GridLayout(3, 1, 5, 5));
        playerStats.setSize(500, 500);
        playerStats.setLayout(new GridLayout(3, 1, 10, 10));
        seedList.setSize(500, 500);
        seedList.setLayout(new GridLayout(9, 9, 10, 10));
        JPanel grid = new JPanel();
        grid.setSize(500, 500);
        grid.setLayout(new GridLayout(10, 5, 10, 10));
        for(int i = 0; i < 50; i++){
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].setBackground(Color.decode("#E84420"));
        }
        for(int i = 0; i < 50; i++){
            grid.add(buttons[i]);
        }

        for(int i = 0; i < 50;i++){
            if(tile[i].getStatus().isRock()) {
                buttons[i].setBackground(Color.gray);
            }
        }
        tilePlowBtn(tile, player);
        seedPlantBtn(tile, player);
        seedLister(player);
        waterBtn(tile, player);
        fertilizerBtn(tile, player);
        mineRockBtn(tile, player);
        digBtn(tile, player);
        nextDayBtn(tile, player);
        HarvestBtn(tile, player);
        farmerUpgrade(player);
        frame.add(options);
        frame.add(grid);
        showStatus(tile);
        updateStats(player);
        frame.add(pStats);
        seedListPanel(player);
        frame.add(seedList);

         frame.setVisible(true);
    }
    public void  showStatus(Tile[] tile){
        JPanel status = new JPanel();
        status.setSize(250, 250);
        status.setLayout(new GridLayout(8, 1, 5, 5));
        for(int i = 0; i < 50; i++){
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toNum = ((JButton) e.getSource()).getText();
                    num.setText("currently selected Plot: " + toNum);
                    getNum = Integer.parseInt(toNum) - 1;


                }
            });
        }
        JButton showStats = new JButton("Show Stats");
        showStats.setPreferredSize(new Dimension(100, 40));
        showStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!tile[getNum].getStatus().isRock() && tile[getNum].getStatus().isOccupied() && !tile[getNum].getStatus().isWithered())
                        stats.setText("<html><body>" + "Seed placed: " + tile[getNum].getSeed().getName() + "<br>"
                                    + "Watered: " + tile[getNum].getStatus().isWatered()  + " (" + tile[getNum].getStatus().getWaterCount() + " " +"(min:"+ tile[getNum].getSeed().getWaterNeeds() + "))" + "<br>"
                                    + "Fertilized: " + tile[getNum].getStatus().isFertilized() + " (" + tile[getNum].getStatus().getFertilizeCount() + " " +"(min:"+ tile[getNum].getSeed().getFertilizerNeeds() + "))" + "<br>"
                                    + "Days before Harvest: " + tile[getNum].getStatus().getDaysLeft() + "</body></html>");
                    else if(tile[getNum].getStatus().isWithered())
                        stats.setText("Plant is Withered");
                    else if(tile[getNum].getStatus().isPlowed())
                        stats.setText("Plot ready for seeds.");
                    else if(tile[getNum].getStatus().isRock())
                        stats.setText("There's a rock.");
                    else
                        stats.setText("Plot Empty and not plowed.");
                    if(tile[getNum].getStatus().isReady()){
                        stats.setText(tile[getNum].getSeed().getName() + " is ready to be harvested!");
                    }
                }
                catch(NullPointerException noSeed) {
                    stats.setText("Nothing in Plot.");
                }

            }
        });
        num = new JLabel(" ");
        stats = new JLabel(" ");
        status.add(new JLabel());
        status.add(num);
        status.add(showStats);
        status.add(stats);
        this.frame.add(status);
    }

    public void seedLister(Player player){
         seedPicker = new JComboBox<>();
         for(int i = 0; i < 8;i++){
             seedPicker.addItem(player.getSeed(i).getName());
         }
         options.add(seedPicker);
    }

    public void tilePlowBtn(Tile[] tile, Player player){
        JButton tilePlow = new JButton("Plow");
        tilePlow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               boolean plowSuccess = player.plow(tile, getNum);
               if(plowSuccess){
                   tilePlowed(tile[getNum], getNum);
                   updateStats(player);
                   loseChecker(tile, player);
               }
               else{
                   JFrame fail = new JFrame("Plow Failed.");
                   fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   frame.setLocationRelativeTo(null);
                   frame.setVisible(true);
                   JOptionPane.showMessageDialog(fail, "Plowing Failed.", "Failed Action", JOptionPane.ERROR_MESSAGE);
               }
            }
        });
        options.add(tilePlow);
    }

    public void seedPlantBtn(Tile[] tile, Player player){
         JButton seedPlant = new JButton("Plant");
         seedPlant.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 boolean plantSuccess = player.plant(tile, getNum, String.valueOf(seedPicker.getSelectedItem()));
                 if(plantSuccess){
                     seedPlanted(tile[getNum], getNum);
                     updateStats(player);
                     loseChecker(tile, player);
                 }
                 else{
                     JFrame fail = new JFrame("Plant Failed.");
                     fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     frame.setLocationRelativeTo(null);
                     frame.setVisible(true);
                     JOptionPane.showMessageDialog(fail, "Seed Planting Failed.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         options.add(seedPlant);
    }

    public void waterBtn(Tile[] tile, Player player){
         JButton waterPlant = new JButton("Water");
         waterPlant.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 boolean waterSuccess = player.water(tile, getNum);
                 if(waterSuccess){
                     plotFed(tile[getNum], getNum);
                     updateStats(player);
                     loseChecker(tile, player);
                 }
                 else{
                     JFrame fail = new JFrame("Water Failed.");
                     fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     frame.setLocationRelativeTo(null);
                     frame.setVisible(true);
                     JOptionPane.showMessageDialog(fail, "Watering the Plant Failed.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
         options.add(waterPlant);
    }
    public void fertilizerBtn(Tile[] tile, Player player){
        JButton fertilizePlant = new JButton("Fertilize");
        fertilizePlant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fertilizeSuccess = player.fertilize(tile, getNum);
                if(fertilizeSuccess){
                    plotFed(tile[getNum], getNum);
                    updateStats(player);
                    loseChecker(tile, player);
                }
                else{
                    JFrame fail = new JFrame("Fertilize Failed.");
                    fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    JOptionPane.showMessageDialog(fail, "Fertilizing the Plant Failed.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        options.add(fertilizePlant);
    }

    public void mineRockBtn(Tile[] tile, Player player){
        JButton mineRock = new JButton("Mine Rock");
        mineRock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean mineSuccess = player.mineRock(tile, getNum);
                if(mineSuccess){
                    resetPlotColor(tile[getNum], getNum);
                    updateStats(player);
                    loseChecker(tile, player);
                }
                else{
                    JFrame fail = new JFrame("Mining Failed.");
                    fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    JOptionPane.showMessageDialog(fail, "Mining Failed.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        options.add(mineRock);
    }

    public void digBtn(Tile[] tile, Player player){
        JButton useShovel = new JButton("Dig Plot");
        useShovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean digSuccess = player.dig(tile, getNum);
                if(digSuccess){
                    resetPlotColor(tile[getNum], getNum);
                    updateStats(player);
                    loseChecker(tile, player);
                }
                else{
                    updateStats(player);
                }
            }
        });
        options.add(useShovel);
    }

    public void nextDayBtn(Tile[] tile, Player player){
         JButton nextDay = new JButton("Next Day");
         nextDay.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 for(int i = 0; i < 50; i++){
                     if((tile[i].getStatus().isOccupied() || tile[i].getStatus().isPlowed()) && !tile[i].getStatus().isWithered()){
                         seedPlanted(tile[i], i);
                     }
                 }
                 player.advanceDay(tile);
                 for(int i = 0; i < 50; i++){
                     if(tile[i].getStatus().isWithered()){
                         plantWithered(tile[i], i);
                     }
                     if(tile[i].getStatus().isReady()){
                         readyToHarvest(tile[i], i);
                     }
                 }
                 updateStats(player);
                 loseChecker(tile, player);
             }
         });
         options.add(nextDay);
    }

    public void HarvestBtn(Tile[] tile, Player player){
        JButton harvest = new JButton("Harvest");
        harvest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean harvestSuccess = player.harvest(tile, getNum);
                updateStats(player);
                if(harvestSuccess){
                    stats.setText("<html><body>" + "Harvested " + " " + tile[getNum].getSeed().getName() + "!" + "<br>"
                                    + "Amount: " + tile[getNum].getSeed().getProductNum() + "<br>" + "Earnings: " + tile[getNum].getFinalPrice()
                                    + "</body></html>");
                    tile[getNum].resetTile();
                    resetPlotColor(tile[getNum], getNum);
                }
                else{
                    JFrame fail = new JFrame("Harvesting Failed.");
                    fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    JOptionPane.showMessageDialog(fail, "Failed to Harvest.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        options.add(harvest);
    }

    public void farmerUpgrade(Player player){
         JButton fTypeUpgrade = new JButton("Upgrade");
         fTypeUpgrade.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(JOptionPane.showConfirmDialog(null, "Pay " + player.getCurrFType().getRegFee() + "? (Can Upgrade every 5 Levels; Max 15)",
             "Upgrade Window",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                     boolean canUpgrade = player.upgradeFarmer();
                     if(canUpgrade) {
                         updateStats(player);
                     }
                     else{
                         JFrame fail = new JFrame("Upgrading Failed");
                         fail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                         frame.setLocationRelativeTo(null);
                         frame.setVisible(true);
                         JOptionPane.showMessageDialog(fail, "Farmer does not meet requirements.", "Failed Action", JOptionPane.ERROR_MESSAGE);
                     }
                 }
             }
         });
         options.add(fTypeUpgrade);
    }

    public void seedListPanel(Player player){
         seedList = new JPanel();
         seedList.setSize(500, 500);
         seedList.setLayout(new GridLayout(9, 3, 10, 10));
         seedList.add(new JLabel("Seed Name"));
        seedList.add(new JLabel("Seed Price"));
         seedList.add(new JLabel("Turnip"));
        seedList.add(new JLabel("5"));
         seedList.add(new JLabel("Carrot"));
        seedList.add(new JLabel("10"));
         seedList.add(new JLabel("Potato"));
        seedList.add(new JLabel("20"));
         seedList.add(new JLabel("Rose"));
        seedList.add(new JLabel("5"));
         seedList.add(new JLabel("Tulips"));
        seedList.add(new JLabel("10"));
         seedList.add(new JLabel("Sunflower"));
        seedList.add(new JLabel("20"));
         seedList.add(new JLabel("Mango"));
        seedList.add(new JLabel("100"));
         seedList.add(new JLabel("Apple"));
        seedList.add(new JLabel("200"));
    }

    public void updateStats(Player player){
        pStats.setFont(new Font("Dialog", Font.BOLD, 16));
        pStats.setText("<html><body>" + "Level: " +  player.getLevel() + " (" + player.getExp() + "exp)" + "<br>"
                + "objectCoins: " + player.getObjectCoins() + "<br>" + "Farmer Type: " + player.getCurrFType().getNameType() + "<br>"
                + "Current Day: " + player.getCurrDay()  + "</body></html>");
    }

    public void tilePlowed(Tile tile, int plot){
        if(tile.getStatus().isPlowed()){
            buttons[plot].setBackground(Color.decode("#E8E820"));
        }
    }

    public void seedPlanted(Tile tile, int plot){
        if(tile.getStatus().isPlowed()){
            buttons[plot].setBackground(Color.decode("#CEEBB1"));
        }
    }

    public void plotFed(Tile tile, int plot){
         if(tile.getStatus().isWatered() && !tile.getStatus().isFertilized()){
             buttons[plot].setBackground((Color.decode("#7CC138")));
         }
         else if(!tile.getStatus().isWatered() && tile.getStatus().isFertilized()){
             buttons[plot].setBackground((Color.decode("#7CC138")));
         }
         else{
             buttons[plot].setBackground((Color.decode("#0DE17A")));
         }
    }

    public void readyToHarvest(Tile tile, int plot){
         if(tile.getStatus().isReady()){
             buttons[plot].setBackground(Color.decode("#D10EE1"));
         }
    }

    public void plantWithered(Tile tile, int plot){
        if(tile.getStatus().isWithered()){
            buttons[plot].setBackground(Color.decode("#A16212"));
        }
    }

    public void resetPlotColor(Tile tile, int plot){
        buttons[plot].setBackground(Color.decode("#E84420"));
    }

    public void loseChecker(Tile[] tile, Player player){
         boolean emptyFarm = false;
         boolean witheredFarm = false;
         int plantAmount = 0;
         int witherAmount = 0;
         for(int i = 0; i < 50; i++){
             if(tile[i].getStatus().isOccupied() && !tile[i].getStatus().isWithered()){
                 plantAmount++;
             }
             if(tile[i].getStatus().isWithered()){
                 witherAmount++;
             }
         }
         if(plantAmount == 0){
             emptyFarm = true;
         }
         if(witherAmount == 50){
             witheredFarm = true;
         }
         if(emptyFarm && player.getObjectCoins() < 5){
             isGameOver = true;
         }
         else if(witheredFarm){
             isGameOver = true;
         }
         else
             isGameOver = false;;

        if(isGameOver){
            JOptionPane.showMessageDialog(frame, "Game Over!");
            System.exit(0);
        }
    }

}

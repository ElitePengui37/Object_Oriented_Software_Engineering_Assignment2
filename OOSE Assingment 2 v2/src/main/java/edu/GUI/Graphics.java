package main.java.edu.gui;

import main.java.edu.observer.Observer;
import main.java.edu.factory.TownInterface;
import main.java.edu.factory.RailwayInterface;
import main.java.edu.state.RailwayController;
import edu.curtin.app.*;
import java.util.List;
import java.util.logging.Logger;

public class Graphics implements Observer
{
    private static final Logger log = Logger.getLogger(Graphics.class.getName()); // imports logger
    private List<RailwayController> railwayControllers; // this holds state of railways

    // Constructor to pass the railway controllers
    public Graphics(List<RailwayController> controllers) 
    {
        this.railwayControllers = controllers;
    }
    
    // method called when graphics object is first created
    public void createObject()
    {
        log.info("Graphics object has been created");
    }

    @Override
    public void update(List<TownInterface> towns, List<RailwayInterface> railways) // updates GUI every day
    {

        processGoodsTransport(towns); // handle transportation of goods


        displayRailConnectionCount(towns, railways); // calculate how many one and two way rails exist for each town
        System.out.println("\n\n");
    }


    public void displayRailConnectionCount(List<TownInterface> towns, List<RailwayInterface> railways) // calculate how many one and two way rails each town has
    {
        for(TownInterface town : towns)
        {
            Integer oneWayRail = 0;
            Integer twoWayRail = 0;

            for(RailwayInterface railway : railways)
            {
                String railInfo = railway.getRailInfo(); // retrieve rail info string for every railway and then count how any times the town shows up

                if(railInfo.contains(town.getName())) // if town name is found to match with currecnt town
                {
                    if(railInfo.endsWith("false")) // if raillway is one way
                    {
                        oneWayRail++;
                    }
                    else if(railInfo.endsWith("true")) // if railway is two way
                    {
                        twoWayRail++;
                    }
                }
            }

            System.out.println(town.getName() + "\tp:" + town.getPopulation() + "\trs:" + oneWayRail + "\trd:" + twoWayRail + "\tgs:" + town.getStockpile() + "  \tgt:" + town.getGoodsTransported()); // main stats

            town.resetGoodsTransported(); // reset all goods transported at the end of the day to prevent previous days goods from being counted
        }
    }

    public void processGoodsTransport(List<TownInterface> towns) // handle transportation of goods between towns
    {
        for(RailwayController controller : railwayControllers)
        {
            String railInfo = controller.getRailway().getRailInfo(); // Get the railway info
            boolean isBuilt = controller.isBuilt(); // Check if the railway is built

            if(isBuilt) // if railway is built otherwise skip as railways under construction are unusable
            {
                // parse railInfo data
                String[] railStats = railInfo.split(" ");
                String firstTownName = railStats[0];
                String secondTownName = railStats[1];
                boolean twoWay = Boolean.parseBoolean(railStats[2]);
                String logString;

                
                TownInterface town1 = findTownObj(towns, firstTownName); // find object with that town name
                TownInterface town2 = findTownObj(towns, secondTownName);


                if(twoWay) // handle bidirectional railways
                {
                    // Deduct up to 100 resources from both towns
                    Integer goodsToTransportFirst = Math.min(100, town1.getStockpile());
                    Integer goodsToTransportSecond = Math.min(100, town2.getStockpile());

                    
                    // log stockpile before deduction
                    logString = "\n\n\nBefore transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods.";
                    log.info(logString);
                    logString = "After transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods.";
                    log.info(logString);


                    town1.reduceStockpile(goodsToTransportFirst);
                    town2.reduceStockpile(goodsToTransportSecond);

                    town1.addGoodsTransported(goodsToTransportFirst);
                    town2.addGoodsTransported(goodsToTransportSecond);

                    // Dlog stockpile after deduction
                    logString = "After transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods." + "Goods transported is " + town1.getGoodsTransported();
                    log.info(logString);
                    logString = "After transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods." + "Goods transported is " + town2.getGoodsTransported();
                    log.info(logString);

                }
                else // next deal with one way railways
                {
                    // For one-way rail, check if the day is odd or even
                    Integer day = App.day;

                    if (day % 2 == 1) 
                    {
                        // Odd day - Transport from first town
                        Integer goodsToTransport = Math.min(100, town1.getStockpile());
                            
                        // log stockpile before deduction
                        logString = "\n\n\nBefore transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods.";
                        log.info(logString);

                        town1.reduceStockpile(goodsToTransport);
                        town1.addGoodsTransported(goodsToTransport);

                        // log stockpile after defuction
                        logString = "After transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods." + "Goods transported is " + town1.getGoodsTransported();
                        log.info(logString);

                        }
                    else
                    {
                        // Even day - Transport from second town
                        Integer goodsToTransport = Math.min(100, town2.getStockpile());

                        // log stockpile before deduction
                        logString = "\n\n\nBefore transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods.";
                        log.info(logString);

                        town2.reduceStockpile(goodsToTransport);
                        town2.addGoodsTransported(goodsToTransport);
                            
                        // log stockpile after deduction
                        logString = "After transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods." + "Goods transported is " + town2.getGoodsTransported();
                        log.info(logString);
                    }
                }    
            }
        }
    }

    private TownInterface findTownObj(List<TownInterface> towns, String townName) // retrive object on concrete town based on town name
    {
        for (TownInterface town : towns) 
        {
            if (town.getName().equals(townName)) 
            {
                return town;
            }
        }
        return null;
    }
}

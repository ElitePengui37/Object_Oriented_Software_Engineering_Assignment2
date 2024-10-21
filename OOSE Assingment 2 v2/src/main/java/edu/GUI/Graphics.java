package main.java.edu.GUI;

import main.java.edu.Observer.Observer;
import main.java.edu.Factory.TownInterface;
import main.java.edu.Factory.RailwayInterface;
import main.java.edu.State.RailwayController;
import edu.curtin.App.*;
import java.util.List;

public class Graphics implements Observer
{
    private List<RailwayController> railwayControllers; // this holds state of railways

        // Constructor to pass the railway controllers
    public Graphics(List<RailwayController> controllers) 
    {
        this.railwayControllers = controllers;
    }
    
    public void CreateObject()
    {
        //System.out.println("Graphics object has been created"); LOG THIS
    }

    @Override
    public void update(List<TownInterface> towns, List<RailwayInterface> railways) // still a very barebones implementation update it later
    {
        Integer day = App.day;


        // handle towns
        /*System.out.println("Updating GUI with the latest towns and railways:");
        
        System.out.println("Checking Towns Values: (GUI)");
        System.out.println("THE CURRENT DAY IS " + day);
        for (TownInterface town : towns) {
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
            System.out.println("RESOURCES: " + town.getStockpile());
        }
        System.out.println("\n\n");*/ // uncomment when finshed debugging
        

        // handle railways
        /*System.out.println("Checking Railways stats: (GUI)");
        for (RailwayInterface railway : railways) {
            System.out.println(railway.getRailInfo());
        }
        System.out.println("\n\n");*/ // uncomment when finished debugging


        // Check the state of all railways
        /*for (RailwayController controller : railwayControllers)
        {
            String railInfo = controller.getRailway().getRailInfo(); // Get the railway info
            boolean isBuilt = controller.isBuilt(); // Check if the railway is built
            System.out.println(railInfo + " - Status is built: " + isBuilt);
        }

        System.out.println("\n\n");*/

        processGoodsTransport(towns);


        displayRailConnectionCount(towns, railways);
        System.out.println("\n\n");
    }


    public void displayRailConnectionCount(List<TownInterface> towns, List<RailwayInterface> railways)
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

            System.out.println(town.getName() + "\tp:" + town.getPopulation() + "\trs:" + oneWayRail + "\trd:" + twoWayRail + "\tgs:" + town.getStockpile() + "\tgt:" + town.getGoodsTransported());

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

                
                TownInterface town1 = findTownObj(towns, firstTownName); // find object with that town name
                TownInterface town2 = findTownObj(towns, secondTownName);


                if(twoWay) // handle bidirectional railways
                {
                    // Deduct up to 100 resources from both towns
                    Integer goodsToTransportFirst = Math.min(100, town1.getStockpile());
                    Integer goodsToTransportSecond = Math.min(100, town2.getStockpile());

                    // Display stockpile before deduction       LOG THESE
                    //System.out.println("Before transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods.");
                    //System.out.println("Before transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods.");


                    town1.reduceStockpile(goodsToTransportFirst);
                    town2.reduceStockpile(goodsToTransportSecond);

                    town1.addGoodsTransported(goodsToTransportFirst);
                    town2.addGoodsTransported(goodsToTransportSecond);

                    // Display stockpile after deduction        LOG THESE
                    //System.out.println("After transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods." + "Goods transported is " + town1.getGoodsTransported());
                    //System.out.println("After transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods." + "Goods transported is " + town2.getGoodsTransported());

                }
                else // next deal with one way railways
                {
                    // For one-way rail, check if the day is odd or even
                    Integer day = App.day;

                    if (day % 2 != 0) 
                    {
                        // Odd day - Transport from first town
                        Integer goodsToTransport = Math.min(100, town1.getStockpile());
                            
                        // Display stockpile before deduction       LOG THIS
                        //System.out.println("Before transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods.");

                        town1.reduceStockpile(goodsToTransport);
                        town1.addGoodsTransported(goodsToTransport);

                        // display stockpile after defuction        LOG THIS
                        //System.out.println("After transporting from " + town1.getName() + ": " + town1.getStockpile() + " goods." + "Goods transported is " + town1.getGoodsTransported());

                        }
                    else
                    {
                        // Even day - Transport from second town
                        Integer goodsToTransport = Math.min(100, town2.getStockpile());

                        // Display stockpile before deduction   LOG THIS
                        //System.out.println("Before transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods.");

                        town2.reduceStockpile(goodsToTransport);
                        town2.addGoodsTransported(goodsToTransport);
                            
                        // Display stockpile after deduction    LOG THIS
                        //System.out.println("After transporting from " + town2.getName() + ": " + town2.getStockpile() + " goods." + "Goods transported is " + town2.getGoodsTransported());
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

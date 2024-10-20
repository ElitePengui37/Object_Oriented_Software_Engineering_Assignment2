package main.java.edu.GUI;

import main.java.edu.Observer.Observer;
import main.java.edu.Factory.TownInterface;
import main.java.edu.Factory.RailwayInterface;
import java.util.List;

public class Graphics implements Observer {
    
    public void CreateObject() {
        System.out.println("Graphics object has been created");
    }

    @Override
    public void update(List<TownInterface> towns, List<RailwayInterface> railways) // still a very barebones implementation update it later
    {
        // handle towns
        System.out.println("Updating GUI with the latest towns and railways:");
        
        System.out.println("Checking Towns Values: (GUI)");
        for (TownInterface town : towns) {
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
            System.out.println("RESOURCES: " + town.getStockpile());
        }
        System.out.println("\n\n");
        

        // handle railways
        System.out.println("Checking Railways stats: (GUI)");
        for (RailwayInterface railway : railways) {
            System.out.println(railway.getRailInfo());
        }
        System.out.println("\n\n");


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

            System.out.println(town.getName() + " p:" + town.getPopulation() + " rs:" + oneWayRail + " rd:" + twoWayRail + " gs:" + town.getStockpile());
        }
    }
}

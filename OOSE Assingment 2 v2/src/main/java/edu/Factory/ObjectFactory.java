package main.java.edu.Factory;

import java.util.List;
import java.util.ArrayList;

public class ObjectFactory
{
    private final List<String> messages;   // msgList
    private final List<TownInterface> towns = new ArrayList<>();   // Store towns
    private final List<RailwayInterface> railways = new ArrayList<>(); // Store railways

    // Constructor for dependency injection
    public ObjectFactory(List<String> messages) {
        this.messages = messages;
    }

    // Method to process messages
    public void processMessages() {
        for (String message : messages)
        {
            String[] parts = message.split(" ");
            String action = parts[0];

            if(action.equals("town-founding"))
            {
                    TownInterface newTown = new ConcreteTownObject();
                    towns.add(newTown);
                    System.out.println("New town founded!");
            }
            else if(action.equals("town-population"))
            {
                    if (parts.length > 1 && !towns.isEmpty())
                    {
                        Integer population = Integer.parseInt(parts[1]);
                        TownInterface lastTown = towns.get(towns.size() - 1);
                        lastTown.setUpdatePopulation(population);
                        System.out.println("Town population updated to: " + population);
                    }
                    else 
                    {
                        System.out.println("No town found or invalid population input.");
                    }
            }
            else if(action.equals("railway-construction")) // railway cases
            {
                    RailwayInterface newRail = new ConcreteRailObject();
                    railways.add(newRail);
                    System.out.println("New railway constructed!");
            }
            else if(action.equals("railway-duplication"))
            {
                    if (!railways.isEmpty())
                    {
                        RailwayInterface lastRail = railways.get(railways.size() - 1);
                        lastRail.setRailwayDuplication(); // testing duplication use this in later by state pattern
                        System.out.println("Railway modified to two-way!");
                    }
                    else
                    {
                        System.out.println("No railway found to duplicate.");
                    }
            }
        }
    }
}

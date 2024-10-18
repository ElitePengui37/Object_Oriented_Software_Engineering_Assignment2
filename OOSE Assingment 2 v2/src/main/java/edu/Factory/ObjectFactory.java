package main.java.edu.Factory;

import java.util.List;
import java.util.ArrayList;

public class ObjectFactory
{
    private List<String> messages;   // msgList
    private final List<TownInterface> towns = new ArrayList<>();   // Store towns
    private final List<RailwayInterface> railways = new ArrayList<>(); // Store railways

    // Constructor
    public ObjectFactory() {}

    public void setMessages(List<String> messages) // message list is set for every timestep
    {
        this.messages = messages;
    }

    // Method to process messages
    public void processMessages() {
        for (String message : messages)
        {
            String[] parts = message.split(" ");
            String action = parts[0];

            if(action.equals("town-founding") && parts.length > 2) // parts more then 2 prevents errors
            {
                String townName = parts[1];
                Integer townPopulation = Integer.parseInt(parts[2]);

                TownInterface newTown = new ConcreteTownObject(townName, townPopulation);
                towns.add(newTown);
                System.out.println("New town founded!");

            }
            else if(action.equals("town-population") && parts.length > 2) // prevent short bugged inputs from making it through
            {
                String townName = parts[1];
                Integer townPopulation = Integer.parseInt(parts[2]); // add exception handling for this part and ignore the value
                boolean found = false;
                
                //System.out.println("DEBUG CHECKING TOWN POPULATION FOR TOWN " + townName + " POPULATION " + townPopulation); // change this to log.info later
                for(TownInterface town : towns) // find town and update population
                {
                    if (town instanceof ConcreteTownObject && ((ConcreteTownObject)town).getName().equals(townName)) // update town population only if town is found (some town names may be error names)
                    {
                        town.setUpdatePopulation(townPopulation);
                        found = true; // town has been found and updated
                    }
                }

                if (!found)
                {
                    System.out.println("Unable to update population " + townName + " not found.");
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

            // debugging block use this to see town statistics in town list
            /*System.out.println("\n\nDebug town list: ");
            for(TownInterface town : towns)
            {
                if (town instanceof ConcreteTownObject) {
                    ConcreteTownObject concreteTown = (ConcreteTownObject) town;
                    System.out.println("Town: " + concreteTown.getName() + ", Population: " + concreteTown.population);
                }
            }*/
        }
    }
}

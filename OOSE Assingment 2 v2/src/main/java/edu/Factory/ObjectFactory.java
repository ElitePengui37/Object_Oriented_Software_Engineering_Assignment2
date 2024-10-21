package main.java.edu.Factory;


import main.java.edu.State.RailwayController;
import main.java.edu.State.BuildingState;
import java.util.List;
import java.util.ArrayList;

public class ObjectFactory
{
    private List<String> messages;   // msgList
    private final List<TownInterface> towns = new ArrayList<>();   // Store towns
    private final List<RailwayInterface> railways = new ArrayList<>(); // Store railways
    private final List<RailwayController> controllers = new ArrayList<>(); // store railway controller classes from state pattern to 

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

            if(action.equals("town-founding") && parts.length == 3) // parts more then 2 prevents errors
            {
                String townName = parts[1];

                // exception handling to prevent non integer values getting passed into town population
                try
                {
                    Integer townPopulation = Integer.parseInt(parts[2]);

                    TownInterface newTown = new ConcreteTownObject(townName, townPopulation);
                    towns.add(newTown);
                    //System.out.println("New town founded!"); // LOG THIS
                } catch (NumberFormatException e)
                {
                    // LOG HERE ABOUT INVALID number
                }


            }
            else if(action.equals("town-population") && parts.length == 3) // prevent short bugged inputs from making it through
            {
                String townName = parts[1];

                try
                {
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
                } catch (NumberFormatException e)
                {
                    // LOG HERE ABOUT INVALID NUMBER
                }
            }
            else if(action.equals("railway-construction") && parts.length == 3) // railway cases part 0 is railway-construction, part 1 and 2 are townnames
            {
                String town1 = parts[1];
                String town2 = parts[2];

                RailwayInterface newRail = new ConcreteRailObject(town1, town2); // two way is set to false by default
                railways.add(newRail);
                
                // Add a new RailwayController for this railway
                RailwayController newController = new RailwayController(newRail);
                controllers.add(newController);
            }
            else if(action.equals("railway-duplication") && parts.length == 3) 
            {
                String town1 = parts[1];
                String town2 = parts[2];
                
                if (!railways.isEmpty())
                {
                    RailwayInterface lastRail = railways.get(railways.size() - 1);
                    lastRail.setRailwayDuplication(); // testing duplication use this in later by state pattern


                    RailwayController lastController = controllers.get(controllers.size() - 1);  // Reset to "Building" state for 5 more days (doesnt matter if rail is still being built)
                    lastController.updateTwoWay();
                }
                else
                {
                    System.out.println("No railway found to duplicate.");
                }
            }

            // Display all railways after processing the messages used for debugging
            //System.out.println("Current list of railways:");
            /*for (RailwayInterface rail : railways)
            {
                if (rail instanceof ConcreteRailObject)
                {
                    System.out.println(((ConcreteRailObject) rail).getRailInfo());
                }
            }*/

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

        // Process railway state updates (build progress)
        for (RailwayController controller : controllers)
        {
            controller.startBuilding();  // Update build state
        }

        // Produce resources for all towns
        for(TownInterface town : towns)
        {
            town.setStockpile();
        }
    }

    


    public List<TownInterface> getTowns()
    {
        return towns; // Return the current list of towns for observer pattern
    }

    public List<RailwayInterface> getRailways()
    {
        return railways; // Return the current list of railways for observer pattern
    }

    public List<RailwayController> getRailwayControllers()
    {
        return controllers;
    }
}

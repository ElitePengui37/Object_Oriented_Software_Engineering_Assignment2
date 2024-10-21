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
    private final List<RailwayController> controllers = new ArrayList<>(); // store railway controller classes from state pattern to see the state of railway

    // empty constructor as previous version had issues with factories duplicating and leading to issues (msgList is injected later on)
    public ObjectFactory() {}

    public void setMessages(List<String> messages) // message list is set for every timestep
    {
        this.messages = messages;
    }

    // processes messages from input and creates and updates objects accordingly
    public void processMessages()
    {
        for (String message : messages)
        {
            String[] parts = message.split(" ");
            String action = parts[0];


            // process town object creations
            if(action.equals("town-founding") && parts.length == 3) // parts == 3 prevents errors as some inputs may have error strings with extra parts
            {
                String townName = parts[1];

                // exception handling to prevent non integer values getting passed into town population
                try
                {
                    Integer townPopulation = Integer.parseInt(parts[2]);

                    TownInterface newTown = new ConcreteTownObject(townName, townPopulation); // create town object
                    towns.add(newTown);
                    //System.out.println("New town founded!"); // LOG THIS
                } catch (NumberFormatException e)
                {
                    // LOG HERE ABOUT INVALID number
                }


            }
            else if(action.equals("town-population") && parts.length == 3) // prevent short or long error inputs from making it through
            {
                String townName = parts[1];

                // exception handling prevents invalid integers from being added to town
                try
                {
                    Integer townPopulation = Integer.parseInt(parts[2]);
                    
                    //System.out.println("DEBUG CHECKING TOWN POPULATION FOR TOWN " + townName + " POPULATION " + townPopulation); LOG THIS
                    for(TownInterface town : towns) // find town and update population
                    {
                        if (town instanceof ConcreteTownObject && ((ConcreteTownObject)town).getName().equals(townName)) // update town population only if town is found (some town names may be error names)
                        {
                            town.setUpdatePopulation(townPopulation);
                        }
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
                
                // Add a new RailwayController for this railway to keep track of state
                RailwayController newController = new RailwayController(newRail);
                controllers.add(newController);
            }
            else if(action.equals("railway-duplication") && parts.length == 3) // handle the modification of rail object when made 2 way
            {
                String town1 = parts[1];
                String town2 = parts[2];
                
                if (!railways.isEmpty()) // prevents railway duplication if no railways exist yet
                {
                    RailwayInterface lastRail = railways.get(railways.size() - 1);
                    lastRail.setRailwayDuplication(); // railway is set to 2 way


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
                    System.out.println(((ConcreteRailObject) rail).getRailInfo());      LOG THIS
                }
            }*/

            // debugging block use this to see town statistics in town list
            /*System.out.println("\n\nDebug town list: ");
            for(TownInterface town : towns)
            {
                if (town instanceof ConcreteTownObject) {
                    ConcreteTownObject concreteTown = (ConcreteTownObject) town;
                    System.out.println("Town: " + concreteTown.getName() + ", Population: " + concreteTown.population);     LOG THIS
                }
            }*/
        }

        // Process railway state updates at the end of the day (build progress)
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

    


    public List<TownInterface> getTowns() // Return the current list of towns for observer pattern
    {
        return towns;
    }

    public List<RailwayInterface> getRailways() // Return the current list of railways for observer pattern
    {
        return railways;
    }

    public List<RailwayController> getRailwayControllers() // return railway states for observer pattern
    {
        return controllers;
    }
}

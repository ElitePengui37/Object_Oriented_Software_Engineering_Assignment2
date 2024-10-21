package main.java.edu.FileIO;

import main.java.edu.Observer.Observer;
import main.java.edu.Factory.TownInterface;
import main.java.edu.Factory.RailwayInterface;
import main.java.edu.State.RailwayController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileProcessor implements Observer
{
    private List<RailwayController> railwayControllers; // this holds state of railways

    // Constructor to pass the railway controllers
    public FileProcessor(List<RailwayController> controllers) 
    {
        this.railwayControllers = controllers;
    }
    
    public void CreateFileObject()
    {
        //System.out.println("File object created"); //LOG THIS
    }

    @Override
    public void update(List<TownInterface> towns, List<RailwayInterface> railways) // barebones implementation make it better later
    {
        String fileName = "simoutput.dot";

        // Try with resources
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {

            writer.write("graph Towns {\n"); // boilerplate strings

            for (TownInterface town : towns) // display all town names
            {
                writer.write("\t" + town.getName() + "\n");
            }

            writer.newLine(); // empty line between towns and rails

            
            for (RailwayController controller : railwayControllers) // writing rail info
            {
                String railCheck = controller.getRailway().getRailInfo(); // Get the railway info
                String[] railStats = railCheck.split(" ");
                String firstTown = railStats[0];
                String secondTown = railStats[1];
                boolean twoWay = Boolean.parseBoolean(railStats[2]);
                boolean isBuilt = controller.isBuilt(); // Check if the railway is built

                if (twoWay) // write to file based on given syntax
                {
                    if (isBuilt) 
                    {
                        writer.write("\t" + firstTown + " -- " + secondTown + " [color=\"black:black\"]\n"); // dual track complete
                    } 
                    else 
                    {
                        writer.write("\t" + firstTown + " -- " + secondTown + " [style=\"dashed\",color=\"black:black\"]\n"); // dual track under construction
                    }
                } 
                else 
                {
                    if (isBuilt) 
                    {
                        writer.write("\t" + firstTown + " -- " + secondTown + "\n"); // single track complete
                    } 
                    else 
                    {
                        writer.write("\t" + firstTown + " -- " + secondTown + " [style=\"dashed\"]\n"); // single track under construction
                    }
                }
            }

            writer.write("}");
        } 
        catch (IOException e) 
        {
            System.out.println("An error has occured in file writing: " + e);
            // LOG ERROR HEREs
        }

       /* System.out.println("Updating file with the latest towns and railways:"); LOG ALL OF THiS
        
        // Implement file writing logic here later
        System.out.println("Checking Towns statss: (FILE)");
        for (TownInterface town : towns) {
            // Write town data to file
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
            System.out.println("RESOURCES : " + town.getStockpile());
        }
        System.out.println("\n\n");
        
        System.out.println("Checking railsways stats: (FILE)"); LOG ALL OF THIS
        for (RailwayInterface railway : railways) {
            // Write railway data to file
            System.out.println(railway.getRailInfo());
        }
        System.out.println("\n\n");*/ // uncomment when finised debugging
    }
}

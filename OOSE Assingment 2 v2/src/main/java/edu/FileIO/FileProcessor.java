package main.java.edu.FileIO;

import main.java.edu.Observer.Observer;
import main.java.edu.Factory.TownInterface;
import main.java.edu.Factory.RailwayInterface;
import java.util.List;

public class FileProcessor implements Observer {
    
    public void CreateFileObject() {
        System.out.println("File object created");
    }

    @Override
    public void update(List<TownInterface> towns, List<RailwayInterface> railways) // barebones implementation make it better later
    {
        System.out.println("Updating file with the latest towns and railways:");
        
        // Implement file writing logic here later
        System.out.println("Checking Towns statss: (FILE)");
        for (TownInterface town : towns) {
            // Write town data to file
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
            System.out.println("RESOURCES : " + town.getStockpile());
        }
        System.out.println("\n\n");
        
        System.out.println("Checking railsways stats: (FILE)");
        for (RailwayInterface railway : railways) {
            // Write railway data to file
            System.out.println(railway.getRailInfo());
        }
        System.out.println("\n\n");
    }
}

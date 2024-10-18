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
        
        // Implement file writing logic here
        System.out.println("Towns:");
        for (TownInterface town : towns) {
            // Write town data to file
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
        }
        
        System.out.println("Railways:");
        for (RailwayInterface railway : railways) {
            // Write railway data to file
            System.out.println(railway.getRailInfo());
        }
    }
}

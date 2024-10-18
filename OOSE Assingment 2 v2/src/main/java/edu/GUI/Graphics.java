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
        System.out.println("Updating GUI with the latest towns and railways:");
        
        System.out.println("Towns:");
        for (TownInterface town : towns) {
            System.out.println(town.getName() + " - Population: " + town.getPopulation());
        }
        
        System.out.println("Railways:");
        for (RailwayInterface railway : railways) {
            System.out.println(railway.getRailInfo());
        }
    }
}

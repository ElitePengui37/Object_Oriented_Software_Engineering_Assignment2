package main.java.edu.Factory;

public class ConcreteTownObject implements TownInterface
{
    public Integer population;  // Public variable
    public String name;

    public ConcreteTownObject(String name, Integer population) { 
        this.population = population;
        this.name = name;
    }

    @Override
    public void setUpdatePopulation(Integer population) {
        this.population = population;
        System.out.println("Population of town " + name + " set to: " + population);
    }

    public String getName()
    {
        return name;
    }

}
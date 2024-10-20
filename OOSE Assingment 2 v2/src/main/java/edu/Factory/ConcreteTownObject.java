package main.java.edu.Factory;

public class ConcreteTownObject implements TownInterface
{
    public Integer population;  // Public variable
    public String name;
    private Integer stockpile = 0; // 0 resources by default

    public ConcreteTownObject(String name, Integer population) { 
        this.population = population;
        this.name = name;
    }

    @Override
    public void setUpdatePopulation(Integer population) {
        this.population = population;
        System.out.println("Population of town " + name + " set to: " + population);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Integer getPopulation()
    {
        return population;
    }

    @Override
    public Integer getStockpile()
    {
        return stockpile;
    }

    @Override
    public void setStockpile()
    {
        stockpile += population; // produce 1 resource per 1 population per 1 day
    }

}
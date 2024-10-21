package main.java.edu.Factory;

public class ConcreteTownObject implements TownInterface
{
    public Integer population;  // Public variable
    public String name;
    private Integer stockpile = 0; // 0 resources by default
    private Integer goodsTransported = 0;

    public ConcreteTownObject(String name, Integer population) { 
        this.population = population;
        this.name = name;
    }

    @Override
    public void setUpdatePopulation(Integer population) {
        this.population = population;
        //System.out.println("Population of town " + name + " set to: " + population); LOG THIS
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

    @Override
    public void reduceStockpile(Integer reduction)
    {
        this.stockpile -= reduction;
    }

    @Override
    public void addGoodsTransported(Integer goods) 
    {
        goodsTransported += goods;
    }

    @Override
    public void resetGoodsTransported() // resets gt to 0 at the end of each day to prevent gt from maving into next day
    {
        this.goodsTransported = 0;
    }

    @Override
    public Integer getGoodsTransported() 
    {
        return goodsTransported;
    }

}
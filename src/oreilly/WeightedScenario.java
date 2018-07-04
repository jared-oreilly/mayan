package oreilly;

public class WeightedScenario
{
    //only use for HashSet
    private int weight;
    private Scenario scenario;

    public WeightedScenario(int weight, Scenario scenario)
    {
        this.weight = weight;
        this.scenario = scenario;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Scenario getScenario()
    {
        return scenario;
    }

    public void setScenario(Scenario scenario)
    {
        this.scenario = scenario;
    }
    
    
}

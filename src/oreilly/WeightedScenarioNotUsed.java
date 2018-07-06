package oreilly;

public class WeightedScenarioNotUsed
{
    //only use for HashSet
    private int weight;
    private Scenario scenario;

    public WeightedScenarioNotUsed(int weight, Scenario scenario)
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

package oreilly;

public class Scenario
{
    private String name;
    private int weight;
    private String flow;
    private String quick;

    public Scenario(String name)
    {
        this.name = name;
        this.weight = 1;
        this.flow = "";
        this.quick = "";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public void incrementWeight()
    {
        weight++;
    }
    
    public String getFlow()
    {
        return flow;
    }

    public void setFlow(String body)
    {
        this.flow = body;
    }

    public String getQuick()
    {
        return quick.substring(0, quick.length()-1);
    }

    public void setQuick(String quick)
    {
        this.quick = quick;
    }
    
    public void appendFlow(String text)
    {
        flow += text;
    }
    
    public void appendQuick(String text)
    {
        quick += text;
    }
    
    public String getArtilleryRepresentation()
    {
        String s = "  - name: '" + getQuick() + "'\n";
        s += "    weight: " + weight + "\n";
        s += "    flow:\n";
        s += "      - log: '" + getQuick() + "'\n";
        s += flow + "\n";
        return s;
    }
    
    @Override
    public boolean equals(Object other)
    {
        return getQuick().equals(((Scenario) other).getQuick());
    }
    
    @Override
    public String toString()
    {
        String s = "name: '" + name + "'\n";
        s += "weight: " + weight + "\n";
        s += "quick: " + getQuick() + "\n";
        //s += "flow: " + flow + "\n";
        return s;
    }
    
    
}

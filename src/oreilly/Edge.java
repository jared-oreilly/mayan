/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

/**
 *
 * @author jared.oreilly
 */
public class Edge
{
    private int id;
    private String title;
    private Node start;
    private Node end;
    private double prob;

    public Edge(int id, String title, Node start, Node end, double prob)
    {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.prob = prob;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Node getStart()
    {
        return start;
    }

    public void setStart(Node start)
    {
        this.start = start;
    }

    public Node getEnd()
    {
        return end;
    }

    public void setEnd(Node end)
    {
        this.end = end;
    }

    public double getProb()
    {
        return prob;
    }

    public void setProb(double prob)
    {
        this.prob = prob;
    }

    @Override
    public String toString()
    {
        String b = "";
        b += id + ": '" + title + "' " + prob + " = ";
        b += start.getId() + ":" + start.getTitle() + " -> " + end.getId() + ":" + end.getTitle();    
        return b;
    }
    
    public String exportEdge()
    {
        String b = "{";
        b += "\"id\": \"" + id + "\", "; 
        b += "\"title\": \"" + title + "\", "; 
        b += "\"startID\": " + start.getId() + ", "; 
        b += "\"endID\": " + end.getId() + ", "; 
        b += "\"prob\": " + prob + "}"; 
        return b;
    }
    
}

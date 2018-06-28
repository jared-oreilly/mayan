package oreilly;

import java.io.*;
import java.util.*;

/**
 *
 * @author jared.oreilly
 */
public class Manager
{

    private String baseUrl;
    private ArrayList<Node> nodes;
    private int numNodes;
    private ArrayList<Edge> edges;
    private int numEdges;

    public Manager(String baseUrl)
    {
        this.baseUrl = baseUrl;
        nodes = new ArrayList<Node>();
        numNodes = 0;
        edges = new ArrayList<Edge>();
        numEdges = 0;
    }

    private Node startNode()
    {
        if (nodes.isEmpty())
        {
            return null;
        } else
        {
            return nodes.get(0);
        }
    }

    public int addNode(String title, String url, String type, String cookie)
    {
        Node n = new Node(numNodes, title, url, type, cookie);
        nodes.add(n);
        numNodes++;
        return numNodes - 1;
    }

    public int addNode(String title, String url, String type, String cookie, String json, String form)
    {
        Node n = new Node(numNodes, title, url, type, cookie, json, form);
        nodes.add(n);
        numNodes++;
        return numNodes - 1;
    }

    public int addEdge(String title, int startID, int endID, double prob)
    {
        Edge e = new Edge(numEdges, title, nodes.get(startID), nodes.get(endID), prob);
        edges.add(e);
        numEdges++;
        Node start = nodes.get(startID);
        start.addEdge(e);
        return numEdges - 1;
    }

    @Override
    public String toString()
    {
        String b = "";
        b += baseUrl + "\n\n";
        b += "NODES\t-> " + numNodes + " <-\n";
        for (int i = 0; i < numNodes; i++)
        {
            b += nodes.get(i) + "\n";
        }
        b += "\nEDGES\t-> " + numEdges + " <-\n";
        for (int i = 0; i < numEdges; i++)
        {
            b += edges.get(i) + "\n";
        }
        b += "----------------------";
        return b;
    }

    public Node getNode(int id)
    {
        return nodes.get(id);
    }

    public Edge getEdge(int id)
    {
        return edges.get(id);
    }

    //piece config and scenarios together - needs work
    public String mayanArtillery()
    {
        String[] names =
        {
            "Test1", "Test2"
        };
        int[] weights =
        {
            1, 1
        };
        String ma = "";
        ma += generateConfig();
        ma += generateScenarios(names, weights);

        try
        {
            String filema = ma.substring(0, ma.length()-1);
            String nl = System.getProperty("line.separator");
            filema = filema.replace("\n", nl);

            PrintWriter writer = new PrintWriter("ma.txt", "UTF-8");
            writer.print(filema);
            writer.close();
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }

        return ma;
    }

    //generate the config part
    public String generateConfig()
    {
        String c = "config:\n";
        c += "  target: '" + baseUrl + "'\n";
        c += "  phases:\n";
        c += "    - duration: 1\n      arrivalRate: 1\n    - duration: 1\n      arrivalRate: 1\n    - duration: 2\n      arrivalRate: 1\n";
        return c;
    }

    //generate all scenarios - needs work
    public String generateScenarios(String[] names, int[] weights)
    {
        String s = "scenarios:\n";
        for (int i = 0; i < names.length; i++)
        {
            s += generateScenario(names[i], weights[i]);
        }
        return s;
    }

    //generate a specific scenario - pretty good
    public String generateScenario(String name, int weight)
    {
        String s = "  - name: '" + name + "'\n";
        s += "    weight: " + weight + "\n";
        s += "    flow:\n";
        s += "      - log: '" + name + "'\n";
        s += generateFlowSteps();
        return s;
    }

    //generate the flow steps in a scenario - needs work
    public String generateFlowSteps()
    {
        String fs = "";
        Node node = startNode();
        while (node != null)
        {
            fs += node.generateFlowStep();
            node = node.takeRandomPath();
        }
        return fs;
    }

}

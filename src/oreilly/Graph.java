package oreilly;

import java.io.*;
import java.util.*;
import org.json.simple.parser.*;
import org.json.simple.*;

public class Graph
{

    private String baseUrl;
    private ArrayList<Node> nodes;
    private int numNodes;
    private ArrayList<Edge> edges;
    private int numEdges;
    private ArrayList<Integer> durArr;
    private ArrayList<Integer> arrArr;
    private int numScenarios = 5;

    public Graph(String baseUrl)
    {
        this.baseUrl = baseUrl;
        nodes = new ArrayList<Node>();
        numNodes = 0;
        edges = new ArrayList<Edge>();
        numEdges = 0;
        durArr = new ArrayList<Integer>();
        arrArr = new ArrayList<Integer>();
    }

    public int getNumScenarios()
    {
        return numScenarios;
    }

    public void setNumScenarios(int numScenarios)
    {
        this.numScenarios = numScenarios;
    }

    private void addDur(int i)
    {
        durArr.add((Integer) i);
    }

    private void addArr(int i)
    {
        arrArr.add((Integer) i);
    }

    public void addPhase(int d, int a)
    {
        addDur(d);
        addArr(a);
    }

    public void deletePhase(int id)
    {
        durArr.remove(id);
        arrArr.remove(id);
    }

    public int getNumPhases()
    {
        return durArr.size();
    }

    public int getLastDur()
    {
        return durArr.get(durArr.size() - 1);
    }

    public int getLastArr()
    {
        return arrArr.get(arrArr.size() - 1);
    }

    public String getPhases()
    {
        String b = "";
        for (int i = 0; i < durArr.size(); i++)
        {
            b += "Phase " + i + ": duration = " + durArr.get(i) + ", arrivalRate = " + arrArr.get(i) + "/s\n";
        }
        return b;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
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

    public String printNodes()
    {
        String b = "";
        b += "NODES\t-> " + numNodes + " <-\n";
        for (int i = 0; i < numNodes; i++)
        {
            b += nodes.get(i) + "\n";
        }
        b += "----------------------";
        return b;
    }

    public String printEdges()
    {
        String b = "";
        b += "EDGES\t-> " + numEdges + " <-\n";
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

    public String mayanArtillery(String filename)
    {
        String[] names = new String[numScenarios];
        int[] weights = new int[numScenarios];

        for (int i = 0; i < numScenarios; i++)
        {
            names[i] = "Test" + i;
            weights[i] = 1;
        }

        String ma = "";
        ma += generateConfig();
        ma += generateScenarios(names, weights);

        try
        {
            String filema = ma.substring(0, ma.length() - 1);
            String nl = System.getProperty("line.separator");
            filema = filema.replace("\n", nl);

            new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).mkdirs();
            PrintWriter writer = new PrintWriter("gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + filename, "UTF-8");
            writer.print(filema);
            writer.close();
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }

        return ma;
    }

    public void runArtillery(String ma, String filename)
    {
        try
        {
            Runtime rt = Runtime.getRuntime();

            new File("gen/runs/" + filename.substring(0, filename.indexOf("."))).mkdirs();
            //System.out.println("cmd /c artillery run gen/artillery/" + filename + " > gen/runs/" + filename);
            Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + filename + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + filename);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = input.readLine()) != null)
            {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();

        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public String generateConfig()
    {
        String c = "config:\n";
        c += "  target: '" + baseUrl + "'\n";
        c += "  phases:\n";
        for (int i = 0; i < durArr.size(); i++)
        {
            c += "    - duration: " + durArr.get(i) + "\n      arrivalRate: " + arrArr.get(i) + "\n";
        }
        return c;
    }

    public String generateScenarios(String[] names, int[] weights)
    {
        String s = "scenarios:\n";
        for (int i = 0; i < names.length; i++)
        {
            s += generateScenario(names[i], weights[i]);
        }
        return s;
    }

    public String generateScenario(String name, int weight)
    {
        String s = "  - name: '" + name + "'\n";
        s += "    weight: " + weight + "\n";
        s += "    flow:\n";
        s += "      - log: '" + name + "'\n";
        s += generateFlowSteps();
        return s;
    }

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

    public String exportGraph(String filename)
    {
        String b = "{";
        b += "\"baseUrl\": \"" + baseUrl + "\", ";
        b += "\"phases\": [";
        if (durArr.isEmpty())
        {
            b += "{\"duration\": " + 1 + ", \"arrivalRate\": " + 1 + "}, ";
        }
        for (int i = 0; i < durArr.size(); i++)
        {
            b += "{\"duration\": " + durArr.get(i) + ", \"arrivalRate\": " + arrArr.get(i) + "}, ";
        }
        b = b.substring(0, b.length() - 2) + "], ";
        b += "\"numScenarios\": \"" + numScenarios + "\", ";
        b += "\"numNodes\": \"" + numNodes + "\", ";
        b += "\"nodes\": [";
        for (int i = 0; i < numNodes; i++)
        {
            b += nodes.get(i).exportNode() + ", ";
        }
        b = b.substring(0, b.length() - 2) + "], ";
        b += "\"numEdges\": \"" + numEdges + "\", ";
        b += "\"edges\": [";
        for (int i = 0; i < numEdges; i++)
        {
            b += edges.get(i).exportEdge() + ", ";
        }
        b = b.substring(0, b.length() - 2) + "]}";

        try
        {
            String fileb = b;
            String nl = System.getProperty("line.separator");
            fileb = fileb.replace("\n", nl);

            PrintWriter writer = new PrintWriter("gen/graphs/" + filename, "UTF-8");
            writer.print(fileb);
            writer.close();
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }

        return b;
    }

    public void importGraph(String filename) throws IOException
    {
        try
        {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("gen/graphs/" + filename));
            JSONObject jsonObject = (JSONObject) obj;

            baseUrl = (String) jsonObject.get("baseUrl");
            numNodes = 0;
            numEdges = 0;

            JSONArray phases = (JSONArray) jsonObject.get("phases");
            for (int i = 0; i < phases.size(); i++)
            {
                JSONObject cur = (JSONObject) phases.get(i);
                int d = Integer.parseInt(cur.get("duration") + "");
                int a = Integer.parseInt(cur.get("arrivalRate") + "");
                addPhase(d, a);
            }

            numScenarios = Integer.parseInt(jsonObject.get("numScenarios") + "");

            JSONArray nodes = (JSONArray) jsonObject.get("nodes");
            for (int i = 0; i < nodes.size(); i++)
            {
                JSONObject cur = (JSONObject) nodes.get(i);
                String j = (String) cur.get("json");
                if (j.equals("null"))
                {
                    j = null;
                }
                String f = (String) cur.get("form");
                if (f.equals("null"))
                {
                    f = null;
                }
                addNode((String) cur.get("title"), (String) cur.get("url"), (String) cur.get("type"), (String) cur.get("cookie"), j, f);
            }

            JSONArray edges = (JSONArray) jsonObject.get("edges");
            for (int i = 0; i < edges.size(); i++)
            {
                JSONObject cur = (JSONObject) edges.get(i);
                String t = (String) cur.get("title");
                int s = Integer.parseInt(cur.get("startID") + "");
                int e = Integer.parseInt(cur.get("endID") + "");
                double p = Double.parseDouble(cur.get("prob") + "");
                addEdge(t, s, e, p);
            }

        } catch (ParseException e)
        {
            System.out.println("Parse Error: " + e);
        }
    }

    public boolean nodeTaken(String title, String type)
    {
        for (int i = 0; i < numNodes; i++)
        {
            if (nodes.get(i).getTitle().equals(title) && nodes.get(i).getType().equals(type))
            {
                return true;
            }
        }
        return false;
    }

    public boolean edgeTaken(String title, String s, String e)
    {
        for (int i = 0; i < numEdges; i++)
        {
            if (edges.get(i).getTitle().equals(title) && edges.get(i).getStart().getId() == Integer.parseInt(s) && edges.get(i).getEnd().getId() == Integer.parseInt(e))
            {
                return true;
            }
        }
        return false;
    }

    public int[] getNodeIDs()
    {
        int[] arr = new int[numNodes];
        for (int i = 0; i < numNodes; i++)
        {
            arr[i] = nodes.get(i).getId();
        }
        return arr;
    }

    public int[] getEdgeIDs()
    {
        int[] arr = new int[numEdges];
        for (int i = 0; i < numEdges; i++)
        {
            arr[i] = edges.get(i).getId();
        }
        return arr;
    }

    public double fetchRemainingProb(int id) throws IndexOutOfBoundsException
    {
        return nodes.get(id).fetchRemainingProb();
    }

    public int requestTotalTime()
    {
        int count = 0;
        for (int i = 0; i < durArr.size(); i++)
        {
            count += durArr.get(i);
        }
        return count;
    }
}

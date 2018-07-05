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
    //possibly use a HashSet and WeightedScenario to make searching quicker
    private ArrayList<Scenario> scens;
    private int numScens;
    private ArrayList<Integer> durArrMain;
    private ArrayList<Integer> arrArrMain;
    private ArrayList<Integer> durArrSingle;
    private ArrayList<Integer> arrArrSingle;
    private int numScensToGenerate = 5;

    public Graph(String baseUrl)
    {
        this.baseUrl = baseUrl;
        nodes = new ArrayList<Node>();
        numNodes = 0;
        edges = new ArrayList<Edge>();
        numEdges = 0;
        scens = new ArrayList<Scenario>();
        numScens = 0;
        durArrMain = new ArrayList<Integer>();
        arrArrMain = new ArrayList<Integer>();
        durArrSingle = new ArrayList<Integer>();
        arrArrSingle = new ArrayList<Integer>();
    }

    public int getNumScenariosToGenerate()
    {
        return numScensToGenerate;
    }

    public void setNumScenariosToGenerate(int numScenarios)
    {
        this.numScensToGenerate = numScenarios;
    }

    private void addDurMain(int i)
    {
        durArrMain.add((Integer) i);
    }

    private void addArrMain(int i)
    {
        arrArrMain.add((Integer) i);
    }

    public void addPhaseMain(int d, int a)
    {
        addDurMain(d);
        addArrMain(a);
    }

    public void deletePhaseMain(int id)
    {
        durArrMain.remove(id);
        arrArrMain.remove(id);
    }

    public int getNumPhasesMain()
    {
        return durArrMain.size();
    }

    public int getLastDurMain()
    {
        return durArrMain.get(durArrMain.size() - 1);
    }

    public int getLastArrMain()
    {
        return arrArrMain.get(arrArrMain.size() - 1);
    }

    public String getPhasesMain()
    {
        String b = "";
        for (int i = 0; i < durArrMain.size(); i++)
        {
            b += "Phase " + i + ": duration = " + durArrMain.get(i) + ", arrivalRate = " + arrArrMain.get(i) + "/s\n";
        }
        return b;
    }

    private void addDurSingle(int i)
    {
        durArrSingle.add((Integer) i);
    }

    private void addArrSingle(int i)
    {
        arrArrSingle.add((Integer) i);
    }

    public void addPhaseSingle(int d, int a)
    {
        addDurSingle(d);
        addArrSingle(a);
    }

    public void deletePhaseSingle(int id)
    {
        durArrSingle.remove(id);
        arrArrSingle.remove(id);
    }

    public int getNumPhasesSingle()
    {
        return durArrSingle.size();
    }

    public int getLastDurSingle()
    {
        return durArrSingle.get(durArrSingle.size() - 1);
    }

    public int getLastArrSingle()
    {
        return arrArrSingle.get(arrArrSingle.size() - 1);
    }

    public String getPhasesSingle()
    {
        String b = "";
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            b += "Phase " + i + ": duration = " + durArrSingle.get(i) + ", arrivalRate = " + arrArrSingle.get(i) + "/s\n";
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

    /*
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
    }*/
    public String mayanArtillery(String filename)
    {
        //make sure the artillery file is there/create itt
        new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).mkdirs();
        //clear artillery file if it is there
        for (File file : new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).listFiles())
        {
            file.delete();
        }

        //generate the big file, after, do the small ones from the scenarios made in the big
        String[] names = new String[numScensToGenerate];
        int[] weights = new int[numScensToGenerate];

        for (int i = 0; i < numScensToGenerate; i++)
        {
            names[i] = "Test" + i;
            weights[i] = 1;
        }

        String ma = "";
        ma += generateConfigMain();
        ma += generateScenarios(names, weights);

        try
        {
            String filema = ma.substring(0, ma.length() - 1);
            String nl = System.getProperty("line.separator");
            filema = filema.replace("\n", nl);

            PrintWriter writer = new PrintWriter("gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + filename, "UTF-8");
            writer.print(filema);
            writer.close();
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }

        //do the small ones now
        try
        {
            String temp, config = generateConfigSingle();
            String ls = System.getProperty("line.separator");
            PrintWriter writer;
            Scenario s;
            for (int i = 0; i < numScens; i++)
            {
                s = scens.get(i);
                temp = config;
                temp += "scenarios:\n";
                temp += s.getArtilleryRepresentation();
                //System.out.println(scens.get(i));
                temp = temp.substring(0, temp.length() - 1);
                temp = temp.replace("\n", ls);

                //new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).mkdirs();
                writer = new PrintWriter("gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + s.getName() + ".txt", "UTF-8");
                writer.print(temp);
                writer.close();
            }
            System.out.println("NUMBER OF UNIQUE SCENARIOS GENERATED: " + numScens);
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }
        return ma;
    }

    /*
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
     */
    public void runArtillery(String ma, String filename, boolean runMain, boolean runSingle)
    {
        try
        {
            //make sure folder in runs is there (create if not)
            new File("gen/runs/" + filename.substring(0, filename.indexOf("."))).mkdirs();

            //delete all in that folder
            for (File file : new File("gen/runs/" + filename.substring(0, filename.indexOf("."))).listFiles())
            {
                if(file.getName().equals(filename))
                {
                    if(runMain)
                    {
                        System.out.println("deleting main!");
                        file.delete();
                    }
                }
                else
                {
                    if(runSingle)
                    {
                        System.out.println("deleting single!");
                        file.delete();
                    }
                }
            }

            Runtime rt = Runtime.getRuntime();

            //run tests for all single files
            if (runSingle)
            {
                System.out.println("going through mini tests!");
                //go through all single files
                for (File file : new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).listFiles())
                {
                    String fn = file.getName();

                    if (!fn.equals(filename))
                    {
                        System.out.println("Running " + fn);
                        Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + fn + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + fn);

                        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                        String line = null;
                        while ((line = input.readLine()) != null)
                        {
                            System.out.println(line);
                        }

                        int exitVal = pr.waitFor();
                    } else
                    {
                        System.out.println("Main file, do not run");
                    }

                }
            }

            if (runMain)
            {
                System.out.println("Running main file!");

                //run main file
                Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + filename + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + filename);

                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;
                while ((line = input.readLine()) != null)
                {
                    System.out.println(line);
                }

                int exitVal = pr.waitFor();
            }

        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public String generateConfigMain()
    {
        String c = "config:\n";
        c += "  target: '" + baseUrl + "'\n";
        c += "  phases:\n";
        for (int i = 0; i < durArrMain.size(); i++)
        {
            c += "    - duration: " + durArrMain.get(i) + "\n      arrivalRate: " + arrArrMain.get(i) + "\n";
        }
        return c;
    }

    public String generateConfigSingle()
    {
        String c = "config:\n";
        c += "  target: '" + baseUrl + "'\n";
        c += "  phases:\n";
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            c += "    - duration: " + durArrSingle.get(i) + "\n      arrivalRate: " + arrArrSingle.get(i) + "\n";
        }
        return c;
    }

    public String generateScenarios(String[] names, int[] weights)
    {
        String s = "scenarios:\n";
        scens.clear();
        numScens = 0;
        for (int i = 0; i < names.length; i++)
        {
            //the function below should add this scenario, but also check if the same scenario has been created already, for grouping
            s += generateScenario(names[i], weights[i]);
        }
        //
        return s;
    }

    public String generateScenario(String name, int weight)
    {
        /*
        Scenario scen = new Scenario(name, "", "");
        String s = "  - name: '" + name + "'\n";
        s += "    weight: " + weight + "\n";
        s += "    flow:\n";
        s += "      - log: '" + name + "'\n";
        s += generateFlowSteps(scen);
        return s;
         */

        Scenario scen = new Scenario(name);
        generateFlowSteps(scen);

        //this part would use the HashSet rather, faster. This does the grouping of scenarios
        int index = scens.indexOf(scen);
        if (index == -1 || numScens == 0)
        {
            scens.add(scen);
            numScens++;
        } else
        {
            scens.get(index).incrementWeight();
        }

        //System.out.println(scen.getQuick());
        return scen.getArtilleryRepresentation();
    }

    public void generateFlowSteps(Scenario scen)
    {
        Node node = startNode();
        while (node != null)
        {
            scen.appendFlow(node.generateFlowStep());
            scen.appendQuick(node.getQuickRepresentation() + " ");
            node = node.takeRandomPath();
        }
    }

    public String exportGraph(String filename)
    {
        String b = "{";
        b += "\"baseUrl\": \"" + baseUrl + "\", ";

        b += "\"phasesMain\": [";
        if (durArrMain.isEmpty())
        {
            b += "{\"duration\": " + 1 + ", \"arrivalRate\": " + 1 + "}, ";
        }
        for (int i = 0; i < durArrMain.size(); i++)
        {
            b += "{\"duration\": " + durArrMain.get(i) + ", \"arrivalRate\": " + arrArrMain.get(i) + "}, ";
        }
        b = b.substring(0, b.length() - 2) + "], ";

        b += "\"phasesSingle\": [";
        if (durArrSingle.isEmpty())
        {
            b += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 1 + "}, ";
            b += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 2 + "}, ";
            b += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 3 + "}, ";
            b += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 4 + "}, ";
            b += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 5 + "}, ";
        }
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            b += "{\"duration\": " + durArrSingle.get(i) + ", \"arrivalRate\": " + arrArrSingle.get(i) + "}, ";
        }
        b = b.substring(0, b.length() - 2) + "], ";

        b += "\"numScenarios\": \"" + numScensToGenerate + "\", ";
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

            JSONArray phasesMain = (JSONArray) jsonObject.get("phasesMain");
            for (int i = 0; i < phasesMain.size(); i++)
            {
                JSONObject cur = (JSONObject) phasesMain.get(i);
                int d = Integer.parseInt(cur.get("duration") + "");
                int a = Integer.parseInt(cur.get("arrivalRate") + "");
                addPhaseMain(d, a);
            }

            JSONArray phasesSingle = (JSONArray) jsonObject.get("phasesSingle");
            for (int i = 0; i < phasesSingle.size(); i++)
            {
                JSONObject cur = (JSONObject) phasesSingle.get(i);
                int d = Integer.parseInt(cur.get("duration") + "");
                int a = Integer.parseInt(cur.get("arrivalRate") + "");
                addPhaseSingle(d, a);
            }

            numScensToGenerate = Integer.parseInt(jsonObject.get("numScenarios") + "");

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

    public int requestTotalMainTime()
    {
        int count = 0;
        for (int i = 0; i < durArrMain.size(); i++)
        {
            count += durArrMain.get(i);
        }
        return count;
    }

    public int requestTotalSingleTime()
    {
        int each = 0;
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            each += durArrSingle.get(i);
        }

        return each * numScens;
    }
}

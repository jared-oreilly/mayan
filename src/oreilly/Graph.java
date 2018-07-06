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
    private final int averageNumber = 3;

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
        String buildUp = "";
        for (int i = 0; i < durArrMain.size(); i++)
        {
            buildUp += "Phase " + i + ": duration = " + durArrMain.get(i) + ", arrivalRate = " + arrArrMain.get(i) + "/s\n";
        }
        return buildUp;
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
        String buildUp = "";
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            buildUp += "Phase " + i + ": duration = " + durArrSingle.get(i) + ", arrivalRate = " + arrArrSingle.get(i) + "/s\n";
        }
        return buildUp;
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
        String buildUp = "";
        buildUp += baseUrl + "\n\n";
        buildUp += "NODES\t-> " + numNodes + " <-\n";
        for (int i = 0; i < numNodes; i++)
        {
            buildUp += nodes.get(i) + "\n";
        }
        buildUp += "\nEDGES\t-> " + numEdges + " <-\n";
        for (int i = 0; i < numEdges; i++)
        {
            buildUp += edges.get(i) + "\n";
        }
        buildUp += "----------------------";
        return buildUp;
    }

    public String printNodes()
    {
        String buildUp = "";
        buildUp += "NODES\t-> " + numNodes + " <-\n";
        for (int i = 0; i < numNodes; i++)
        {
            buildUp += nodes.get(i) + "\n";
        }
        buildUp += "----------------------";
        return buildUp;
    }

    public String printEdges()
    {
        String buildUp = "";
        buildUp += "EDGES\t-> " + numEdges + " <-\n";
        for (int i = 0; i < numEdges; i++)
        {
            buildUp += edges.get(i) + "\n";
        }
        buildUp += "----------------------";
        return buildUp;
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
        //make sure the artillery file is there/create itt
        new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).mkdirs();
        //clear artillery file if it is there
        for (File file : new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).listFiles())
        {
            file.delete();
        }

        //generate the big file, after, do the small ones from the scenarios made in the big
        String[] scenarioCountNames = new String[numScensToGenerate];
        int[] defaultWeights = new int[numScensToGenerate];

        for (int i = 0; i < numScensToGenerate; i++)
        {
            scenarioCountNames[i] = "Test" + i;
            defaultWeights[i] = 1;
        }

        String mayanArtillery = "";
        mayanArtillery += generateConfigMain();
        mayanArtillery += generateScenarios(scenarioCountNames, defaultWeights);

        try
        {
            String filema = mayanArtillery.substring(0, mayanArtillery.length() - 1);
            String seperator = System.getProperty("line.separator");
            filema = filema.replace("\n", seperator);

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
            String tempBuildUp, config = generateConfigSingle();
            String seperator = System.getProperty("line.separator");
            PrintWriter writer;
            Scenario s;
            for (int i = 0; i < numScens; i++)
            {
                s = scens.get(i);
                tempBuildUp = config;
                tempBuildUp += "scenarios:\n";
                tempBuildUp += s.getArtilleryRepresentation();

                tempBuildUp = tempBuildUp.substring(0, tempBuildUp.length() - 1);
                tempBuildUp = tempBuildUp.replace("\n", seperator);

                writer = new PrintWriter("gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + s.getQuick() + ".txt", "UTF-8");
                writer.print(tempBuildUp);
                writer.close();
            }
            System.out.println("NUMBER OF UNIQUE SCENARIOS GENERATED: " + numScens);
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }
        return mayanArtillery;
    }

    public void runArtillery(String ma, String filename, boolean runMain, boolean runSingle, boolean runAverages)
    {
        try
        {
            //make sure folder in runs is there (create if not)
            new File("gen/runs/" + filename.substring(0, filename.indexOf("."))).mkdirs();

            //delete all in that folder
            for (File file : new File("gen/runs/" + filename.substring(0, filename.indexOf("."))).listFiles())
            {
                if (file.getName().equals(filename))
                {
                    if (runMain)
                    {
                        file.delete();
                    }
                } else
                {
                    if (runSingle)
                    {
                        file.delete();
                    }
                }
            }

            Runtime rt = Runtime.getRuntime();

            //run tests for all single files
            if (runSingle)
            {
                //go through all single files
                int count = 0;
                for (File file : new File("gen/artillery/" + filename.substring(0, filename.indexOf("."))).listFiles())
                {
                    String fn = file.getName();

                    if (!fn.equals(filename))
                    {
                        if (runAverages)
                        {
                            for (int k = 0; k < averageNumber; k++)
                            {
                                System.out.print(java.time.LocalTime.now() + "->\t" + count + ": Running " + fn.substring(0, fn.indexOf(".")) + "___" + k + ".txt" + "... ");
                                Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + fn + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + fn.substring(0, fn.indexOf(".")) + "___" + k + ".txt");

                                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                                String line = null;
                                while ((line = input.readLine()) != null)
                                {
                                    System.out.println(line);
                                }

                                int exitVal = pr.waitFor();
                                System.out.println("done");
                                count++;
                            }

                        } else
                        {
                            System.out.print(count + ": Running " + fn + "... ");
                            Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + fn + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + fn);

                            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                            String line = null;
                            while ((line = input.readLine()) != null)
                            {
                                System.out.println(line);
                            }

                            int exitVal = pr.waitFor();
                            System.out.println("done");
                            count++;
                        }

                    }

                }
            }

            if (runMain)
            {
                System.out.print(">: Running main file... ");

                //run main file
                Process pr = rt.exec("cmd /c artillery run gen/artillery/" + filename.substring(0, filename.indexOf(".")) + "/" + filename + " > gen/runs/" + filename.substring(0, filename.indexOf(".")) + "/" + filename);

                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;
                while ((line = input.readLine()) != null)
                {
                    System.out.println(line);
                }

                int exitVal = pr.waitFor();
                System.out.println("done");
            }

        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public String generateConfigMain()
    {
        String buildUp = "config:\n";
        buildUp += "  target: '" + baseUrl + "'\n";
        buildUp += "  phases:\n";
        for (int i = 0; i < durArrMain.size(); i++)
        {
            buildUp += "    - duration: " + durArrMain.get(i) + "\n      arrivalRate: " + arrArrMain.get(i) + "\n";
        }
        return buildUp;
    }

    public String generateConfigSingle()
    {
        String buildUp = "config:\n";
        buildUp += "  target: '" + baseUrl + "'\n";
        buildUp += "  phases:\n";
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            buildUp += "    - duration: " + durArrSingle.get(i) + "\n      arrivalRate: " + arrArrSingle.get(i) + "\n";
        }
        return buildUp;
    }

    public String generateScenarios(String[] names, int[] weights)
    {
        String buildUp = "scenarios:\n";
        scens.clear();
        numScens = 0;
        for (int i = 0; i < names.length; i++)
        {
            //the function below should add this scenario, but also check if the same scenario has been created already, for grouping
            buildUp += generateScenario(names[i], weights[i]);
        }
        return buildUp;
    }

    public String generateScenario(String name, int weight)
    {
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

        return scen.getArtilleryRepresentation();
    }

    public void generateFlowSteps(Scenario scen)
    {
        Node node = startNode();
        while (node != null)
        {
            scen.appendFlow(node.generateFlowStep());
            scen.appendQuick(node.getQuickRepresentation() + "_");
            node = node.takeRandomPath();
        }
    }

    public String exportGraph(String filename)
    {
        String buildUp = "{";
        buildUp += "\"baseUrl\": \"" + baseUrl + "\", ";

        buildUp += "\"phasesMain\": [";
        if (durArrMain.isEmpty())
        {
            buildUp += "{\"duration\": " + 1 + ", \"arrivalRate\": " + 1 + "}, ";
        }
        for (int i = 0; i < durArrMain.size(); i++)
        {
            buildUp += "{\"duration\": " + durArrMain.get(i) + ", \"arrivalRate\": " + arrArrMain.get(i) + "}, ";
        }
        buildUp = buildUp.substring(0, buildUp.length() - 2) + "], ";

        buildUp += "\"phasesSingle\": [";
        if (durArrSingle.isEmpty())
        {
            //warm up phase
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 1 + "}, ";
            //steadily increasing phase
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 1 + "}, ";
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 2 + "}, ";
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 3 + "}, ";
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 4 + "}, ";
            buildUp += "{\"duration\": " + 10 + ", \"arrivalRate\": " + 5 + "}, ";
        }
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            buildUp += "{\"duration\": " + durArrSingle.get(i) + ", \"arrivalRate\": " + arrArrSingle.get(i) + "}, ";
        }
        buildUp = buildUp.substring(0, buildUp.length() - 2) + "], ";

        buildUp += "\"numScenarios\": \"" + numScensToGenerate + "\", ";
        buildUp += "\"numNodes\": \"" + numNodes + "\", ";
        buildUp += "\"nodes\": [";
        for (int i = 0; i < numNodes; i++)
        {
            buildUp += nodes.get(i).exportNode() + ", ";
        }
        buildUp = buildUp.substring(0, buildUp.length() - 2) + "], ";
        buildUp += "\"numEdges\": \"" + numEdges + "\", ";
        buildUp += "\"edges\": [";
        for (int i = 0; i < numEdges; i++)
        {
            buildUp += edges.get(i).exportEdge() + ", ";
        }
        buildUp = buildUp.substring(0, buildUp.length() - 2) + "]}";

        try
        {
            String fileText = buildUp;
            String nl = System.getProperty("line.separator");
            fileText = fileText.replace("\n", nl);

            PrintWriter writer = new PrintWriter("gen/graphs/" + filename, "UTF-8");
            writer.print(fileText);
            writer.close();
        } catch (IOException e)
        {
            System.out.println("Problem with writing to file: " + e);
        }

        return buildUp;
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

    public boolean edgeTaken(String title, String startID, String endID)
    {
        for (int i = 0; i < numEdges; i++)
        {
            if (edges.get(i).getTitle().equals(title) && edges.get(i).getStart().getId() == Integer.parseInt(startID) && edges.get(i).getEnd().getId() == Integer.parseInt(endID))
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

    public String requestTotalMainTime()
    {
        int count = 0;
        for (int i = 0; i < durArrMain.size(); i++)
        {
            count += durArrMain.get(i);
        }
        int hours = count / (60 * 60);
        count %= (60 * 60);
        int minutes = count / 60;
        count %= 60;
        return "" + hours + "h:" + minutes + "m:" + count + "s";
    }

    public String requestTotalSingleTime()
    {
        int each = 0;
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            each += durArrSingle.get(i);
        }
        int count = each * numScens;
        int hours = count / (60 * 60);
        count %= (60 * 60);
        int minutes = count / 60;
        count %= 60;
        return "" + hours + "h:" + minutes + "m:" + count + "s";
    }

    //for averages
    public String requestTotalSingleTime(boolean flag)
    {
        int each = 0;
        for (int i = 0; i < durArrSingle.size(); i++)
        {
            each += durArrSingle.get(i);
        }
        int count = each * numScens;
        count *= 3;
        int hours = count / (60 * 60);
        count %= (60 * 60);
        int minutes = count / 60;
        count %= 60;
        return "" + hours + "h:" + minutes + "m:" + count + "s";
    }
}

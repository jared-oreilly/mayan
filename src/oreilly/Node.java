package oreilly;

import java.util.*;

/**
 *
 * @author jared.oreilly
 */
public class Node
{

    private int id;
    private String title;
    private String url;
    private ArrayList<Edge> edges;
    private int numEdges;
    private String type;
    private String cookie;
    private String json;
    private String form;

    public Node(int id, String title, String url, String type, String cookie)
    {
        this.id = id;
        this.title = title;
        this.url = url;
        this.edges = new ArrayList<Edge>();
        this.numEdges = 0;
        this.type = type;
        this.cookie = cookie;
    }

    public Node(int id, String title, String url, String type, String cookie, String json, String form)
    {
        this.id = id;
        this.title = title;
        this.url = url;
        this.edges = new ArrayList<Edge>();
        this.numEdges = 0;
        this.type = type;
        this.cookie = cookie;
        this.json = json;
        this.form = form;
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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getCookie()
    {
        return cookie;
    }

    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getJson()
    {
        return json;
    }

    public void setJson(String json)
    {
        this.json = json;
    }

    public int getNumEdges()
    {
        return numEdges;
    }

    public void setNumEdges(int numEdges)
    {
        this.numEdges = numEdges;
    }

    public String getFormData()
    {
        return form;
    }

    public void setFormData(String formData)
    {
        this.form = formData;
    }

    public void addEdge(Edge e)
    {
        edges.add(e);
        numEdges++;
    }

    public Node getFirstNodeAlongEdge()
    {
        if (edges.isEmpty())
        {
            return null;
        } else
        {
            return edges.get(0).getEnd();
        }
    }

    public Node takeRandomPath()
    {
        if (edges.isEmpty())
        {
            return null;
        } else
        {
            double rand = Math.random();
            //System.out.println(rand);
            int i = -1;
            double count = 0;
            while (rand > count)
            {
                i++;
                count += edges.get(i).getProb();
                //System.out.println(count);
            }
            return edges.get(i).getEnd();
        }
    }

    @Override
    public String toString()
    {
        String b = "";
        b += id + ": '" + title + "' " + url + " is " + type;
        b += " with edges: " + edges;
        return b;
    }

    //the one step in the flow
    public String generateFlowStep()
    {
        String afs = "";
        afs += "      - " + type.toLowerCase() + ":\n";

        afs += "          url: '" + url + "'\n";
        if (json != null)
        {
            afs += "          json:\n";
            afs += "            " + json + "\n";
        }
        if (form != null)
        {
            afs += "          form:\n";
            afs += form + "\n";
        }
        afs += "          headers:\n";
        afs += "            Cookie: '" + cookie + "'\n";
        return afs;
    }
    
    public String exportNode()
    {
        String b = "{";
        b += "\"id\": \"" + id + "\", "; 
        b += "\"title\": \"" + title + "\", "; 
        b += "\"url\": \"" + url + "\", "; 
        /*
        b += "\"edges\": [";
        for(int i = 0; i < numEdges; i++)
        {
            b += edges.get(0).getId() + ", ";
        }
        b = b.substring(0, b.length()-2);
        b += "], ";
        b += "\"numEdges\": " + numEdges + ", "; 
        */
        b += "\"type\": \"" + type + "\", "; 
        b += "\"cookie\": \"" + cookie + "\", "; 
        b += "\"json\": \"" + json + "\", "; 
        b += "\"form\": \"" + form + "\"}"; 
        return b;
    }
    
    public double fetchRemainingProb()
    {
        double count = 0;
        for(int i = 0; i < numEdges; i++)
        {
            count += edges.get(i).getProb();
        }
        count = 1 - count;
        count = count * 1000;
        count = Math.round(count);
        count = count / 1000.0;
        //System.out.println(count);
        return count;
    }

}

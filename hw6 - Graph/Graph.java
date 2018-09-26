/*
 * Name: Jibram Jimenez-Loza
 * Student ID: 2017843466
 */
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Do not import anything else
 */

public class Graph
{
    /*
     * Our instance variables
     *
     * numNodes - int - holds the number of nodes currently in the graph
     * numEdges - int - holds the number of edges currently in the graph
     * nodes - ArrayList<Node> - holds all the nodes indexed by their label
     *                           if node n has label i, it must be located in
     *                           nodes.get(i).
     * edges - ArrayList<Edge> - holds all of the nodes in the graph
     *                           these can be in any order
     * adjacencyList - HashMap<Node, HashMap<Node, Edge>> - the adjacency list for the graph
     *                                                      with this, you can get an edge
     *                                                      between node u and v (if it exists)
     *                                                      by calling Edge e = adjacencyList.get(u).get(v);
     * INFINITY - final int - you can use this value to represent infinity in dijkstra's algorithm
     *
     * You may add any other instance variables that you wish
     */

    private int numNodes, numEdges;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private HashMap<Node, HashMap<Node, Edge>> adjacencyList;
    private final int INFINITY = Integer.MAX_VALUE;
    private boolean[] visited;
    /*
    * Constructor for our Graph
    */
    public Graph()
    {
        numNodes = 0;
        numEdges = 0;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        adjacencyList = new HashMap<Node, HashMap<Node, Edge>>();

    }

    /*
    * Add a new node to the graph
    *
    * The nodes in the graph are labeled from 0 to numNodes-1
    * so the new node should have label numNodes (before incrementing)
    *
    * For example, if we have nodes 0,1,2 (so numNodes = 3) then addNode()
    * will make a new node with label 3 and then increment numNodes
    *
    * Make sure you place the new node in the correct position in nodes
    * and that you insert it into adjacencyList
    * 
    * Return the new node
    */
    public Node addNode()
    {
        Node node = new Node(numNodes);
        nodes.add(node);
        adjacencyList.put(node, new HashMap<Node, Edge>());
        numNodes++;
        return node;
    }

    /*
    * Add an edge with the given endpoints and weight to the graph
    *
    * You do not have to worry about the case where u or v does not exist
    * If there is already an edge between u and v, you should update the
    * weight of that edge to the new weight and return that edge
    * Otherwise, you should create a new edge with the correct properties
    *
    * Be sure to insert it into edges and into adjacencyList in the appropriate places
    * Remember that this graph is undirected, so an edge between u and v is
    * also an edge between v and u.
    *
    * Return the new edge or the updated edge if it already existed
    *
    */        
    public Edge addEdge(Node u, Node v, int weight)
    {
        if (getAdjacencyList().get(u).get(v) == null)
        {
            Edge edge = new Edge(u, v, weight);
            edges.add(edge);
            getAdjacencyList().get(u).put(v, edge);
            getAdjacencyList().get(v).put(u, edge);
            numEdges++;
            return edge;
        }
        else
        {
            getAdjacencyList().get(u).get(v).setWeight(weight);
            getAdjacencyList().get(v).get(u).setWeight(weight);
            return getAdjacencyList().get(u).get(v);
        }
    }

    public HashMap<Node, Integer> dijkstra(Node source)
    {
        HashMap<Node, Integer> distance = new HashMap<Node, Integer>();
        PriorityQueue<NodeWrapper> queue = new PriorityQueue<NodeWrapper>();
        NodeWrapper wrapper;
        distance.put(source, 0);
        queue.add(new NodeWrapper(source, 0));
        for (int i = 0; i < numNodes; i++)
        {
            if (i != source.getLabel())
            {
                distance.put(nodes.get(i), INFINITY);
                queue.add(new NodeWrapper(nodes.get(i), INFINITY));
            }
        }
        
        while (queue.isEmpty() == false)
        {
            wrapper = queue.poll();
            if (wrapper.getValue() == INFINITY) {continue;}
            for (int i = 0; i < numNodes; i++)
            {
                if (getAdjacencyList().get(wrapper.getNode()).get(nodes.get(i)) != null)
                {
                    int value = distance.get(wrapper.getNode()) + getAdjacencyList().get(wrapper.getNode()).get(nodes.get(i)).getWeight();
                    if (value < distance.get(nodes.get(i)))
                    {
                        distance.remove(nodes.get(i));
                        distance.put(nodes.get(i), value);
                        queue.add(new NodeWrapper(nodes.get(i), value));
                    }
                }
            }
        }

        return distance;
    }

    /*
    * Return the length of the shortest path from source to target
    * If there is no such path, this should return INFINITY
    */
    public int shortestPathLength(Node source, Node target)
    {
        return dijkstra(source).get(target);
    }

    /*
    * Return a list of all of the edges in a minimum spanning tree of
    * the graph. The order does not matter. If there are multiple valid
    * minimum spanning trees, you can return any of them.
    */
    public ArrayList<Edge> minSpanningTree()
    {   
        ArrayList<Edge> tree = new ArrayList<Edge>();

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        for (int i = 0; i < numEdges; i++)
        {
            queue.add(edges.get(i));
        }
        
        HashMap<Node, Integer> groups = new HashMap<Node, Integer>();
        for (int i = 0; i < numNodes; i++)
        {
            groups.put(nodes.get(i), i);
        }

        while (queue.isEmpty() == false)
        {
            Edge temp = queue.poll();
            if (groups.get(temp.getU()) != groups.get(temp.getV()))
            {
                tree.add(temp);
                groups.replace(temp.getV(), groups.get(temp.getU()));
            }
        }

        return tree;
    }

    /*
    * Return the total weight of the minimum spanning tree of the graph
    */
    public int minSpanningTreeWeight() //Runs in O(e) overall since the two functions.
    {
        ArrayList<Edge> minSpanningTree = minSpanningTree();
        int weight = 0;
        for (int i = 0; i < minSpanningTree.size(); i++)
        {
            weight += minSpanningTree.get(i).getWeight();
        }
        return weight;
    }

    /*
    * Return true if there is any path between u and v and false otherwise
    */
    public boolean areUVConnected(Node u, Node v)
    {
        return (shortestPathLength(u, v) != INFINITY);
    }

    /*
    * Return true if there is only one connected component in the graph and false otherwise
    */
    public boolean isConnected()
    {
        return (numConnectedComponents() == 1);
    }

    /*
    * Return the number of connected components in the graph
    */
    public int numConnectedComponents()
    {
        visited = new boolean[numNodes];
        int count = 0;
        for (int i = 0; i < numNodes; i++)
        {
            if (visited[i] == false)
            {
                depthSearch(nodes.get(i));
                count++;
            }
        }
        return count;
    }

    public void depthSearch(Node index)
    {   //Could be optimized in the grab adjacent 
        visited[index.getLabel()] = true;
        //Here we need to iterate through the vertex's adjacent vertex and try em out.
        for(Node n: adjacencyList.get(index).keySet())
        {
            if (visited[n.getLabel()] == false) {depthSearch(n);}
        }
    }

    /*
    * Return the number of nodes in the graph
    */
    public int getNumNodes()
    {
        return numNodes;
    }

    /*
    * Return the number of edges in the graph
    */
    public int getNumEdges()
    {
        return numEdges;
    }

    /*
    * Return the nodes in the graph
    */
    public ArrayList<Node> getNodes()
    {
        return nodes;
    }

    /*
    * Return the edges in the graph
    */
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    /*
    * Return a hashmap with all of the nodes in the graph as the keys
    *     and the values being a list of all the edges that have the node
    *     as an endpoint
    */
    public HashMap<Node, HashMap<Node, Edge>> getAdjacencyList()
    {
        return adjacencyList;
    }

    /*
    * Return the adjacency matrix representing the graph
    * The value at [i][j] should be the weight between the nodes with
    * labels i and j (or 0 if there is no edge between them)
    */
    public int[][] getAdjacencyMatrix()
    {
        HashMap<Node, HashMap<Node, Edge>> adjacencyList = getAdjacencyList();

        int[][] adjacencyMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++)
        {
            for (int j = 0; j < numNodes; j++)
            {
                if (adjacencyList.get(nodes.get(i)).get(nodes.get(j)) != null)
                {
                    adjacencyMatrix[i][j] = adjacencyList.get(nodes.get(i)).get(nodes.get(j)).getWeight();
                }
                else
                {
                    adjacencyMatrix[i][j] = 0;
                }
            }
        }
        return adjacencyMatrix;
    }
}

/*
 *  ================================
 *  Do not modify anything below this comment
 *  ================================
 */

class Node
{
    private int label;

    public Node(int label)
    {
        this.label = label;
    }

    public void setLabel(int label)
    {
        this.label = label;
    }

    public int getLabel()
    {
        return label;
    }
}

class NodeWrapper implements Comparable<NodeWrapper>
{
    private final Node n;
    private final int value;

    public NodeWrapper(Node n, int value)
    {
        this.n = n;
        this.value = value;
    }

    public Node getNode()
    {
        return n;
    }

    public int getValue()
    {
        return value;
    }

    public int compareTo(NodeWrapper other)
    {
        return Integer.compare(this.value, other.getValue());
    }
}

class Edge implements Comparable<Edge>
{
    private Node u,v;
    private int weight;

    public Edge(Node u, Node v, int weight)
    {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public void setU(Node u)
    {
        this.u = u;
    }

    public void setV(Node v)
    {
        this.v = v;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Node getU()
    {
        return u;
    }

    public Node getV()
    {
        return v;
    }

    public int getWeight()
    {
        return weight;
    }

    public int compareTo(Edge e)
    {
        return Integer.compare(this.weight, e.getWeight());
    }
}

import java.util.*;

class Graph {
    private int V; // No. of Vertices
    private LinkedList<Integer> adj[]; // Adjacency list

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    // Functions to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w);
    }

    // print BFS travresal from a given source v
    void BFS(int s) {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while(queue.size() != 0 )
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();

            while(i.hasNext())
            {
                int n = i.next();
                if( !visited[n] )
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();

        while(i.hasNext()) {
            int n = i.next();

            if(!visited[n])
                DFSUtil(n, visited);
        }
    }

    // The function to do DFS traversal
    // It uses recursive
    // DFSUtil()

    void DFS(int v) {
        // Mark all the vertices as
        // not visisted(set as
        // false by the default in java)
        boolean visisted[] = new boolean[V];


        // Call the recursive helper
        // function to print DFS
        // traversal
        DFSUtil(v, visisted);
    }
}

class BNode {
    int key;
    BNode left, right;

    public BNode(int item)
    {
        key = item;
        left = right = null;
    }
}

class BinaryTree {
    // Root of Binary Tree
    BNode root;

    // Constructors
    BinaryTree(int key) {
        root = new BNode(key);
    }

    BinaryTree() {
        root = null;
    }
}

class TNode<T> {
    private List<TNode<T>> children = new ArrayList<TNode<T>>();
    private TNode<T> parent = null;
    private T data = null;

    public TNode(T data) {
        this.data = data;
    }

    public TNode(T data, TNode<T> parent) {
        this.data = data;
        setParent(parent);
    }

    public List<TNode<T>> getChildren() {
        return children;
    }

    public void setParent(TNode<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        TNode<T> child = new TNode<T>(data);
        addChild(child);
    }

    public void addChild(TNode<T> child)
    {
        child.parent = this;
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }


}

class GNode implements Comparator<GNode> {
    public int node;
    public int cost;

    public GNode() {

    }

    public GNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(GNode node1, GNode node2) {
        if( node1.cost < node2.cost )
            return -1;
        if( node1.cost > node2.cost )
            return 1;

        return 0;
    }
}

class DPQ {
    public int dist[];
    private Set<Integer> settled;
    private PriorityQueue<GNode> pq;
    private int V; // Number of vertices
    List<List<GNode>> adj;

    public DPQ(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<GNode>(V, new GNode());
    }

    public void dijkstra(List<List<GNode>> adj, int src)
    {
        this.adj = adj;

        for(int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new GNode(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while(settled.size() != V ) {
            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is finalized
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u) {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for(int i = 0; i < adj.get(u).size(); i++) {
            GNode v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if( !settled.contains((v.node)) ) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if( newDistance < dist[v.node] )
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new GNode(v.node, dist[v.node]));
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        // Graph Strcuture and Traversal
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breath First Traversal (starting from vertex 2)");

        g.BFS(2);

        System.out.println("\nFollowing is Depth First Traversal (starting from vertex 2)");

        g.DFS(2);

        // Binary Structure
        BinaryTree tree = new BinaryTree();

        tree.root = new BNode(1);
        tree.root.left = new BNode(2);
        tree.root.right = new BNode(3);

        tree.root.left.left = new BNode(4);

        // Tree
        TNode<String> parentNode = new TNode<>("Parent");
        TNode<String> childNode1 = new TNode<>("Child 1", parentNode);
        TNode<String> childNode2 = new TNode<>("Child 2");
        childNode2.setParent(parentNode);

        TNode<String> grandChildNode = new TNode<String>("Grandchild of parentNode, Child of childNode1", childNode1);
        List<TNode<String>> childNodes = parentNode.getChildren();

        // Shortest Path
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<GNode> > adj = new ArrayList<List<GNode> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<GNode> item = new ArrayList<GNode>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new GNode(1, 9));
        adj.get(0).add(new GNode(2, 6));
        adj.get(0).add(new GNode(3, 5));
        adj.get(0).add(new GNode(4, 3));

        adj.get(2).add(new GNode(1, 2));
        adj.get(2).add(new GNode(3, 4));

        // Calculate the single source shortest path
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("\nThe shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}

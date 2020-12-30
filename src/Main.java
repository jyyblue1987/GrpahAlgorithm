import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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
public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breath First Traversl (starting from vertex 2)");

        g.BFS(2);

        System.out.println("\nFollowing is Depth First Traversl (starting from vertex 2)");

        g.DFS(2);
    }
}

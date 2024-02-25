// Java program for the above approach
import java.io.*;
import java.util.*;
class Rabbit {

    // Node class
    static class Node implements Comparator<Node> {

        // Stores the node
        public int node;

        // Stores the weight
        // of the edge
        public int cost;

        public Node() {}

        // Constructor
        public Node(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }

        // Costume comparator
        @Override
        public int compare(Node node1, Node node2)
        {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }

    // Function to insert a node
    // in adjacency list
    static void addEdge(ArrayList<ArrayList<Node> > adj,
                        int x, int y, int w)
    {
        adj.get(x).add(new Node(y, w));
        adj.get(y).add(new Node(x, w));
    }

    // Auxiliary function to find shortest paths using
    // Dijekstra
    static void dijkstra(ArrayList<ArrayList<Node> > adj,
                         int src, int n, int dist[],
                         int paths[])
    {
        // Stores the distances of every node in shortest
        // order
        PriorityQueue<Node> pq
                = new PriorityQueue<Node>(n + 1, new Node());

        // Stores if a vertex has been visited or not
        Set<String> settled = new HashSet<String>();

        // Adds the source node with 0 distance to pq
        pq.add(new Node(src, 0));

        dist[src] = 0;
        paths[src] = 1;

        // While pq is not empty()
        while (!pq.isEmpty()) {

            // Stores the top node of pq
            int u = pq.peek().node;

            // Stores the distance
            // of node u from s
            int d = pq.peek().cost;

            // Pop the top element
            pq.poll();

            for (int i = 0; i < adj.get(u).size(); i++) {
                int to = adj.get(u).get(i).node;
                int cost = adj.get(u).get(i).cost;

                // If edge is marked
                if (settled.contains(to + " " + u))
                    continue;

                // If dist[to] is greater
                // than dist[u] + cost
                if (dist[to] > dist[u] + cost) {

                    // Add the node to to the pq
                    pq.add(new Node(to, d + cost));

                    // Update dist[to]
                    dist[to] = dist[u] + cost;

                    // Update paths[to]
                    paths[to] = paths[u];
                }

                // Otherwise
                else if (dist[to] == dist[u] + cost) {
                    paths[to] = (paths[to] + paths[u]);
                }

                // Mark the edge visited
                settled.add(to + " " + u);
            }
        }
    }

    // Function to find the count of shortest path and
    // distances from source node to every other node
    static void
    findShortestPaths(ArrayList<ArrayList<Node> > adj,
                      int s, int n)
    {
        // Stores the distances of a
        // node from source node
        int[] dist = new int[n + 5];

        // Stores the count of shortest
        // paths of a node from
        // source node
        int[] paths = new int[n + 5];

        for (int i = 0; i <= n; i++)
            dist[i] = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++)
            paths[i] = 0;

        dijkstra(adj, s, n, dist, paths);

        System.out.println(paths[n]);
    }

    // Driver Code
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        // Input
       int N,M;
       String[] data = scanner.nextLine().split(" ");
       N = Integer.parseInt(data[0]);
       M = Integer.parseInt(data[1]);

        ArrayList<ArrayList<Node> > adj = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++) {
            String[] line = scanner.nextLine().split(" ");
            addEdge(adj, Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        }

        // Function call
        findShortestPaths(adj, 0, N-1);
    }
}

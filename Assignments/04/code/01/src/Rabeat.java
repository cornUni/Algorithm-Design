import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;



public class Rabeat {
    static int shortest_count = 0;
    static int N,M;
    static class Node {
        private final int value;
        private boolean visited;
        private final ArrayList<Node> adjacentVertices;

        public Node(int value) {
            this.value = value;
            this.visited = false;
            adjacentVertices = new ArrayList<>();
        }

        public int getValue() {
            return value;
        }

        public ArrayList<Node> getAdjacentVertices() {
            return adjacentVertices;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }

    static class Edge {
        Node src;
        Node dst;
        int weight;

        public Edge(Node src, Node dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    static public class Graph {
        int numberOfVertices;
        ArrayList<Node> nodes;
        ArrayList<Edge> edges;

        public Graph(int numberOfVertices, int numberOfEdges) {
            this.numberOfVertices = numberOfVertices;
            nodes = new ArrayList<>();
            edges = new ArrayList<>();
        }
        public int findWeight(int srcVal, int dstVal){
            for (Edge edge:edges) {
                if (edge.src.getValue() == srcVal && edge.dst.getValue() == dstVal)
                    return  edge.getWeight();
                else if (edge.src.getValue() == dstVal && edge.dst.getValue() == srcVal)
                    return  edge.getWeight();
            }
            return Integer.MAX_VALUE;
        }
        public void addNode(int value){
            if (findVertex(value) == null){
                nodes.add(new Node(value));
            }
        }
        public void addEdges(String edgeInfo){

                String[] dataLine = edgeInfo.split(" ");
                edges.add(new Edge(findVertex(Integer.parseInt(dataLine[0])),
                        findVertex(Integer.parseInt(dataLine[1])),
                        Integer.parseInt(dataLine[2])
                ));

                Objects.requireNonNull(findVertex(Integer.parseInt(dataLine[0]))).getAdjacentVertices().add(findVertex(Integer.parseInt(dataLine[1])));
                Objects.requireNonNull(findVertex(Integer.parseInt(dataLine[1]))).getAdjacentVertices().add(findVertex(Integer.parseInt(dataLine[0])));
            }

        public Node findVertex(int value){
            for (Node v:nodes) {
                if (v.getValue() == value)
                    return v;
            }
            return null;
        }
        public ArrayList<Node> getNodes() {
            return nodes;
        }
    }
    public static   HashMap<Node, Integer> dijkstraDistances(int nodeValue, Graph graph) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // hashmap initialization
        for (Node v : graph.getNodes()) {
            v.setVisited(false);
            if (v.getValue() == nodeValue){
                distanceMap.put(v, 0);
            }
            else
                distanceMap.put(v, Integer.MAX_VALUE);
        }
        for (Node ignored :graph.getNodes()) {
            Node vertice = findMinDistance(distanceMap, graph);
            vertice.setVisited(true);

            for (Node ve: graph.getNodes()) {
                if (!ve.isVisited() && vertice.getAdjacentVertices().contains(ve) && distanceMap.get(vertice) +
                        graph.findWeight(vertice.getValue(), ve.getValue()) < distanceMap.get(ve)) {
                    distanceMap.put(ve, distanceMap.get(vertice) + graph.findWeight(vertice.getValue(), ve.getValue()));
                }
            }
        }
        return distanceMap;
    }
    private static Node findMinDistance(HashMap<Node, Integer> distanceMap, Graph graph){
        int minDistance = Integer.MAX_VALUE;
        Node minDistanceVertex  = null;
        for (Node v: graph.getNodes()) {
            if (!v.isVisited() && distanceMap.get(v) < minDistance) {
                minDistance = distanceMap.get(v);
                minDistanceVertex = v;
                if (v.value == N-1)
                    shortest_count =1;
            }
            if (!v.isVisited() && distanceMap.get(v) == minDistance && v.value == N-1 ) {
                shortest_count ++;
            }
        }
        return minDistanceVertex;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] data = scanner.nextLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);

        Graph graph = new Graph(N, M);

        for (int i = 0; i < M; i++) {
            String edgeInfo = scanner.nextLine();
            String[] edge = edgeInfo.split(" ");
            graph.addNode(Integer.parseInt(edge[0]));
            graph.addNode(Integer.parseInt(edge[1]));
            graph.addEdges(edgeInfo);
        }

        HashMap<Node, Integer> mapped = dijkstraDistances(0, graph);
        System.out.println(shortest_count);
    }
}

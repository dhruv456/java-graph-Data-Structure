import java.util.ArrayList;
import java.util.LinkedList;

public class ShortestPath {
    static int[] vertexDistance;
    // Edge class
    static class Edge {
        int fVertex;
        int tVertex;
        int weight;
        Edge(int fVertex, int tVertex, int weight){
            this.fVertex = fVertex; 
            this.tVertex = tVertex; 
            this.weight = weight; 
        }
    }
    // addEges will add edge to adj(Adjecency list)
    static void addEdge(int u, int v, int weight, ArrayList<ArrayList<Edge>> adj) {
        Edge newEdge = new Edge(u, v, weight); 
        // puting edge directly in adjecy will be helpfull in retrival if edge for relaxation.
        adj.get(u).add(newEdge);
    }

    public static void depthFirstSearch(ArrayList<ArrayList<Edge>> adj, int str, Boolean[] vertBooleans, LinkedList<Integer> vertexStack) {
        // System.out.println(str);
        vertBooleans[str] = true;
        for (int i = 0; i < adj.get(str).size(); i++) {
            int v = adj.get(str).get(i).tVertex;
            if(!vertBooleans[v]) {
                depthFirstSearch(adj, v, vertBooleans, vertexStack);
            }
        }
        vertexStack.push(str);
    }

    static void relaxVertex(int u_vertex, int v_vertex, int weight) {
        if(vertexDistance[v_vertex] > vertexDistance[u_vertex] + weight) {
            vertexDistance[v_vertex] = vertexDistance[u_vertex] + weight;
        }
    }

    public static void dagShortPath(ArrayList<ArrayList<Edge>> adj, LinkedList<Integer> vertexStack) {
        for (int i = 0; i < vertexDistance.length; i++) {
            int vert = vertexStack.pop();
            for (int j = 0; j < adj.get(vert).size(); j++) {
                relaxVertex(vert, adj.get(vert).get(j).tVertex, adj.get(vert).get(j).weight);
            }
        }
    }

    public static void main(String[] args) {
        int V = 8;      // Number of vertices
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>(V); // adjcency list

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Edge>());
        }
        
        addEdge(0, 1, 3, adj);
        addEdge(0, 5, 2, adj);
        addEdge(0, 3, 8, adj);
        addEdge(1, 2, 20, adj);
        addEdge(1, 6, 1, adj);
        addEdge(3, 5, 1, adj);
        addEdge(3, 4, 9, adj);
        addEdge(4, 6, 10, adj);
        addEdge(6, 7, 7, adj);
        addEdge(7, 2, 6, adj);

        Boolean[] vertexVisit = new Boolean[V]; // show is vertex visited or not
        for (int i = 0; i < vertexVisit.length; i++) {
            vertexVisit[i] = false; // seting all vertex to not visited
        }
        // printAdj(adj);
        LinkedList<Integer> vertexStack = new LinkedList<>();  // topological sorted list
        for (int i = 0; i < vertexVisit.length; i++) {
            if(!vertexVisit[i]) {
                depthFirstSearch(adj, i, vertexVisit, vertexStack);
            }
        }
        // System.out.println(vertexStack);
        vertexDistance = new int[V]; // this is distance from source
        for (int i = 0; i < vertexDistance.length; i++) {
            vertexDistance[i] = 1000000;  // initializing vertex distance from infinity like number
        }
        vertexDistance[0] = 0; // source distance is 0
        // calling directed acyclic graph single source shortest path method   
        dagShortPath(adj, vertexStack); 
        for (int i = 0; i < vertexVisit.length; i++) {
            System.out.print(vertexDistance[i] + " ");  // result:-  0 3 17 8 17 2 4 11
        }
    }
}

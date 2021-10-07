import java.util.ArrayList;

public class BellmanFord {
    static int[] vertexDistance;
    static int[] predecessorArray;
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

    static void addEdge(int u, int v, int weight, ArrayList<ArrayList<Integer>> adj, ArrayList<Edge> edgesArray) {
        adj.get(u).add(v);
        Edge edge = new Edge(u,v,weight);
        edgesArray.add(edge);
    }
    
    // relax the Vertex
    static void relaxVertex(int u_vertex, int v_vertex, int weight) {
        if(vertexDistance[v_vertex] > vertexDistance[u_vertex] + weight) {
            vertexDistance[v_vertex] = vertexDistance[u_vertex] + weight;
            predecessorArray[v_vertex] = u_vertex;
        }
    }

    public static boolean BLF(ArrayList<Edge> edges) {
        for (int i = 0; i < vertexDistance.length - 1; i++) {
            // every edge should be relax
            for(Edge eg : edges) {
                relaxVertex(eg.fVertex, eg.tVertex, eg.weight);
            }
        }
        for(Edge eg : edges) {
            if(vertexDistance[eg.tVertex] > vertexDistance[eg.fVertex] + eg.weight) {
                return false;
            }
        }
        return true;
    }

    // Main function
    public static void main(String[] args) {
        // single source is '0'
        // number of vertices is 5 i.e (0,1,2,3,4,5,6,7)
        int noVertex = 8;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(noVertex);
        for (int i = 0; i < noVertex; i++) {
            adj.add(new ArrayList<>());
        }
        ArrayList<Edge> edgesArray = new ArrayList<>();
        // addEdge(u, v, w, adj, edgesArray);
        addEdge(0, 1, 3, adj, edgesArray);
        addEdge(0, 5, 2, adj, edgesArray);
        addEdge(0, 3, 8, adj, edgesArray);
        addEdge(1, 2, 20, adj, edgesArray);
        addEdge(1, 6, 1, adj, edgesArray);
        addEdge(3, 5, 1, adj, edgesArray);
        addEdge(3, 4, 9, adj, edgesArray);
        addEdge(4, 6, 10, adj, edgesArray);
        addEdge(6, 7, 7, adj, edgesArray);
        addEdge(7, 2, 6, adj, edgesArray);
        // initializing vertexDistance array and predecessorArray array
        vertexDistance = new int[noVertex];
        for (int i = 0; i < vertexDistance.length; i++) {
            vertexDistance[i] = 1000000;
        }
        vertexDistance[0] = 0; // source distance is 0

        predecessorArray = new int[noVertex];
        for (int i = 0; i < predecessorArray.length; i++) {
            predecessorArray[i] = -1;
        }
        BLF(edgesArray);
        for(int n : vertexDistance) {
            System.out.print(n + " ");
        }
        System.out.println("-------------------------------------------");
        for(int n : predecessorArray) {
            System.out.print(n + " ");
        }
    }
}

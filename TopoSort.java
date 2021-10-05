import java.util.ArrayList;
import java.util.LinkedList;

public class TopoSort {
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void depthFirstSearch(ArrayList<ArrayList<Integer>> adj, int str, Boolean[] vertBooleans, LinkedList<Integer> vertexStack) {
        // System.out.println(str);
        vertBooleans[str] = true;
        for (int i = 0; i < adj.get(str).size(); i++) {
            int v = adj.get(str).get(i);
            if(!vertBooleans[v]) {
                depthFirstSearch(adj, v, vertBooleans, vertexStack);
            }
        }
        vertexStack.push(str);
    }

    public static void main(String[] args) {
        int V = 5;      // Number of vertices
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }
        /*
         0------->1----->4
         |       /|\   /|\
         |        |   /
        \|/       |  /
         3-------->2
        */
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 1);
        addEdge(adj, 2, 4);
        addEdge(adj, 3, 2);
        addEdge(adj, 0, 3);

        Boolean[] vertexVisit = new Boolean[V];
        for (int i = 0; i < vertexVisit.length; i++) {
            vertexVisit[i] = false;
        }
        // printAdj(adj);
        LinkedList<Integer> vertexStack = new LinkedList<>();
        for (int i = 0; i < vertexVisit.length; i++) {
            if(!vertexVisit[i]) {
                depthFirstSearch(adj, i, vertexVisit, vertexStack);
            }
        }
        System.out.println(vertexStack);
    }
}

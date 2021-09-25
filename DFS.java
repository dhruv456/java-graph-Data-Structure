import java.util.ArrayList;

public class DFS {
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void depthFirstSearch(ArrayList<ArrayList<Integer>> adj, int str, Boolean[] vertBooleans) {
        System.out.println(str);
        vertBooleans[str] = true;
        for (int i = 0; i < adj.get(str).size(); i++) {
            int v = adj.get(str).get(i);
            if(!vertBooleans[v]) {
                depthFirstSearch(adj, v, vertBooleans);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }
        /*
        0-----1---4
        |     |  /
        |     | /
        |     |/
        3-----2
        */
        addEdge(adj, 2, 3);
        addEdge(adj, 0, 3);
        addEdge(adj, 4, 1);
        addEdge(adj, 0, 1);
        addEdge(adj, 2, 1);
        addEdge(adj, 2, 4);

        Boolean[] vertexVisit = new Boolean[V];
        for (int i = 0; i < vertexVisit.length; i++) {
            vertexVisit[i] = false;
        }
        // printAdj(adj);
        for (int i = 0; i < vertexVisit.length; i++) {
            if(!vertexVisit[i]) {
                depthFirstSearch(adj, i, vertexVisit);
            }
        }
    }
}

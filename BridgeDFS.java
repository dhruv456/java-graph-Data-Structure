import java.util.ArrayList;

public class BridgeDFS {
    static Boolean[] vertexVisit;
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static void depthFirstSearch(ArrayList<ArrayList<Integer>> adj, int str, int parent, int[] tin, int[] low, int timer) {
        // System.out.println(str);
        tin[str] = low[str] = timer++;
        vertexVisit[str] = true;
        for (int v : adj.get(str)) {
            if(v == parent) 
            {
                continue;
            }
            if(!vertexVisit[v]) {
                depthFirstSearch(adj, v, str, tin, low, timer);
                low[str] = Math.min(low[str], low[v]);
                // System.out.println("l");
                if(low[v] > tin[str]) {
                    System.out.println(v + " ---- " + str);
                }
            } else {
                low[str] = Math.min(low[str], low[v]);
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
        // addEdge(adj, 0, 3);
        addEdge(adj, 4, 1);
        addEdge(adj, 0, 1);
        addEdge(adj, 2, 1);
        addEdge(adj, 2, 4);

        vertexVisit = new Boolean[V];
        for (int i = 0; i < vertexVisit.length; i++) {
            vertexVisit[i] = false;
        }
        // printAdj(adj);
         int[] tin = new int[V];
         int[] low = new int[V];
        for (int i = 0; i < vertexVisit.length; i++) {
            if(!vertexVisit[i]) {
                depthFirstSearch(adj, i, -1, tin, low, 0);
            }
        }
    }
}


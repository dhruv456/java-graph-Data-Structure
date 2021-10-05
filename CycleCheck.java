import java.util.ArrayList;

public class CycleCheck {
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public static Boolean cycleDFS(ArrayList<ArrayList<Integer>> adj, int str, int prev, Boolean[] vertBooleans) {
        // System.out.println(str);
        vertBooleans[str] = true;
        for (int i = 0; i < adj.get(str).size(); i++) {
            int v = adj.get(str).get(i);
            if(v == prev) {
                continue;
            }
            if(!vertBooleans[v]) {
                if(cycleDFS(adj, v, str, vertBooleans)){
                    System.out.println(str + " true");
                    return true;
                }
            } else {
                
                System.out.println(str + " true");
                return true;
            }
        }
        System.out.println(str + " false");
        return false;
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
        addEdge(adj, 0, 1);
        addEdge(adj, 2, 1);
        addEdge(adj, 4, 1);
        addEdge(adj, 2, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 0, 3);

        Boolean[] vertexVisit = new Boolean[V];
        for (int i = 0; i < vertexVisit.length; i++) {
            vertexVisit[i] = false;
        }
        // printAdj(adj);
        for (int i = 0; i < vertexVisit.length; i++) {
            if(!vertexVisit[i]) {
                if(cycleDFS(adj, i, -1, vertexVisit)){
                    System.out.println("CYCLE IS PRESENt");
                    return;
                }
            }
        }
        System.out.println("CYCLE IS NOT PRESENt");
    }
}

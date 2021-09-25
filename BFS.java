import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    
    // adding edge function to create graph (Bidirectional)
    static void addEdge(ArrayList<ArrayList<Integer> > adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    // printing graph in adjency manner
    static void printAdj(ArrayList<ArrayList<Integer> > adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(i + "--->");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "+adj.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    static void BreadthFirstSearch(ArrayList<ArrayList<Integer>> adj, int s, Boolean[] vertexVisit){
        LinkedList<Integer> queList = new LinkedList<>();
        queList.offer(s);
        vertexVisit[s] = true;
        System.out.println(s);
        while(!queList.isEmpty()) {
            int u = queList.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i);
                if(!vertexVisit[v]) {
                    queList.offer(v);
                    vertexVisit[v] = true;
                    System.out.println(v);
                }
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
                BreadthFirstSearch(adj, i, vertexVisit);
            }
        }
    }
}
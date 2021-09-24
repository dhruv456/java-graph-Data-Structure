import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
    static class Vertex {
        int value;
        String color = "white";
        int distance = 9999999;
        Vertex predessorVertex = null;
    }
    // adding edge function to create graph (Bidirectional)
    static void addEdge(ArrayList<Vertex> vertexArrayList, ArrayList<ArrayList<Vertex> > adj, int u, int v) {
        adj.get(u).add(vertexArrayList.get(v));
        adj.get(v).add(vertexArrayList.get(u));
    }
    // printing graph in adjency manner
    static void printAdj(ArrayList<ArrayList<Vertex> > adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.print(i + "--->");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> "+adj.get(i).get(j).value);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void BreadthFirstSearch(ArrayList<ArrayList<Vertex>> adj, Vertex s){
        LinkedList<Vertex> queList = new LinkedList<>();
        s.distance = 0;
        s.color = "gray";
        queList.offer(s);
        while(!queList.isEmpty()) {
            Vertex u = queList.remove();
            for (int i = 0; i < adj.get(u.value).size(); i++) {
                Vertex v = adj.get(u.value).get(i);
                if(v.color == "white") {
                    v.color = "gray";
                    v.distance = u.distance + 1;
                    v.predessorVertex = u;
                    queList.offer(v);
                    System.out.println(v.value);
                }
            }
            u.color = "black";
        }
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>(V);
        ArrayList<Vertex> vertexArrayList = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Vertex>());
        }

        // creating vertex and storing them in a vertexArrayList
        for (int i = 0; i < V; i++) {
            Vertex newVertex = new Vertex();
            newVertex.value = i;
            vertexArrayList.add(newVertex);
        }
        
        addEdge(vertexArrayList, adj, 2, 3);
        addEdge(vertexArrayList, adj, 0, 3);
        addEdge(vertexArrayList, adj, 4, 1);
        addEdge(vertexArrayList, adj, 0, 1);

        // printAdj(adj);
        BreadthFirstSearch(adj, vertexArrayList.remove(0));
    }
}
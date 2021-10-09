import java.util.ArrayList;
import java.util.PriorityQueue;

// import org.graalvm.compiler.graph.Node;

public class DijkstrasAlgorithm {
    
    static ArrayList<ArrayList<Node>> adj;
    static int[] vertDistance; 
    
    static class Node implements Comparable<Node> {
        int v,w;
        Node(int _v, int _w){v = _v; w = _w;}
        @Override
        public int compareTo(Node o) {
            if(v>o.v) {
                return 1;
            } else if(v<o.v) {
                return -1;
            }
            return 0;
        }
    }

    static void addEdge(int u, int v, int weight) {
        Node newNode = new Node(v, weight); 
        // puting edge directly in adjecy will be helpfull in retrival if edge for relaxation.
        adj.get(u).add(newNode);
    }

    static void DijkstrasAlgo(ArrayList<ArrayList<Node>> adj, int sour) {
        PriorityQueue<Node> pr = new PriorityQueue<>();
        vertDistance[sour] = 0;
        pr.add(new Node(sour,0));
        while (!pr.isEmpty()) {
            Node node = pr.poll();
            // int d = node.w;
            for (Node n : adj.get(node.v)) {
                if(vertDistance[n.v] > vertDistance[node.v] + n.w) {
                    vertDistance[n.v] = vertDistance[node.v] + n.w;
                    pr.add(new Node(n.v, vertDistance[n.v]));
                }
            }
        }
    }

    public static void main(String[] args) {
        int V = 8;
        adj = new ArrayList<>(V);
        vertDistance = new int[V];
        for (int i = 0; i < V; i++){vertDistance[i] = 1000000;}
        for (int i = 0; i < V; i++) {adj.add(new ArrayList<>());}
        addEdge(0, 1, 3);
        addEdge(0, 5, 2);
        addEdge(0, 3, 8);
        addEdge(1, 2, 20);
        addEdge(1, 6, 1);
        addEdge(3, 5, 1);
        addEdge(3, 4, 9);
        addEdge(4, 6, 10);
        addEdge(6, 7, 7);
        addEdge(7, 2, 6);

        DijkstrasAlgo(adj, 0);

        for (int i : vertDistance) {
            System.out.print(i + " ");
        }
    }
}
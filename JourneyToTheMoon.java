
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class JourneyToTheMoon {
    // Vertex frame
    static class Vertex {
        int value;
        String color = "white";
    }
    // adding ege function to create graph (Bidirectional)
    static void addEdge(ArrayList<Vertex> vertexArrayList, ArrayList<ArrayList<Vertex> > adj, int u, int v) {
        adj.get(u).add(vertexArrayList.get(v));
        adj.get(v).add(vertexArrayList.get(u));
    }

    
    // Breadth first Search algorithm midified as per quesions
    static void BreadthFirstSearch(ArrayList<ArrayList<Vertex>> adj, Vertex s, ArrayList<Integer> grpMember){
        LinkedList<Vertex> queList = new LinkedList<>();
        s.color = "gray";
        int memberCount = 1;
        queList.offer(s);
        while(!queList.isEmpty()) {
            Vertex u = queList.remove();
            for (int i = 0; i < adj.get(u.value).size(); i++) {
                Vertex v = adj.get(u.value).get(i);
                if(v.color == "white") {
                    v.color = "gray";
                    memberCount++;
                    queList.offer(v);
                }
            }
            u.color = "black";
        }
        grpMember.add(memberCount);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int p = sc.nextInt();
        
        ArrayList<ArrayList<Vertex>> adj = new ArrayList<>(n);
        ArrayList<Vertex> vertexArrayList = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Vertex>());
        }
        
        // creating vertex and storing them in a vertexArrayList
        for (int i = 0; i < n; i++) {
            Vertex newVertex = new Vertex();
            newVertex.value = i;
            vertexArrayList.add(newVertex);
        }
        
        for (int i = 0; i < p; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(vertexArrayList, adj, u, v);
        }
        ArrayList<Integer> astronatMemberPerGroup = new ArrayList<>(); 
        for(Vertex ver : vertexArrayList){
            if(ver.color == "white") {
                BreadthFirstSearch(adj, ver, astronatMemberPerGroup);
            }
        }
        // puting combination
        long totalPair = 0;
        for (int i = 0; i < astronatMemberPerGroup.size()-1; i++) {
            for (int j = i+1; j < astronatMemberPerGroup.size(); j++) {
                totalPair += (long) astronatMemberPerGroup.get(i)*astronatMemberPerGroup.get(j);
                totalPair += (long) astronatMemberPerGroup.get(i)*astronatMemberPerGroup.get(j+1);
                j++;
            }
        }
        System.out.println(totalPair);
        sc.close();
    }
}

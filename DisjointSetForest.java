import java.util.ArrayList;

public class DisjointSetForest {
    static class Vertex {
        int value;
        int rank = 0;
        Vertex prt = this;
        Vertex(int value){
            this.value = value;
        }
    }
    static void makeSet(ArrayList<Vertex> ver, int n) {
        for (int i = 0; i < n; i++) {
            ver.add(new Vertex(i));
        }
    }

    // union

    static void union(Vertex x, Vertex y) {
        Vertex a = find(x);
        Vertex b = find(y);
        if(a != b) {
            if(a.rank == b.rank) {
                a.rank++;
                b.prt = a;
            }
            if(a.rank > b.rank) {
                b.prt = a;
            } else {
                a.prt = b;
            }
        }
    }

    // find

    static Vertex find(Vertex x) {
        if(x == x.prt) {
            return x;
        }
        x.prt = find(x.prt);
        return x.prt;
    }

    public static void main(String[] args) {
        ArrayList<Vertex> ver = new ArrayList<>();
        int n = 5;
        makeSet(ver,n);

        union(ver.get(2), ver.get(0));
        union(ver.get(1), ver.get(3));
        
        System.out.println(find(ver.get(3)).value);
        System.out.println(find(ver.get(0)).value);
        
        union(ver.get(3), ver.get(0));
        
        System.out.println(find(ver.get(3)).value);
        System.out.println(find(ver.get(0)).value);

    }
}

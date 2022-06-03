import java.util.*;
public class mColoring {
    public static boolean graphcoloring(List<Integer>[] G, int[] color, int i, int m) {
        
        if(i > 0) {
            // check adjancent of previous node are not of same color
            for(int k : G[i-1]){
                if(color[k] == color[i-1]) {
                    return false;
                }
            }
        }
        if(i == color.length) {
            return true;
        }
        for(int j = 1; j <= m; j++){
            color[i] = j;
            if(graphcoloring(G, color, i+1, m)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
    }
}

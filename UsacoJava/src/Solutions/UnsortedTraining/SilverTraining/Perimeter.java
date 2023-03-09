package Solutions.UnsortedTraining.SilverTraining;/*
USACO 2013 February Contest, Silver
Problem 1. Perimeter
USACO Silver Training
Concepts: O(1) lookup table, conservative floodfill techniques
 */
import java.io.*;
import java.util.*;
public class Perimeter {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static HashMap<Integer, HashMap<Integer, Boolean>> haybales = new HashMap<>();
    static HashMap<Integer, HashMap<Integer, Boolean>> visited = new HashMap<>();
    static int N;
    static int perimeter = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("perimeter.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        int out_x = 0;
        int out_y = 0;
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            add(haybales, x, y);
            if (x > out_x){
                out_x=x;
                out_y=y;
            }
        }
        out_x++;
        //unit test
        if (!submission) {
            out.println("begin: " + "[" + out_x + ", " + out_y + "]");
            out.println(haybales);
        }
        //logic
        //outdoor floodfill
        floodfill(out_x, out_y);
        //turn in answer
        out.println(perimeter);
        out.close();
    }
    static int[] dx = {0,0,1,-1,-1,-1,1,1};
    static int[] dy = {1,-1,0,0,1,-1,1,-1};
    public static void floodfill(int x, int y){
        if (contains(visited, x, y)) return;
        add(visited, x, y);
        if (contains(haybales, x, y) || isolated(x,y)) return;
        for (int i=0;i<4;i++) {
            if (contains(haybales, x+dx[i],y+dy[i])) perimeter++;
        }
        for (int i=0;i<4;i++) {
            floodfill(x+dx[i],y+dy[i]);
        }
    }
    public static boolean isolated(int x, int y){
        for (int i=0;i<8;i++) {
            if (contains(haybales, x+dx[i],y+dy[i])) return false;
        }
        return true;
    }
    public static boolean contains(HashMap<Integer, HashMap<Integer, Boolean>> map, int x, int y) {
        return map.containsKey(x) && map.get(x).containsKey(y);
    }
    public static void add(HashMap<Integer, HashMap<Integer, Boolean>> map, int x, int y) {
        if (!map.containsKey(x)) {
            map.put(x, new HashMap<>());
        }
        map.get(x).put(y, true);
    }
}

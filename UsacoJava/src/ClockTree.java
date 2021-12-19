import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Silver
Problem 3. Clock Tree
 */
public class ClockTree {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Room[] barn;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("clocktree.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());

        //logic
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
    private static class Room {
        int clock;
        int id;
        ArrayList<Room> connected = new ArrayList<>();
        public Room(int c, int i){
            clock=c;
            id=i;
        }
        public void tick(int n){
            clock = (clock+n-1)%12+1;
        }
    }
}

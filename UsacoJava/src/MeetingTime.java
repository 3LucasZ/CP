import java.io.*;
import java.util.*;
/*
USACO 2015 January Contest, Silver
Problem 3. Meeting Time
USACO Silver Training
 */
public class MeetingTime {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
    }
}

package Bronze.UsacoGuideBronze.Simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SpeedingTicket {
    //param
    static int highwayNum;
    static int bessieNum;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("speeding.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        highwayNum = Integer.parseInt(st.nextToken());
        bessieNum = Integer.parseInt(st.nextToken());
        ArrayList<Integer> highway = new ArrayList<Integer>();
        ArrayList<Integer> bessie = new ArrayList<Integer>();
        for (int j=0;j<highwayNum;j++) {
            st = new StringTokenizer(f.readLine());
            int len = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for (int i=0;i<len;i++) {
                highway.add(speed);
            }
        }
        for (int j=0;j<bessieNum;j++) {
            st = new StringTokenizer(f.readLine());
            int len = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for (int i=0;i<len;i++) {
                bessie.add(speed);
            }
        }
        int max = 0;
        for (int i=0;i<100;i++) {
            max = Math.max(max, bessie.get(i) - highway.get(i));
        }
        //logic
        System.out.println(highway);
        System.out.println(bessie);
        //turn in answer
        out.println(max);
        out.close();
        f.close();
    }
    public static void PrintArray(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}
class Segment {
    int len;
    int speed;
    public Segment(int x, int y) {
        len = x;
        speed = y;
    }
    public String toString() {
        return "(" + len + ", " + speed + ")";
    }
}

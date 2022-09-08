package USACO.Bronze.Open2020;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SocialDistancing2 {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("socdist2.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("socdist2.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        Cow[] cows = new Cow[n];
        //logic
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), st.nextToken().equals("1"));
        }
        Arrays.sort(cows, (a, b) -> a.position - b.position);
        int radius = Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            if (!cows[i].isSick) {
                if (i>0 && cows[i-1].isSick) {
                    radius = Math.min(radius, cows[i].position - cows[i-1].position);
                }
                if (i<n-1 && cows[i+1].isSick) {
                    radius = Math.min(radius, cows[i+1].position - cows[i].position);
                }
            }
        }

        radius --;
        int counter = 1;
        for (int i=0;i<n-1;i++) {
            if (cows[i].isSick && !cows[i+1].isSick) {
                counter ++;
            }
            if(cows[i].isSick && cows[i+1].isSick && cows[i+1].position - cows[i].position > radius) {
                counter ++;
            }
        }
        if (!cows[n-1].isSick) counter --;
        //printArr(cows);
        //System.out.println(radius);

        //turn in answer
        out.println(counter);
        out.close();
        f.close();
    }
    public static void printArr(Object[] arr){
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i].toString() + ", ");
        }
        System.out.println(str);
    }
}
class Cow {
    int position;
    boolean isSick;
    public Cow(int p, boolean i) {
        position = p;
        isSick = i;
    }
    public String toString() {
        return position + ": " + isSick;
    }
}
/*
6
1 0
2 1
3 0
4 1
5 0
6 1
 */
package USACO.Bronze.RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

public class combo {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        int N = Integer.parseInt(f.readLine());

        StringTokenizer a = new StringTokenizer(f.readLine());
        int farmAr[] = {Integer.parseInt(a.nextToken()),Integer.parseInt(a.nextToken()),Integer.parseInt(a.nextToken())};
        a = new StringTokenizer(f.readLine());
        int masterAr[] = {Integer.parseInt(a.nextToken()),Integer.parseInt(a.nextToken()),Integer.parseInt(a.nextToken())};

        int repeats = 0;
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                for (int k=1;k<=N;k++) {
                    if ((distance(i,farmAr[0],N) <= 2 && distance(i,masterAr[0],N) <= 2) && (distance(j,farmAr[1],N) <= 2 && distance(j,masterAr[1],N) <= 2) && (distance(k,farmAr[2],N) <= 2 && distance(k,masterAr[2],N) <= 2)) {
                        repeats += 1;
                    }
                }
            }
        }
        if (N > 5) {
            out.println(250 - repeats);
        }
        else {
            out.println((2 * (int) Math.pow(N, 3)) - repeats);
        }
        out.close();
    }
    public static int distance(int a,int b,int n) {
        if (Math.abs(a-b) < n - Math.abs(a-b)){
            return Math.abs(a-b);
        }
        else {
            return n - Math.abs(a-b);
        }
    }
}

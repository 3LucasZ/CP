package Procrastinate;

import java.io.*;
import java.util.StringTokenizer;

/*
USACO 2014 December Contest, Silver
Problem 2. Marathon
USACO Silver Training
Concepts: DP Normal
WORK IN PROGRESS BC TLE!!!
 */
public class Marathon {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int x[];
    static int y[];
    static int travel[][];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("marathon.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        x = new int[N];
        y = new int[N];
        travel = new int[N][K+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        calculateTravel(0, 0, 0, 0);
        //printDP();
        //turn in answer
        out.println(travel[N-1][K]);
        out.close();
    }
    public static void calculateTravel(int net, int start, int end, int skips){
        //outta bounds
        if (skips > K || start >= N || end >= N) return;
        net += Math.abs(x[start]-x[end]) + Math.abs(y[start]-y[end]);
        if (travel[end][skips] != 0 && travel[end][skips] < net) return;
        travel[end][skips] = net;
        for (int i=1;i<=K-skips+1;i++) {
            calculateTravel(net, end, end+i,skips+i-1);
        }
    }
    public static void printDP(){
        for (int r=0;r<N;r++) {
            for (int c=0;c<=K;c++){
                if (travel[r][c] > 9) out.print(travel[r][c] + " ");
                else out.print(travel[r][c] + "  ");
            }
            out.println();
        }
        out.println();
    }
}

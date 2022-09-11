package USACO.Gold.Dec2021;

import java.io.*;
import java.util.*;

public class HILO2 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] valueOf;
    static int[] indexOf;
    static Node[] nodeOf;
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        valueOf = new int[N+1];
        indexOf = new int[N+1];
        nodeOf = new Node[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            valueOf[i]=Integer.parseInt(st.nextToken());
            nodeOf[i]=new Node(valueOf[i]);
            indexOf[valueOf[i]]=i;
        }
        indexOf[0]=N+1;

        //LO monotonic stack tracer
        Stack<Integer>[] LO = new Stack[N+1]; LO[0] = new Stack<>();
        for (int x=1;x<=N;x++){
            LO[x]=(Stack<Integer>)LO[x-1].clone();
            while (!LO[x].isEmpty() && LO[x].peek()>indexOf[x]) LO[x].pop();
            LO[x].add(indexOf[x]);
        }


        //HI monotonic stack tracer
        Stack<Integer>[] HI = new Stack[N+1]; HI[N]= new Stack<>();
        for (int x=N-1;x>=0;x--){
            HI[x]=(Stack<Integer>)HI[x+1].clone();
            while (!HI[x].isEmpty() && HI[x].peek()>indexOf[x+1]) HI[x].pop();
            HI[x].add(indexOf[x+1]);
        }


        if (debug) {
            for (int i=0;i<=N;i++) {
                System.out.print("LO: " + LO[i] + " ");
                System.out.print("HI: " + HI[i]);
                System.out.println();
            }
        }

        //HILO merging
        for (int x=0;x<=N;x++){
            int ans = 0;
            boolean lastLO = false;
            while (!LO[x].isEmpty() || !HI[x].isEmpty()){
                if (LO[x].isEmpty()) {
                    HI[x].pop();
                    if (lastLO) {ans++;}
                    lastLO=false;
                }else if (HI[x].isEmpty()) {
                    LO[x].pop();
                    lastLO=true;
                }
                else if (HI[x].peek()>LO[x].peek()){
                    HI[x].pop();
                    if (lastLO) {ans++;}
                    lastLO=false;
                }
                 else {
                    LO[x].pop();
                    lastLO=true;
                }
            }
            out.println(ans);
        }
        out.close();
    }
    private static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value=value;
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}

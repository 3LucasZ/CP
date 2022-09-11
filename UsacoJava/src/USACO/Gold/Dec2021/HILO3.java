package USACO.Gold.Dec2021;

import java.io.*;
import java.util.*;

public class HILO3 {
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
        Stack<Integer> LO = new Stack<Integer>();
        ArrayList<Integer>[] remLO = new ArrayList[N+1]; for (int i=0;i<=N;i++) remLO[i] = new ArrayList<>();
        for (int x=1;x<=N;x++){
            while (!LO.isEmpty() && LO.peek()>indexOf[x]) remLO[x].add(LO.pop());
            LO.add(indexOf[x]);
        }

        //HI monotonic stack tracer
        Stack<Integer> HI = new Stack<Integer>();
        ArrayList<Integer>[] addHI = new ArrayList[N+1]; for (int i=0;i<=N;i++) addHI[i] = new ArrayList<Integer>();
        for (int x=N;x>=1;x--){
            while (!HI.isEmpty() && HI.peek()>indexOf[x]) addHI[x].add(HI.pop());
            HI.add(indexOf[x]);
        }
        while (!HI.isEmpty()) addHI[0].add(HI.pop());

        if (debug){
            for (int i=0;i<=N;i++){
                System.out.print(i+": remLO: "+remLO[i]+" ");
                System.out.print("addHI: "+addHI[i]);
                System.out.println();
            }
        }

        //simulate setup: x=0
        //true = HI false = LO
        out.println(0);
        for (int add : addHI[0]) map.put(add,true);

        //simulate
        if (debug) System.out.println(map);
        for (int x=1;x<=N;x++){
            //add hi rem hi_x
            for (int add : addHI[x]) add(add,true);
            rem(indexOf[x], true);

            //rem lo add lo_x
            for (int rem : remLO[x]) rem(rem,false);
            add(indexOf[x], false);

            //ret
            out.println(ans);
            if (debug) System.out.println(map+": "+ans);
        }

        out.close();
    }
    static TreeMap<Integer,Boolean> map = new TreeMap<>();
    static int ans = 0;
    public static void add(int num, boolean type){
        Integer lower = map.lowerKey(num);
        Boolean l = lower==null?null:map.get(lower);
        Integer higher = map.higherKey(num);
        Boolean r = higher==null?null:map.get(higher);
        if (l!=null && r!=null && l==true && r==false) ans--;
        if (l!=null && l==true && type==false) ans++;
        if (r!=null && type==true && r==false) ans++;
        map.put(num,type);
    }
    public static void rem(int num, boolean type){
        Integer lower = map.lowerKey(num);
        Boolean l = lower==null?null:map.get(lower);
        Integer higher = map.higherKey(num);
        Boolean r = higher==null?null:map.get(higher);
        if (l!=null && r!=null && l==true && r==false) ans++;
        if (l!=null && l==true && type==false) ans--;
        if (r!=null && type==true && r==false) ans--;
        map.remove(num);
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

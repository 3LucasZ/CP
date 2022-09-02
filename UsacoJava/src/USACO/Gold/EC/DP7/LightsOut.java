package USACO.Gold.EC.DP7;

import java.io.*;
import java.util.*;
/*
Lights Out
Gold Advanced B - DP - Optimization problem
Thoughts:
really easy logic: each position is either toggle or not and keep states, calculating time and space easy
the implementation though... mine is extremely messy... can be way better if wasnt so lazy
 */
public class LightsOut {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int L;
    static int T;
    static boolean[] lights;
    static boolean[] fork;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        lights = new boolean[L];
        T = Integer.parseInt(st.nextToken());
        fork = new boolean[T];
        String str1 = br.readLine();
        for (int i=0;i<L;i++){
            lights[i]=str1.charAt(i)=='1';
        }
        String str2 = br.readLine();
        for (int i=0;i<T;i++){
            fork[i]=str2.charAt(i)=='1';
        }
        if (debug){
            System.out.println(Arrays.toString(lights));
            System.out.println(Arrays.toString(fork));
        }
        //logic
        HashSet<Item> states = new HashSet<>();
        int initOnes=0;
        for (int i=0;i<T-1;i++) if (lights[i]) initOnes++;
        boolean initBits[] = new boolean[T-1];
        for (int i=0;i<T-1;i++) initBits[i] = lights[i];
        int initForks = 0;
        states.add(new Item(initBits, initOnes, initForks));

        for (int i=T-1;i<L;i++){
            if (debug) System.out.println(states);
            HashSet<Item> nextStates = new HashSet<>();
            for (Item state : states){
                boolean[] scope = new boolean[T];
                for (int j=0;j<T-1;j++) scope[j]=state.bits[j];
                scope[T-1]=lights[i];
                int nextOnes = state.ones + (lights[i]?1:0);;

                //opt1: dont do anything
                boolean[] nextBits = new boolean[T-1];
                for (int j=1;j<T;j++) nextBits[j-1]=scope[j];
                nextStates.add(new Item(nextBits, nextOnes, state.forks));

                //opt1: toggle
                nextBits = new boolean[T-1];
                for (int j=0;j<T;j++){
                    if (fork[j]){
                        if (scope[j])nextOnes--;
                        else nextOnes++;
                        scope[j]=!scope[j];
                    }
                }
                for (int j=1;j<T;j++) nextBits[j-1]=scope[j];
                nextStates.add(new Item(nextBits, nextOnes, state.forks+1));
            }
            states=nextStates;
        }
        if (debug) System.out.println(states);

        //turn in answer
        Item min = new Item(new boolean[]{},Integer.MAX_VALUE,Integer.MAX_VALUE);
        for (Item state : states){
            if (state.ones < min.ones) min = state;
            if (state.ones == min.ones && state.forks < min.forks) min = state;
        }
        out.println(min.forks);
        out.close();
    }
    private static class Item {
        boolean[] bits;
        int ones;
        int forks;

        public Item(boolean[] b, int o, int f){
            bits=b;
            ones=o;
            forks=f;
        }

        @Override
        public boolean equals(Object o){
            if (this==o) return true;
            if (o==null) return false;
            if (this.getClass()!=o.getClass()) return false;
            Item other = (Item) o;
            return Arrays.equals(bits, other.bits) && ones==other.ones;
        }

        @Override
        public int hashCode(){
            int sum=0;
            for (int i=0;i<bits.length;i++) if (bits[i]) sum+=Math.pow(2,i);
            return forks*60000+ones*1000+sum;
        }

        public String toString(){
            return "["+Arrays.toString(bits)+", "+ones+", "+forks+"]";
        }
    }
}

import java.io.*;
import java.util.*;

public class LightsOut {
    //io
    static boolean submission = false;
    static boolean debug = true;
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

        //turn in answer
        out.println();
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
            return ones*128+sum;
        }
    }
}

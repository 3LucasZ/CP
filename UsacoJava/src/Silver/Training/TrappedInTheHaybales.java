package Silver.Training;

import java.io.*;
import java.util.*;
/*
USACO 2015 US Open, Silver
Problem 2. Trapped in the Haybales (Silver)
USACO Silver Training
Concepts: edge casing, sweep line, starting with basic algorithm -> going to efficient algorithm
 */
public class TrappedInTheHaybales {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int B;
    static Haybale[] haybales;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("trapped.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        haybales = new Haybale[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            Haybale h = new Haybale(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            haybales[i] = h;
        }
        Arrays.sort(haybales,(a,b)->a.position-b.position);
        //out.println(Arrays.toString(haybales));
        //edge cases :)
        if (B < haybales[0].position || B > haybales[N-1].position ) {
            out.println(-1);
            out.close();
            return;
        }
        //logic
        int l;
        int r;
        for (l=0;l<N;l++) {
            if (B >= haybales[l].position && B <= haybales[l+1].position) break;
        }
        r = l+1;
        int[] toAdd = new int[N];

        //left sweep
        int r_pointer = r;
        for (int l_pointer=l;l_pointer>=0;l_pointer--) {
            while (haybales[r_pointer].position-haybales[l_pointer].position>haybales[r_pointer].size) {
                r_pointer++;
                if (r_pointer >= N) break;
            }
            if (r_pointer >= N) {
                break;
            }
            int add = haybales[r_pointer].position-haybales[l_pointer].position - haybales[l_pointer].size;
            if (add <= 0) {
                out.println(0);
                out.close();
                return;
            }
            toAdd[l_pointer] = add;
//            out.println(l_pointer);
//            out.println(r_pointer);
//            out.println();
        }
//        out.println(Arrays.toString(toAdd));

        int l_pointer = l;
        for (r_pointer=r;r_pointer<=N-1;r_pointer++){
            while (haybales[r_pointer].position-haybales[l_pointer].position>haybales[l_pointer].size) {
                l_pointer--;
                if (l_pointer < 0) break;
            }
            if (l_pointer < 0) {
                break;
            }
            int add = haybales[r_pointer].position-haybales[l_pointer].position - haybales[r_pointer].size;
            if (add <= 0) {
                out.println(0);
                out.close();
                return;
            }
            toAdd[r_pointer] = add;
//            out.println(l_pointer);
//            out.println(r_pointer);
//            out.println();
        }
//        out.println(Arrays.toString(toAdd));
        int min_bales = Integer.MAX_VALUE;
        for (int i=0;i<N;i++) {
            if (toAdd[i] != 0){
                min_bales = Math.min(min_bales, toAdd[i]);
            }
        }
        //turn in answer
        if (min_bales == Integer.MAX_VALUE) out.println(-1);
        else out.println(min_bales);
        out.close();
    }
    private static class Haybale {
        int position;
        int size;
        public Haybale(int s, int p){
            position = p;
            size = s;
        }
        public String toString(){
            return "["+position+": "+size+"]";
        }
    }
}

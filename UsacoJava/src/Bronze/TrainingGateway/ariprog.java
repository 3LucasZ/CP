package Bronze.TrainingGateway;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: ariprog
USACO Training Revisited

USACO Training
Complete Search
Read problem statement carefully!!!!
Complexity: O(M*M) + O(M*M*M*M*N/N)
Complexity seems really large but we can reason from good constant factors and if (lastSearch > max) break that it will still work :)
 */
public class ariprog {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static boolean[] bisquare;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("ariprog.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        //find all bisquares
        int max = 2*M*M;
        bisquare = new boolean[max+1];
        for (int p=0;p<=M;p++) {
            for (int q=p;q<=M;q++) {
                bisquare[p*p+q*q]=true;
            }
        }
        //search for arith progressions (efficiently) and store them in ans pairs
        ArrayList<Pair> ans = new ArrayList<>();
        for(int a=0;a<=max;a++){
            for(int b=1;b<=max;b++){
                int lastSearch = a+(N-1)*b;
                if (lastSearch>max) break;
                boolean good = true;
                for (int term=0;term<N;term++){
                    if (!bisquare[a+term*b]) {
                        good=false;
                        break;
                    }
                }
                if (good) ans.add(new Pair(a,b));
            }
        }
        //turn in ans
        Collections.sort(ans,(a,b)->a.b-b.b);
        if (ans.size()==0) out.println("NONE");
        for(Pair p : ans) out.println(p);
        out.close();
    }
    private static class Pair {
        int a;
        int b;
        public Pair(int a, int b){
            this.a=a;
            this.b=b;
        }
        public String toString(){
            return a+" "+b;
        }
    }
}

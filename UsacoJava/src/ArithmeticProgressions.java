import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: skidesign
USACO Training Revisited
USACO Training
Complete Search
 */
public class ArithmeticProgressions {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    //const
    static double epsilon = 0.0000000000001;
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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        //logic
        ArrayList<Pair> ans = new ArrayList<>();
        for (int p=0;p<M;p++) {
            for (int q=p;q<M;q++) {
                int S = p*p+q*q;
                for (int b=1;b<=2*S;b++) {
                    double a = ((2.0*S/N)-(N+1)*b)/2.0;
                    if (a >= 0 && Math.abs(a-Math.round(a)) < epsilon) {
                        ans.add(new Pair((int) Math.round(a),b));
                    }
                }
            }
        }
        //turn in answer
        Collections.sort(ans, (a,b)->a.b-b.b);
        for (int i=0;i<ans.size();i++){
            out.println(ans.get(i));
        }
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

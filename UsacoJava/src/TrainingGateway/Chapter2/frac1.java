package TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: frac1
 */
public class frac1 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static ArrayList<Fraction> fracs = new ArrayList<>();
    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("frac1.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1][N+1];
        for (int den=1;den<=N;den++){
            for (int num=0;num<=den;num++) {
                Fraction next = new Fraction(num,den);
                if (!visited[next.num][next.den]){
                    visited[next.num][next.den]=true;
                    fracs.add(next);
                }
            }
        }
        //logic: sort
        Collections.sort(fracs,(a,b)->{
            return a.num*b.den-b.num*a.den;
        });
        //turn in answer
        for (Fraction frac : fracs) out.println(frac);
        out.close();
    }
    private static class Fraction {
        int num;
        int den;
        public Fraction(int num, int den){
            this.num=num;
            this.den=den;
            simplify();
        }
        public void simplify(){
            int div = gcd(num,den);
            num/=div;
            den/=div;
        }
        public int gcd(int a, int b){
            while (a>0&&b>0){
                if (a > b) a-=b;
                else b-=a;
            }
            return Math.max(a,b);
        }
        public String toString(){
            return num+"/"+den;
        }
    }
}

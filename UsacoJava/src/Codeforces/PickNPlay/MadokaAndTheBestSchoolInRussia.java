package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class MadokaAndTheBestSchoolInRussia {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //prime process
    static ArrayList<Integer> primes = new ArrayList<>();
    static int MAXPRIME = (int)1e5;

    public static void main(String[] args) throws IOException {
        //prime process
        primes.add(2);
        for (int i=3;i<=MAXPRIME;i++){
            if (isPrime(i)) primes.add(i);
        }
        if (debug) System.out.println(primes);

        //tcs handle
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve()?"YES":"NO");
        out.close();
    }

    public static boolean solve() throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int Xs = 0;
        while (X%D==0) {X/=D;Xs++;}
        if (debug) System.out.println(Xs);

        int PFs = 0;
        for (int i=2;i*i<=X;i++){
            while (X%i==0) {
                X/=i;
                PFs++;
            }
        }

        if (Xs == 1) return false;
        if (Xs == 2 && PFs < 2) return false;
        if (isPrime(D) && PFs < 2) return false;


        return true;
    }

    static boolean isPrime(int num){
        for (int p=0;primes.get(p)*primes.get(p)<=num;p++) {
            if (num%primes.get(p)==0) return false;
        }
        return true;
    }
}

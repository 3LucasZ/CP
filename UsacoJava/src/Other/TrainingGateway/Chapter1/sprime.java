package Other.TrainingGateway.Chapter1;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: sprime
 */

public class sprime {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static ArrayList<Integer> primes = new ArrayList<>();
    static ArrayList<Integer> superPrimes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("sprime.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input and gen primes
        N = Integer.parseInt(br.readLine());
        genPrimes();
        if (!submission) System.out.println(primes);
        //logic
        superPrimes.add(0);
        for (int i=0;i<N;i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int sp : superPrimes){
                for (int dig=1;dig<=9;dig++){
                    if (isPrime(sp*10+dig)){
                        temp.add(sp*10+dig);
                    }
                }
            }
            superPrimes=temp;
        }
        //ret ans
        for (int sp : superPrimes)out.println(sp);
        out.close();
    }
    public static void genPrimes(){
        primes.add(2);
        for (int i=3;i<1e5;i++) if (isPrime(i)) primes.add(i);
    }
    public static boolean isPrime(int n){
        if (n==1) return false;
        for (int prime : primes){
            if (prime*prime>n) break;
            if (n%prime==0) return false;
        }
        return true;
    }
}

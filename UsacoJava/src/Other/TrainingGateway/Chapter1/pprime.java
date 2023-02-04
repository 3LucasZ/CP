package Other.TrainingGateway.Chapter1;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: pprime
Thoughts: prime(n) is true if n%num!= 0 for all primes < sqrt(n)
 */
public class pprime {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //prime
    static ArrayList<Integer> primes = new ArrayList<>();
    static TreeSet<Integer> palindromes = new TreeSet<>();
    static int A;
    static int B;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("pprime.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        //setup
        genPrimes();
        genPalindromes();
        //unit test
        if (!submission){
            System.out.println(digitsOf(123094));
            System.out.println(primes);
            System.out.println(primes.size());
            System.out.println(palindromes);
            System.out.println(palindromes.size());
        }
        //logic
        primePalindromes(A,B);
        out.close();
    }
    public static void primePalindromes(int a, int b){
        for (int palindrome : palindromes){
            if (palindrome < a) continue;
            if (palindrome > b) break;
            if (isPrime(palindrome)) out.println(palindrome);
        }
    }
    public static void genPalindromes(){
        for (int test=1;test<=9999;test++){
            ArrayList<Integer> arr = new ArrayList<>();
            int tmp = test;
            while (tmp != 0){
                arr.add(0,tmp%10);
                tmp/=10;
            }
            int dig = arr.size();
            int[] ret1 = new int[dig*2];
            int[] ret2 = new int[dig*2-1];
            for (int i=0;i<dig;i++){
                ret1[i]=arr.get(i);
                ret1[ret1.length-1-i]=arr.get(i);
                ret2[i]=arr.get(i);
                ret2[ret2.length-1-i]=arr.get(i);
            }
            int num1 = 0;
            for (int i=0;i<ret1.length;i++){
                num1+=ret1[i]*(Math.pow(10,i));
            }
            int num2 = 0;
            for (int i=0;i<ret2.length;i++){
                num2+=ret2[i]*(Math.pow(10,i));
            }
            palindromes.add(num1);
            palindromes.add(num2);
        }
    }
    public static void genPrimes(){
        primes.add(2);
        for (int i=3;i<=10000;i++){
            if (isPrime(i)) primes.add(i);
        }
    }
    public static boolean isPrime(int n){
        for (int prime : primes){
            if (prime*prime > n) break;
            if (n%prime==0) return false;
        }
        return true;
    }
    public static int digitsOf(int n){
        return (int)(Math.log(n)/Math.log(10))+1;
    }
}

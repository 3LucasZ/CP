package Helper.NT;

import java.util.ArrayList;

public class SievePrimes {
    public static void main(String[] args){
        ArrayList<Integer> primes = primesLEQ(5000);
        System.out.println(primes);
    }
    public static ArrayList<Integer> primesLEQ(int n){
        boolean[] prime = new boolean[n+1]; for (int i=2;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (int p=2;p<=n;p++){
            if (prime[p]) ret.add(p);
        }
        return ret;
    }
}

package Experiments.Logic;

public class DecimalBinarySearch {
    public static void main(String[] args) {
        double lo = 1e-12;
        double hi = 1e12;
        double epsilon = 1e-12;
        long ops = 0;
        System.out.println("lo: "+lo+", hi: "+hi);
        while (lo+epsilon<hi){
            lo = (lo+hi)/2;
            ops++;
        }
        System.out.println("Operations: "+ops);
    }
}

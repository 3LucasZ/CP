package Solutions.USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;/*
USACO 2018 January Contest, Silver
Problem 2. Rental Service
USACO Guide Silver Custom Comparators
MAIN CONCEPTS:
occasional integer overflow -> USE LONG, READ PROBLEM CAREFULLY
use long for final value and even intermediate values
prefix sums before complete search -> O(N) time instead of O(N^2)
Custom comparators, sorting logic, sorting problem solving
don't overthink and just bash if necessary
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RentalService {
    static class Store implements Comparable<Store> {
        int capacity;
        int price;
        public Store(int c, int p) {capacity = c; price = p;}
        public int compareTo(Store other) {
            return Integer.compare(price, other.price);
        }
        public String toString() {
            return "(" + capacity + ": " + price + "c)";
        }
    }
    //param
    static int numCows;
    static int[] cows;
    static int numStores;
    static Store[] stores;
    static long[] profitFromStores;
    static int numFarmers;
    static int[] farmers;
    static long[] profitFromFarmers;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("rental.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        numCows = Integer.parseInt(st.nextToken());
        cows = new int[numCows];
        numStores = Integer.parseInt(st.nextToken());
        stores = new Store[numStores];
        numFarmers = Integer.parseInt(st.nextToken());
        farmers = new int[numFarmers];
        for (int i=0;i<numCows;i++) {
            cows[i] = Integer.parseInt(f.readLine());
        }
        for (int i=0;i<numStores;i++) {
            st = new StringTokenizer(f.readLine());
            stores[i] = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i=0;i<numFarmers;i++) {
            farmers[i] = Integer.parseInt(f.readLine());
        }
        //normalize
        Arrays.sort(cows);
        Arrays.sort(stores);
        Arrays.sort(farmers);
        //logic:
        // let i be number of max cows used on stores.
        // let N-i be number of min cows used on farmers.
        // test out all i from 0 to N
        profitFromFarmers = new long[numCows+1];
        profitFromFarmers[0] = 0;
        for (int i=0;i<numCows;i++) {
            if (i<=numFarmers-1) {
                profitFromFarmers[i + 1] = profitFromFarmers[i] + farmers[numFarmers - 1 - i];
            }
            else {
                profitFromFarmers[i+1] = -1;
            }
        }
        profitFromStores = new long[numCows+1];
        profitFromStores[0] = 0;
        int storeIndex = numStores-1;
        for (int i=0;i<numCows;i++) {
            long newProfit = 0;
            while (cows[numCows - 1 - i] > 0) {
                if (storeIndex < 0) {
                    if (newProfit == 0) {
                        profitFromStores[i + 1] = -1;
                    }
                    break;
                }
                //cow milk less than most expensive store capacity
                if (cows[numCows - 1 - i] < stores[storeIndex].capacity) {
                    //update profit made
                    newProfit += cows[numCows - 1 - i] * stores[storeIndex].price;
                    //reduce store capacity
                    stores[storeIndex].capacity -= cows[numCows - 1 - i];
                    //empty cow
                    cows[numCows - 1 - i] = 0;
                    //cow milk equals most expensive store capacity
                } else if (cows[numCows - 1 - i] == stores[storeIndex].capacity) {
                    //update profit made
                    newProfit += cows[numCows - 1 - i] * stores[storeIndex].price;
                    //empty cow
                    cows[numCows - 1 - i] = 0;
                    //move on to next store
                    storeIndex--;
                    //cow milk more than most expensive store capacity
                } else {
                    //update profit made
                    newProfit += stores[storeIndex].capacity * stores[storeIndex].price;
                    //reduce cow
                    cows[numCows - 1 - i] -= stores[storeIndex].capacity;
                    //move on to next store
                    storeIndex--;
                }
            }
            if (profitFromStores[i+1]!=-1) profitFromStores[i+1] = profitFromStores[i] + newProfit;
        }
        //unit testing checkpoint
        //printArr(cows);
        //printArr(stores);
        //printArr(farmers);
        //printArr(profitFromFarmers);
        //printArr(profitFromStores);
        //turn in answer
        long max = 0;
        for (int i=0;i<=numCows;i++) {
            if (profitFromFarmers[i] != -1 && profitFromStores[numCows - i] != -1) {
                max = Math.max(max, profitFromFarmers[i] + profitFromStores[numCows - i]);
            }
        }
        out.println(max);
        out.close();
        f.close();
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void printArr(Object[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}

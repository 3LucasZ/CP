package Solutions.USACO.Season2018_2019.Dec2018.Bronze;

import java.io.*;
import java.util.*;

public class BackAndForth {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n = 10;
    static ArrayList<Integer> buckets1 = new ArrayList<Integer>();
    static ArrayList<Integer> buckets2 = new ArrayList<Integer>();
    //mediterranean
    static TreeSet<Integer> netSums = new TreeSet<Integer>();

    public static void main(String[] args) throws IOException {
        //setup
        String str1;
        String str2;
        if (submission) {
            f = new BufferedReader(new FileReader("backforth.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
            str1 = f.readLine();
            str2 = f.readLine();
        }
        else {
            str1 = "1 1 1 1 1 1 1 1 1 2";
            str2 = "5 5 5 5 5 5 5 5 5 5";
        }
        StringTokenizer st1 = new StringTokenizer(str1);
        StringTokenizer st2 = new StringTokenizer(str2);
        for (int i=0;i<n;i++) {
            buckets1.add(Integer.parseInt(st1.nextToken()));
            buckets2.add(Integer.parseInt(st2.nextToken()));
        }
        //logic
        bucketSimulate(buckets1, buckets2,true, 1, 0);

        //print
        if (submission) {
            out.println(netSums.size());
            f.close();
            out.close();
        }
        else {
            //System.out.println(netSums);
            System.out.println(netSums.size());
        }
    }
    public static void bucketSimulate(ArrayList<Integer> currentBuckets1, ArrayList<Integer> currentBuckets2, boolean isBarnOne, int trip, int prevSum) {
        if (trip > 4) {
            netSums.add(prevSum);
        }
        else {
            if (isBarnOne) {
                for (int i = 0; i < currentBuckets1.size(); i++) {
                    int chosen = currentBuckets1.get(i);
                    ArrayList<Integer> nextBuckets1 = new ArrayList<Integer>();
                    ArrayList<Integer> nextBuckets2 = new ArrayList<Integer>();
                    nextBuckets1 = (ArrayList<Integer>) currentBuckets1.clone();
                    nextBuckets2 = (ArrayList<Integer>) currentBuckets2.clone();
                    nextBuckets1.remove(Integer.valueOf(chosen));
                    nextBuckets2.add(chosen);
                    bucketSimulate(nextBuckets1, nextBuckets2, false, trip + 1, prevSum - chosen);
                }
            } else {
                for (int i = 0; i < currentBuckets2.size(); i++) {
                    int chosen = currentBuckets2.get(i);
                    ArrayList<Integer> nextBuckets1 = new ArrayList<Integer>();
                    ArrayList<Integer> nextBuckets2 = new ArrayList<Integer>();
                    nextBuckets1 = (ArrayList<Integer>) currentBuckets1.clone();
                    nextBuckets2 = (ArrayList<Integer>) currentBuckets2.clone();
                    nextBuckets1.add(chosen);
                    nextBuckets2.remove(Integer.valueOf(chosen));
                    bucketSimulate(nextBuckets1, nextBuckets2, true, trip + 1, prevSum + chosen);
                }
            }
        }
    }
}
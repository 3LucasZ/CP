package USACOGuide.UsacoGuideBronze.Recursion;/*
USACO 2019 December Contest, Bronze
Problem 3. Livestock Lineup
USACO Guide Bronze Complete Search with Recursion Hard
Concepts: generating permutations of a set, accurate bash, comparing arraylists to find the "max"
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LivestockLineup {
    static boolean submission = true;
    //param
    static int n;
    static Pair[] constraints;
    static boolean[] chosen = new boolean[8];
    static String[] cows = {"Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"};
    static ArrayList<Integer> permutation = new ArrayList<Integer>();
    static ArrayList<ArrayList<Integer>> passingPermutations = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("lineup.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        constraints = new Pair[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String cow1 = st.nextToken();
            for (int j=0;j<4;j++) {
                st.nextToken();
            }
            String cow2 = st.nextToken();
            constraints[i] = new Pair(cow1, cow2);
        }
        //logic
        permute();
        ArrayList<Integer> bestPermuation = passingPermutations.get(0);
        for (ArrayList<Integer> p : passingPermutations) {
            for (int i=0;i<8;i++) {
                if (cows[bestPermuation.get(i)].compareTo(cows[p.get(i)]) == 0) continue;
                else if (cows[bestPermuation.get(i)].compareTo(cows[p.get(i)]) < 0) break;
                else bestPermuation = p;
            }
        }
        for (int i=0;i<8;i++) {
            out.println(cows[bestPermuation.get(i)]);
        }
        out.close();
        f.close();
    }
    public static void permute() {
        if (permutation.size() == 8) {
            //process permutation
            boolean passAll = true;
            for (Pair constraint : constraints) {
                boolean passOne = false;
                for (int i=0;i<permutation.size();i++) {
                    if (cows[permutation.get(i)].equals(constraint.cow1)) {
                        if (i-1 >= 0 && cows[permutation.get(i-1)].equals(constraint.cow2)) {
                            passOne = true;
                        }
                        if (i+1 < permutation.size() && cows[permutation.get(i+1)].equals(constraint.cow2)) {
                            passOne = true;
                        }
                    }
                }
                if (!passOne) passAll = false;
            }
            if (passAll) passingPermutations.add((ArrayList<Integer>) permutation.clone());
        } else {
            for (int i=0;i<8;i++) {
                if (chosen[i]) continue;
                chosen[i] = true;
                permutation.add(i);
                permute();
                chosen[i] = false;
                permutation.remove(permutation.size()-1);
            }
        }
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
}
class Pair {
    String cow1;
    String cow2;
    public Pair(String c1, String c2) {
        cow1 = c1;
        cow2 = c2;
    }
}

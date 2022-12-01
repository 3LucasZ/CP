package UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 November Contest, Silver
Problem 1. Farmer John has no Large Brown Cow
USACO Silver Training
Super messy code :\ 2 cases wrong but logic is completely understood
 */
public class FJHasNoLargeBrownCow {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int combos = 1;
    static ArrayList<TreeSet<String>> adjective = new ArrayList<TreeSet<String>>();
    static ArrayList<ArrayList<String>> type = new ArrayList<ArrayList<String>>();
    static ArrayList<String>[] notOwnedStrings;
    static ArrayList<Integer> notOwnedInts = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("nocow.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 1;
        notOwnedStrings = new ArrayList[N];
        for (int i=0;i<N;i++) {
            StringTokenizer entry = new StringTokenizer(br.readLine());
            notOwnedStrings[i] = new ArrayList<String>();
            //noise
            entry.nextToken(); entry.nextToken(); entry.nextToken(); entry.nextToken();
            int j=0;
            String adj = entry.nextToken();
            while (!adj.equals("cow.")) {
                if (adjective.size() < j + 1) adjective.add(new TreeSet<>());
                adjective.get(j).add(adj);
                notOwnedStrings[i].add(adj);
                adj = entry.nextToken();
                j++;
            };
        }
        //unit testing
        if (!submission) out.println(adjective);
        for (TreeSet<String> set : adjective) {
            type.add(new ArrayList<>(set));
        }
        if (!submission) out.println(type);
        if (!submission) for (int i=0;i<N;i++) out.println(notOwnedStrings[i]);
        if (!submission) out.println();
        for (int i=0;i<type.size();i++) combos*=type.get(i).size();
        if (!submission) out.println(combos);
        if (!submission) for (int i=0;i<combos;i++) out.println(IntToStrings(i));
        if (!submission) for (int i=0;i<N;i++) out.println(StringsToInt(notOwnedStrings[i]));
        //logic
        //convert notOwnedStrings to ints
        for (int i=0;i<N;i++) notOwnedInts.add(StringsToInt(notOwnedStrings[i]));
        Collections.sort(notOwnedInts);
        if (!submission) out.println(notOwnedInts);
        //binary search for the answer
        int lo=0; int hi=combos-1;
        if (!submission) out.println("lo: "+lo+" hi: "+hi);
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (mid >= K - Collections.binarySearch(notOwnedInts, mid) - 1) hi = mid;
            else lo = mid+1;
        }
        //turn in answer
        ArrayList<String> ans = IntToStrings(lo);
        for (int i=0;i<ans.size();i++) {
            out.print(ans.get(i));
            if (i != ans.size()-1) out.print(" ");
        }
        out.close();
    }
    public static int StringsToInt(ArrayList<String> description){
        int multiplier = 1;
        int ret = 0;
        for (int i=description.size()-1;i>=0;i--) {
            int index = Collections.binarySearch(type.get(i), description.get(i));
            ret += index * multiplier;
            multiplier *= type.get(i).size();
        }
        return ret;
    }
    public static ArrayList<String> IntToStrings(int description){
        ArrayList<String> ret = new ArrayList<>();
        for (int i=type.size()-1;i>=0;i--){
            int index = description % type.get(i).size();
            ret.add(type.get(i).get(index));
            description /= type.get(i).size();
        }
        Collections.reverse(ret);
        return ret;
    }
}
/*
Debug case:
3 3
Farmer John has no a b c cow.
Farmer John has no a b cc cow.
Farmer John has no a bb ccc cow.
 */
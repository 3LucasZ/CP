package Other.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2014 February Contest, Silver
Problem 1. Auto-Complete
Extremely fun USACO problem
word logic + binary search + safe (no runtime error) checking
 */
public class AutoComplete {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int W;
    static Entry[] dictionary;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("auto.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dictionary = new Entry[W];
        for (int i=0;i<W;i++) {
            dictionary[i] = new Entry(br.readLine(), i+1);
        }
        //logic
        Arrays.sort(dictionary, (a,b)->a.word.compareTo(b.word));
        if (!submission) for (int i=0;i<W;i++) out.println(dictionary[i]);
        //handle queries
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            String prefix = st.nextToken();
            int first = findFirst(prefix);
            int search = first+index-1;
            if (search < 0 || search >= W || dictionary[search].word.length() < prefix.length() || !dictionary[search].word.substring(0, prefix.length()).equals(prefix)) out.println(-1);
            else out.println(dictionary[first+index-1].id);
        }
        out.close();
    }
    public static int findFirst(String prefix){
        int lo=0;
        int hi=W-1;
        while (lo < hi) {
            int mid = (hi+lo)/2;
            if (dictionary[mid].word.compareTo(prefix) >= 0) hi=mid;
            else lo=mid+1;
        }
        return lo;
    }
    private static class Entry {
        String word;
        int id;
        public Entry(String w, int i){
            word=w;
            id=i;
        }
        public String toString(){
            return "["+id+": "+word+"]";
        }
    }
}

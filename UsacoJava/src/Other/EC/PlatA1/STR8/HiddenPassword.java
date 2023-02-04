package Other.EC.PlatA1.STR8;

import java.io.*;
import java.util.*;
/*
PROB: HiddenPassword
LANG: JAVA
*/
public class HiddenPassword {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static StringBuilder word = new StringBuilder();
    public static void main(String[] args) throws IOException {
        //parse
        setup("HiddenPassword");
        N = Integer.parseInt(br.readLine());
        while (true){
            String s = br.readLine();
            if (s==null||s.equals("")) break;
            word.append(s);
        }
        //"hack" the word
        word = word.append(word).append("}");
        //Suffix Array
        SuffixArray sa = new SuffixArray(word.toString());
        if (debug){
            for (int i=0;i<sa.height;i++){
                //System.out.println(i+": "+Arrays.toString(sa.index[i]));
            }
        }
        //Find first 0 in sa
        int i=0;
        for (;i<word.length();i++) if (sa.get(i)==0) break;
        //ret
        out.println(i);
        out.close();
    }
    private static class SuffixArray{
        String str;
        int sz;
        int height=1;
        public int[] index;
        public SuffixArray(String str){
            this.str=str;
            sz=str.length();
            int len=1; while (len<sz) {height++;len*=2;}
            index = new int[sz];
            init();
        }
        public void init(){
            //base
            ArrayList<Pair> pairs = new ArrayList<>();
            for(int l=0;l<sz;l++) pairs.add(new Pair(l,str.charAt(l),0));
            Collections.sort(pairs);
            int in=0;
            for (int l=0;l<sz;l++) {
                if (l>0 && !pairs.get(l).equals(pairs.get(l-1)))in++;
                index[pairs.get(l).id]=in;
            }
            //binary jumping
            int jump = 1;
            for (int i=1;i<height;i++){
                pairs = new ArrayList<>();
                for (int l=0;l<sz;l++){
                    int r=l+jump;
                    int lv = index[l];
                    int rv = r>=sz?-1:index[r];
                    pairs.add(new Pair(l,lv,rv));
                }
                Collections.sort(pairs);
                in=0;
                for (int l=0;l<sz;l++) {
                    if (l>0 && !pairs.get(l).equals(pairs.get(l-1))) in++;
                    index[pairs.get(l).id]=in;
                }
                jump*=2;
            }
        }
        public int get(int i){
            return index[i];
        }
        private static class Pair implements Comparable<Pair>{
            int id;
            int i;
            int j;
            public Pair(int id, int i, int j){
                this.id=id;
                this.i=i;
                this.j=j;
            }

            @Override
            public int compareTo(Pair o) {
                if (i==o.i) return j-o.j;
                return i-o.i;
            }

            @Override
            public boolean equals(Object o){
                Pair other = (Pair)o;
                return i==other.i&&j==other.j;
            }
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
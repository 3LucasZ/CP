package Solutions.TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;
/*
PROB: lgame
LANG: JAVA
*/
public class lgame {
    static boolean submission = true;
    static boolean debug = false;

    //in
    static int N;
    static int[] orig;

    //points, dict
    static Map<Character,Integer> points = new HashMap<>();
    static int K;
    static ArrayList<int[]> freq = new ArrayList<>();
    static ArrayList<Integer> len = new ArrayList<>();
    static ArrayList<String> dict = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //setup
        setup("lgame");
        pre();
        String str = br.readLine();
        N = str.length();
        orig = new int[26];
        for (int i=0;i<N;i++) orig[str.charAt(i)-'a']++;
        //pre comp size sets
        ArrayList<Integer> sizeN = new ArrayList<>();
        ArrayList<Integer> size3 = new ArrayList<>();
        ArrayList<Integer> size4 = new ArrayList<>();
        for (int i=0;i<K;i++) {
            if (len.get(i)==3) size3.add(i);
            if (len.get(i)==4) size4.add(i);
        }
        //dbg
        if (debug){
            System.out.println("N: "+N+", S: "+Arrays.toString(orig));
            for (int i=0;i<K;i++){
                System.out.println(dict.get(i)+": points = "+value(i)+", freq = "+Arrays.toString(freq.get(i)));
            }
        }
        //bash
        int bestPoints = 0;
        ArrayList<Pair> win = new ArrayList<>();
        for (int i=0;i<K;i++){
            if (match(i)) {
                if (value(i)>bestPoints){
                    win = new ArrayList<>();
                    bestPoints = value(i);
                }
                if (value(i)==bestPoints){
                    win.add(new Pair(i));
                }
            }
        }
        //case 1: N=6
        if (N>=6){
            for (int i=0;i<size3.size();i++){
                for (int j=i+1;j<size3.size();j++){
                    int ii=size3.get(i);
                    int jj=size3.get(j);
                    if (match(ii,jj)){
                        int val = value(ii)+value(jj);
                        if (val>bestPoints){
                            win = new ArrayList<>();
                            bestPoints = val;
                        }
                        if (val==bestPoints){
                            win.add(new Pair(ii,jj));
                        }
                    }
                }
            }
        }
        //case 2: N=7
        if (N>=7){
            for (int i=0;i<size3.size();i++){
                for (int j=0;j<size4.size();j++){
                    int ii=size3.get(i);
                    int jj=size4.get(j);
                    if (match(ii,jj)){
                        int val = value(ii)+value(jj);
                        if (val>bestPoints){
                            win = new ArrayList<>();
                            bestPoints = val;
                        }
                        if (val==bestPoints){
                            win.add(new Pair(ii,jj));
                        }
                    }
                }
            }
        }
        //ret
        out.println(bestPoints);
        Collections.sort(win);
        for (Pair w : win){
            out.println(w);
        }
        out.close();
    }
    private static class Pair implements Comparable<Pair> {
        int i;
        int j;
        public Pair(int i){
            this.i=i;
            this.j=-1;
        }
        public Pair (int i, int j){
            this.i=i;
            this.j=j;
            if (dict.get(i).compareTo(dict.get(j))>0){
                this.j=i;
                this.i=j;
            }
        }
        @Override
        public int compareTo(Pair other){
            if (i==other.i){
                return dict.get(j).compareTo(dict.get(other.j));
            } else {
                return dict.get(i).compareTo(dict.get(other.i));
            }
        }
        public String toString(){
            if (j==-1){
                return dict.get(i);
            } else {
                return dict.get(i) + " " + dict.get(j);
            }
        }
    }
    public static void pre() throws IOException {
        //point system
        points.put('q',7);
        points.put('w',6);
        points.put('e',1);
        points.put('r',2);
        points.put('t',2);
        points.put('y',5);
        points.put('u',4);
        points.put('i',1);
        points.put('o',3);
        points.put('p',5);
        points.put('a',2);
        points.put('s',1);
        points.put('d',4);
        points.put('f',6);
        points.put('g',5);
        points.put('h',5);
        points.put('j',7);
        points.put('k',6);
        points.put('l',3);
        points.put('z',7);
        points.put('x',7);
        points.put('c',4);
        points.put('v',6);
        points.put('b',5);
        points.put('n',2);
        points.put('m',5);

        //dict
        BufferedReader gameDict = new BufferedReader(new FileReader("lgame.dict"));
        while (true){
            String str = gameDict.readLine();
            if (str.charAt(0)=='.') break;
            freq.add(new int[26]);
            len.add(str.length());
            for (int i=0;i<str.length();i++){
                freq.get(freq.size()-1)[str.charAt(i)-'a']++;
            }
            dict.add(str);
        }
        K = dict.size();
    }
    //constant time matching
    public static boolean match(int word){
        for (int i=0;i<26;i++){
            if (freq.get(word)[i]>orig[i]) return false;
        }
        return true;
    }
    public static boolean match(int word1, int word2){
        for (int i=0;i<26;i++){
            if (freq.get(word1)[i]+freq.get(word2)[i]>orig[i]) return false;
        }
        return true;
    }
    //constant time evaluation
    public static int value(int word){
        int ret = 0;
        for (int i=0;i<26;i++){
            ret+=points.get((char)('a'+i))*freq.get(word)[i];
        }
        return ret;
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
import java.io.*;
import java.util.*;
/*
PROB: lgame
LANG: JAVA
*/
public class lgame {
    static boolean submission = false;
    static boolean debug = true;

    //in
    static int N;
    static String given;

    //points, dict
    static Map<Character,Integer> points = new HashMap<>();
    static HashSet<Integer> dict = new HashSet<>();

    //hash
    static long A = 2000000011;
    static long B = 2000000033;
    static long[] pow = new long[7];
    public static void main(String[] args) throws IOException {
        //setup
        setup("lgame");
        pre();
        given = br.readLine();
        N = given.length();

        //bash each combo
        perm(new ArrayList<>());
    }
    public static void process(ArrayList<Integer> p){
        long hash = 0;
        for (int i=0;i<p.size();i++) hash=(hash+pow[i]*given.charAt(p.get(i)))%B;
        int hsh = (int) hash;
        
    }
    public static void perm(ArrayList<Integer> p){
        if (p.size()!=0) process(p);
        for (int i=0;i<N;i++){
            boolean good = true;
            for (int j:p){
                if (j == i) {
                    good = false;
                    break;
                }
            }
            if (good){
                p.add(i);
                perm(p);
                p.remove(p.size()-1);
            }
        }
    }
    public static void pre () throws IOException {
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

        //hash pow
        pow[0]=1; for (int i=1;i<7;i++) pow[i]=(pow[i-1]*A)%B;

        //dict
        BufferedReader gameDict = new BufferedReader(new FileReader("lgame.dict"));
        while (true){
            String str = gameDict.readLine();
            if (str.charAt(0)=='.') break;
            dict.add(getHash(str));
        }
    }
    public static int getHash(String str){
        long hash = 0;
        for (int i=0;i<str.length();i++) hash=(hash+pow[i]*str.charAt(i))%B;
        return (int) hash;
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
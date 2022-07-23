package Misc.Procrastinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SerejaAndBrackets {
    //io
    static boolean debug = true;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static String str;
    static TreeSet<Bracket> Ls = new TreeSet<>((a,b)->a.index-b.index);
    static TreeSet<Bracket> Rs = new TreeSet<>((a,b)->a.index-b.index);

    static int M;
    public static void main(String[] args) throws IOException {
        //parse input
        str = br.readLine();
        N = str.length();
        //create Ls and Rs TreeSet search trees
        int l=0;
        int r=0;
        for (;l<N;l++){
            if (str.charAt(l)=='('){
                r=Math.max(l,r);
                while (r<N&&str.charAt(r)!=')') r++;
                if (r>=N) break;
                Bracket L = new Bracket(l+1, Ls.size()+1);
                Bracket R = new Bracket(r+1, Ls.size()+1);
                Ls.add(L);
                Rs.add(R);
                r++;
            }
        }
        if (debug) System.out.println(Ls);
        if (debug) System.out.println(Rs);

        //parsing, logic, outputting all at once hehe
        M = Integer.parseInt(br.readLine());
        for (int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Bracket bound1 = Ls.ceiling(new Bracket(u,0));
            Bracket bound2 = Rs.floor(new Bracket(v,0));
            if (bound1==null||bound2==null){
                out.println(0);
                continue;
            }
            if (debug) out.print(bound1+" "+bound2);
            out.println(Math.max(0,(bound2.id-bound1.id+1)*2));
        }
        out.close();
    }
    private static class Bracket {
        int index;
        int id;
        public Bracket(int index, int id){
            this.index=index;
            this.id=id;
        }
        public String toString(){
            return "["+id+": "+index+"]";
        }
    }
}
//)())((((((((()()())((((())))()()))(((((()(((()(((((())(((((()))(()((()()(()((()()())()((())())))(
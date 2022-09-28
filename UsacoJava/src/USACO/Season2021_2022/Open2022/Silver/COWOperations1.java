package USACO.Season2021_2022.Open2022.Silver;

import java.io.*;
import java.util.*;

public class COWOperations1 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static String str;
    static int N;

    public static void main(String[] args) throws IOException {
        //parse input
        str = br.readLine();
        N = Integer.parseInt(br.readLine());

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken())-1;
            int v=Integer.parseInt(st.nextToken())-1;
            out.print(solve(u,v)?"Y":"N");
        }
        out.close();
    }

    public static boolean solve(int u, int v){
        Stack<Character> stack = new Stack();
        for (int i=u;i<=v;i++) stack.add(str.charAt(i));

        while (stack.size()>2){
            char l = stack.pop();
            char ll = stack.pop();
            if (l==ll) continue;
            if (l!='C'&&ll!='C'){
                stack.add('C');
            } else if (l!='O'&&ll!='O') {
                stack.add('O');
            } else {
                stack.add('W');
            }
        }

        if (stack.size()==1) return stack.pop()=='C';
        int a = stack.pop();
        int b = stack.pop();
        return (a=='W'&&b=='O')||(a=='O'&&b=='W');
    }
}

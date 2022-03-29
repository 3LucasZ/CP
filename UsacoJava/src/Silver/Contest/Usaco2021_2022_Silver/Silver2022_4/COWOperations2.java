package Silver.Contest.Usaco2021_2022_Silver.Silver2022_4;

import java.io.*;
import java.util.*;

public class COWOperations2 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static String str;
    static int N;
    static int Q;
    static String compress[];

    public static void main(String[] args) throws IOException {
        //parse input
        str = " "+br.readLine();
        N = str.length()-1;
        compress = new String[N+1];

        //preprocess compress
        compress[0]="";
        Stack<Character> stack = new Stack();
        for (int i=1;i<=N;i++){
            stack.add(str.charAt(i));
            if (stack.size()==1){
                compress[i]=String.valueOf(stack.peek());
                continue;
            }
            char l = stack.pop();
            char ll = stack.pop();
            if (l==ll) {
                compress[i]="";
                continue;
            }
            if (l!='C'&&ll!='C'){
                stack.add('C');
                compress[i]="C";
            } else if (l!='O'&&ll!='O') {
                stack.add('O');
                compress[i]="O";
            } else {
                stack.add('W');
                compress[i]="W";
            }
        }
        if (debug) out.println(Arrays.toString(compress));

        Q = Integer.parseInt(br.readLine());
        for (int i=0;i<Q;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            String next=compress[u-1]+compress[v];
            out.print((next.equals("C")||next.equals("OW")||next.equals("WO"))?"Y":"N");
        }
        out.close();
    }
}

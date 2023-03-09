package Solutions.TrainingGateway.Chapter2;/*
PROB: fracdec
LANG: JAVA
 */
import java.io.*;
import java.util.*;
public class fracdec {
    //io
    static boolean submission = true;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int D;
    static int[] vis;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("fracdec.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //logic
        StringBuilder ans = new StringBuilder();
        ans.append(N/D+".");
        vis = new int[D+1];
        int rem=(N%D);
        vis[rem]=1;
        int i=1;
        while (true){
            i++;
            rem*=10;
            ans.append(rem/D);
            rem%=D;
            if (rem==0) break;
            if (vis[rem]!=0) break;
            vis[rem]=i;
        }
        if (rem!=0&&vis[rem]!=0){
            ans.insert(ans.length()-(i-vis[rem]),'(');
            ans.insert(ans.length(),')');
        }

        //turn in answer
        String anss = ans.toString();
        for (int j=0;j<ans.length();j++){
            if (j!=0&&j%76==0) out.println();
            out.print(anss.charAt(j));
        }
        out.println();
        out.close();
    }
}

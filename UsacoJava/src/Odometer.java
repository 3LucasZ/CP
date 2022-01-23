import java.io.*;
import java.util.*;

public class Odometer {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int[] S;
    static int[] E;
    //choose
    static int MAX = 19;
    static int[][] choose;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //choose test
        choose = new int[MAX][MAX];
        fillChoose();
        //for (int i=0;i<MAX;i++) out.println(Arrays.toString(choose[i]));

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        String Sstr = st.nextToken();
        S = new int[Sstr.length()];
        String Estr = st.nextToken();
        E = new int[Estr.length()];
        for (int i=0;i<Sstr.length();i++) {
            S[i]=Sstr.charAt(i)-48;
        }
        for (int i=0;i<Estr.length();i++) {
            E[i]=Estr.charAt(i)-48;
        }
        //logic
        specials(S);
        specials(E);
        //turn in answer
        out.println();
        out.close();
    }
    public static long specials(int[] num){
        //step 1: fill the ones with less digits
        long ans = 0;
        for (int dig=1;dig<=num.length-1;dig++){
            for (int l=(dig+1)/2;l<=dig;l++) {
                if (dig%2==0 && l==dig/2) ans += 5*choose[dig][l];
                else ans += 10*choose[dig][l];
            }
        }
        out.println(ans);
        return ans;
    }
    public static void fillChoose(){
        for (int row=0;row<MAX;row++) {
            choose[row][0] = 1;
            for (int col=0;col<row;col++) {
                choose[row][col+1]=choose[row][col]*(row-col)/(col+1);
            }
        }
    }
}

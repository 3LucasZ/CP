package Gold.EC.DP1;

import java.io.*;
import java.util.*;
/*
Round Numbers
USACO Gold Advanced B Training
Focus: DP
Commentary:
- Basically: How many numbers <= 2*10^9 have binaries where #0's >= #1's?
- Complementary, since round(A, B) == round(0, B) - round(0, A-1)
- Dynamic Programming to build a choose table
- concept of change bit 1 -> 0, all successive bits can be whatever
- edge cases where bin is size 0 or the initial number is round
 */
public class RoundNumbers {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int S;
    static int E;
    //const
    static final int MAX = 31;
    //N choose K dp memoization
    static int[][] choose;
    //ans
    static int ans=0;
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
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        //unit test
        //choose stuff
        choose = new int[MAX+1][MAX+1];
        fillChoose();
        //for(int i=0;i<choose.length;i++) out.println(Arrays.toString(choose[i]));
        //bin stuff
        ArrayList<Integer> Sbin = toBinary(S-1);
        ArrayList<Integer> Ebin = toBinary(E);
        //out.println(Sbin);
        //out.println(Ebin);
        //round stuff
        int Sround = round(Sbin);
        if (Sbin.size()!=0 && countZeros(Sbin) >= (Sbin.size()+1)/2) Sround++;
        int Eround = round(Ebin);
        if (countZeros(Ebin) >= (Ebin.size()+1)/2) Eround++;
        //out.println(Sround);
        //out.println(Eround);
        //turn in answer
        ans += Eround - Sround;
        out.println(ans);
        out.close();
    }
    static int round(ArrayList<Integer> bin){
        int ret = 0;
        for (int i=1;i<=bin.size()-2;i++) {
            ret += gen(i, (1+i+1)/2);
        }
        //out.println("step1: "+ret);
        int needed = (bin.size()+1)/2;
        for (int i=1;i<bin.size();i++){
            if (bin.get(i)==0){
                needed--;
            }
            else {
                ret += gen(bin.size()-i-1, needed-1);
            }
        }
        return ret;
    }
    static int gen(int blanks, int needed){
        //out.println("gen[ blanks: "+blanks+", needed: "+needed+"]");
        int ret = 0;
        for (int i=Math.max(0,needed);i<=blanks;i++){
            ret += choose[blanks][i];
        }
        return ret;
    }
    //O(log(num))
    static ArrayList<Integer> toBinary (int num){
        ArrayList<Integer> ret = new ArrayList<>();
        while (num > 0){
            int add = num%2;
            ret.add(add);
            num/=2;
        }
        Collections.reverse(ret);
        return ret;
    }
    static void fillChoose(){
        for (int N=0;N<=MAX;N++){
            choose[N][0]=1;
            for (int K=1;K<=N;K++){
                choose[N][K]=(int)((long)choose[N][K-1]*(N-K+1)/K);
            }
        }
    }
    static int countZeros(ArrayList<Integer> arrayList){
        int cnt=0;
        for (int i=0;i<arrayList.size();i++) {
            if (arrayList.get(i)==0) cnt++;
        }
        return cnt;
    }
}

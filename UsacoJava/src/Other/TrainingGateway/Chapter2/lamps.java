package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: lamps
 */
public class lamps {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int C;
    //0-off 1-on 2-undecided
    static int config[];
    static boolean lampOn[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("lamps.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());
        config = new int[N+1];
        lampOn = new boolean[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Arrays.fill(config, 2);
        while (true){
            int next = Integer.parseInt(st.nextToken());
            if (next==-1) break;
            config[next]=1;
        }
        st = new StringTokenizer(br.readLine());
        while (true){
            int next = Integer.parseInt(st.nextToken());
            if (next==-1) break;
            config[next]=0;
        }
        //logic
        boolean[][] ans = new boolean[16][N+1];
        for (int i=0;i<16;i++){
            lampOn = new boolean[N+1];
            Arrays.fill(lampOn, true);
            int j=i;
            for (int k=0;k<4;k++){
                if (j%2==1){
                    if (k==0) toggle();
                    else if (k==1) toggleOdd();
                    else if (k==2) toggleEven();
                    else if (k==3) toggleTri();
                }
                j/=2;
            }
            ans[i] = lampOn;
        }
        if (!submission) {
            for (int i=0;i<16;i++) System.out.println(i+": "+toString(ans[i]));
        }
        ArrayList<String> res = new ArrayList<>();
        for (int i=0;i<16;i++){
            int bits = Integer.bitCount(i);
            boolean pass = false;
            if (C==0 && bits==0){
                pass=true;
            }
            else if (C==1 && bits==1) {
                pass=true;
            }
            else if (C==2 && (bits==0 || bits==2)) {
                pass=true;
            }
            else if (C==3 && (bits==3 || bits==1)) {
                pass=true;
            }
            else if (C>=4 && C%2==0 && (bits==4 || bits==2 || bits==0)) {
                pass=true;
            }
            else if (C>=5 && C%2==1 && (bits==3 || bits==1)){
                pass=true;
            }
            if (!pass) continue;
            boolean bad=false;
            for (int j=1;j<=N;j++){
                if (!(config[j]==2 || (config[j]==0 && !ans[i][j]) || (config[j]==1 && ans[i][j]))) bad=true;
            }
            if (!bad){
                res.add(toString(ans[i]));
            }
        }
        Collections.sort(res);
        //print ans
        for (int i=0;i<res.size();i++){
            out.println(res.get(i));
        }
        if (res.size()==0) out.println("IMPOSSIBLE");
        out.close();
    }
    public static void toggle(){
        for (int i=1;i<=N;i++)lampOn[i]=!lampOn[i];
    }
    public static void toggleOdd(){
        for (int i=1;i<=N;i+=2)lampOn[i]=!lampOn[i];
    }
    public static void toggleEven(){
        for (int i=2;i<=N;i+=2) lampOn[i]=!lampOn[i];
    }
    public static void toggleTri(){
        for (int i=1;i<=N;i+=3) lampOn[i]=!lampOn[i];
    }
    public static String toString(boolean[] arr){
        String ret = "";
        for (int i=1;i<arr.length;i++) {
            if (arr[i]) ret+="1";
            else ret+="0";
        }
        return ret;
    }
}

package Solutions.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: preface
 */
public class preface {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    //helper
    static String[] rep = new String[]{"I","V","X","L","C","D","M"};
    static int[] ans = new int[]{0,0,0,0,0,0,0};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("preface.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        //logic
        int[] pref = new int[7];
        for (int i=1;i<=N;i++){
            pref[0]++;
            normalize(pref);
            for (int j=0;j<7;j++){
                if (pref[j]==4){
                    ans[j]++;
                    if (pref[j+1]==0) ans[j+1]++;
                    else {
                        ans[j+1]--;
                        ans[j+2]++;
                    }
                }
                else ans[j]+=pref[j];
            }
            if (!submission){
                System.out.println(Arrays.toString(pref));
                for (int k=0;k<7;k++){
                    if (ans[k]==0) continue;
                    System.out.println(rep[k]+" "+ans[k]);
                }
            }
        }
        //turn in answer
        for (int i=0;i<7;i++){
            if (ans[i]==0) continue;
            out.println(rep[i]+" "+ans[i]);
        }
        out.close();
    }
    public static void normalize(int[] pref){
        if (pref[0]==5){
            pref[0]=0;
            pref[1]++;
        }
        if (pref[1]==2){
            pref[1]=0;
            pref[2]++;
        }
        if (pref[2]==5){
            pref[2]=0;
            pref[3]++;
        }
        if (pref[3]==2){
            pref[3]=0;
            pref[4]++;
        }
        if (pref[4]==5){
            pref[4]=0;
            pref[5]++;
        }
        if (pref[5]==2){
            pref[5]=0;
            pref[6]++;
        }
    }
}

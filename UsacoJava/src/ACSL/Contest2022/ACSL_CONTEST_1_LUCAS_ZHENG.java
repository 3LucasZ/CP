package ACSL.Contest2022;

import java.io.*;

public class ACSL_CONTEST_1_LUCAS_ZHENG {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        for (int i=0;i<5;i++)solve();
        out.close();
    }
    public static void solve() throws IOException {
        String str = br.readLine();
        int[] weight = new int[]{1,1,2,3,5};
        int[] state = new int[]{0, 0, 0};
        for (int i=0;i<5;i++){
            if (str.charAt(i)=='R' || str.charAt(i)=='Y' || str.charAt(i)=='M'){
                state[0]+=weight[i];
            }
            if (str.charAt(i)=='G' || str.charAt(i)=='Y' || str.charAt(i)=='C'){
                state[1]+=5*weight[i];
            }
            if (str.charAt(i)=='B' || str.charAt(i)=='M' || str.charAt(i)=='C'){
                state[2]+=5*weight[i];
            }
        }
        normalizeTime(state);
        out.println(formatInt(state[0])+":"+formatInt(state[1])+":"+formatInt(state[2]));
    }
    public static void normalizeTime(int[] arr){
        if (arr[2]>=60){
            arr[1]++;
            arr[2]-=60;
        }
        if (arr[1]>=60){
            arr[0]++;
            arr[1]-=60;
        }
        if (arr[0]>=12){
            arr[0]-=12;
        }
    }
    public static String formatInt(int n){
        if (n==0) return "00";
        if (n/10==0) return "0"+n;
        return ""+n;
    }
}
/*
Test cases:
Input:
RWGBG
RCMGB
BYYGR
MRGBW
YYYYY

Output:
01:35:15
03:20:40
08:30:05
02:10:20
01:00:00
 */

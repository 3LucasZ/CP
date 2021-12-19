/*
USACO 2020 US Open Contest, Silver
Problem 2. Cereal
 */
import java.io.*;
import java.util.*;
public class Cereal {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int M;
    static Choice[] cows;
    static int[] takenBy;
    static int[] choiceNum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("cereal.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cows = new Choice[N+1];
        takenBy = new int[M+1];
        choiceNum = new int[M+1];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(f.readLine());
            cows[N-i+1] = new Choice(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        //out.println(Arrays.toString(cows));
        //logic
        int ans = 0;
        String ret = "";
        for (int i=1;i<=N;i++) {
            int nextC = takenBy[cows[i].first];
            int nextI = choiceNum[cows[i].first];
            takenBy[cows[i].first]=i;
            choiceNum[cows[i].first]=1;
            ans++;
            //out.println(nextC);
            //out.println(nextI);
            while (nextC != 0){
                if (nextI==1){
                    int nextC2 = takenBy[cows[nextC].second];
                    int nextI2 = choiceNum[cows[nextC].second];
                    takenBy[cows[nextC].second] = nextC;
                    choiceNum[cows[nextC].second] = 2;
                    nextC=nextC2;
                    nextI=nextI2;
                }
                else {
                    ans--;
                    break;
                }
            }
            //out.println(Arrays.toString(takenBy));
            //out.println(Arrays.toString(choiceNum));
            //out.println(ans);
            //out.println();
            ret = ans + "\n" + ret;
        }
        out.print(ret);
        out.close();
        f.close();
    }
    private static class Choice {
        int first;
        int second;
        public Choice(int f, int s){
            first = f;
            second = s;
        }
        public String toString(){
            return "["+first+", "+second+"]";
        }
    }
}
/*
5 9
2 8
3 4
3 4
5 1
2 7
 */
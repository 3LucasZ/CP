package Training.BronzeTraining;import java.io.*;
import java.util.*;

public class taming {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] log = new int[n];
        for(int i=0;i<n;i++) {
            log[i] = Integer.parseInt(st.nextToken());
        }
        if(log[0] == -1){
            log[0] = 0;
        }
        else if(log[0] > 0){
            out.println(-1);
            out.close();
        }
        for(int i=n-1;i>=0;i--){
            if(i != n-1 && log[i] == -1){
                if(log[i+1] > 0){
                    log[i] = log[i+1] - 1;
                }
            }
        }
        for(int i=0;i<=n-2;i++){
            if(log[i] + 1 == log[i+1] || log[i+1] == 0 || log[i+1] == -1){

            }
            else{
                out.print(-1);
                out.close();
            }
        }
        int zeroCount = 0;
        int negCount = 0;
        for(int l : log){
            if(l == 0){
                zeroCount ++;
            }
            if(l == -1){
                negCount ++;
            }
        }
        int maxBreakouts = zeroCount + negCount;
        int minBreakouts = zeroCount;
        //out.println(Arrays.toString(log));
        out.println(minBreakouts + " " + maxBreakouts);
        out.close();
    }
}

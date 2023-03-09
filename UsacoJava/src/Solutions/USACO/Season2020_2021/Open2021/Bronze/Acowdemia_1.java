package Solutions.USACO.Season2020_2021.Open2021.Bronze;

import java.io.*;
import java.util.*;
public class Acowdemia_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] sortedCited = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            sortedCited[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sortedCited);
        int hIndex = 0;
        for(int i = n-1;i>=0;i--){
            if(sortedCited[i] >= hIndex + 1){
                hIndex += 1;
            }
            else if(L > 0){
                for(int j = i;j<n;j++){
                    if(sortedCited[j] < hIndex + 1){
                        L--;
                        sortedCited[j] ++;
                    }
                    if(L == 0){
                        break;
                    }
                }
                for(int j = i-1;j>=0;j--){
                    if(L == 0){
                        break;
                    }
                    L--;
                    sortedCited[j] ++;
                }
            }
            else{
                break;
            }
        }
        Arrays.sort(sortedCited);
        hIndex = 0;
        for(int i = n-1;i>=0;i--) {
            if (sortedCited[i] >= hIndex + 1) {
                hIndex += 1;
            } else {
                break;
            }
        }
        out.println(hIndex);
        out.close();
    }
}

package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: sort3
 */
public class sort3 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] arr;
    static int[] end;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("sort3.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(br.readLine());
        end = Arrays.copyOf(arr, N);
        Arrays.sort(end);
        if (!submission) {
            System.out.println(Arrays.toString(arr));
            System.out.println(Arrays.toString(end));
        }
        //logic
        int dubOps = 0;
        int noOps = 0;
        for (int i=0;i<N;i++) if (arr[i]==end[i]) noOps++;
        for (int i=0;i<N;i++)
            for (int j=i+1;j<N;j++){
                if (arr[i]!=end[i] && end[i]==arr[j] && end[j]==arr[i]){
                    dubOps++;
                    arr[i]=end[i];
                    arr[j]=end[j];
                }
            }
        int tripOps = 2*(N-(2*dubOps+noOps))/3;

        if (!submission) {
            System.out.println(dubOps);
            System.out.println(tripOps);
        }

        //turn in answer
        out.println(dubOps + tripOps);
        out.close();
    }
}

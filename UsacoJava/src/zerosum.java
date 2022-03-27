import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: zerosum
 */
public class zerosum {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("zerosum.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[2*N-1];
        for (int i=0;i<2*N-1;i+=2) {
            arr[i] = (i+2)/2;
        }

        //logic
        for (int i = 0; i < Math.pow(3, N - 1); i++) {
            out.println(Arrays.toString(gen(i)));
        }


        out.close();
    }

    public static int[] gen(int num){
        int[] ret=Arrays.copyOf(arr, 2*N-1);
        for (int i=0;i<N-1;i++){
            int op = num%3;
            ret[2*N-2*i-3]=op;
            num/=3;
        }
        return ret;
    }

    public static int res(int[] arr){
        ArrayList<Integer> arr2 = new ArrayList<>();
        //for (int i=0;i)
        return 0;
    }
}
package TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: zerosum
 */
public class zerosum {
    //io
    static boolean submission = true;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[] arr;

    static HashMap<Integer, Character> dict = new HashMap<Integer, Character>()
    {{
        put(0,' ');
        put(1,'+');
        put(2,'-');
    }};

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
            int[] gen = gen(i);
            if (debug){
                System.out.println(interpreter(gen)+": "+res(gen));
            }
            if (res(gen)==0) out.println(interpreter(gen));
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
        int ret = 0;
        ArrayList<Integer> arr2 = new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            if (i%2==1 && arr[i]==0){
                int li = arr2.size()-1;
                arr2.set(li, arr2.get(li)*10+arr[i+1]);
                i++;
            }
            else {
                arr2.add(arr[i]);
            }
        }
        ret+=arr2.get(0);
        for (int i=1;i<arr2.size();i+=2){
            if (arr2.get(i)==1)ret+=arr2.get(i+1);
            else ret-=arr2.get(i+1);
        }
        return ret;
    }
    public static String interpreter(int[] arr){
        StringBuilder ret = new StringBuilder();
        for (int i=0;i<arr.length;i++){
            if (i%2==0) ret.append(arr[i]);
            else ret.append(dict.get(arr[i]));
        }
        return ret.toString();
    }
}
package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: runround
 */
public class runround {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static long N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("runround.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Long.parseLong(br.readLine());
        if (!submission){
            ArrayList<Integer> t = toDig(N);
            System.out.println(t);
            System.out.println(nextIndex(t, 0));
        }
        //logic
        long next = N+1;
        while (true){
            ArrayList<Integer> ten = toDig(next);
            HashSet<Integer> temp = new HashSet<>(ten);
            if (temp.size()!=ten.size()) {
                next++;
                continue;
            };
            boolean[] visited = new boolean[ten.size()];
            int index=0;
            int i=0;
            for (;i<ten.size();i++){
                index=nextIndex(ten, index);
                if (visited[index]) break;
                visited[index]=true;
            }
            if (i==ten.size()) {
                //turn in answer
                out.println(next);
                out.close();
                return;
            }
            next++;
        }

    }
    public static ArrayList<Integer> toDig(long num){
        ArrayList<Integer> ret = new ArrayList<>();
        while (num > 0){
            ret.add((int)(num%10));
            num/=10;
        }
        Collections.reverse(ret);
        return ret;
    }
    public static int nextIndex(ArrayList<Integer> arr, int index){
        return (index+arr.get(index))%arr.size();
    }
}

package Solutions.USACOGuide.UsacoGuideGold.Stacks;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Nearest Smaller Value
USACO Gold Guide - Stacks - Easy
Thoughts:
Compare this with the NearestSmallerValues V1
This is O(N) and the other is O(NlogN)
the logic:
pop from stack until a lesser is on top
print lesser
add num to stack
 */
public class NearestSmallerValues2 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int[] arr;

    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //logic
        for (int i=1;i<=N;i++){
            while (!stack.isEmpty()&&arr[stack.peek()]>=arr[i]) stack.pop();
            if (stack.isEmpty()) out.print(0+" ");
            else out.print(stack.peek()+" ");
            stack.add(i);
        }
        out.close();
    }
}

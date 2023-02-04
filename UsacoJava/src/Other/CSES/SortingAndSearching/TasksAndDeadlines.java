package Other.USACOGuide.UsacoGuideSilver.SilverGreedy;/*
CSES Problem Set
Tasks And Deadlines
USACO Silver Guide - Greedy + Sorting - Easy
 */
import java.io.*;
import java.util.*;

public class TasksAndDeadlines {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static Task[] tasks;
    static long reward = 0;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        tasks = new Task[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(a, d);
            reward += d;
        }
        //logic
        Arrays.sort(tasks, (a,b)->a.duration-b.duration);
        for (int i=0;i<N;i++) {
            reward -= (long)(tasks[i].duration) * (N-i);
        }
        //turn in answer
        out.println(reward);
        out.close();
    }
    private static class Task {
        int duration;
        int deadline;
        public Task(int a, int d) {
            duration = a;
            deadline = d;
        }
    }
}

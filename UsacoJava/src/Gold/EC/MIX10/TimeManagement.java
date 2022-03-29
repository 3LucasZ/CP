package Gold.EC.MIX10;

import java.io.*;
import java.util.*;

public class TimeManagement {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Task[] tasks;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        tasks = new Task[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tasks[i] = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(tasks, (a,b)->b.deadline-a.deadline);

        //logic: greedy
        int fin = tasks[0].deadline;
        for (int i=1;i<N;i++){
            fin = Math.min(tasks[i].deadline, fin-tasks[i-1].time);
        }

        //turn in answer
        out.println(Math.max(-1, fin-tasks[N-1].time));

        out.close();
    }

    private static class Task {
        int time;
        int deadline;
        public Task (int t, int d){
            time=t;
            deadline=d;
        }
    }
}

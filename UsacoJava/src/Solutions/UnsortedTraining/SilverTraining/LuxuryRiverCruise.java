package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 US Open, Silver
Problem 3. Luxury River Cruise
USACO Silver Training
Concepts: Noise + cycling, functional graphs
2 hours of debugging because switched port with onPort on the end :(
 */
public class LuxuryRiverCruise {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int K;
    static int[] command;
    static int[][] getPort;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cruise.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        getPort = new int[N+1][2];
        visited = new int[N+1];
        Arrays.fill(visited, -1);
        command = new int[M];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            getPort[i][0]=left;
            getPort[i][1]=right;
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            int c = st.nextToken().charAt(0) == 'L' ? 0 : 1;
            command[i] = c;
        }
        //logic
        //loop thru commands until a cycle is detected
        int onPort = 1;
        visited[1] = 0;
        int trip = 0;
        while (true){
            //run through all the commands
            for (int i=0;i<M;i++) {
                onPort = getPort[onPort][command[i]];
            }
            trip++;
            if (visited[onPort]!=-1) {
                break;
            }
            visited[onPort]=trip;
        }
        int noise = visited[onPort] - 1;
        int cycleSize = trip - (noise + 1);
        //how many runs?
        int runTimes;
        if (K <= noise){
            runTimes = K;
        }
        else {
            runTimes = (K-noise) % cycleSize;
            if (runTimes == 0) runTimes = cycleSize;
            runTimes += noise;
        }
        //simulate
        int port = 1;
        for (int i=0;i<runTimes;i++) {
            for (int j=0;j<M;j++) {
                port = getPort[port][command[j]];
            }
        }
        if (!submission){
            out.println(Arrays.toString(visited));
            out.println(Arrays.toString(command));
            out.println("Noise: "+noise);
            out.println("Cycle size: "+cycleSize);
            out.println("Cycle start at: " + onPort);
            out.println("Finished at trip: " + trip);
            out.println("Run times: " + runTimes);
        }
        out.println(port);
        out.close();
    }
}

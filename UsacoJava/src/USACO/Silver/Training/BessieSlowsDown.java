package USACO.Silver.Training;/*
USACO 2014 January Contest, Silver
Problem 1. Bessie Slows Down
USACO Silver Training
Super fun problem! simulation
 */
import java.io.*;
import java.util.*;
public class BessieSlowsDown {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static double time = 0;
    static double position = 0;
    static int speed = 1;
    static PriorityQueue<Integer> timeEvents = new PriorityQueue<>();
    static PriorityQueue<Integer> distanceEvents = new PriorityQueue<>();
    //const
    final static double epsilon = 0.00000001;
    final static int infinity = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("slowdown.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer event = new StringTokenizer(br.readLine());
            if (event.nextToken().charAt(0)=='T'){
                timeEvents.add(Integer.parseInt(event.nextToken()));
            }
            else {
                distanceEvents.add(Integer.parseInt(event.nextToken()));
            }
        }
        timeEvents.add(infinity);
        distanceEvents.add(infinity);
        if (!submission) {
            out.println(timeEvents);
            out.println(distanceEvents);
        }
        //logic
        //simulate by processing events in order
        for (int i=0;i<N;i++) {
            double timeTillT = timeEvents.peek()-time;
            double distTillT = timeTillT/speed;

            double distTillD = (distanceEvents.peek()-position);
            double timeTillD = distTillD*speed;

            if (equals(timeTillD, timeTillT) || timeTillT < timeTillD) {
                time += timeTillT;
                position += distTillT;
                timeEvents.poll();
            } else {
                time += timeTillD;
                position += distTillD;
                distanceEvents.poll();
            }
            speed++;
        }
        time += (1000 - position)*speed;
        //turn in answer
        out.println(Math.round(time));
        out.close();
    }
    public static boolean equals(double a, double b){
        return Math.abs(a-b) <= epsilon;
    }
}

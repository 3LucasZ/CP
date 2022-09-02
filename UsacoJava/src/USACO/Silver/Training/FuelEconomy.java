package USACO.Silver.Training;

import java.io.*;
import java.util.*;
/*
USACO 2013 US Open, Silver
Problem 2. Fuel Economy
USACO Silver Training
Thoughts:
Looked at solution (gave up early)
Greedy solution with stacks
part1: is impossible? (trivial)
part2: what is the next station for each station that is cheaper (stack O(N))
part3: greedy NlogN+N
case 1: reachable station w/ cheaper gas
fill till can barely reach
case 2: no further station w/ cheaper gas or station w/cheaper gas too far ahead
fill till full, go next
 */
public class FuelEconomy {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int G;
    static int B;
    static int D;
    static Station[] stations;

    //helper
    static int[] nextCheaperStation;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("fuel.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        stations = new Station[N+1];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            stations[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        stations[N] = new Station(D, Integer.MIN_VALUE);

        // sort stations
        Arrays.sort(stations, (a,b)->a.loc-b.loc);
        if (!submission) System.out.println(Arrays.toString(stations));

        //check if there was any impossibilities along the way (distances > G)
        boolean bad = false;
        if (stations[0].loc>B) bad = true;
        for (int i=1;i<=N;i++){
            if (stations[i].loc-stations[i-1].loc>G) bad = true;
        }

        //next cheaper station filling
        nextCheaperStation = new int[N+1];
        Stack<Integer> cheapMonotonic = new Stack<Integer>();
        for (int i=N;i>=0;i--) {
            while (!cheapMonotonic.isEmpty()) {
                Station next = stations[cheapMonotonic.peek()];
                if (stations[i].price <= next.price) cheapMonotonic.pop();
                else break;
            }
            if (cheapMonotonic.isEmpty()) nextCheaperStation[i]=-1;
            else nextCheaperStation[i]=cheapMonotonic.peek();
            cheapMonotonic.add(i);
        }
        if (!submission) System.out.println(Arrays.toString(nextCheaperStation));

        //init: drive to first gas station
        int index = 0;
        B -= stations[0].loc;
        long pay = 0;

        //driving while loop
        while (index < N){
            //case 1: reachable station w/ cheaper gas
            // fill till can barely reach
            if (nextCheaperStation[index]!=-1&&stations[nextCheaperStation[index]].loc-stations[index].loc<=G) {
                int distance = stations[nextCheaperStation[index]].loc-stations[index].loc;
                int buy = Math.max(0,distance-B);
                pay += (long)buy*stations[index].price;
                B += buy;
                B -= distance;
                index=nextCheaperStation[index];
            }
            //case 2: no further station w/ cheaper gas or station w/cheaper gas too far ahead
            //fill till full, go next
            else {
                pay += (long)(G-B)*(stations[index].price);
                B = G-(stations[index+1].loc-stations[index].loc);
                index++;
            }
            if (!submission) System.out.println("Paid: "+pay+", On: "+index+", Gas: "+B);
        }

        //turn in answer
        if (bad) out.println(-1);
        else out.println(pay);
        out.close();
    }
    private static class Station {
        int price;
        int loc;
        public Station(int loc, int price){
            this.loc=loc;
            this.price=price;
        }
        public String toString(){
            return "["+loc+", "+price+"]";
        }
    }
}

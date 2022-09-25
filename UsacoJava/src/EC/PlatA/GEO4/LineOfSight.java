package EC.PlatA.GEO4;

import java.io.*;
import java.util.*;

public class LineOfSight {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int R;
    static ArrayList<Cow> cows = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows.add(new Cow(x,y));
        }

        //sweep
        int ans = 0;
        Collections.sort(cows, Comparator.comparingDouble(a -> a.entry));
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (int i=0;i<2;i++){
            for (Cow cow : cows){
                while (!pq.isEmpty() && pq.peek()<cow.entry) pq.poll();
                if (i==1) ans += pq.size();
                pq.add(cow.exit);
                cow.entry+=2*Math.PI;
                cow.exit+=2*Math.PI;
            }
        }

        //ret
        out.println(ans);
        out.close();
    }
    private static class Cow {
        double entry;
        double exit;
        public Cow(int x, int y){
            double fi = Math.abs(Math.acos((double)R/Math.hypot(x,y)));
            double theta = Math.atan2(y,x);
            entry = theta-fi;
            if (entry < 0) entry+=2*Math.PI;
            exit = entry + 2*fi;
        }
    }


















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}

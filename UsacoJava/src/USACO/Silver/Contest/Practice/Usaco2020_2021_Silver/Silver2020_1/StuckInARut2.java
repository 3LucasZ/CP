package USACO.Silver.Contest.Practice.Usaco2020_2021_Silver.Silver2020_1;

import java.io.*;
import java.util.*;

public class StuckInARut2 {
    //default
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static ArrayList<Cow> original = new ArrayList<>();
    static ArrayList<Cow> eastCows = new ArrayList<Cow>();
    static ArrayList<Cow> northCows = new ArrayList<Cow>();
    public static void main(String[] args) throws IOException {
        f = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        //parse input
        N = Integer.parseInt(f.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String type = st.nextToken();
            Cow toAdd =  new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if (type.equals("E")) {
                eastCows.add(toAdd);
            }
            if (type.equals("N")) {
                northCows.add(toAdd);
            }
            original.add(toAdd);
        }
        Collections.sort(eastCows, (a, b) -> a.y - b.y);
        Collections.sort(northCows, (a, b) -> a.x - b.x);

        //System.out.println(eastCows);
        //System.out.println(northCows);
        //logic
        for (Cow ECow : eastCows) {
            for (Cow NCow : northCows){
                if (NCow.finished) continue;
                int intersectionXLength = NCow.x - ECow.x;
                int intersectionYLength = ECow.y - NCow.y;
                if (intersectionXLength >= 0 && intersectionYLength >= 0) {
                    if (intersectionXLength > intersectionYLength) {
                        ECow.finished = true;
                        NCow.stoppedCows.add(ECow);
                        break;
                    }
                    else if (intersectionXLength == intersectionYLength) {}
                    else {
                        NCow.finished = true;
                        ECow.stoppedCows.add(NCow);
                    }
                }
            }
        }
        //turn in answer
        for (int i=0;i<N;i++) {
            if (original.get(i).stopped == -1) original.get(i).calcStopped();
            out.println(original.get(i).stopped);
        }
        out.close();
        f.close();
    }
    private static class Cow {
        int x;
        int y;
        boolean finished = false;
        ArrayList<Cow> stoppedCows = new ArrayList<>();
        int stopped = -1;
        public Cow(int x1, int y1) {
            x = x1;
            y = y1;
        }
        public void calcStopped() {
            int c = 0;
            for (Cow s : stoppedCows) {
                if (s.stopped == -1) s.calcStopped();
                c += (1 + s.stopped);
            }
            stopped = c;
        }
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
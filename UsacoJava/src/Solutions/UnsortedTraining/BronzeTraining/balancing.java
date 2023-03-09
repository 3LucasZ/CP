package Solutions.UnsortedTraining.BronzeTraining;
import java.io.*;
import java.util.*;

public class balancing {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        List<Integer> cowX = new ArrayList<>();
        List<Integer> cowY = new ArrayList<>();

        for (int i=0;i<N;i++){
            st = new StringTokenizer(f.readLine());
            cowX.add(Integer.parseInt(st.nextToken()));
            cowY.add(Integer.parseInt(st.nextToken()));
        }

        int minMax=N;
        for (int a : cowX) {
            for (int b : cowY) {
                int xLine = a - 1;
                int yLine = b - 1;
                int Q1 = 0;
                int Q2 = 0;
                int Q3 = 0;
                int Q4 = 0;
                for (int i = 0; i<N;i++) {
                    int x = cowX.get(i);
                    int y = cowY.get(i);
                    if(x > xLine && y > yLine) {
                        Q1 += 1;
                    }
                    else if(x < xLine && y > yLine) {
                        Q2 += 1;
                    }
                    else if(x < xLine && y < yLine) {
                        Q3 += 1;
                    }
                    else {
                        Q4 += 1;
                    }
                }
                int localMax = Math.max(Math.max(Q1,Q2),Math.max(Q3,Q4));
                //out.println(Q1 + " " + Q2 + " " + Q3 + " " + Q4);
                if (localMax < minMax) {
                    minMax = localMax;
                }
            }
        }
        out.println(minMax);
        out.close();
    }
}

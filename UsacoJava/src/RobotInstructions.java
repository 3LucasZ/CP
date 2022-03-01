import java.io.*;
import java.util.*;

public class RobotInstructions {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static Instruction destination;
    static Instruction[] instructions;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        //logic
        for (int i=0;i<N;i++){

        }
        //turn in answer
        out.println();
        out.close();
    }
    private static class Instruction {
        int x;
        int y;
        public Instruction(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MazeTacToe {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    //imd
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(f.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
    public static void floodfill(int r, int c) {

    }
}

package Other.USACOGuide.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;
/*
UNFINISHED
SKIPPED
(easy logic, hard implementation)
 */
public class SwapGame {
    static boolean submission = false;
    static boolean debug = true;

    static int pow9[];

    public static void main(String[] args) throws IOException {
        //ez setup pow9
        pow9 = new int[10];pow9[0]=1;for (int i=1;i<=9;i++) pow9[i]=pow9[i-1]*9;

        //parse
        setup("");
        State init = new State();
        for (int r=0;r<3;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=0;c<3;c++){
                init.grid[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        //add init state to BFS
        Queue<State> BFS = new LinkedList();
        BFS.add(init);

        //test
        testState();

        //BFS with states (aka ff)
        int[] dist = new int[pow9[9]];
        while (BFS.isEmpty()){
            State next = BFS.poll();

        }

    }
    public static void testState(){

    }
    private static class State {
        int[][] grid;
        public State(){};
        public State(State toCopy){
            for (int r=0;r<3;r++){
                for (int c=0;c<3;c++){
                    grid[r][c]=toCopy.grid[r][c];
                }
            }
        }
        static int pack(State state){
            int ret = 0;
            for (int r=0;r<3;r++){
                for (int c=0;c<3;c++){
                    ret+=state.grid[r][c]*pow9[3*r+c];
                }
            }
            return ret;
        }
        static State unpack(int state){
            State ret = new State();
            for (int r=0;r<3;r++){
                for (int c=0;c<3;c++){
                    ret.grid[r][c]=state%9;
                    state%=9;
                }
            }
            return ret;
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

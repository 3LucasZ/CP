import java.io.*;
import java.util.*;

/*
PROB: spin
LANG: JAVA
 */


public class spin {
    static boolean submission = true;
    static boolean debug = true;

    static int N = 5;

    static int[] speed;
    static boolean[][] hole;

    static boolean[][][] online;
    public static void main(String[] args) throws IOException {
        //parse
        setup("spin");
        speed = new int[N];
        hole = new boolean[N][360];

        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            speed[i] = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            for (int wedge=0;wedge<K;wedge++){
                int start = Integer.parseInt(st.nextToken());
                int end = start+Integer.parseInt(st.nextToken());
                for (int deg=start;deg<=end;deg++) hole[i][deg%360]=true;
            }
        }

        //look up table: online[wheel id][time%360][degree]
        online = new boolean[N][360][360];
        for (int i=0;i<5;i++){
            for (int deg=0;deg<360;deg++){
                if (hole[i][deg]){
                    for (int t=0;t<360;t++) {
                        int future = (deg+speed[i]*t)%360;
                        online[i][t][future]=true;
                    }
                }
            }
        }

        //ret
        fin();
        out.close();
    }
    public static void fin(){
        for (int t=0;t<360;t++){
            for (int deg=0;deg<360;deg++){
                boolean pass = true;
                for (int id=0;id<N;id++){
                    if (!online[id][t][deg]) pass=false;
                }
                if (pass) {
                    out.println(t);
                    return;
                }
            }
        }
        out.println("none");
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

package Other.TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: msquare
LANG: JAVA
 */

public class msquare {
    static boolean submission = true;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //parse
        setup("msquare");
        int[][] endData = new int[2][4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int c=0;c<4;c++)endData[0][c]=Integer.parseInt(st.nextToken());
        for (int c=3;c>=0;c--)endData[1][c]=Integer.parseInt(st.nextToken());
        Cube end = new Cube(endData);

        //BFS
        HashMap<Integer,Integer> distance = new HashMap<>();
        HashMap<Integer,String> seq = new HashMap<>();

        Cube init = new Cube(new int[][]{{1,2,3,4},{8,7,6,5}});
        Queue<Cube> BFS = new LinkedList<>();
        BFS.add(init);

        distance.put(init.unpack(),0);
        seq.put(init.unpack(),"");

        while (!BFS.isEmpty()){
            Cube next = BFS.poll();
            //if (next.equals(end)) break;
            int nextVal = next.unpack();
            Cube child; int childVal;

            child = next.A();
            childVal = child.unpack();
            if (!distance.containsKey(childVal)) {
                BFS.add(child);
                distance.put(childVal,distance.get(nextVal)+1);
                seq.put(childVal,seq.get(nextVal)+"A");
            }

            child = next.B();
            childVal = child.unpack();
            if (!distance.containsKey(childVal)) {
                BFS.add(child);
                distance.put(childVal,distance.get(nextVal)+1);
                seq.put(childVal,seq.get(nextVal)+"B");
            }

            child = next.C();
            childVal = child.unpack();
            if (!distance.containsKey(childVal)) {
                BFS.add(child);
                distance.put(childVal,distance.get(nextVal)+1);
                seq.put(childVal,seq.get(nextVal)+"C");
            }
        }

        out.println(distance.get(end.unpack()));
        out.println(seq.get(end.unpack()));
        out.close();
    }
    private static class Cube {
        int[][] data;
        public Cube(){
            data = new int[2][4];
        }
        public Cube(int[][] data){
            this.data = new int[2][4];
            for (int r=0;r<2;r++)for (int c=0;c<4;c++)this.data[r][c]=data[r][c];
        }
        public int unpack(){
            int ret =0;
            int pow=1;
            for (int r=0;r<2;r++) for (int c=0;c<4;c++){
                    ret+=data[r][c]*pow;
                    pow*=8;
            }
            return ret;
        }
        public Cube A(){
            Cube ret = new Cube();
            ret.data[0]=data[1].clone();
            ret.data[1]=data[0].clone();
            return ret;
        }
        public Cube B(){
            Cube ret = new Cube();
            for (int r=0;r<2;r++) for (int c=0;c<4;c++){
                    ret.data[r][(c+1)%4]=data[r][c];
            }
            return ret;
        }
        public Cube C(){
            Cube ret = clone();
            ret.data[0][1]=data[1][1];
            ret.data[0][2]=data[0][1];
            ret.data[1][2]=data[0][2];
            ret.data[1][1]=data[1][2];
            return ret;
        }
        public Cube clone(){
            return new Cube(this.data);
        }
        boolean equals(Cube o){
            for (int r=0;r<2;r++) for (int c=0;c<4;c++) if (data[r][c]!=o.data[r][c])return false;
            return true;
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

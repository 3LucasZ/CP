package USACO.Bronze.Usaco2020_2021_Bronze.Bronze2021_4;

import java.io.*;
import java.util.*;
public class Acowdemia_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int[] xc = {-1,0,0,1};
    static int[] yc = {0,1,-1,0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        String pasture[][] = new String[rows][columns];
        for(int r=0;r<rows;r++){
            String row = br.readLine();
            for(int c=0;c<columns;c++){
                pasture[r][c] = String.valueOf(row.charAt(c));
            }
        }
        /*
        for(String[] line : pasture){
            out.println(Arrays.toString(line));
        }
         */
        int counter = 0;
        for(int r=0;r<rows;r++){
            for(int c=0;c<columns;c++){
                if(pasture[r][c].equals("G")){
                    if(adjCows(pasture,r,c) >= 2){
                        counter += 1;
                    }
                    if(r+1 < rows && c+1 < columns){
                        if(pasture[r+1][c].equals("C") && pasture[r][c+1].equals("C") && pasture[r+1][c+1].equals("G") && adjCows(pasture,r,c) == 2 && adjCows(pasture,r+1,c+1) == 2){
                            counter --;
                        }
                    }
                    if(r+1 < rows && c-1 >= 0){
                        if(pasture[r+1][c].equals("C") && pasture[r][c-1].equals("C") && pasture[r+1][c-1].equals("G") && adjCows(pasture,r,c) == 2 && adjCows(pasture,r+1,c-1) == 2){
                            counter --;
                        }
                    }


                }
            }
        }
        out.println(counter);
        out.close();

    }
    public static int adjCows(String[][] map,int r, int c){
        int adj = 0;
        for(int i=0;i<4;i++){
            if(r + xc[i] >= 0 && c + yc[i] >= 0 && r + xc[i] < map.length && c + yc[i] < map[0].length) {
                if (map[r + xc[i]][c + yc[i]].equals("C")) {
                    adj++;
                }
            }
        }
        return adj;
    }
}

package USACO.Silver.UsacoGuideSilver.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*Introduction to UsacoGuideSilver.DFS
CSES Problem Set
Building Roads
USACO Guide Silver Very Easy
*/
public class BuildingRoads {
    //param
    //num cities
    static int n;
    //num roads
    static int m;
    static ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();
    static boolean[] found;
    static ArrayList<Integer> heads = new ArrayList<>();
    static int mainHead;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        found = new boolean[n+1];
        for (int i=0;i<n+1;i++) {
            adjMatrix.add(new ArrayList<Integer>());
        }
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            adjMatrix.get(first).add(second);
            adjMatrix.get(second).add(first);
        }
        //System.out.println(adjMatrix);
        //arbitrary!
        mainHead = 1;
        for (int i=1;i<n+1;i++) {
            if (!found[i]) {
                heads.add(i);
                traverseTree(i);
            }
        }
        //System.out.println(heads.size());
        //printArr(found);
        //turn in answer
        out.println(heads.size()-1);
        for (int i=1;i<heads.size();i++) {
            out.println(mainHead + " " + heads.get(i));
        }
        out.close();
        f.close();
    }
    public static void traverseTree(int head) {
        found[head] = true;
        for(int i=0;i<adjMatrix.get(head).size();i++) {
            if (!found[adjMatrix.get(head).get(i)]) {
                found[adjMatrix.get(head).get(i)] = true;
                traverseTree(adjMatrix.get(head).get(i));
            }
        }
    }
    public static void printArr(boolean[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void print2DArr(boolean[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
    }
}
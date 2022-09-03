package USACO.Gold.UsacoGuideGold.Stacks;

import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;
/*
PROB: DishWashing
LANG: JAVA
*/
public class DishWashing {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] dirty;

    public static void main(String[] args) throws IOException {
        //parse
        setup("dishes");
        N = Integer.parseInt(br.readLine());
        dirty = new int[N]; for (int i=0;i<N;i++) dirty[i]=Integer.parseInt(br.readLine());

        //simulate
        int ans = 0;
        int lastClean = 0;
        TreeMap<Integer,Stack<Integer>> counter = new TreeMap<>();
        for (int i=0;i<N;i++){
            //dirty[i] will break the sim
            if (dirty[i]<lastClean) break;
            Integer place = counter.ceilingKey(dirty[i]);
            ans++;
            //biggest plate: create new stack
            if (place==null) {
                Stack<Integer> stack = new Stack(); stack.add(dirty[i]);
                counter.put(dirty[i],stack);
            }
            //add to place
            else {
                //illegal add: remove all to its top and left
                if (counter.get(place).peek()<dirty[i]){
                    Stack<Integer> left = counter.get(counter.firstKey());
                    while (true){
                        if (left.isEmpty()) {
                            counter.remove(counter.firstKey());
                            left=counter.get(counter.firstKey());
                        }
                        if (left.peek()>dirty[i]) break;
                        lastClean = left.pop();
                    }
                }
                //add dirty[i] to stack
                counter.get(place).add(dirty[i]);
            }
            if (debug) {
                System.out.println("counter: "+counter);
            }
        }

        //ret
        out.println(ans);
        out.close();
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
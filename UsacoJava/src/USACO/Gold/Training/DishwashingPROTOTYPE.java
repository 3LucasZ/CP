package USACO.Gold.Training;

import java.io.*;
import java.util.*;

public class DishwashingPROTOTYPE {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] initStack;
    public static void main(String[] args) throws IOException {
        setup("dishes");
        N = Integer.parseInt(br.readLine());
        initStack = new int[N];
        for (int i=0;i<N;i++)initStack[i] = Integer.parseInt(br.readLine());

        //if (debug) for (int i=1;i<=5;i++) System.out.println(tryPrefix(i));
        //bin search
        int lo=1; int hi=N;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (tryPrefix(mid)) lo=mid;
            else hi=mid-1;
        }
        out.println(lo);
        out.close();
    }
    public static boolean tryPrefix(int size){
        //get prefix of size
        int[] stack = new int[size];
        for (int i=0;i<size;i++)stack[i]=initStack[i];
        //coordinate compress
        Integer[] order = new Integer[size];
        for (int i=0;i<size;i++)order[i]=i;
        Arrays.sort(order, Comparator.comparingInt(a -> stack[a]));
        for (int i=0;i<size;i++) stack[order[i]]=i;
        //make into actual stack
        Stack<Integer> dirtyStack = new Stack<>();
        for (int i=size-1;i>=0;i--) dirtyStack.add(stack[i]);
        if (debug) System.out.println(dirtyStack);

        //trackers
        TreeMap<Integer, Deque<Integer>> soapyStacks = new TreeMap<>();
        int min = 0;

        //keep popping dirty stack
        while (!dirtyStack.isEmpty()){
            int next = dirtyStack.pop();
            //pop is smallest of EVERYTHING, add to clean smartly
            if (next == min){
                min++;
                while (!soapyStacks.isEmpty()){
                    Deque<Integer> front = soapyStacks.get(soapyStacks.firstKey());
                    while (!front.isEmpty() && front.peekFirst()==min){
                        min++;
                        front.pollFirst();
                    }
                    if (!front.isEmpty() && front.peekFirst()!=min) break;
                    soapyStacks.pollFirstEntry();
                }
            }
            //pop is largest
            else if (soapyStacks.isEmpty()||next>soapyStacks.lastKey()){
//                Deque<Integer> last = soapyStacks.isEmpty()?null:soapyStacks.get(soapyStacks.lastKey());
//                if (last!=null && last.peekFirst() > next){
//                    return false;
//                }
//                else {
                    soapyStacks.put(next,new LinkedList<>());
                    soapyStacks.get(next).addFirst(next);
                //}
            }
            //mediocre pop
            else {
                int key = soapyStacks.higherKey(next);
                Deque<Integer> pos = soapyStacks.get(key);
                pos.addFirst(next);
                soapyStacks.remove(key);
                soapyStacks.put(next,pos);
            }
            if (debug) System.out.println(soapyStacks);
        }
        return soapyStacks.isEmpty();
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
/*
10
4
10
2
8
1
7
6
9
5
3
 */

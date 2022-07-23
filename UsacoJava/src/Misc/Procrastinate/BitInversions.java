package Misc.Procrastinate;/*
CSES Problem Set
Bit Inversions
USACO Silver Guide - More Ops on Sorted Sets - Hard Example
 */
import java.util.*;
import java.io.*;
public class BitInversions {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static TreeSet<Integer> checkpoint = new TreeSet<>();
    static MultiSet sizes = new MultiSet();
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        //parse input
        String str = " "+br.readLine();
        N = str.length()-1;
        char last = str.charAt(1);
        int cnt = 1;
        checkpoint.add(1);
        for (int i=2;i<=N;i++){
            if (str.charAt(i)==last){
                cnt++;
                if (i==N) sizes.add(cnt);
            }
            else {
                checkpoint.add(i);
                sizes.add(cnt);
                cnt=1;
            }
            last = str.charAt(i);
        }
        //unit testing
//        out.println(checkpoint);
//        out.println(sizes);
//        for (int c : checkpoint){
//            out.println(getComponentSize(c));
//        }
//        out.println();
//        invert(3);
//        logic
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            invert(Integer.parseInt(st.nextToken()));
        }
        out.close();
    }
    public static void invert(int index){
        int component = checkpoint.floor(index);
        int size = getComponentSize(component);

        Integer lower_component = checkpoint.lower(index);
        Integer lower_component_size = getComponentSize(lower_component);

        Integer higher_component = checkpoint.higher(index);
        Integer higher_component_size = getComponentSize(higher_component);

        //index is 1
        if (index == 1){
            //out.println("INDEX IS 1 CASE");
            if (size == 1){
                checkpoint.remove(higher_component);

                sizes.remove(size);
                sizes.remove(higher_component_size);
                sizes.add(higher_component_size+1);
            } else {
                checkpoint.add(2);
                sizes.remove(size);
                sizes.add(1);
                sizes.add(size-1);
            }
        }
        //index is N
        else if (index == N) {
            //out.println("INDEX IS N CASE");
            checkpoint.remove(index);
            sizes.remove(1);
            sizes.remove(lower_component_size);
            sizes.add(lower_component_size+1);
        }
        //single sized
        else if (size == 1){
            //out.println("SINGLE SIZED CASE");
            checkpoint.remove(index);
            checkpoint.remove(index+1);

            sizes.add(higher_component_size+1+lower_component_size);
            sizes.remove(higher_component_size);
            sizes.remove(1);
            sizes.remove(lower_component_size);
        }
        //begin point
        else if (index == component) {
            //out.println("BEGIN POINT CASE");
            checkpoint.remove(index);
            checkpoint.add(index+1);

            sizes.remove(size);
            sizes.remove(lower_component_size);
            sizes.add(size-1);
            sizes.add(lower_component_size+1);
        }
        //endpoint
        else if (index == component+size-1) {
            //out.println("END POINT CASE");
            checkpoint.remove(higher_component);
            checkpoint.add(higher_component-1);

            sizes.remove(size);
            sizes.remove(higher_component_size);
            sizes.add(size-1);
            sizes.add(higher_component_size+1);
        }
        //middle point
        else {
            //out.println("MIDDLE POINT CASE");
            checkpoint.add(index);
            checkpoint.add(index+1);

            sizes.remove(size);
            sizes.add(index-component);
            sizes.add(1);
            sizes.add(component+size-index-1);
        }
        out.print(sizes.ms.lastKey() + " ");
    }
    public static Integer getComponentSize(Integer index){
        if (index == null) return null;
        if (checkpoint.higher(index)==null){
            return N-index+1;
        }
        return checkpoint.higher(index)-index;
    }
    private static class MultiSet {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public MultiSet(){}
        public void add(int e){
            if (!ms.containsKey(e)) {
                ms.put(e,1);
            }
            else {
                ms.put(e, ms.get(e) + 1);
            }
        }
        public void remove(int e){
            if (ms.get(e)==1) ms.remove(e);
            else ms.put(e,ms.get(e)-1);
        }
        public String toString(){
            return ms.toString();
        }
    }
}

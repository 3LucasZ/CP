package Misc.Procrastinate;

import java.io.*;
import java.util.*;

public class EqualizeTheRemainders {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //param
    static int N;
    static int M;
    static int E;
    static Queue<Element>[] atMod;
    static Element[] elements;
    static long sum=0;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = N/M;
        elements = new Element[N];
        atMod = new Queue[N];
        for (int i=0;i<N;i++) atMod[i] = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            int a = Integer.parseInt(st.nextToken());
            Element e = new Element(i, a);
            atMod[a%M].add(e);
            elements[i] = e;
        }

        //logic
        for (int relax=0;relax<2;relax++){
            for (int i=0;i<M;i++){
                int nextQueue = 1;
                while (atMod[i].size()>E) {
                    Element take = atMod[i].poll();
                    while (atMod[(i+nextQueue)%M].size()>=E) nextQueue++;
                    take.value+=nextQueue;
                    atMod[(i+nextQueue)%M].add(take);
                    sum+=nextQueue;
                }
            }
        }

        //turn in answer
        out.println(sum);
        for (Element e : elements){
            out.print(e.value+" ");
        }
        out.println();
        out.close();
    }
    private static class Element {
        int index;
        long value;
        public Element(int i, long v){
            index=i;
            value=v;
        }
    }
}

package USACO.Season2017_2018.Feb2018.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Slingshot
LANG: JAVA
*/
public class Slingshot {
    static boolean submission = true;
    static boolean debug = false;

    static int N,M;
    static ArrayList<Event> events = new ArrayList<>();
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //parse
        setup("slingshot");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] ans = new int[M];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            Event next = new Event(x,y,true);
            events.add(next);
            next.t=t;
        }
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Event next = new Event(x,y,false);
            events.add(next);
            next.id=i;
            ans[i]=Math.abs(next.y-next.x);
        }
        //compress
        Collections.sort(events,(a,b)->a.y-b.y);
        int compress=1; events.get(0).yc=1;
        for (int i=1;i<events.size();i++){
            if (events.get(i).y!=events.get(i-1).y) compress++;
            events.get(i).yc=compress;
        }
        if (debug) System.out.println(events);
        //sweep init
        SegTree top, bot;
        //sweep l->r and then r->l
        for (int i=0;i<2;i++) {
            top = new SegTree(N + M);
            bot = new SegTree(N + M);
            if (i==0) Collections.sort(events,(a,b)->a.x-b.x); else Collections.sort(events,(a,b)->b.x-a.x);
            for (Event e : events) {
                if (e.isS) {
                    bot.set(e.yc, Sgizmo(e,i==0,true));
                    top.set(e.yc,Sgizmo(e,i==0,false));
                } else {
                    int b = bot.min(1,e.yc);
                    if (b!=INF) ans[e.id]=Math.min(ans[e.id],Rgizmo(e,i==0,true)+b);
                    int t = top.min(e.yc,N+M);
                    if (t!=INF) ans[e.id]=Math.min(ans[e.id],Rgizmo(e,i==0,false)+t);
                }
            }
        }
        //ret
        for (int i=0;i<M;i++) out.println(ans[i]);
        out.close();
    }
    public static int Sgizmo(Event e,boolean l, boolean b){
        int ret = e.t;
        if (l) ret-=e.x; else ret+=e.x;
        if (b) ret-=e.y; else ret+=e.y;
        return ret;
    }
    public static int Rgizmo(Event e, boolean l, boolean b){
        int ret = 0;
        if (l) ret+=e.x; else ret-=e.x;
        if (b) ret+=e.y; else ret-=e.y;
        return ret;
    }
    private static class Event {
        int id;
        int x;
        int y;
        int yc;
        boolean isS;
        int t;
        public Event(int x,int y,boolean isS){
            this.x=x;
            this.y=y;
            this.isS=isS;
        }
        public String toString(){
            return "[S: "+isS+", x: "+x+", y: "+y+" yc: "+yc+"]";
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        int[] tree;
        public SegTree(int n){
            init(n);
        }
        public SegTree(int n, int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new int[2*size+1]; Arrays.fill(tree,INF);
        }
        void set(int k, int x){
            k+=size-1;
            tree[k]=Math.min(tree[k],x);
            for (k/=2;k>=1;k/=2){
                tree[k]=Math.min(tree[2*k],tree[2*k+1]);
            }
        }
        int min(int a, int b) {
            a+=size-1;
            b+=size-1;
            int ret = INF;
            while (a<=b){
                if (a%2==1) ret=Math.min(ret,tree[a++]);
                if (b%2==0) ret=Math.min(ret,tree[b--]);
                a/=2;
                b/=2;
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
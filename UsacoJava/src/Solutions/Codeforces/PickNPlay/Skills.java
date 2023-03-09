package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: Skills
LANG: JAVA
*/
public class Skills {
    static boolean submission = false;
    static boolean debug = false;

    static int N; //skills
    static int A; //max
    static int cf; //pts*#max
    static int cm; //pts*min
    static long M; //currency
    static Skill[] skills;

    public static void main(String[] args) throws IOException {
        //* parse
        setup("Skills");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        cf = Integer.parseInt(st.nextToken());
        cm = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        skills = new Skill[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            skills[i] = new Skill(i,Long.parseLong(st.nextToken()));
        }

        //* sort by height
        Arrays.sort(skills, Comparator.comparingLong(a -> -a.level));
        if (debug) System.out.println("Skills: "+Arrays.toString(skills));

        //* precompute heights from right to left
        long[] sum = new long[N+1];
        for (int i=N-1;i>=0;i--){
            sum[i]=sum[i+1]+skills[i].level;
        }
        if (debug){
            System.out.println("sums: "+Arrays.toString(sum));
        }

        //* greedy complete search
        //get ans baseline
        long ans = 0;
        int numMax = 0;
        int min = 0;

        int loo=0;
        int hii=A;
        while (loo<hii){
            int mid = (loo+hii+1)/2;
            //find index
            int index = indexLessThanVal(0,mid);
            long cost = 0;
            if (index!=N){
                cost=(long)(N-index)*(mid)-sum[index];
            }
            if (cost <= M) loo=mid;
            else hii=mid-1;
            if (debug) System.out.println("min: "+mid+", index: "+index+", cost: "+cost);
        }
        //update score and ans
        ans= (long) cm*loo;
        if (loo==A) {
            ans+= (long) N*cf;
            numMax=N;
        }
        min=loo;
        if (debug) System.out.println("Default ans: "+ans);
        //sweep "pull up" section left to right
        int used = 0;
        for (int i=0;i<N;i++){
            long score = (long) cf *(i+1);
            //pull up i
            used+=A-skills[i].level; if (used>M) break;
            //bin search for best min
            int lo=0;
            int hi=A;
            while (lo<hi){
                int mid = (lo+hi+1)/2;
                //find index
                int index = indexLessThanVal(i+1,mid);
                long cost = 0;
                if (index!=N){
                    cost=(long)(N-index)*(mid)-sum[index];
                }
                if (used + cost <= M) lo=mid;
                else hi=mid-1;
                if (debug) System.out.println("min: "+mid+", index: "+index+", cost: "+cost);
            }
            //update score and ans
            score += (long) cm*lo;
            if (score > ans){
                numMax = i+1;
                min=lo;
                ans=score;
            }
            if (debug) System.out.println("["+i+": "+score+"]");
        }

        //ret
        out.println(ans);
        if (debug) System.out.println("NumMax: "+numMax+", Min: "+min);
        long[] finalLevels = new long[N];
        for (int i=0;i<N;i++) finalLevels[skills[i].index]=skills[i].level;
        for (int i=0;i<numMax;i++) {
            finalLevels[skills[i].index]=A;
        }
        for (int i=0;i<N;i++){
            finalLevels[skills[i].index]=Math.max(finalLevels[skills[i].index],min);
        }
        for (int i=0;i<N;i++) out.print(finalLevels[i]+" ");
        out.close();
    }

    public static int indexLessThanVal(int lo, int val){
        if (lo>=N) return 0;
        if (skills[lo].level<val) {
            if (debug) System.out.println("val: "+val+", index: "+lo);
            return lo;
        }
        int hi=N-1;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (skills[mid].level>=val) lo=mid;
            else hi=mid-1;
        }
        if (debug) System.out.println("val: "+val+", index: "+lo);
        return lo+1;
    }

    private static class Skill {
        int index;
        long level;
        public Skill(int i, long l){
            index=i;
            level=l;
        }
        public String toString(){
            return ""+level;
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

/*
2 6 0 1 4
5 1
 */
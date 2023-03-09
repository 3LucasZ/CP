package Solutions.USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
Codeforces Round #190 (Div. 1)
B. Ciel and Duel
USACO Silver Guide - Greedy Algorithms with Sorting - Normal
Thoughts:
super dumb issues along the way but the logic was right from the beginning at least
long and messy code that AT LEAST luckily worked with few iterations
mistakes:
when is it impossible checks?
line up largest attacks with smallest defenses for max damage
 */
public class CielAndDuel {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static int M;
    static ArrayList<Integer> strength = new ArrayList<>();
    static ArrayList<Integer> attack = new ArrayList<>();
    static ArrayList<Integer> defense = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            if (type.equals("ATK")) attack.add(power);
            else defense.add(power);
        }
        for (int i=0;i<M;i++){
            int power = Integer.parseInt(br.readLine());
            strength.add(power);
        }
        //sort
        Collections.sort(strength);
        Collections.sort(defense);
        Collections.sort(attack);
        //logic
        int case1dmg = case1();
        if (debug) System.out.println(case1dmg);
        int case2dmg = case2();
        if (debug) System.out.println(case2dmg);
        //turn in answer
        out.println(Math.max(case1dmg,case2dmg));
        out.close();
    }
    public static int case2(){
        int dmg = 0;
        for (int i=0;i<Math.min(attack.size(),strength.size());i++) dmg = Math.max(dmg, case2a(i));
        return dmg;
    }
    public static int case2a(int last){
        int[] strengths = new int[last+1];
        for (int i=0;i<=last;i++) strengths[i]=strength.get(strength.size()-last+i-1);
        int[] attacks = new int[last+1];
        for (int i=0;i<=last;i++) attacks[i]=attack.get(i);
        int dmg = 0;
        if (debug){
            System.out.println(Arrays.toString(strengths));
            System.out.println(Arrays.toString(attacks));
        }
        for (int i=0;i<=last;i++){
            if (attacks[i]>strengths[i]) return 0;
            dmg += strengths[i]-attacks[i];
        }
        return dmg;
    }

    public static int case1(){
        //case 1: knockout everything
        int dmg = 0;
        PriorityQueue<Integer> strengthPQ = new PriorityQueue<>(strength);
        PriorityQueue<Integer> attackPQ = new PriorityQueue<>(attack);
        PriorityQueue<Integer> defensePQ = new PriorityQueue<>(defense);
        PriorityQueue<Integer> leftoverStrength = new PriorityQueue<>();
        if (debug) {
            System.out.println(strengthPQ);
            System.out.println(attackPQ);
            System.out.println(defensePQ);
        }
        while (!defensePQ.isEmpty()){
            if (strengthPQ.isEmpty()) return 0;
            int next = defensePQ.poll();
            while (!strengthPQ.isEmpty()) {
                int nextS = strengthPQ.poll();
                if (nextS > next) break;
                leftoverStrength.add(nextS);
                if (defensePQ.isEmpty() && strengthPQ.isEmpty()) return 0;
            }
        }
        while (!strengthPQ.isEmpty()) leftoverStrength.add(strengthPQ.poll());
        if (debug) System.out.println(leftoverStrength);



        while (!attackPQ.isEmpty()){
            if (leftoverStrength.isEmpty()) return 0;
            int next = attackPQ.poll();
            while (!leftoverStrength.isEmpty()){
                int nextS = leftoverStrength.poll();
                if (nextS >= next) {
                    dmg += nextS-next;
                    break;
                }
                dmg += nextS;
                if (leftoverStrength.isEmpty() && attackPQ.isEmpty()) return 0;
            }
        }
        while (!leftoverStrength.isEmpty()) dmg += leftoverStrength.poll();
        return dmg;
    }
}

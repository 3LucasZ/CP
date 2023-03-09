package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 January Contest, Silver
Problem 3. Party Invitations
USACO Silver Training
Concepts: Queue, reverse index, grouping, smart O(N) counting, NOT a DSU problem
 */
public class PartyInvitations {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int G;
    static TreeSet<Integer>[] groups;
    static ArrayList<Integer>[] groupsWith;
    static Queue<Integer> active = new LinkedList<Integer>();
    static boolean[] invited;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("invite.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("invite.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        groups = new TreeSet[G];
        groupsWith = new ArrayList[N+1];
        invited = new boolean[N+1];
        for (int i=1;i<=N;i++) {
            groupsWith[i] = new ArrayList<>();
        }
        for (int i=0;i<G;i++) {
            groups[i] = new TreeSet<>();
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            for (int j=0;j<S;j++) {
                int id = Integer.parseInt(st.nextToken());
                groups[i].add(id);
                groupsWith[id].add(i);
            }
//            if (S==1) {
//                active.add(groups[i].first());
//            }
        }
        //logic
        active.add(1);
        while (!active.isEmpty()){
            Integer remove = active.poll();
            ans++;
            for (int group : groupsWith[remove]) {
                groups[group].remove(remove);
                if (groups[group].size() == 1) {
                    int toInvite = groups[group].first();
                    if (!invited[toInvite]) {
                        invited[toInvite] = true;
                        active.add(groups[group].first());
                    }
                }
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}

package IntroToTreeAlgor;
/*
CSES Problem Set
Subordinates
USACO Guide Silver - Intro to Tree Algors Easy
 */
import java.io.*;
import java.util.*;
public class Subordinates {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(f.readLine());
        nodes = new Node[n+1];
        for (int i=1;i<=n;i++) {
            nodes[i] = new Node(i);
        }
        StringTokenizer inStr = new StringTokenizer(f.readLine());
        for (int i=2;i<=n;i++) {
            nodes[Integer.parseInt(inStr.nextToken())].addChild(nodes[i]);
        }
        nodes[1].updTot();
        for (int i=1;i<=n;i++) {
            out.print(nodes[i].tot + " ");
        }
        out.close();
        f.close();
    }
    private static class Node {
        int id;
        ArrayList<Node> children;
        int tot;
        public Node(int i) {
            id = i;
            children = new ArrayList<>();
            tot = 0;
        }
        public void addChild(Node n) {
            children.add(n);
        }
        public void updTot() {
            tot = 0;
            for (int i=0;i<children.size();i++) {
                children.get(i).updTot();
                tot += (1 + children.get(i).tot);
            }
        }
        public String toString() {
            return "Node " + id + " with " + tot;
        }
    }
}

package Other.TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;
/*
PROB: heritage
LANG: JAVA
 */
public class heritage {
    static boolean submission = true;
    static boolean debug = false;

    static int[] nodes;
    static int[] pos;
    public static void main(String[] args) throws IOException {
        //parse
        setup("heritage");
        String str;

        str = br.readLine();
        pos = new int[26];
        for (int i=0;i<str.length();i++) pos[str.charAt(i)-'A']=i;
        if (debug) out.println(Arrays.toString(pos));

        str = br.readLine();
        nodes = new int[str.length()];
        for (int i=0;i<str.length();i++) nodes[i]=str.charAt(i)-'A';
        if (debug) out.println(Arrays.toString(nodes));

        //constructive tree
        Node head = null;
        for (int node : nodes){
            if (head==null) {
                head = new Node(node);
                continue;
            }

            Node cur = head;
            while (true){
                if (pos[node]<pos[cur.value]) {
                    if (cur.left==null) {
                        cur.left = new Node(node);
                        break;
                    }
                    cur = cur.left;
                }
                else {
                    if (cur.right==null) {
                       cur.right = new Node(node);
                       break;
                    }
                    cur = cur.right;
                }
            }
        }

        //print postorder
        postorder(head);
        out.println();
        out.close();
    }

    public static void postorder(Node node){
        if (node==null) return;
        postorder(node.left);
        postorder(node.right);
        out.print((char)(node.value+'A'));
    }

    private static class Node {
        Node left;
        Node right;
        int value;
        public Node(int value){
            this.value=value;
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

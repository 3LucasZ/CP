package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: shopping
LANG: JAVA
 */

public class shopping {
    static boolean submission = true;
    static boolean debug = false;

    static int S; //special offers
    static ArrayList<Product>[] offers;
    static int[] offerCost;

    static int B; //products
    static int[] need; //how many of product needed
    static int[] oneCost; //individual product cost

    static HashMap<Integer, Integer> idMap = new HashMap<>(); // Map<code,id>
    static int[][][][][] cost; //memoize cost for a basket

    static int INF = Integer.MAX_VALUE/3;

    public static void main(String[] args) throws IOException {
        //parse
        setup("shopping");
        S = Integer.parseInt(br.readLine());
        offers = new ArrayList[S]; for (int i=0;i<S;i++) offers[i]=new ArrayList<>();
        offerCost = new int[S];
        for (int i=0;i<S;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            for (int j=0;j<N;j++){
                int code = Integer.parseInt(st.nextToken());
                int amt = Integer.parseInt(st.nextToken());
                offers[i].add(new Product(code,amt));
            }
            offerCost[i]=Integer.parseInt(st.nextToken());
        }
        B = Integer.parseInt(br.readLine());
        need = new int[5];
        oneCost = new int[5];
        for (int i=0;i<B;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            idMap.put(Integer.parseInt(st.nextToken()),i);
            need[i]=Integer.parseInt(st.nextToken());
            oneCost[i]=Integer.parseInt(st.nextToken());
        }

        //debug
        if (debug){
            System.out.println(idMap);
            System.out.println(Arrays.toString(need));
            System.out.println(Arrays.toString(oneCost));
            for (ArrayList<Product> offer : offers) System.out.println(offer);
        }

        //dp and ret
        cost = new int[6][6][6][6][6];
        out.println(newCost(new int[]{need[0],need[1],need[2],need[3],need[4]}));
        out.close();
    }
    public static int newCost(int[] basket) {
        if (debug) System.out.println(Arrays.toString(basket));
        for (int i=0;i<5;i++) if (basket[i]<0) return INF; //invalid basket
        if (Arrays.equals(basket, new int[5])) return 0; //base case
        if (oldCost(basket)!=0) return oldCost(basket); //already found
        //try each offer on the basket
        int best = INF;
        for (int i=0;i<S;i++){
            ArrayList<Product> offer = offers[i];
            //new basket
            int[] newBasket = Arrays.copyOf(basket, 5);
            for (Product product : offer){
                int id = idMap.get(product.code);
                newBasket[id]-=product.amount;
            }
            best = Math.min(best,newCost(newBasket)+offerCost[i]);
        }
        //basket can't be made with offers
        if (best==INF){
            best=0;
            for (int i=0;i<B;i++)best+=oneCost[i]*basket[i];
        }
        //clean up
        cost[basket[0]][basket[1]][basket[2]][basket[3]][basket[4]]=best;
        return best;
    }
    public static int oldCost(int [] basket){
        return cost[basket[0]][basket[1]][basket[2]][basket[3]][basket[4]];
    }
    private static class Product {
        int code;
        int amount;
        public Product(int c, int a){
            code=c;
            amount=a;
        }
        public String toString(){
            return "["+code+", "+amount+"]";
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

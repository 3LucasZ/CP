package ACSL.Contest2022;
import java.util.*;
public class ACSL_CONTEST_4_LUCAS_ZHENG {
    public static void main(String[] args){
        System.out.println("22.5: "+22.5f);
        ComplexNumber test = new ComplexNumber(0.4f,0.35f);
        System.out.println("Testing: "+test);
        System.out.println("Length: "+getCycleLength(test));
    }

    static boolean debug = true;
    public static int numFibonacciCycles(float realPartLL, float imagPartLL, float realPartUR, float imagPartUR, float incr) {
        int r1 = (Math.round(realPartLL*1000));
        int i1 = (Math.round(imagPartLL*1000));
        int r2 = (Math.round(realPartUR*1000));
        int i2 = (Math.round(imagPartUR*1000));
        int add = (Math.round(incr*1000));

        HashSet<Integer> fibSet = new HashSet<>();
        ArrayList<Integer> fibList = new ArrayList<>();
        fibList.add(1);
        fibList.add(2);
        for (int i=2;i<=12;i++)fibList.add(fibList.get(i-1)+fibList.get(i-2));
        for (int i=0;i<=12;i++)fibSet.add(fibList.get(i));
        if (debug) {
            System.out.println(fibList);
            System.out.println("Start: "+new ComplexNumber(r1,i1));
            System.out.println("End: "+new ComplexNumber(r2,i2));
            System.out.println("Increment: "+add);
            ComplexNumber test = new ComplexNumber(400,350);
            System.out.println("Testing: "+test);
            System.out.println("Length: "+getCycleLength(test));
        }

        int cnt = 0;
        for (int j=i1;j<=i2;j+=add){
            for (int i=r1;i<=r2;i+=add){
                ComplexNumber next = new ComplexNumber(i,j);
                int len = getCycleLength(next);
                if (fibSet.contains(len))cnt++;
                if (debug){
                    System.out.println(next);
                    System.out.println("len: "+len);
                    System.out.println();
                }
            }
        }
        return cnt;
    }

    public static int getCycleLength(ComplexNumber cn){
        ArrayList<ComplexNumber> seen = new ArrayList<>();
        int time = 0;
        ComplexNumber pro = new ComplexNumber(0,0);
        while (true) {
            for (ComplexNumber see : seen){
                if (see.equals(pro)) return time-see.time;
            }
            pro.time=time;
            seen.add(pro);

            time++;
            pro = pro.clone();
            pro.square();
            pro.add(cn);
            pro.round();
            if (debug) {
                System.out.println(pro);
                System.out.println("abs: "+Math.round(pro.abs()));
            }
            if (pro.abs()>4) return 0;
        }
    }

    private static class ComplexNumber {
        float a;
        float b;
        int time;
        public ComplexNumber(float a, float b){
            this.a=a;
            this.b=b;
        }
        public float abs() {
            return (float)Math.sqrt(a*a+b*b);
        }
        public ComplexNumber clone(){
            return new ComplexNumber(a,b);
        }
        public void add(ComplexNumber o){
            a+=o.a;
            b+=o.b;
        }
        public void square(){
            float aa = a*a-b*b;
            float bb = 2*a*b;
            this.a=aa;
            this.b=bb;
        }
        public void round(){
            a=Math.round(a*1000)/1000.0f;
            b=Math.round(b*1000)/1000.0f;
        }
        public boolean equals(ComplexNumber o){
            return a==o.a&&b==o.b;
        }
        public String toString(){
            return a+" + "+b+"i";
        }
    }
}

package Helpers.NT;

public class EGCDWrapper{
    public static void main(String[] args){
        long a = 102;
        long b = 38;
        EGCD egcd = new EGCD(a,b);
        System.out.println(a+"x"+egcd.x+" + "+b+"x"+egcd.y+" = "+egcd.gcd);
        System.out.println("generator: "+egcd.xi+", "+egcd.yi);
    }
    private static class EGCD {
        /*
        Find gcd(a,b) -> gcd
        Find initial solution to ax+by=gcd(a,b) -> x,y
        Find the incrementer (x,y) + m(b/gcd(a,b), -a/gcd(a,b))
        */
        long x;
        long y;
        long xi;
        long yi;
        long gcd;
        public EGCD(long aa, long bb){
            long a = aa;
            long b = bb;
            x=0;
            y=1;
            long u=1;
            long v=0;
            while (a!=0){
                //std euclidean
                long q=b/a;
                long r=b%a;
                b=a;
                a=r;

                //extended
                long m=x-u*q;
                long n=y-v*q;
                x=u;
                y=v;
                u=m;
                v=n;
            }
            gcd=b;
            xi=bb/gcd;
            yi=aa/gcd;
        }
    }
}

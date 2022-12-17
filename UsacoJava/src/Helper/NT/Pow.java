package Helper.NT;

public class Pow {
    public static void main(String[] args){
        System.out.println(pow(2,5));
        System.out.println(pow(2,6));
        System.out.println(pow(2,7));
        System.out.println(pow(2,8));
        System.out.println(pow(2,9));
        System.out.println(pow(2,10));
    }
    public static long pow(long x, int p){
        if(x==0) return 0;
        if(p==0) return 1;
        if(p%2==1)return x*pow(x,p-1);
        else return pow(x*x,p/2);
    }
}

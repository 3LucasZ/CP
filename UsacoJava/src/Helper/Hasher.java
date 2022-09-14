package Helper;

public class Hasher {
    //hash helpers
    static long A = (long)1e9+7;
    static long B = (long)1e9+9;
    static long[] pow;
    public static void setup(){
        pow = new long[1000];
        pow[0]=1;
        for (int i=1;i<1000;i++){
            pow[i]=(pow[i-1]*A)%B;
        }
    }
    private static class RollHash {
        long A;
        long B;
        long[] preHash;
        public RollHash(String str, long A, long B){
            this.A=A;
            this.B=B;
            preHash = new long[str.length()];
            preHash[0]=str.charAt(0);
            for (int i=1;i<str.length();i++){
                preHash[i]=(preHash[i-1]*A+str.charAt(i))%B;
            }
        }
        public int getHash(int l, int r){
            if (l==0) return (int)preHash[r];
            return (int)((((preHash[r]-preHash[l-1]*pow[r-l+1])%B)+B)%B);
        }
    }
}

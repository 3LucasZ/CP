package USACO.Gold.UsacoGuideGold.StringHashing;

public class StringHasher {

    //hash helpers
    final static long A = (long)1e9+7;
    final static long B = (long)1e9+9;
    static int N;
    static long[] pow;
    static long[] preHash;

    public StringHasher(int N){
        this.N=N;
        pow = new long[N];
        pow[0]=1;
        for (int i=1;i<N;i++){
            pow[i]=(pow[i-1]*A)%B;
        }

    }
    public static long getHash(int l, int r){
        if (l==0) return preHash[r];
        return (((preHash[r]-preHash[l-1]*pow[r-l+1])%B)+B)%B;
    }

    public static void setup(String str){
        preHash = new long[str.length()];
        preHash[0]=str.charAt(0);
        for (int i=1;i<str.length();i++){
            preHash[i]=(preHash[i-1]*A+str.charAt(i))%B;
        }
    }
}

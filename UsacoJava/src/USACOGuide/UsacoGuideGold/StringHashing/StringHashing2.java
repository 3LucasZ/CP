package USACOGuide.UsacoGuideGold.StringHashing;

public class StringHashing2 {

    //hash helpers
    static long A = (long)2e9+11;
    static long B = (long)2e9+33;
    static long[] pow;
    static long[] preHash;

    public static void main(String[] args){
        String str = "Computer Science Math";
        setup(str);
        System.out.println(getHash(0, 15));
        System.out.println(StringHashing1.getHash("Computer Science"));
        System.out.println(getHash(17, 20));
        System.out.println(StringHashing1.getHash("Math"));
    }
    public static long getHash(int l, int r){
        if (l==0) return preHash[r];
        return (((preHash[r]-preHash[l-1]*pow[r-l+1])%B)+B)%B;
    }
    public static void setup(String str){
        pow = new long[str.length()];
        pow[0]=1;
        for (int i=1;i<str.length();i++){
            pow[i]=(pow[i-1]*A)%B;
        }

        preHash = new long[str.length()];
        preHash[0]=str.charAt(0);
        for (int i=1;i<str.length();i++){
            preHash[i]=(preHash[i-1]*A+str.charAt(i))%B;
        }
    }
}

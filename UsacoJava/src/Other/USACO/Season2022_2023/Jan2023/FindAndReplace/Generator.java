package Other.USACO.Season2022_2023.Jan2023.FindAndReplace;

import java.io.*;

public class Generator{
	public static void generate() {
		int len = 3000;
		long l = randLong(LL1/2,LL1);
		long r = l+len;
		int q = 500;
		out.println(l+" "+r+" "+q);
		for (int i=0;i<q;i++){
			char node = randChar();
			String str = randStr(randInt(1,100));
			out.println(node+" "+str);
		}
		System.out.println("Check:"+l);
	}


	static long pow10(int i){
		return (int)Math.pow(10,i);
	}
	static long randLong(long l, long r){
		return (long)(l+Math.random()*(r-l+1));
	}
	static int randInt(int l, int r){
		return (int)(l+Math.random()*(r-l+1));
	}
	static char randChar(){
		return (char)('a'+Math.random()*26);
	}
	static char randChar(int maxChar){
		return (char)('a'+Math.random()*maxChar);
	}
	static String randStr(int len, int maxChar){
		StringBuilder ret = new StringBuilder();
		for (int i=0;i<len;i++){
			ret.append(randChar(maxChar));
		}
		return ret.toString();
	}
	static String randStr(int len){
		StringBuilder ret = new StringBuilder();
		for (int i=0;i<len;i++){
			ret.append(randChar());
		}
		return ret.toString();
	}
	static final int I1 =(int)(1e9);
	static final int I2 =(int)(2e9);
	static final long LL1 = (long)(1e18);
	static final long LL2 = (long)(2e18);

	static PrintWriter out;
	public static void main(String[] args) throws IOException{
		if (System.getenv("CP")==null){
			out = new PrintWriter(new FileWriter("io/in.txt"));
		} else{
			out=new PrintWriter(new FileWriter(System.getenv("CP")+"/io/in.txt"));
		}
		generate();
		out.close();
	}
}

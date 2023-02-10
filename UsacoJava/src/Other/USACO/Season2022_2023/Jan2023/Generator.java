package Other.USACO.Season2022_2023.Jan2023;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Generator{
	public static void generate() {
		int n = 1000;
		io.println(n);
		for (int i=0;i<n;i++){
			io.print(2*randInt(1,1000)+" ");
		}
		io.println();
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
	static String toBin(int bin, int len){
		StringBuilder ret =new StringBuilder();
		for (int i=0;i<len;i++){
			ret.append(bin%2);
			bin/=2;
		}
		return ret.toString();
	}
	static final int I1 =(int)(1e9);
	static final int I2 =(int)(2e9);
	static final long LL1 = (long)(1e18);
	static final long LL2 = (long)(2e18);

	static PrintWriter io;
	public static void main(String[] args) throws IOException{
		if (System.getenv("CP")==null){
			io= new PrintWriter(new FileWriter("io/in.txt"));
		} else{
			io=new PrintWriter(new FileWriter(System.getenv("CP")+"/io/in.txt"));
		}
		generate();
		io.close();
	}
}

package Helpers;

import java.util.ArrayList;

public class BinaryWrapperAndTester{
	public static void main(String[] args){
		int x = 100;
		System.out.println("Original:"+Binary.toStr(x, 8));
		x = Binary.rotateR(x,8,3);
		System.out.println("Shift by 3:"+Binary.toStr(x,8));
		x = Binary.toggle(x,3);
		System.out.println("Toggle bit 3:"+Binary.toStr(x,8));
		x = Binary.set(x,7);
		System.out.println("Set bit 7:"+Binary.toStr(x,8));
		x = 512;
		System.out.println("Original:"+Binary.toStr(x,10));
		System.out.println("Lowest bit:"+(int)(Math.log(Integer.lowestOneBit(x))/Math.log(2)));
	}

	/**
	 * Assume:
	 * zero indexed bit
	 */
	private static class Binary{
		static int toInt(String str){
			return 0;
		}
		static String toStr(int bin, int len){
			String ret = "";
			for (int i=0;i<len;i++){
				ret+=bin%2;
				bin/=2;
			}
			return ret;
		}
		static String toStr(boolean[] bins, int len){
			ArrayList<String> ret = new ArrayList<>();
			for (int i=0;i<bins.length;i++){
				if (bins[i]) ret.add(toStr(i,len));
			}
			return ret.toString();
		}
		static int rotateL(int bin,int len,int shift){
			return (bin >> shift) | (bin << (len-shift));
		}
		static int rotateR(int bin, int len, int shift){
			return (bin << shift) | (bin >> (len-shift));
		}
		static boolean on(int bin, int bit){
			return (bin&(1<<bit))>0;
		}
		static int toggle(int bin, int bit){
			return bin^(1<<bit);
		}
		static int set(int bin, int bit){
			return bin|(1<<bit);
		}
		static int unset(int bin, int bit){
			return bin&(1<<bit);
		}
	}
}

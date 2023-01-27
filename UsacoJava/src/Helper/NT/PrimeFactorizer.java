package Helper.NT;

import java.util.ArrayList;

public class PrimeFactorizer{
	//O(sqrt(sqrt(N))) primeFactorizer
	//Well tested to work: PotionBrewingClass
	//run SievePrimes first to feed the primes

	static ArrayList<Integer> primeFactorize(int val,ArrayList<Integer> primes){
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i=0;primes.get(i)*primes.get(i)<=val;i++){
			int p = primes.get(i);
			while (val%p==0){
				ret.add(p);
				val/=p;
			}
		}
		if (val>1) ret.add(val);
		return ret;
	}
}

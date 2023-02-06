import java.io.*;
import java.util.*;

public class Sol {
	public static final long MAXVAL = 1000000000000000000L;

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(System.getenv("CP")+"/io/in.txt"));
		PrintWriter yeah = new PrintWriter(new FileWriter(System.getenv("CP")+"/io/cmp.txt"));
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		long l = Long.parseLong(tokenizer.nextToken());
		long r = Long.parseLong(tokenizer.nextToken());
		int amtOperations = Integer.parseInt(tokenizer.nextToken());
		List<Operation> operations = new ArrayList<>();
		for (; amtOperations > 0; amtOperations--) {
			tokenizer = new StringTokenizer(in.readLine());
			char before = tokenizer.nextToken().charAt(0);
			char[] after = tokenizer.nextToken().toCharArray();
			operations.add(new Operation(before, after));
		}

		BigString[] currs = new BigString[26];
		for (char chara = 'a'; chara <= 'z'; chara++) {
			currs[chara - 'a'] = new BigString(true, chara, null, 1);
		}
		Collections.reverse(operations);
		for (Operation operation : operations) {
			if (operation.after.length == 1) {
				currs[operation.before - 'a'] = currs[operation.after[0] - 'a'];
			} else {
				BigString[] elements = new BigString[operation.after.length];
				long length = 0;
				for (int j = 0; j < elements.length; j++) {
					elements[j] = currs[operation.after[j] - 'a'];
					length += elements[j].length;
					length = Math.min(length, MAXVAL);
				}
				currs[operation.before - 'a'] = new BigString(false, '\0', elements, length);
			}
		}

		StringBuilder out = new StringBuilder();
		currs[0].append(l, r, out);
		yeah.println(out);
		yeah.close();
	}

	static class Operation {
		final char before;
		final char[] after;

		Operation(char before, char[] after) {
			this.before = before;
			this.after = after;
		}
	}

	static class BigString {
		final boolean isSingleton;
		final char chara;
		final BigString[] elements;
		final long length;

		BigString(boolean isSingleton, char chara, BigString[] elements, long length) {
			this.isSingleton = isSingleton;
			this.chara = chara;
			this.elements = elements;
			this.length = length;
		}

		void append(long from, long to, StringBuilder builder) {
			from = Math.max(from, 1);
			to = Math.min(to, length);
			if (from <= to) {
				if (isSingleton) {
					builder.append(chara);
				} else {
					long curr = 0;
					for (BigString element : elements) {
						element.append(from - curr, to - curr, builder);
						curr += element.length;
						curr = Math.min(curr, MAXVAL);
					}
				}
			}
		}
	}
}
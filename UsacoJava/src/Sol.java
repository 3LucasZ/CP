import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol {
	public static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(tokenizer.nextToken());
		long a = Long.parseLong(tokenizer.nextToken());
		long b = Long.parseLong(tokenizer.nextToken());
		char[] digits = in.readLine().replace(" ", "").toCharArray();

		long[][] answersLeft = solve(("" + (a - 1L)).toCharArray(), digits);
		long[][] answersRight = solve(("" + b).toCharArray(), digits);

		StringBuilder out = new StringBuilder();
		for (int q = Integer.parseInt(in.readLine()); q > 0; q--) {
			tokenizer = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(tokenizer.nextToken()) - 1;
			int r = Integer.parseInt(tokenizer.nextToken()) - 1;
			long answer = answersRight[l][r] - answersLeft[l][r];
			answer += MOD;
			answer %= MOD;
			out.append(answer).append('\n');
		}
		System.out.print(out);
	}

	static long[][] solve(char[] limit, char[] digits) {
		long[][] answers = new long[digits.length][digits.length];
		for (int j = 0; j < digits.length; j++) {
			long[][][] dp = new long[limit.length][limit.length][3];
			for (int k = j; k < digits.length; k++) {
				for (int x = 0; x < limit.length; x++) {
					for (int y = limit.length - 1; y > x; y--) {
						if (digits[k] > limit[x]) {
							for (int c = 0; c <= 2; c++) {
								dp[x][y][2] += dp[x + 1][y][c];
							}
						} else if (digits[k] == limit[x]) {
							for (int c = 0; c <= 2; c++) {
								dp[x][y][c] += dp[x + 1][y][c];
							}
						} else {
							for (int c = 0; c <= 2; c++) {
								dp[x][y][0] += dp[x + 1][y][c];
							}
						}

						dp[x][y][2] += dp[x][y - 1][2];
						dp[x][y][compare(digits[k], limit[y])] += dp[x][y - 1][1];
						dp[x][y][0] += dp[x][y - 1][0];

						for (int c = 0; c <= 2; c++) {
							dp[x][y][c] %= MOD;
						}
					}
				}
				for (int x = 0; x < limit.length; x++) {
					dp[x][x][compare(digits[k], limit[x])] += 2;
				}

				for (int x = 0; x < limit.length; x++) {
					answers[j][k] += dp[x][limit.length - 1][0];
					answers[j][k] += dp[x][limit.length - 1][1];
					if (x != 0) {
						answers[j][k] += dp[x][limit.length - 1][2];
					}
					answers[j][k] %= MOD;
				}
			}
		}
		return answers;
	}

	static int compare(char a, char b) {
		return Integer.signum(a - b) + 1;
	}
}
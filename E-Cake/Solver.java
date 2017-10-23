import java.util.*;
import java.io.*;

public class Solver {

	char[][] cake;
	int rows;
	int cols;

	public Solver(char[][] cake, int rows, int cols) {
		this.cake = cake;
		this.rows = rows;
		this.cols = cols;		
		solve();
	}

	public void solve() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				System.out.print(cake[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/*
	   MAIN
	   */

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();

		for (int i = 1; i <= t; ++i) {
			int rows = in.nextInt();
			int cols = in.nextInt();
			char[][] cake = new char[rows][cols];

			for (int r = 0; r < rows; r++) {
				String rowStr = in.next();
				for (int c = 0; c < cols; c++) {
					cake[r][c] = rowStr.charAt(c);
				}
			}

			Solver solver = new Solver(cake, rows, cols);

			// if (result == -1) {
			//    System.out.println("Case #" + i + ": IMPOSSIBLE"); 
			// } else {
			//    System.out.println("Case #" + i + ": " + result); 
			}
		}
	}


import java.util.*;
import java.io.*;

public class PankakeSolver {

    private String row;
    private int k;
    private int numberOfFlips;

    public PankakeSolver(String row, int k) {
        this.row = row;
        this.k = k;
    }

    public int solve() {

        for (int i = 0; i < row.length() - k + 1; i++) {
            if (row.charAt(i) == '-'){
                flip(i);
                numberOfFlips++;
            }
        }

        if (row.contains("-"))
            return -1;
        else
            return numberOfFlips;
    }

    public void flip(int i) {
        char[] array = row.toCharArray();
        for (int j = i; j < i + k; j++) {
            if (array[j] == '+') {
                array[j] = '-';
            } else {
                array[j] = '+';
            }
        }
        row = new String(array);
    }

    /*
    MAIN
    */

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            String row = in.next();
            int k = in.nextInt();

            PankakeSolver solver = new PankakeSolver(row, k);

            int result = solver.solve();

            if (result == -1) {
                System.out.println("Case #" + i + ": IMPOSSIBLE"); 
            } else {
                System.out.println("Case #" + i + ": " + result); 
            }
        }
    }
}
import java.util.*;
import java.io.*;

public class Solver {

    private long n;

    public Solver(long n) {
        this.n = n;
    }

    public long solve() {
        String str = n + "";
        char[] a = new char[str.length()];
        a = str.toCharArray();

        for (int i = a.length - 1; i > 0; i--) {

            if (a[i - 1] > a[i]) {
                a[i - 1]--;
                for (int j = i; j < a.length; j++) {
                    a[j] = '9'; 
                }
            }
        }

        String result = new String(a);
        return Long.valueOf(result);
    }

    /*
    MAIN
    */

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            long n = in.nextLong();
            Solver solver = new Solver(n);

            long result = solver.solve();

            if (result == -1) {
                System.out.println("Case #" + i + ": IMPOSSIBLE"); 
            } else {
                System.out.println("Case #" + i + ": " + result); 
            }
        }
    }
}
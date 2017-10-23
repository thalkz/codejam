import java.util.*;
import java.io.*;

public class Solver {

    private int modifs;
    private int n;
    private char[][] stage;

    public Solver (int n) {
        this.n = n;
        this.stage = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = "" + i;
            stage[i][0] = str.charAt(0);
        }

        for (int i = 0; i < n; i++) {
            String str = "" + i;
            stage[0][i] = str.charAt(0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                stage[i][j] = '.';
            }
        }
    }

    public void setModel(char model, int row, int col) {
        stage[row][col] = model;
    }

    @Override
    public String toString() {
        String result = "\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += stage[i][j] + " ";
            }
            result += "\n";
        }
        result += "Style = " + getStyle() + ", modifs = " + modifs + "\n";
        result += isValid();
        
        return result;
    }

    public int getStyle() {
        int style = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (stage[i][j] == 'x' || stage[i][j] == '+') {
                    style += 1;
                } else if (stage[i][j] == 'o') {
                    style += 2;
                }
            }
        }
        return style;
    }

    public String isValid () {

        // Check rows
        for (int i = 1; i < n; i++) {
            int cnt = 0;
            for (int j = 1; j < n; j++) {
                if (stage[i][j] == 'o' || stage[i][j] == 'x') {
                    cnt++;
                }
            }
            if (cnt > 1) {
                return "Invalid row : " + i;
            }
        }

        // Check cols
        for (int j = 1; j < n; j++) {
            int cnt = 0;
            for (int i = 1; i < n; i++) {
                if (stage[i][j] == 'o' || stage[i][j] == 'x') {
                    cnt++;
                }
            }
            if (cnt > 1) {
                return "Invalid col : " + j;
            }
        }

        // Check diags
        for (int l = 1; l < n; l++) {
            int cnt = 0;            
            for (int k = 2 - n; k <= n - 2; k++) {
                if ((l+k) < n && (l+k) > 0) {
                    if (stage[l][l + k] == 'o' || stage[l][l + k] == '+') {
                        cnt++;
                    }
                }
            }
            if (cnt > 1) {
                return "Invalid Right Diag";
            }
        }

        for (int k = n - 2; k >= 2 - n; k--) {
            int cnt = 0;
            for (int l = 1; l < n; l++) {
                if ((l+k) < n && (l+k) > 0) {
                    if (stage[l+k][l] == 'o' || stage[l+k][l] == '+') {
                        cnt++;
                    }
                }
            }
            if (cnt > 1) {
                return "Invalid Left Diag";
            }
        }

        return "Valid";
    }

    public void solve() {

    }

    /* MAIN */

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();

            Solver solver = new Solver(n + 1);

            for (int j = 0; j < m; j++) {
                char model = in.next().charAt(0);
                int row = in.nextInt();
                int col = in.nextInt();
                solver.setModel(model, row, col);
            }

            System.out.println(solver.toString()); 
            // Solver solver = new Solver(stage);           
            // System.out.println("Case #" + i + ": " + max + " " + min); 
        }
    }
}
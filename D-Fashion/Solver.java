import java.util.*;
import java.io.*;

public class Solver {

    private int modifs;
    private int n;
    private Set<Model> models = new HashSet();
    private Set<Model> startingModels = new HashSet();   
    private char[][] startingStage; 

    public Solver (int n) {
        this.n = n;
    }

    public void addStartingModel(char type, int row, int col) {
        startingModels.add(new Model(type, row, col));
        models.add(new Model(type, row, col));        
    }

    @Override
    public String toString() {
        int size = n + 1;
        char[][] stage = new char[size][size];

        for (int i = 0; i < size; i++) {
            String str = "" + i;
            stage[i][0] = str.charAt(0);
        }

        for (int i = 0; i < size; i++) {
            String str = "" + i;
            stage[0][i] = str.charAt(0);
        }

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                stage[i][j] = '.';
            }
        }

        for (Model model : models) {
            stage[model.getRow()][model.getCol()] = model.getType();
        }

        String result = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result += stage[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    // public int getStyle() {
    //     int style = 0;

    //     for (Model model: models) {
    //         if (model.getType() == 'o') {
    //             style += 2;
    //         } else {
    //             style += 1;
    //         }
    //     }
    //     return style;
    // }

    // public int getModifs() {
    //     return modifs;
    // }

    public boolean isValid () {
        // Check lines
        for (Model model : models) {
            if (model.isO() || model.isX()) {
                for (Model other : models) {
                    if (sameLine(model, other) && !other.isP() && !other.equals(model)) {
                        return false;
                    }
                }
            }
        }

        // Check diags
        for (Model model : models) {
            if (model.isO() || model.isP()) {
                for (Model other : models) {
                    if (sameDiag(model, other) && !other.isX() && !other.equals(model)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean sameLine(Model a, Model b) {
        return (a.row == b.row || a.col == b.col);
    }

    public boolean sameDiag(Model a, Model b) {
        return (a.row + a.col == b.row + b.col || a.col - a.row == b.col - b.row);
    }

    public void solve() {
        int size = n + 1;
        startingStage = new char[size][size];
        for (Model model : startingModels) {
            startingStage[model.getRow()][model.getCol()] = model.getType();
            // System.out.println("Starting : " + model.type + " (" + model.row + ", " + model.col + ")");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                boolean lineOccupied = lineOccupied(i, j);
                boolean diagOccupied = diagOccupied(i, j);

                if (!lineOccupied && !diagOccupied) {
                    // System.out.println(i + " " + j + " -> o");
                    removeAt(i,j);
                    models.add(new Model('o', i, j));
                } else if (lineOccupied && !diagOccupied) {
                    // System.out.println(i + " " + j + " -> +");   
                    removeAt(i,j);                    
                    models.add(new Model('+', i, j));
                } else if (!lineOccupied && diagOccupied) {
                    // System.out.println(i + " " + j + " -> x");
                    removeAt(i,j);                    
                    models.add(new Model('x', i, j));
                } else {
                    // System.out.println(i + " " + j + " -> .");
                }

                for (Model m : models) {
                    // System.out.println("[" + m.type + " " + m.row + " " + m.col + "]");
                }
            }
        }
    }

    public void removeAt (int i, int j) {
        
        Set<Model> toRemove = new HashSet();

        for (Model m : models) {
            if (m.row == i && m.col == j) {
                toRemove.add(m);
            }
        }

        for (Model k : toRemove) {
            models.remove(k);
        }
    }

    public boolean lineOccupied(int i, int j) {
        boolean found = false;
        for (Model other : models) {
            if ((i == other.row || j == other.col) 
                    && !other.isP()
                    && !(i == other.row && j == other.col)) {
                
                // System.out.println("[line " + other.type + " " + other.row + " " + other.col + "]");
                found = true;
            }
        }
        return found;
    }

    public boolean diagOccupied(int i, int j) {
        boolean found = false;
        for (Model other : models) {
            if (((i + j) == (other.row + other.col) || (i - j) == (other.row - other.col)) 
                    && !other.isX()
                    && !(i == other.row && j == other.col)) {
                // System.out.println("[diag " + other.type + " " + other.row + " " + other.col + "]");                
                found = true;
            }
        }
        return found;
    }

    public void print(int caseNumber) {

        int size = n + 1;
        int changes = 0;
        int points = 0;
        ArrayList<String> details = new ArrayList();

        char[][] stage = new char[size][size];
        for (Model model : models) {
            stage[model.getRow()][model.getCol()] = model.getType();
        }

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (startingStage[i][j] != stage[i][j]) {
                    changes++;
                    details.add(stage[i][j] + " " + i + " " + j);
                }

                if (stage[i][j] == 'o') {
                    points += 2;
                } else if (stage[i][j] == 'x' || stage[i][j] == '+'){
                    points++;
                }
            }
        }

        System.out.println("Case #"+ caseNumber + ": " + points + " " + changes);
        for (String s : details) {
            System.out.println(s);
        }
    }

    public class Model {
        public char type;
        public int row;
        public int col;

        public Model (char type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }

        public char getType() {
            return type;
        }

        public boolean isX() {
            return type == 'x';
        }

        public boolean isO() {
            return type == 'o';
        }

        public boolean isP() {
            return type == '+';
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Model) {
                return (((Model) obj).row == row && ((Model) obj).col == col);
            } else {
                return false;
            }
        }
    }

    /* MAIN */

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {

            int n = in.nextInt();
            int m = in.nextInt();

            Solver solver = new Solver(n);

            for (int j = 0; j < m; j++) {
                char model = in.next().charAt(0);
                int row = in.nextInt();
                int col = in.nextInt();
                solver.addStartingModel(model, row, col);
            }

            solver.solve();
            
            System.out.println(solver.toString()); 
            System.out.println(solver.isValid()); 
            
            // solver.print(i);
        }
    }
}
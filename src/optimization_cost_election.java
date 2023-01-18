import java.util.Arrays;

public class Simplex {
    public static void main(String[] args) {
        // Coefficients of the constraints
        double[][] coefficients = {{-2, 8, 0, 10}, {5, 2, 0, 0}, {3, -5, 10, -2}};
        // Constants of the constraints
        double[] constants = {50, 100, 25};
        // Lower bounds for variables
        double[] lowerBounds = {0, 0, 0, 0};
        // Objective function
        double[] objectiveFunction = {1, 1, 1, 1};
        // Number of variables and inequalities
        int numVariables = 4;
        int numInequalities = 3;
        // Initialize tableau
        double[][] tableau = new double[numInequalities + 1][numVariables + numInequalities + 1];
        for (int i = 0; i < numInequalities; i++) {
            for (int j = 0; j < numVariables; j++) {
                tableau[i][j] = coefficients[i][j];
            }
            tableau[i][numVariables + i] = 1;
            tableau[i][numVariables + numInequalities] = constants[i];
        }
        // Initialize the last row of the tableau
        for (int i = 0; i < numVariables; i++) {
            tableau[numInequalities][i] = -objectiveFunction[i];
        }
        // Run simplex algorithm
        int iteration = 0;
        int pivotCol, pivotRow;
        while ((pivotCol = findPivotCol(tableau)) != -1 && (pivotRow = findPivotRow(tableau, pivotCol)) != -1) {
            System.out.println("Iteration: " + iteration + " Tableau:" + Arrays.deepToString(tableau));
            pivot(tableau, pivotRow, pivotCol);
            iteration++;
        }
        // Print results
        System.out.println("Optimal solution found:");
        double sum = 0; // initialize sum variable
        for (int i = 0; i < numVariables; i++) {
            double variableValue = tableau[i][numVariables + numInequalities];
            System.out.println("x" + (i + 1) + " = " + variableValue);
            sum = sum + variableValue; // add variable value to sum
        }
        System.out.println("Minimum cost: " + sum);
    }

    public static int findPivotCol(double[][] tableau) {
        int pivotCol = 0;
        double lowest = tableau[tableau.length - 1][0];
        // find the most negative element in the last row
        for (int i = 1; i < tableau[0].length - 1; i++) {
            if (tableau[tableau.length - 1][i] < lowest) {
                lowest = tableau[tableau.length - 1][i];
                pivotCol = i;
            }
        }
        if (lowest >= 0)
        return -1;
return pivotCol;
}


public static int findPivotRow(double[][] tableau, int pivotCol) {
    int pivotRow = 0;
    double minQuotient = Double.POSITIVE_INFINITY;
    // find the smallest quotient of the constants and the pivot column
    for (int i = 0; i < tableau.length - 1; i++) {
        if (tableau[i][pivotCol] > 0) {
            double quotient = tableau[i][tableau[0].length - 1] / tableau[i][pivotCol];
            if (quotient < minQuotient) {
                minQuotient = quotient;
                pivotRow = i;
            }
        }
    }
    if (minQuotient == Double.POSITIVE_INFINITY)
        return -1;
    return pivotRow;
}

public static void pivot(double[][] tableau, int pivotRow, int pivotCol) {
    double pivotValue = tableau[pivotRow][pivotCol];
    // Normalize pivot row
    for (int i = 0; i < tableau[0].length; i++) {
        tableau[pivotRow][i] /= pivotValue;
    }
    // Eliminate pivot column from other rows
    for (int i = 0; i < tableau.length; i++) {
        if (i != pivotRow) {
            double scale = tableau[i][pivotCol];
            for (int j = 0; j < tableau[0].length; j++) {
                tableau[i][j] -= scale * tableau[pivotRow][j];
            }
        }
    }
}
}

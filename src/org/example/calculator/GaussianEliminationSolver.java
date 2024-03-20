package org.example.calculator;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class for performing Gaussian elimination on a matrix.
 */
public class GaussianEliminationSolver {
  private final JTextField[][] fields;

  /**
   * Constructs a org.example.calculator.GaussianEliminationSolver with the provided text fields.
   *
   * @param textFields a 2D array of JTextField objects representing the matrix.
   */
  public GaussianEliminationSolver(JTextField[][] textFields) {
    this.fields = textFields;
  }

  /**
   * Validates and parses the input from the text fields into a matrix.
   *
   * @param m the matrix to fill with parsed values
   * @return true if there is an error, false otherwise
   */
  public boolean validateInputs(double[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        try {
          m[i][j] = parseDouble(fields[i][j].getText());
        } catch (NumberFormatException e) {
          showMessageDialog(null, "Invalid value: " + fields[i][j].getText(), "Error", ERROR_MESSAGE);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Performs Gaussian elimination on the given matrix.
   *
   * @param m the matrix to apply Gaussian elimination on
   * @param sol the solution vector
   * @return the solution vector with results rounded
   */
  public double[] performGaussianElimination(double[][] m, double[] sol) {
    // Apply Gauss elimination
    for (int i = 0; i < m.length - 1; i++) {
      for (int j = i + 1; j < m.length; j++) {
        double factor = m[j][i] / m[i][i];
        for (int k = i; k < m[i].length; k++) {
          m[j][k] -= factor * m[i][k];
        }
      }
    }

    // Back-substitution
    for (int i = m.length - 1; i >= 0; i--) {
      double sum = 0;
      for (int j = i + 1; j < m.length; j++) {
        sum += m[i][j] * sol[j];
      }
      sol[i] = (m[i][m.length] - sum) / m[i][i];
    }
    return roundResults(sol);
  }

  /**
   * Displays the results in the given text area.
   *
   * @param textArea the text area to display the results in
   * @param det the determinant value
   * @param solution the solution vector
   */
  public void displayResults(JTextArea textArea, double det, double[] solution) {
    textArea.setText(
        format("Determinant = %.2f%n", det)
        + format("x = %.2f%n", solution[0])
        + format("y = %.2f%n", solution[1])
        + format("z = %.2f", solution[2])
    );
  }

  /**
   * Rounds the elements of the given array to three decimal places.
   *
   * @param arr the array to round
   * @return the rounded array
   */
  public double[] roundResults(double[] arr) {
    double[] result = new double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      result[i] = Math.round(arr[i] * 1000.0) / 1000.0;
    }
    return result;
  }

  /**
   * Calculates the determinant of a 3x3 matrix.
   *
   * @param m the 3x3 matrix
   * @return the determinant value
   */
  public double calculateDeterminant(double[][] m) {
    return m[0][0] * m[1][1] * m[2][2] + m[0][1] * m[1][2] * m[2][0] + m[0][2] * m[1][0] * m[2][1]
      - m[0][2] * m[1][1] * m[2][0] - m[0][0] * m[1][2] * m[2][1] - m[0][1] * m[1][0] * m[2][2];
  }
}

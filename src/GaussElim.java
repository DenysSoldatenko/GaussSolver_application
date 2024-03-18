import javax.swing.*;

public class GaussElim {
  private final JTextField[][] fields;

  public GaussElim(JTextField[][] textFields) {
    this.fields = textFields;
  }

  public boolean exceptionMessage(double[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        try {
          m[i][j] = Double.parseDouble(fields[i][j].getText());
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(null, "Invalid value: "
            + fields[i][j].getText(), "Error", JOptionPane.ERROR_MESSAGE);
          return true;
        }
      }
    }
    return false;
  }

  public double[] GaussMethod(double[][] m, double[] sol) {
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
    return round(sol);
  }

  public void showResult(JTextArea textArea, double det, double[] solution) {
    textArea.setText(String.format("Determinant = %.2f%n", det)
      + String.format("x = %.2f%n", solution[0])
      + String.format("y = %.2f%n", solution[1])
      + String.format("z = %.2f", solution[2]));
  }

  public double[] round(double[] arr) {
    double[] result = new double[arr.length];
    for (int i = 0; i < arr.length; i++) {
      result[i] = Math.round(arr[i] * 1000.0) / 1000.0;
    }
    return result;
  }

  public double determinant(double[][] m) {
    return m[0][0] * m[1][1] * m[2][2] + m[0][1] * m[1][2] * m[2][0] + m[0][2] * m[1][0] * m[2][1]
      - m[0][2] * m[1][1] * m[2][0] - m[0][0] * m[1][2] * m[2][1] - m[0][1] * m[1][0] * m[2][2];
  }
}
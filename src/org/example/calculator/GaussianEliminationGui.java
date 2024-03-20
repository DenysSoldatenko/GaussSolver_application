package org.example.calculator;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Color.decode;
import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GUI for solving systems of linear equations using Gaussian Elimination.
 */
public class GaussianEliminationGui implements ActionListener {
  private final JTextField[][] fields = new JTextField[3][4];
  private final JTextArea resultArea;

  /**
   * Constructs the GUI for Gaussian Elimination.
   */
  public GaussianEliminationGui() {
    JFrame frame = new JFrame("Gaussian elimination calculator");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        JTextField field = new JTextField();
        field.setFont(new Font("Comic Sans MS", PLAIN, 20));
        fields[i][j] = field;

        if (j < 3) {
          inputPanel.add(field);
          inputPanel.add(new JLabel(j == 0 ? "x" : j == 1 ? "y" : "z"));
          if (j < 2) {
            inputPanel.add(new JLabel("+"));
          }
        } else {
          inputPanel.add(new JLabel("="));
          inputPanel.add(field);
        }
      }
    }

    JButton solveButton = new JButton("Solve");
    solveButton.setForeground(decode("#000000"));
    solveButton.setFont(new Font("Comic Sans MS", PLAIN, 25));
    solveButton.addActionListener(this);
    solveButton.setFocusable(false);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    buttonPanel.add(solveButton);

    resultArea = new JTextArea(4, 28);
    resultArea.setFont(new Font("Comic Sans MS", PLAIN, 30));
    resultArea.setEditable(false);

    JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    resultPanel.add(resultArea);

    frame.add(inputPanel, CENTER);
    frame.add(buttonPanel, SOUTH);
    frame.add(resultPanel, NORTH);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
  }

  /**
   * Handles the action event triggered by the user.
   * This method performs the Gaussian Elimination process on a matrix and displays the results.
   *
   * @param e The action event triggered by the user.
   */
  public void actionPerformed(ActionEvent e) {
    GaussianEliminationSolver gauss = new GaussianEliminationSolver(fields);
    double[][] matrix = new double[3][4];
    double[] solution = new double[3];

    if (gauss.validateInputs(matrix)) {
      return;
    }

    if (gauss.calculateDeterminant(matrix) != 0.0) {
      solution = gauss.performGaussianElimination(matrix, solution);
      gauss.displayResults(resultArea, gauss.calculateDeterminant(matrix), solution);
    } else {
      showMessageDialog(null, "Determinant should not be equal to zero", "Error", ERROR_MESSAGE);
    }
  }
}

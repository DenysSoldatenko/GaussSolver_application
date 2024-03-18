import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GaussGUI implements ActionListener {
  private final JTextField[][] fields = new JTextField[3][4];
  private final JTextArea resultArea;

  public GaussGUI() {
    JFrame frame = new JFrame("Gaussian elimination calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        JTextField field = new JTextField();
        field.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
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
    solveButton.setForeground(Color.decode("#000000"));
    solveButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
    solveButton.addActionListener(this);
    solveButton.setFocusable(false);
    buttonPanel.add(solveButton);

    resultArea = new JTextArea(4, 28);
    resultArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
    resultArea.setEditable(false);
    resultPanel.add(resultArea);

    frame.add(inputPanel, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.add(resultPanel, BorderLayout.NORTH);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
  }

  public void actionPerformed(ActionEvent e) {
    GaussElim gauss = new GaussElim(fields);
    double[][] matrix = new double[3][4];
    double[] solution = new double[3];

    if (gauss.exceptionMessage(matrix)) return;

    if (gauss.determinant(matrix) != 0.0) {
      solution = gauss.GaussMethod(matrix, solution);
      gauss.showResult(resultArea, gauss.determinant(matrix), solution);
    } else {
      JOptionPane.showMessageDialog(null, "Determinant should not be equal to zero",
        "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
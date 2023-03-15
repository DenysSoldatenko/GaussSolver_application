# Gaussian elimination calculator
This program performs Gaussian elimination on a 3x4 matrix to solve a system of linear equations. The program provides a graphical user interface (GUI) built using Java Swing.

### How to Use
1. Enter the coefficients of the linear equations into the text fields provided. The first three columns represent the coefficients of x, y, and z, respectively, and the last column represents the constant.
2. Click the "Solve" button to perform the Gaussian elimination and obtain the solution to the system of equations.
3. The solution will be displayed in the text area above the colums.

Note: The program only supports 3x4 matrices.

### Features
- Input validation to ensure that the user enters valid values into the text fields.
- Exception handling to handle invalid input and display an error message.
- Calculates the determinant of the matrix to check if the system of equations has a unique solution.
- Rounds the solution to three decimal places for readability.

###  Technical Details
The program is built using Java 8 and Swing. The GaussGUI class contains the GUI components and handles user input using the actionPerformed method, which calls methods from the GaussElim class to perform the Gaussian elimination and display the result.

The GaussElim class contains the main logic for performing the Gaussian elimination. The exceptionMessage method validates the input and displays an error message if the user enters invalid values. The GaussMethod method performs the Gaussian elimination and returns the solution as an array. The showResult method formats and displays the solution in the text area. The determinant method calculates the determinant of the matrix. The round method rounds the solution to three decimal places.

The program provides a simple and user-friendly interface for solving systems of linear equations using Gaussian elimination.

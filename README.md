# Gaussian Elimination Calculator Application

This program performs Gaussian elimination on a 3x4 matrix to solve a system of linear equations. The application provides a graphical user interface (GUI) built using Java Swing.

## How to Use
1. Enter the coefficients of the linear equations into the text fields provided. The first three columns represent the coefficients of x, y, and z, respectively, and the last column represents the constant.
2. Click the "Solve" button to perform the Gaussian elimination and obtain the solution to the system of equations.
3. The solution will be displayed in the text area above the columns.

**Note:** The program only supports 3x4 matrices.

## Features
- **Input Validation:** Ensures that the user enters valid values into the text fields.
- **Exception Handling:** Handles invalid input and displays an error message.
- **Determinant Calculation:** Checks if the system of equations has a unique solution.
- **Rounded Solution:** Rounds the solution to three decimal places for readability.

## Technical Details
The program is built using Java 8 and Swing. 
The `GaussianEliminationGui` class contains the GUI components and handles user input 
using the `actionPerformed` method, which calls methods from the `GaussianEliminationSolver` class 
to perform the Gaussian elimination and display the result. The `GaussianEliminationSolver` class contains 
the main logic, including methods for validating input, performing Gaussian elimination, 
displaying results, calculating the determinant, and rounding the solution.

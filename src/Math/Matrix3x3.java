package Math;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Rafael Gomez
 */
public class Matrix3x3 {

    protected double [][] matrix;

    public Matrix3x3() {
        matrix = new double [3][3];
        for(int i = 0; i < 3; i++) {
            matrix[i][i] = 1d;
        }
    }

    public Matrix3x3(double [][] matrix) {
        this.matrix = matrix;
    }

    public void set(int row, int col, double value) {
        matrix[row][col] = value;
    }
    public double get(int row, int col) {
        return matrix[row][col];
    }

    public static double[] times(double [][] matrix1, double [] point1) {
        double[] result = new double[3];

        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += matrix1[i][j] * point1[j];
            }
        }

        return result;
    }

    public static double [][] times(double [][] m1, double [][] m2) {
        double [][] newMatrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                double sumResult = 0;
                for(int k = 0; k < 3; k++) {
                    sumResult += m1[i][k] * m2[k][j];
                }
                newMatrix[i][j] = sumResult;
            }
        }
        return newMatrix;
    }
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                resultString.append(matrix[row][col]).append(" ");
            }
            resultString.append("\n");
        }
        return resultString.toString();
    }

    public static void main(String [] args) {
        System.out.println("Matrix-Vector multiplication");
        Matrix3x3 m1 = new Matrix3x3();
        System.out.println(m1);
    }
}

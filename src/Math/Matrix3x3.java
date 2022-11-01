package Math;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Rafael Gomez
 */
public class Matrix3x3 {

    public double [][] matrix;

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

    public static Point3 times(Matrix3x3 matrix1, Point3 point) {
        double newX;
        double newY;
        double newZ;
        System.out.println(matrix1);
        newX = matrix1.matrix[0][0] * point.x + matrix1.matrix[0][1] * point.y + matrix1.matrix[0][2] * point.z;
        newY = matrix1.matrix[1][0] * point.x + matrix1.matrix[1][1] * point.y + matrix1.matrix[1][2] * point.z;
        newZ = matrix1.matrix[2][0] * point.x + matrix1.matrix[2][1] * point.y + matrix1.matrix[2][2] * point.z;
        System.out.print("newX");
        System.out.println(newX);
        System.out.print("newY");
        System.out.println(newY);
        System.out.print("newZ");
        System.out.println(newZ);
        return new Point3(newX, newY, newZ);
    }

    public static double [][] times(Matrix3x3 m1, Matrix3x3 m2) {
        double [][] newMatrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                double sumResult = 0;
                for(int k = 0; k < 3; k++) {
                    sumResult += m1.get(i,k) * m2.get(i,k);
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

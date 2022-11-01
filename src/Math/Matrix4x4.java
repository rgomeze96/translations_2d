package Math;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;

public class Matrix4x4 {

    protected double [][] matrix;

    public Matrix4x4() {
        matrix = new double [4][4];
        for(int i = 0; i < 4; i++) {
            matrix[i][i] = 1.0;
        }
    }

    public Matrix4x4(double [][] matrix) {
        this.matrix = matrix;
    }

    public void set(int row, int column, double value) {
        matrix[row][column] = value;
    }

    public double get(int row, int column) {
        return matrix[row][column];
    }

    public static Vector4 times(Matrix4x4 matrix, Vector4 vector) {
        double [] resultVector = new double[4];
        for(int i = 0; i < 4; i++) {
            double sumResult = 0;
            for(int j = 0; j < 4; j++) {
                sumResult += matrix.get(i,j) * vector.get(j);
            }
            resultVector[i] = sumResult;
        }
        double w = resultVector[3];
        if(w != 1) {
            for(int i = 0; i < 4; i++) {
                resultVector[i] = resultVector[i]/w;
            }
        }
        return new Vector4(resultVector);
    }

    public static double [][] times(double [][] m1, double [][] m2) {
        double [][] newMatrix = new double[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                double sumResult = 0;
                for(int k = 0; k < 4; k++) {
                    sumResult += m1[i][k] * m2[k][j];
                }
                newMatrix[i][j] = sumResult;
            }
        }
        return newMatrix;
    }

    public static void main(String [] args) {
        Matrix4x4 m1 = new Matrix4x4();
        Vector4 v1 = new Vector4();
        Vector4 v2 = Matrix4x4.times(m1, v1);
        System.out.println(v2);
    }
}

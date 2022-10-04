package Math;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author Rafael Gomez
 */
public class Vector4 {
    double [] vector;
    public double x;
    public double y;
    public double z;
    public double w;
    
    public Vector4 () {
        vector = new double [4];
        vector[3] = 1d;
    }

    public Vector4 (double x, double y, double z) {
        vector = new double[4];
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
        vector[3] = 1d;
    }

    public Vector4(double[] newVector) {
    }

    public static Vector4 crossProduct(Vector4 vector1, Vector4 vector2) {
        double x = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
        double y = - (vector1.getX() * vector2.getZ() - vector1.getZ() * vector2.getX());
        double z = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();
        return new Vector4(x, y, z);
    }

    public static double dotProduct(Vector4 vector1, Vector4 vector2) {
        return  vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() +
                vector1.getZ() * vector2.getZ();
    }

    public double vectorMagnitude() {
        return Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2]);
    }

    public void normalizeVector() {
        double vectorMagnitude = this.vectorMagnitude();
        vector[0] /= vectorMagnitude;
        vector[1] /= vectorMagnitude;
        vector[2] /= vectorMagnitude;
    }

    public double get(int pos) {
        return vector[pos];
    }
    public double getX() {
        return vector[0];
    }
    public double getY() {
        return vector[1];
    }
    public double getZ() {
        return vector[2];
    }
    
}

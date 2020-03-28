package com.company;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Matrix {
    private int[][] mx;
    private int n, m;
    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        mx = new int[n][m];
    }

    public Matrix(int[][] buf) {
        copyArr(buf);
    }

    Matrix(@NotNull Matrix _mx) {
        copyArr(_mx.mx);
    }
    private void add(@NotNull Matrix _mx, int del) throws Exception {
        if (_mx.n != n || _mx.m != m) throw new Exception();
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                mx[i][j] += _mx.mx[i][j] * del;
            }
        }
    }

    public void add(Matrix _mx) throws Exception {
        add(_mx, 1);
    }

    public void sub(Matrix _mx) throws Exception {
        add(_mx, -1);
    }

    public void mul(@NotNull Matrix _mx) throws Exception {
        if (m != _mx.n) throw new Exception();
        int[][] buf = new int[n][_mx.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < _mx.m; j++) {
                for (int k = 0; k < m; k++) {
                    buf[i][j] += mx[i][k] * _mx.mx[k][j];
                }
            }
        }
        copyArr(buf);
    }

    private void copyArr(@NotNull int[][] buf) {
        n = buf.length;
        m = buf[0].length;
        mx = new int[n][m];
        for(int i = 0; i < n; i++) {
            if(m >= 0) System.arraycopy(buf[i], 0, mx[i], 0, m);
        }
    }

    public void trans() {
        int[][] buf = new int[m][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                buf[j][i] = mx[i][j];
            }
        }
        copyArr(buf);
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0;j < m;j++) {
                output.append(mx[i][j] + " ");
            }
            output.append("\n");
        }
        return String.valueOf(output);
    }
}

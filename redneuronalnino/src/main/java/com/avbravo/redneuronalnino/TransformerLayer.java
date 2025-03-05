/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronalnino;

import java.util.Random;

/**
 *
 * @author avbravo
 */

import java.util.Random;

public record TransformerLayer(double[][] weightsQuery, double[][] weightsKey, double[][] weightsValue, int inputSize, int hiddenSize) {
    public TransformerLayer(int inputSize, int hiddenSize) {
        this(initializeWeights(inputSize, hiddenSize),
             initializeWeights(inputSize, hiddenSize),
             initializeWeights(inputSize, hiddenSize),
             inputSize, hiddenSize);
    }

    private static double[][] initializeWeights(int rows, int cols) {
        double[][] weights = new double[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                weights[i][j] = random.nextDouble() * 2 - 1;
            }
        }
        return weights;
    }

    public double[] forward(double[] input) {
        double[] query = matrixVectorMultiply(weightsQuery, input);
        double[] key = matrixVectorMultiply(weightsKey, input);
        double[] value = matrixVectorMultiply(weightsValue, input);

        double dotProduct = dot(query, key);
        double scale = Math.sqrt(hiddenSize);
        double attentionScore = dotProduct / scale;
        double softmax = Math.exp(attentionScore);

        double[] output = new double[hiddenSize];
        for (int i = 0; i < hiddenSize; i++) {
            output[i] = softmax * value[i];
        }
        return output;
    }

    private double dot(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }

    private double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }
}
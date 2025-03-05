/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronalnino;

/**
 *
 * @author avbravo
 */
import java.util.Random;

public record NeuralNetwork(Tokenizer tokenizer, EmbeddingLayer embeddingLayer, TransformerLayer transformerLayer, double[][] weightsOutput, int vocabSize, int embeddingDim, int hiddenSize) {
    public NeuralNetwork(Tokenizer tokenizer, int embeddingDim, int hiddenSize) {
        this(tokenizer,
             new EmbeddingLayer(tokenizer.indexToToken.size(), embeddingDim),
             new TransformerLayer(embeddingDim, hiddenSize),
             initializeWeights(hiddenSize, tokenizer.indexToToken.size()),
             tokenizer.indexToToken.size(),
             embeddingDim,
             hiddenSize);
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

    public int predictNextToken(int[] inputSequence) {
        double[] hiddenState = new double[hiddenSize];
        for (int tokenIndex : inputSequence) {
            double[] embedding = embeddingLayer.getEmbedding(tokenIndex);
            hiddenState = transformerLayer.forward(embedding);
        }

        double[] output = new double[vocabSize];
        for (int i = 0; i < vocabSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                output[i] += hiddenState[j] * weightsOutput[j][i];
            }
        }

        output = softmax(output);

        int predictedToken = 0;
        double maxProb = output[0];
        for (int i = 1; i < output.length; i++) {
            if (output[i] > maxProb) {
                maxProb = output[i];
                predictedToken = i;
            }
        }
        return predictedToken;
    }

    private double[] softmax(double[] x) {
        double[] exps = new double[x.length];
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            exps[i] = Math.exp(x[i]);
            sum += exps[i];
        }
        for (int i = 0; i < x.length; i++) {
            exps[i] /= sum;
        }
        return exps;
    }
}
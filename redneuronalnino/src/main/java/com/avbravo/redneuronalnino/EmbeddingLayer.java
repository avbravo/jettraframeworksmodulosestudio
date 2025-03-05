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
public record EmbeddingLayer(double[][] embeddings, int vocabSize, int embeddingDim) {

    public EmbeddingLayer(int vocabSize, int embeddingDim) {
        this(initializeEmbeddings(vocabSize, embeddingDim), vocabSize, embeddingDim);
    }

    private static double[][] initializeEmbeddings(int vocabSize, int embeddingDim) {
        double[][] embeddings = new double[vocabSize][embeddingDim];
        Random random = new Random();
        for (int i = 0; i < vocabSize; i++) {
            for (int j = 0; j < embeddingDim; j++) {
                embeddings[i][j] = random.nextDouble() * 2 - 1; // Valores entre -1 y 1
            }
        }
        return embeddings;
    }

    public double[] getEmbedding(int tokenIndex) {
        return embeddings[tokenIndex];
    }

}

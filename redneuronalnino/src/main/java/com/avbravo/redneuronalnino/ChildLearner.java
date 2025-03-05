/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronalnino;

/**
 *
 * @author avbravo
 */
import java.util.List;
import java.util.Random;

import java.util.List;
import java.util.Random;

public record ChildLearner(Tokenizer tokenizer, EmbeddingLayer embeddingLayer, TransformerLayer transformerLayer, NeuralNetwork neuralNetwork, Tutor tutor, Memory memory) {
    public ChildLearner(Tokenizer tokenizer, int embeddingDim, int hiddenSize, List<String> examples) {
        this(tokenizer,
             new EmbeddingLayer(tokenizer.indexToToken.size(), embeddingDim),
             new TransformerLayer(embeddingDim, hiddenSize),
             new NeuralNetwork(tokenizer, embeddingDim, hiddenSize),
             new Tutor(examples),
             new Memory());
    }

    public void learnFromExample(String example) {
        int[] tokenizedExample = tokenizer.tokenize(example);
        int predictedTokenIndex = neuralNetwork.predictNextToken(tokenizedExample);

        String predictedToken = tokenizer.detokenize(new int[]{predictedTokenIndex});
        String feedback = tutor.evaluate(predictedToken);

        System.out.println("Intento: " + predictedToken);
        System.out.println("Retroalimentación: " + feedback);

        if (feedback.contains("¡Correcto!")) {
            memory.remember(predictedToken);
        }
    }

    public void explore() {
        List<String> examples = memory.successfulAttempts();
        if (!examples.isEmpty()) {
            String example = examples.get(new Random().nextInt(examples.size()));
            System.out.println("Explorando ejemplo recordado: " + example);
        } else {
            System.out.println("No hay ejemplos en memoria para explorar.");
        }
    }
}
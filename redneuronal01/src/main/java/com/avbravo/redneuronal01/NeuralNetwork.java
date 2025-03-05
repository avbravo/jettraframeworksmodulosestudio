/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronal01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class NeuralNetwork {

    private List<Layer> layers;

    public NeuralNetwork(int[] layerSizes) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerSizes.length - 1; i++) {
            layers.add(new Layer(layerSizes[i + 1], layerSizes[i]));
        }
    }

    public double[] feedForward(double[] inputs) {
        for (Layer layer : layers) {
            inputs = layer.feedForward(inputs);
        }
        return inputs;
    }

    public void train(double[][] trainingData, double[][] expectedOutputs, int epochs, double learningRate) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < trainingData.length; i++) {
                double[] inputs = trainingData[i];
                double[] targets = expectedOutputs[i];

                // Paso 1: Feedforward
                double[] outputs = feedForward(inputs);

                // Paso 2: Calcular errores
                double[] errors = new double[outputs.length];
                for (int j = 0; j < outputs.length; j++) {
                    errors[j] = targets[j] - outputs[j];
                }

                // Paso 3: Backpropagation
                backpropagate(errors, learningRate);
            }
        }
    }

    private void backpropagate(double[] errors, double learningRate) {
        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer layer = layers.get(i);
            List<Neuron> neurons = layer.getNeurons();
            System.out.println("\tlayer.getNeurons().size() " + layer.getNeurons().size());
            System.out.println("\terrors.size() " + errors.length);
            for (int j = 0; j < neurons.size(); j++) {
                System.out.println("\t {j } " + j);
                Neuron neuron = neurons.get(j);
                System.out.println("\t\tneuron = "+neuron.toString());
                System.out.println("\t {calculando delta }");
                double delta = errors[j] * sigmoidDerivative(neuron.getOutput());
                System.out.println("\t\t delta "+delta);
                // Ajustar pesos
                double[] deltaWeights = new double[neuron.getWeights().length];
                for (int k = 0; k < deltaWeights.length; k++) {
                    deltaWeights[k] = learningRate * delta * (i == 0 ? 0 : layers.get(i - 1).getNeurons().get(k).getOutput());
                }
                neuron.adjustWeights(deltaWeights);

                // Ajustar sesgo
                neuron.adjustBias(learningRate * delta);
            }
        }
    }

    private double sigmoidDerivative(double x) {
        System.out.println("\t\t sigmoidDerivative x ="+x + "x * (1 - x)"+x * (1 - x));
        return x * (1 - x);
    }
}

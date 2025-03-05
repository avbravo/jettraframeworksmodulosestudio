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
public class Layer {
     private List<Neuron> neurons;

    public Layer(int numNeurons, int numInputsPerNeuron) {
        neurons = new ArrayList<>();
        for (int i = 0; i < numNeurons; i++) {
            neurons.add(new Neuron(numInputsPerNeuron));
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public double[] feedForward(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            double weightedSum = neuron.getBias();
            for (int j = 0; j < inputs.length; j++) {
                weightedSum += inputs[j] * neuron.getWeights()[j];
            }
            neuron.setOutput(sigmoid(weightedSum)); // Aplicar función de activación
            outputs[i] = neuron.getOutput();
        }
        return outputs;
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}

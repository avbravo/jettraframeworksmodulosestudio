/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.avbravo.redneuronal01;

/**
 *
 * @author avbravo
 */
public class Neuron {

    private double output; // Salida de la neurona
    private double[] weights; // Pesos de las conexiones
    private double bias; // Sesgo

    public Neuron(int numInputs) {
        this.weights = new double[numInputs];
        this.bias = Math.random() * 2 - 1; // Inicializar sesgo entre -1 y 1
        for (int i = 0; i < numInputs; i++) {
            this.weights[i] = Math.random() * 2 - 1; // Inicializar pesos entre -1 y 1
        }
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }

    public void adjustWeights(double[] deltaWeights) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += deltaWeights[i];
        }
    }

    public void adjustBias(double deltaBias) {
        bias += deltaBias;
    }
}

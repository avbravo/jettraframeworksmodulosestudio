/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.redneuronal01;

/**
 *
 * @author avbravo
 */
public class Redneuronal01 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
         NeuralNetwork nn = new NeuralNetwork(new int[]{2, 2, 1});

        // Datos de entrenamiento para XOR
        double[][] trainingData = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        double[][] expectedOutputs = {
            {0},
            {1},
            {1},
            {0}
        };

        // Entrenar la red
        nn.train(trainingData, expectedOutputs, 10000, 0.1);

        // Probar la red
        System.out.println("Pruebas:");
        for (double[] data : trainingData) {
            double[] output = nn.feedForward(data);
            System.out.printf("Entrada: [%f, %f] -> Salida: %f%n", data[0], data[1], output[0]);
        }
    }
}

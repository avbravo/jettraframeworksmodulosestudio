/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.redneuronalnino;

/**
 *
 * @author avbravo
 */
import java.util.Arrays;
import java.util.List;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Datos de entrenamiento: fragmentos de código
        List<String> codeSnippets = Arrays.asList(
            "x = 5",
            "y = x + 3",
            "print(y)",
            "if x > 0 print(x)"
        );

        // Tokenizador
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.fit(codeSnippets);

        // Modelo de aprendizaje
        ChildLearner learner = new ChildLearner(tokenizer, 10, 20, codeSnippets);

        // Fase 1: Aprendizaje básico
        for (String snippet : codeSnippets) {
            learner.learnFromExample(snippet);
        }

        // Fase 2: Exploración
        learner.explore();
    }
}
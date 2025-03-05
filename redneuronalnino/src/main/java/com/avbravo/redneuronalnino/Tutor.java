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

import java.util.List;

public record Tutor(List<String> correctExamples) {
    public String evaluate(String generatedCode) {
        for (String example : correctExamples) {
            if (generatedCode.equals(example)) {
                return "¡Correcto! Este código es válido.";
            }
        }
        return "Error: Este código no coincide con ningún ejemplo conocido.";
    }
}
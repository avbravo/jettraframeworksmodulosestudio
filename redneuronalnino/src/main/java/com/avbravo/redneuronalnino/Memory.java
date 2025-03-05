/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.redneuronalnino;

/**
 *
 * @author avbravo
 */
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public record Memory(List<String> successfulAttempts) {
    public Memory() {
        this(new ArrayList<>());
    }

    public void remember(String code) {
        successfulAttempts.add(code);
    }
}
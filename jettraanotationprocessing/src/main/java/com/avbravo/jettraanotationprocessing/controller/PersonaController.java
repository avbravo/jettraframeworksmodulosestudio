/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraanotationprocessing.controller;

import com.avbravo.jettraframework.annotationprocessing.EndpointController;
import com.avbravo.jettraframework.annotationprocessing.GetEndpoint;

/**
 *
 * @author avbravo
 */
@EndpointController(basePath = "/api")
public class PersonaController {
    @GetEndpoint(path = "/personas")
    public void getAllPersonas() {
        // Lógica para obtener todas las personas
    }

    @GetEndpoint(path = "/personas/{id}")
    public void getPersonaById() {
        // Lógica para obtener una persona por ID
    } 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.personainject.services;

import com.avbravo.personainject.inyecction.ApplicationScoped;
import com.avbravo.personainject.inyecction.Inject;
import com.avbravo.personainject.model.Persona;
import com.avbravo.personainject.repository.PersonaRepository;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class PersonaService {
    @Inject
    private PersonaRepository personaRepository;

    public void agregarPersona(String id, String nombre, int edad) {
        Persona persona = new Persona(id, nombre, edad);
        personaRepository.agregarPersona(persona);
    }

    public Persona buscarPersona(String id) {
        return personaRepository.buscarPorId(id);
    }

    public void actualizarPersona(String id, String nuevoNombre, int nuevaEdad) {
        Persona persona = personaRepository.buscarPorId(id);
        if (persona != null) {
            persona.setNombre(nuevoNombre);
            persona.setEdad(nuevaEdad);
            personaRepository.actualizarPersona(persona);
        }
    }

    public void eliminarPersona(String id) {
        personaRepository.eliminarPersona(id);
    }

    public void listarPersonas() {
        System.out.println("Lista de personas:");
        personaRepository.listarPersonas().forEach(System.out::println);
    }

}

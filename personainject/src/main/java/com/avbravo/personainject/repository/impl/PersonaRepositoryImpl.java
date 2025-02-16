/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.personainject.repository.impl;

import com.avbravo.personainject.inyecction.ApplicationScoped;
import com.avbravo.personainject.model.Persona;
import com.avbravo.personainject.repository.PersonaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class PersonaRepositoryImpl  implements PersonaRepository {

    private final List<Persona> personas = new ArrayList<>();

    @Override
    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    @Override
    public Persona buscarPorId(String id) {
        Optional<Persona> persona = personas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return persona.orElse(null);
    }

    @Override
    public List<Persona> listarPersonas() {
        return new ArrayList<>(personas);
    }

    @Override
    public void actualizarPersona(Persona persona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId().equals(persona.getId())) {
                personas.set(i, persona);
                break;
            }
        }
    }

    @Override
    public void eliminarPersona(String id) {
        personas.removeIf(p -> p.getId().equals(id));
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.personainject.repository;

import com.avbravo.personainject.model.Persona;
import java.util.List;

/**
 *
 * @author avbravo
 */
public interface PersonaRepository {
     void agregarPersona(Persona persona);
    Persona buscarPorId(String id);
    List<Persona> listarPersonas();
    void actualizarPersona(Persona persona);
    void eliminarPersona(String id);
}

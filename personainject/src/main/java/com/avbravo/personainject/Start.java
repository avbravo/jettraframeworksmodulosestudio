/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.personainject;

import com.avbravo.personainject.container.SimpleCDIContainer;
import com.avbravo.personainject.model.Persona;
import com.avbravo.personainject.repository.impl.PersonaRepositoryImpl;
import com.avbravo.personainject.services.PersonaService;

/**
 *
 * @author avbravo
 */
public class Start {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        // Crear el contenedor CDI
        SimpleCDIContainer container = new SimpleCDIContainer();

        // Registrar el repositorio y el servicio
        container.registerBean(PersonaRepositoryImpl.class);
        container.registerBean(PersonaService.class);

        // Obtener el servicio
        PersonaService personaService = container.getBean(PersonaService.class);

        // Agregar personas
        personaService.agregarPersona("1", "Juan", 30);
        personaService.agregarPersona("2", "María", 25);

        // Listar personas
        personaService.listarPersonas();

        // Buscar una persona
        Persona persona = personaService.buscarPersona("1");
        System.out.println("Persona encontrada: " + persona);

        // Actualizar una persona
        personaService.actualizarPersona("1", "Juan Pérez", 31);
        System.out.println("Persona actualizada: " + personaService.buscarPersona("1"));

        // Eliminar una persona
        personaService.eliminarPersona("2");
        personaService.listarPersonas();

    }
}

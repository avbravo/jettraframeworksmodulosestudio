/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.jettraanotationprocessing;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author avbravo
 */
public class Jettraanotationprocessing {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Registrar los handlers generados automáticamente
//        server.createContext("/api/personas", new PersonaControllerHandler.GetAllPersonasHandler());
//        server.createContext("/api/personas/{id}", new PersonaControllerHandler.GetPersonaByIdHandler());

        // Iniciar el servidor
        server.setExecutor(null); // Usa el executor por defecto
        server.start();
        System.out.println("Servidor iniciado en http://localhost:8080");
    }
}

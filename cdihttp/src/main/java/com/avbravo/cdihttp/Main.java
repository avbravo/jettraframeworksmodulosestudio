/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.cdihttp;

import com.avbravo.cdihttp.cdi.application.Application;
import com.avbravo.cdihttp.db.DB;
import java.util.ArrayList;

/**
 *
 * @author avbravo
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        DB.country = new ArrayList<>();
        DB.user = new ArrayList<>();
        Application.initialize("com.avbravo.cdihttp.repository");
        Application.initialize("com.avbravo.cdihttp.repository.implementation");
        Application.initialize("com.avbravo.cdihttp.service");

        // Iniciar el servidor HTTP
        HttpServerApp.startServer();

        System.out.println("Application is running...");
    }
}

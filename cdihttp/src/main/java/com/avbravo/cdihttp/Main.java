/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.cdihttp;

import com.avbravo.cdihttp.db.DB;
import com.avbravo.jettraframework.cdi.container.JettraConfigApplication;
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
        JettraConfigApplication.initialize("com.avbravo.cdihttp.repository");
        JettraConfigApplication.initialize("com.avbravo.cdihttp.repository.implementation");
        JettraConfigApplication.initialize("com.avbravo.cdihttp.service");

        // Iniciar el servidor HTTP
        HttpServerApp.startServer();

        System.out.println("Application is running...");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.avbravo.jmoordbjettrra;

import com.avbravo.jettraframework.JettraFramework;
import com.avbravo.jettraframework.cdi.container.JettraConfigApplication;
import com.avbravo.jettraframework.enumerations.Protocol;
import com.avbravo.jettraframework.model.JettraContext;
import com.avbravo.jmoordbjettrra.controller.CountryHandler;
import com.avbravo.jmoordbjettrra.controller.UserHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class Start {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        /*
        Carga la configuracion para @CDI
         */
        JettraConfigApplication.initialize("com.avbravo.jmoordbjettrra.repository");
        JettraConfigApplication.initialize("com.avbravo.jmoordbjettrra.repository.implementation");
        JettraConfigApplication.initialize("com.avbravo.jmoordbjettrra.produces");
        JettraConfigApplication.initialize("com.avbravo.jmoordbjettrra.controller");

        List<JettraContext> jettraContexts = new ArrayList<>();
        jettraContexts.add(new JettraContext("/country", new CountryHandler()));
        jettraContexts.add(new JettraContext("/country/", new CountryHandler()));
        jettraContexts.add(new JettraContext("/user", new UserHandler()));
        jettraContexts.add(new JettraContext("/user/", new UserHandler()));
      

        String host = "localhost";
        JettraFramework local = new JettraFramework.Builder()
                .protocol(Protocol.HTTP)
                .port(8080)
                .logo(Boolean.TRUE)
                .jettraContext(jettraContexts)
                .start();
    }
}

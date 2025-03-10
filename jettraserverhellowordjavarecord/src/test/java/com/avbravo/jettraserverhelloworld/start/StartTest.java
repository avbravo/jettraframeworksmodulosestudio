/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.start;

import com.avbravo.jettraserverhelloworld.configuration.JakartaRestConfiguration;
import com.avbravo.jettraserverhelloworld.controller.CountryController;
import com.avbravo.jettraserverhelloworld.controller.EmployeeController;
import com.avbravo.jettraserverhelloworld.controller.HelloController;
import com.avbravo.jettraserverhelloworld.view.EmpleadoView;
import com.avbravo.jettraserverhelloworld.view.HomeView;
import com.avbravo.jettraserverhelloworld.view.J2htmlView;
import com.avbravo.jettraserverhelloworld.view.JmoordbJ2htmlView;
import com.avbravo.jettraserverhelloworld.view.TailwindView;
import com.avbravo.jettraserverhelloworld.view.template.AboutView;
import com.avbravo.jettraserverhelloworld.view.template.TemplateView;
import com.jettraserver.JettraServer;
import com.jettraserver.enumerations.Protocol;
import com.jettraserver.health.JettraHealthController;
import jakarta.ws.rs.SeBootstrap;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author avbravo
 */
public class StartTest {
    
    public StartTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class Start.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
         String host="localhost";
//        JettraServer local = new JettraServer.Builder()
//                .protocol(Protocol.HTTP)
//                .host(host)
//                .port(7070)
//                .sslClientAuthentication(SeBootstrap.Configuration.SSLClientAuthentication.NONE)
//                .rootPath("api")
//                .logo(Boolean.TRUE)
//                .application(new JakartaRestConfiguration() {
//                    @Override
//                    public Set<Class<?>> getClasses() {
//                        Set<Class<?>> classes = new HashSet<>();
//                        classes.add(EmployeeController.class);
//                        classes.add(HelloController.class);
//                        classes.add(CountryController.class);
//                        classes.add(JettraHealthController.class);
//
//                        classes.add(TailwindView.class);
//                        classes.add(TemplateView.class);
//                        classes.add(HomeView.class);
//                        classes.add(AboutView.class);
//
//                        classes.add(EmpleadoView.class);
//                        classes.add(J2htmlView.class);
//                        classes.add(JmoordbJ2htmlView.class);
//                        return classes;
//                    }
//                }
//                )
//                .start();
        String[] args = null;
        Start.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

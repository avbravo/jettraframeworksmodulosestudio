/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.avbravo.jettraserverhelloworld.start;

import com.avbravo.jettraserverhelloworld.configuration.JakartaRestConfiguration;
import com.avbravo.jettraserverhelloworld.controller.CountryController;
import com.avbravo.jettraserverhelloworld.controller.EmployeeController;
import com.avbravo.jettraserverhelloworld.controller.HelloController;
import com.avbravo.jettraserverhelloworld.view.BlankView;
import com.avbravo.jettraserverhelloworld.view.CalendarView;
import com.avbravo.jettraserverhelloworld.view.ChartJSView;
import com.avbravo.jettraserverhelloworld.view.CountryView;
import com.avbravo.jettraserverhelloworld.view.DarkModeView;
import com.avbravo.jettraserverhelloworld.view.DashboardView;
import com.avbravo.jettraserverhelloworld.view.EmpleadoView;
import com.avbravo.jettraserverhelloworld.view.FormsView;
import com.avbravo.jettraserverhelloworld.view.GraficaView;
import com.avbravo.jettraserverhelloworld.view.template.AboutView;
import com.avbravo.jettraserverhelloworld.view.HomeView;
import com.avbravo.jettraserverhelloworld.view.J2htmlView;
import com.avbravo.jettraserverhelloworld.view.JmoordbJ2htmlView;
import com.avbravo.jettraserverhelloworld.view.LoginView;
import com.avbravo.jettraserverhelloworld.view.MapaView;
import com.avbravo.jettraserverhelloworld.view.RegistrarseView;
import com.avbravo.jettraserverhelloworld.view.TableView;
import com.avbravo.jettraserverhelloworld.view.TabsView;
import com.avbravo.jettraserverhelloworld.view.TailwindView;
import com.avbravo.jettraserverhelloworld.view.template.TemplateView;
import com.jettraserver.JettraServer;
import com.jettraserver.enumerations.Protocol;
import com.jettraserver.health.JettraHealthController;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.TransactionManager;

import jakarta.ws.rs.SeBootstrap.Configuration.SSLClientAuthentication;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import jakarta.transaction.UserTransaction;

/**
 *
 * @author avbravo
 */
public class Start {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
      
//          UserTransaction userTransaction = TransactionManager.userTransaction();
//        userTransaction.begin();
//        // Perform transactional work
//        userTransaction.commit();
//         Car car = new CarBuilder()
//                 .setMarca("Toyota")
//                 .setModelo("Corolla")
//                 .build();
//
//        System.out.println("--->Toyota[]= "+car.marca);
////      assertEquals("toyota", car.marca);
              /**
         * Con RootPath
         */
  String host="localhost";
//  String host="192.168.60.243";
   //      String host="192.168.50.116";
        JettraServer local = new JettraServer.Builder()
                .protocol(Protocol.HTTP)
                .host(host)
                .port(8080)
                .runInternalTest(Boolean.FALSE)
                .sslClientAuthentication(SSLClientAuthentication.NONE)
                .rootPath("api")
                .logo(Boolean.TRUE)
                .application(new JakartaRestConfiguration() {
                    @Override
                    public Set<Class<?>> getClasses() {
                        Set<Class<?>> classes = new HashSet<>();
                        classes.add(EmployeeController.class);
                        classes.add(HelloController.class);
                        classes.add(CountryController.class);
                        classes.add(JettraHealthController.class);

                        classes.add(ChartJSView.class);
                        classes.add(GraficaView.class);
                        classes.add(TailwindView.class);
                        classes.add(TemplateView.class);
                        classes.add(MapaView.class);
                        classes.add(HomeView.class);
                        classes.add(AboutView.class);
                        classes.add(EmpleadoView.class);
                        classes.add(LoginView.class);
                        classes.add(J2htmlView.class);
                        classes.add(JmoordbJ2htmlView.class);
                        classes.add(TableView.class);
                        classes.add(TabsView.class);
                        classes.add(CalendarView.class);
                        classes.add(FormsView.class);
                        classes.add(BlankView.class);
                        classes.add(DashboardView.class);
                        classes.add(RegistrarseView.class);
                        classes.add(DarkModeView.class);
                        classes.add(CountryView.class);
                        return classes;
                    }
                }
                )
                .start();
        
        
//SeBootstrap.start(JakartaRestConfiguration.class)
//        .thenApply(instance -> {
//            instance.stopOnShutdown((stopResult -> System.out.println("Container has stopped.")));
//            try (Client client = ClientBuilder.newClient()) {
//                final Response response = client.target(instance.configuration()
//                        .baseUriBuilder()
//                        .path("rest")).request().get();
//                System.out.println(response.readEntity(String.class));
//            }
//            return instance;
//        }).toCompletableFuture().get();
        /**
         * Con RootPath
         */
//        JettraServer local = new JettraServer.Builder()
//                .protocol("HTTP")
//                .host("localhost")
//                .port(8080)
//                .rootPath("api")
//                .logo(Boolean.TRUE)
//                .application(
//                        new JakartaRestConfiguration() {
//                    @Override
//                    public Set<Class<?>> getClasses() {
//                        Set<Class<?>> classes = new HashSet<>();
//                        classes.add(EmployeeController.class);
//                        classes.add(HelloController.class);
//                        classes.add(JettraHealthController.class);
//                        return classes;
//                    }
//                }
//                )
//                .jaxRun();

  
// http://localhost:8080/api/jettrahello       

        /**
         * Sin RootPath
         *
         */
//        JettraServer sinRootPath = new JettraServer.Builder()
//                .protocol("HTTP")
//                .host("localhost")
//                .port(8081)
//                 .rootPath("")
//.logo(Boolean.FALSEº)
//                .application(
//                        new JakartaRestConfiguration(){
//                    @Override
//                    public Set<Class<?>> getClasses() {
//                        Set<Class<?>> classes = new HashSet<>();
//                        classes.add(EmployeeController.class);
//                        classes.add(HelloController.class);
//                        classes.add(JettraHealthController.class);
//                        return classes;
//                    }
//                }
//                )
//                .start();
        // http://localhost:8081/jettrahello
//        //HTTPS/TLS
//        // Se necesita certificado
//        JettraServer local = new JettraServer.Builder()
//                .protocol("HTTPS")
//                .host("localhost")
//                .rootPath("api")
//                .tls("TLSv1.2")
//                .port(8080)
//                .application(
//                        new JakartaRestConfiguration(){
//                    @Override
//                    public Set<Class<?>> getClasses() {
//                        Set<Class<?>> classes = new HashSet<>();
//                        classes.add(EmployeeController.class);
//                        classes.add(HelloController.class);
//                        classes.add(JettraHealthController.class);
//                        return classes;
//                    }
//                }
//                )
//                .start();
    }
}

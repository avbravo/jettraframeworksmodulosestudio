/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.avbravo.cdihttp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import jakarta.faces.FactoryFinder;
import jakarta.faces.application.Application;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.FacesContextFactory;
import jakarta.faces.lifecycle.Lifecycle;
import jakarta.faces.lifecycle.LifecycleFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author avbravo
 */
public class Main {

    public static void main(String[] args) throws IOException {
         HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Manejador para solicitudes HTTP
        server.createContext("/", exchange -> {
            try {
                // Simular el ciclo de vida de JSF
                FacesContext facesContext = createFacesContext(exchange);
                Lifecycle lifecycle = getLifecycle();

                // Resolver la vista (index.xhtml)
                Application application = facesContext.getApplication();
                ViewHandler viewHandler = application.getViewHandler();
                UIViewRoot viewRoot = viewHandler.createView(facesContext, "/views/index.xhtml");
                facesContext.setViewRoot(viewRoot);

                // Ejecutar el ciclo de vida de JSF
                lifecycle.execute(facesContext);
                lifecycle.render(facesContext);

                // Obtener la respuesta renderizada
                String response = facesContext.getResponseWriter().toString();
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                // Liberar recursos
                facesContext.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Iniciar el servidor
        server.setExecutor(null); // Usa el executor por defecto
        server.start();
        System.out.println("Servidor iniciado en http://localhost:8080/");
    }

    private static FacesContext createFacesContext(HttpExchange exchange) {
        // Crear un contexto personalizado para JSF
        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        return contextFactory.getFacesContext(null, exchange.getRequestHeaders(), exchange.getResponseBody(), null);
    }

    private static Lifecycle getLifecycle() {
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        return lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
    }
}
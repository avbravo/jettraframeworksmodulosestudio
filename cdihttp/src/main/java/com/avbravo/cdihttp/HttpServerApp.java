/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp;

/**
 *
 * @author avbravo
 */
import com.avbravo.cdihttp.cdi.application.Application;
import com.avbravo.cdihttp.service.CountryService;
import com.avbravo.cdihttp.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerApp {

    public static void startServer() {
        try {
               long start = System.currentTimeMillis();

                System.out.println("\n___________________________________________________________________________");
                System.out.println("                          Htttp configuration....");
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/users/add", exchange -> handleAddUser(exchange));
            server.createContext("/users/get", exchange -> handleGetUser(exchange));
            server.createContext("/countries/add", exchange -> handleAddCountry(exchange));
            server.createContext("/countries/get", exchange -> handleGetCountry(exchange));

        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            server.start();
            System.out.println("HTTP Server started on port 8080");
            
               long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                   System.out.println("\tServer started in WITH TEST: " + timeElapsed + "ms");
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to start HTTP server", e);
        }
    }

    private static void handleAddUser(HttpExchange exchange) throws IOException {
        UserService userService = Application.getBean(UserService.class);

        if ("POST".equals(exchange.getRequestMethod())) {
            String user = new String(exchange.getRequestBody().readAllBytes());
            userService.addUser(user);

            sendResponse(exchange, "User added: " + user);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use POST.");
        }
    }

    private static void handleGetUser(HttpExchange exchange) throws IOException {
        UserService userService = Application.getBean(UserService.class);

        if ("GET".equals(exchange.getRequestMethod())) {
            String id = exchange.getRequestURI().getQuery().split("=")[1];
            String user = userService.getUser(id);

            sendResponse(exchange, "Retrieved user: " + user);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use GET.");
        }
    }
   
    private static void handleAddCountry(HttpExchange exchange) throws IOException {
        CountryService countryService = Application.getBean(CountryService.class);

        if ("POST".equals(exchange.getRequestMethod())) {
            String country = new String(exchange.getRequestBody().readAllBytes());
            countryService.addCountry(country);

            sendResponse(exchange, "Country added: " + country);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use POST.");
        }
    }

    private static void handleGetCountry(HttpExchange exchange) throws IOException {
        CountryService countryService = Application.getBean(CountryService.class);

        if ("GET".equals(exchange.getRequestMethod())) {
            String id = exchange.getRequestURI().getQuery().split("=")[1];
            String country = countryService.getCountry(id);

            sendResponse(exchange, "Retrieved country: " + country);
        } else {
            sendErrorResponse(exchange, "Invalid request method. Use GET.");
        }
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void sendErrorResponse(HttpExchange exchange, String errorMessage) throws IOException {
        exchange.sendResponseHeaders(400, errorMessage.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(errorMessage.getBytes());
        os.close();
    }
}
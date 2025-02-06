/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jmoordbjettrra.controller;
import com.avbravo.jettraframework.cdi.Inject;
import com.avbravo.jettraframework.cdi.Prototype;
import com.avbravo.jmoordbjettrra.model.Country;
import com.avbravo.jmoordbjettrra.repository.CountryRepository;
import com.avbravo.jmoordbjettrra.repository.CountryRepositoryImpl;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author avbravo
 */
@Prototype
public class CountryHandler    implements HttpHandler {
    @Inject
    CountryRepository countryRepository;
    
    private static final List<Country> countrys = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();

            try {
                if (method.equalsIgnoreCase("GET") && path.equals("/country")) {
                    handleGetAllCountrys(exchange);
                } else if (method.equalsIgnoreCase("GET") && path.startsWith("/country/")) {
                    handleGetCountryById(exchange);
                } else if (method.equalsIgnoreCase("POST") && path.equals("/country")) {
                    handleCreateCountry(exchange);
                } else if (method.equalsIgnoreCase("DELETE") && path.startsWith("/country/")) {
                    handleDeleteCountry(exchange);
                } else {
                    sendResponse(exchange, 404, "Endpoint no encontrado");
                }
            } catch (Exception e) {
                sendResponse(exchange, 500, "Error interno del servidor: " + e.getMessage());
            }
        }

        private void handleGetAllCountrys(HttpExchange exchange) throws IOException {
            
          //  countrys.add(new Country("avbravo","aristides","avbravo@gmail.com"));
//            String jsonResponse = objectMapper.writeValueAsString(countrys);
         List<Country> countrys= countryRepository.findAll();
             String jsonResponse = objectMapper.writeValueAsString(countrys);
            sendResponse(exchange, 200, jsonResponse);
        }

        private void handleGetCountryById(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            Optional<Country> country = countrys.stream()
                    .filter(u -> u.getIdcountry().equals(id))
                    .findFirst();

            if (country.isPresent()) {
                String jsonResponse = objectMapper.writeValueAsString(country.get());
                sendResponse(exchange, 200, jsonResponse);
            } else {
                sendResponse(exchange, 404, "Usuario no encontrado");
            }
        }

        private void handleCreateCountry(HttpExchange exchange) throws IOException {
            String body = new String(exchange.getRequestBody().readAllBytes());
            Country country = objectMapper.readValue(body, Country.class);
            countrys.add(country);

            sendResponse(exchange, 201, objectMapper.writeValueAsString(country));
        }

        private void handleDeleteCountry(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String id = path.substring(path.lastIndexOf("/") + 1);

            boolean removed = countrys.removeIf(country -> country.getIdcountry().equals(id));

            if (removed) {
                sendResponse(exchange, 204, "");
            } else {
                sendResponse(exchange, 404, "Usuario no encontrado");
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


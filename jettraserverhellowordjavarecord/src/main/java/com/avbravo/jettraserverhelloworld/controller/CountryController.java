/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.controller;

import com.avbravo.jettraserverhelloworld.model.Country;
import com.avbravo.jettraserverhelloworld.model.Employee;
import com.avbravo.jettraserverhelloworld.repository.CountryRepository;
import com.jettraserver.config.JettraConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Path("/country")
@ApplicationScoped
public class CountryController implements Serializable, JettraConfig {

    @Inject
    CountryRepository countryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            List<Country> result = countryRepository.findAll();
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e.getLocalizedMessage().toString()).build();
        }

    }
// </editor-fold>

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Country country) throws URISyntaxException {
        Optional<Country> countryResult = countryRepository.save(country);
        URI uri = new URI("/country/" + countryResult.get().idcountry());
        return Response.created(uri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountry(Country country) throws URISyntaxException {
        try {
            System.out.println("---------------------------UPDATE----------------------------");
            System.out.println("country  "+country.toString());
            System.out.println("--------------------------------------------------------");
            countryRepository.update(country);
        } catch (Exception e) {
            System.out.println("error " + e.getLocalizedMessage());
            Response.ok("error " + e.getLocalizedMessage());
        }
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCountry(@PathParam("id") String id) {
        System.out.println("---------------------------------------------");
        System.out.println(">>>> id :"+id);
        System.out.println("---------------------------------------------");
        Optional<Country> match = countryRepository.findByPk(id);
        Country country = match.get();
        if (match.isPresent()) {
            return Response.ok(country, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        System.out.println(">>>> delete "+id);
         countryRepository.deleteByPk(id);

        return Response.ok().build();
    }
}

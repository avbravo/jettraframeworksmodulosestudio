package com.avbravo.jettraserverhelloworld.view.template;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.avbravo.jettraserverhelloworld.services.EmployeeService;
import com.jettraserver.view.JettraView;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avbravo
 */
@Path("/view/template")
public class TemplateView implements JettraView{

    @Inject
    EmployeeService employeeService;
    @Override
    public Response draw() {
        return generate("com/web/pages/template/tailwindadmintemplate/index.html");
        // return generate("com/web/pages/template/template.html");
       // return generate("com/web/pages/template/admintemplate/admintemplate.html");
      //  return generate("com/web/pages/template/template.html");
        
    }


    

}

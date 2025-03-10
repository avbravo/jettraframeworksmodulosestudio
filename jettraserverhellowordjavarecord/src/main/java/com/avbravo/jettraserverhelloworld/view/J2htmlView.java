/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.view;

import com.avbravo.jettraserverhelloworld.model.Employee;
import com.avbravo.jettraserverhelloworld.services.EmployeeService;
import com.jettraserver.view.JettraView;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.List;
import static j2html.TagCreator.*;
import jakarta.inject.Inject;

/**
 *
 * @author avbravo
 */
@Path("/view/j2html")
public class J2htmlView implements JettraView{
  @Inject
    EmployeeService employeeService;
  
    @Override
    public Response draw() {
        List<Employee> result = employeeService.getAllEmployees();
        
        
        var bod = body(
                div(attrs("#employees"),
                        p("Some sibling element"),
                        each(filter(result, employee -> employee != null), employee
                                -> div(attrs(".employee"),
                                h2(employee.getFirstname()),
                                img().withSrc(employee.getJobTitle()),
                                p(employee.getLastname())
                        )
                        )
                )
        ).render();

        return Response.ok(bod).build();
        
    }

  

//  
}

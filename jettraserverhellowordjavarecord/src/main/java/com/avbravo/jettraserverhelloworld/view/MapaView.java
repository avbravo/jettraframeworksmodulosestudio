package com.avbravo.jettraserverhelloworld.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.avbravo.jettraserverhelloworld.services.EmployeeService;
import com.jettraserver.view.JettraView;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Path("/view/mapa")
public class MapaView implements JettraView{

   
   

    @Override
    public Response draw() {
        return generate("com/web/pages/mapa.html");
    }

}

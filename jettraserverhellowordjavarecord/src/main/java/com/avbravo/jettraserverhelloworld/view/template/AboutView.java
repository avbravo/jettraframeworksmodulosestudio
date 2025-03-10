/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.view.template;

import com.jettraserver.view.JettraView;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author avbravo
 */
@Path("/view/about")
public class AboutView implements JettraView{

    @Override
    public Response draw() {
        return generate("com/web/pages/template/about.html");
        
    }

}

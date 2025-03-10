/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.start;

import com.avbravo.jettraprocessor.annotation.Informacion;
import com.avbravo.jettraserverhelloworld.annotationprocessor.Pagina;
import java.lang.annotation.Annotation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author avbravo
 */
public class PaginaTest {

    public PaginaTest() {
    }


    @Test
    public void testSomeMethod() {
        Pagina welcome = new Pagina();

        Annotation companyAnnotation = welcome
                .getClass()
                .getAnnotation(Informacion.class);
        Informacion informacion = (Informacion) companyAnnotation;
        assertEquals("bienvenido", informacion.name());

    }

}

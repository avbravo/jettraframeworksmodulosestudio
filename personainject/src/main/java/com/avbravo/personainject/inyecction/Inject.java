/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.personainject.inyecction;

/**
 *
 * @author avbravo
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Anotación para marcar un bean con ámbito ApplicationScoped
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    
}

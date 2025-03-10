/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.annotationprocessor;

import com.avbravo.jettraprocessor.annotation.BuilderProperty;

/**
 *
 * @author avbravo
 */
public class Medico {
    private int id;
    private String firstname;
  
    private String lastname;
   

    public Medico() {
    }

    public Medico(int id) {
        this.id = id;
    }

    public Medico(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

    }
    // Note that getter , setter, hashcode and equals method are removed for brevity

    @Override
    public String toString() {
        return "Medico{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + '}';
    }

    
    
   
    public int getId() {
        return id;
    }
@BuilderProperty
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }
@BuilderProperty
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
@BuilderProperty
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
    
}

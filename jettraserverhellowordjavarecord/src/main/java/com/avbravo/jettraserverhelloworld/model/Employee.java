/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.model;

import com.avbravo.jettraprocessor.annotation.BuilderProperty;

/**
 *
 * @author avbravo
 */
public class Employee {
     private int id;
    private String firstname;
  
    private String lastname;
    private String jobTitle;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String firstname, String lastname, String jobTitle) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.jobTitle = jobTitle;
    }
    // Note that getter , setter, hashcode and equals method are removed for brevity

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", jobTitle=" + jobTitle + '}';
    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    
    
}

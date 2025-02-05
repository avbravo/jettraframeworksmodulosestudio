/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.cdihttp.repository.implementation;

import com.avbravo.cdihttp.repository.CountryRepository;
import com.avbravo.jettraframework.cdi.Singleton;

/**
 *
 * @author avbravo
 */
@Singleton
public class CountryRepositoryImpl implements CountryRepository {

    @Override
    public void save(String country) {
        System.out.println("Saving country: " + country);
    }

    @Override
    public String find(String id) {
        System.out.println("Finding country with ID: " + id);
        return "Country-" + id;
    }
    
}

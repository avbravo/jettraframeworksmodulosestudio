/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.service;

import com.avbravo.cdihttp.repository.CountryRepository;
import com.avbravo.jettraframework.cdi.Inject;
import com.avbravo.jettraframework.cdi.Prototype;

/**
 *
 * @author avbravo
 */
@Prototype
public class CountryService {
     @Inject
    private CountryRepository countryRepository;

    public void addCountry(String country) {
        countryRepository.save(country);
    }

    public String getCountry(String id) {
        return countryRepository.find(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.repository;

/**
 *
 * @author avbravo
 */
public interface CountryRepository {
    void save(String countryr);
    String find(String id);
}

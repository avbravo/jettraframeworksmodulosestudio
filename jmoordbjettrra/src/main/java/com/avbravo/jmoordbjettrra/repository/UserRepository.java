/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jmoordbjettrra.repository;

import com.avbravo.jmoordbjettrra.model.User;
import java.util.List;

/**
 *
 * @author avbravo
 */
public interface UserRepository {
    void save(String user);
    String find(String id);
    List<User> findAll();
}

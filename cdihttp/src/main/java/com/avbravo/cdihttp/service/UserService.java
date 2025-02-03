/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.service;

import com.avbravo.cdihttp.cdi.Inject;
import com.avbravo.cdihttp.cdi.Prototype;
import com.avbravo.cdihttp.repository.UserRepository;

/**
 *
 * @author avbravo
 */
@Prototype
public class UserService {
    @Inject
    private UserRepository userRepository;

    public void addUser(String user) {
        userRepository.save(user);
    }

    public String getUser(String id) {
        return userRepository.find(id);
    }
}

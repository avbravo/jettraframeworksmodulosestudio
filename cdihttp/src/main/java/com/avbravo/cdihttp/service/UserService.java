/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.service;

import com.avbravo.cdihttp.repository.UserRepository;
import com.avbravo.jettraframework.cdi.Inject;
import com.avbravo.jettraframework.cdi.Prototype;

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.repository.implementation;

import com.avbravo.cdihttp.cdi.Singleton;
import com.avbravo.cdihttp.db.DB;
import com.avbravo.cdihttp.model.User;
import com.avbravo.cdihttp.repository.UserRepository;

/**
 *
 * @author avbravo
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(String user) {
        System.out.println("Saving user: " + user);
        DB.user.add(new User("1-2",user,""));
    }

    @Override
    public String find(String id) {
        System.out.println("Finding user with ID: " + id);
        return DB.user.getLast().getId()+ " "+DB.user.getLast().getName();
//        return "User-" + id;
    }
    
}

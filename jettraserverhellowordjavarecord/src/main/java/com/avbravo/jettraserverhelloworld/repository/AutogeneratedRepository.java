/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.repository;

import com.jmoordb.core.annotation.autosecuence.Autogenerated;
import com.jmoordb.core.annotation.autosecuence.AutosecuenceRepository;
import com.jmoordb.core.annotation.enumerations.ConfigEngine;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import com.jmoordb.core.model.Autosequence;

/**
 *
 * @author avbravo
 */

@AutosecuenceRepository(entity = Autosequence.class, configEngine = ConfigEngine.JETTRA_CONFIG)
public interface AutogeneratedRepository {

    @Autogenerated()
    public Long generate(String database, String collection);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.repository;

import com.avbravo.jettraserverhelloworld.model.Country;
import com.jmoordb.core.annotation.enumerations.CaseSensitive;
import com.jmoordb.core.annotation.enumerations.ConfigEngine;
import com.jmoordb.core.annotation.enumerations.LikeByType;
import com.jmoordb.core.annotation.enumerations.TypeOrder;
import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.CountLikeBy;
import com.jmoordb.core.annotation.repository.LikeBy;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(entity = Country.class,configEngine = ConfigEngine.JETTRA_CONFIG)
public interface CountryRepository extends CrudRepository<Country, String> {

//    @Find 
//    public List<Country> findAll();
      
    @Lookup
    public List<Country> lookup(Search search);

    @Count()
    public Long count(Search... search);
    
    @CountLikeBy(caseSensitive = CaseSensitive.NO, likeByType = LikeByType.ANYWHERE)
    public Long countLikeByCountry(String country);

    @LikeBy(caseSensitive = CaseSensitive.NO, typeOrder = TypeOrder.ASC, likeByType = LikeByType.ANYWHERE)
    public List<Country> likeByCountry(String country);
}

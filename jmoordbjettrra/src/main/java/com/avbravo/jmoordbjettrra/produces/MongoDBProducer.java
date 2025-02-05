/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jmoordbjettrra.produces;

/**
 *
 * @author avbravo
 */
import com.avbravo.jettraframework.cdi.ApplicationScoped;
import com.avbravo.jettraframework.cdi.Disposes;
import com.avbravo.jettraframework.cdi.Produces;
import com.avbravo.jettraframework.config.JettraConfig;
import com.jmoordb.core.annotation.DateSupport;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.io.Serializable;

@ApplicationScoped
@DateSupport(jakartaSource = JakartaSource.JAKARTA)
public class MongoDBProducer implements Serializable, JettraConfig {

//    @Inject
//    private Config config;
//    @Inject
//    @ConfigProperty(name = "mongodb.uri")
//    private String mongodburi;
private String mongodburi =getMicroprofileConfig("mongodb.uri");


    @Produces
    @ApplicationScoped
    public MongoClient mongoClient() {      
        MongoClient mongoClient = MongoClients.create(mongodburi);
       return mongoClient;

    }

    public void close(@Disposes final MongoClient mongoClient) {
        mongoClient.close();
    }

}

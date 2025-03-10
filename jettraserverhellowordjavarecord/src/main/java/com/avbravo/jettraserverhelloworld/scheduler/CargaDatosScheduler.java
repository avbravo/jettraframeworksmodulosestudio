/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.scheduler;

import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.MessagesUtil;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author josecollado
 */
@Stateless
public class CargaDatosScheduler implements Serializable{
    
//    private String nameOfCollection = "medicion_";
//    @Inject
//    Estacion1Services estacionServices;
//    @Inject
//    MedicionRepository medicionRepository;
//    @Inject
//    EstacionRepository estacionRepository;
    
    //@Schedule(hour = "02", minute = "30", second = "00", persistent = false)
    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void schedule1() {
        try {
            System.out.println("---at "+new Date());
        //Hilos para cada estacion
//        List<Estacion> estacionList = estacionRepository.findAll();
//        for(Estacion e:estacionList){
//
//            // Hilos
//            String url = "http://127.0.0.1:" + e.getUrl(); //Cambiar por la dirección de las estaciones reales
//            ConsoleUtil.info("Estoy buscando en: " + url);
//            ConsoleUtil.info("--------------------");
//            List<Medicion> list= estacionServices.findAll(url);
//            if(list == null || list.isEmpty()){
//                ConsoleUtil.warning("No encontre nada");
//            }         
//            {                
//                for(Medicion m:list){
//                    ConsoleUtil.info("CO: " + m.getCo().toString());
//                    ConsoleUtil.info("O3: " + m.getO3().toString());
//                    ConsoleUtil.info("NO2: " + m.getNo2().toString());
//                    ConsoleUtil.info("SO2: " + m.getSo2().toString());
//                    ConsoleUtil.info("PM 10: " + m.getPm10().toString());
//                    ConsoleUtil.info("PM 2.5: " + m.getPm25().toString());
//                    ConsoleUtil.info("FechaHora: " + m.getFechahora().toString());
//                    Boolean conIsoDate = Boolean.TRUE;
//                    if (conIsoDate) {
//                        Date dateconverter = JmoordbCoreDateUtil.dateToiSODateToDate(m.getFechahora());
//                        medicionRepository.setDynamicDatabase("lecturas_" + JmoordbCoreDateUtil.anioDeUnaFecha(dateconverter).toString().trim() + "db");
//                        Integer numeroMes = JmoordbCoreDateUtil.mesDeUnaFechaStartEneroWith0(dateconverter);
//                        medicionRepository.setDynamicCollection(nameOfCollection + m.getIdestacion().toString().trim() + "_" + JmoordbCoreDateUtil.getNombreMes(numeroMes).toLowerCase());
//
//                    } else {
//                        medicionRepository.setDynamicDatabase("lecturas_" + JmoordbCoreDateUtil.anioDeUnaFecha(m.getFechahora()).toString().trim() + "db");
//                        Integer numeroMes = JmoordbCoreDateUtil.mesDeUnaFechaStartEneroWith0(m.getFechahora());
//                        medicionRepository.setDynamicCollection(nameOfCollection + m.getIdestacion().toString().trim() + "_" + JmoordbCoreDateUtil.getNombreMes(numeroMes).toLowerCase());
//
//                    }
//                    medicionRepository.save(m);
//                }  
//            }
//        }
        } catch (Exception e) {
            ConsoleUtil.error(MessagesUtil.nameOfClassAndMethod() + " error: " + e.getLocalizedMessage());

        }

    }

}

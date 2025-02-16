/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.personainject.container;

import com.avbravo.personainject.inyecction.ApplicationScoped;
import com.avbravo.personainject.inyecction.Inject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avbravo
 */
public class SimpleCDIContainer {
     private final Map<Class<?>, Object> beanMap = new HashMap<>();

    /**
     * Registra un bean en el contenedor.
     */
    public <T> void registerBean(Class<T> beanClass) throws Exception {
        if (beanClass.isAnnotationPresent(ApplicationScoped.class)) {
            T instance = beanClass.getDeclaredConstructor().newInstance();
            beanMap.put(beanClass, instance);
        }
    }

    /**
     * Obtiene una instancia de un bean registrado.
     */
    public <T> T getBean(Class<T> beanClass) throws Exception {
        if (!beanMap.containsKey(beanClass)) {
            registerBean(beanClass);
        }
        return beanClass.cast(beanMap.get(beanClass));
    }

    /**
     * Inyecta dependencias en un objeto.
     */
    public void injectDependencies(Object target) throws Exception {
        for (Field field : target.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = getBean(fieldType);
                field.set(target, dependency);
            }
        }
    }

}

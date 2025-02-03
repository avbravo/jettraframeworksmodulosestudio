/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.cdihttp.cdi.application;

import com.avbravo.cdihttp.cdi.container.BeanContainer;

/**
 *
 * @author avbravo
 */
public class Application {
     private static final BeanContainer container = new BeanContainer();

    public static void initialize(String packageName) {
        container.scanAndRegister(packageName);
    }

    public static <T> T getBean(Class<T> beanClass) {
        return container.getBean(beanClass);
    }
}

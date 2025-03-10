/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.start;

import com.avbravo.jettraserverhelloworld.annotationprocessor.Car;
import com.avbravo.jettraserverhelloworld.annotationprocessor.CarBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author avbravo
 */
public class CarUnitTest {

    public CarUnitTest() {
    }

   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        Car car = new CarBuilder()
                .setMarca("Toyota")
                .setModelo("Corolla")
                .build();
        assertEquals("Toyota", car.marca);

    }
}

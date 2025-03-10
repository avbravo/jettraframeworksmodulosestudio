/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.jettraserverhelloworld.services;

/**
 *
 * @author avbravo
 */

import com.avbravo.jettraserverhelloworld.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeService implements Serializable{
     public EmployeeService() {
    }

    static List<Employee> employeeList = new ArrayList();

    static {
        employeeList.add(new Employee(1, "Thomas", "Paine", "Sales"));
        employeeList.add(new Employee(2, "Bill", "Withers", "Accountant"));
        employeeList.add(new Employee(3, "Fela", "Kuti", "Software Developer"));

    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public int addEmployee(Employee employee) {
        int newId = employeeList.size() + 1;
        employee.setId(newId);
        employeeList.add(employee);
        return newId;
    }

    public void delete(Employee employee) {
        employeeList.remove(employee);
    }
    
    public boolean update(Employee employee) {
        int index = employeeList.indexOf(employee);
        if (index >= 0) {
            employeeList.set(index, employee);
            return true;
        }
        return false;
    }

    public static List<Employee> getEmployeeList() {
        return employeeList;
    }

}

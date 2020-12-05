package com.employeeslist;

import com.AddEmployees.Employee;
import com.configuration.DatabaseHandler;

import java.sql.SQLException;
import java.util.LinkedList;

public class EmployeesList {

    private static LinkedList<Employee> employeesList = new LinkedList<>();
    private static DatabaseHandler databaseHandler = new DatabaseHandler();


    public boolean addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        if (findEmployeeIndex(employee) > 0){
            System.out.println("Employee is already in database");
            return false;
        }

        this.employeesList.add(employee);
        return true;
    }

    public boolean updateEmployee(Employee oldEmployee, Employee newEmployee){
        int foundPosition = findEmployeeIndex(oldEmployee);
        if (foundPosition < 0){
            System.out.println(oldEmployee.getFirstName() + " is not in the database");
        }

        this.employeesList.add(newEmployee);
        System.out.println(oldEmployee.getFirstName() + " has been replaced with " + newEmployee.getFirstName());
        return true;
    }

    public boolean removeEmployee(Employee existingEmployee){
        int foundPosition = findEmployeeIndex(existingEmployee);
        if (foundPosition < 0){
            System.out.println(existingEmployee.getFirstName() + " is not found in the database");
        }

        this.employeesList.remove(foundPosition);
        System.out.println(existingEmployee + " has been removed!");
        return true;
    }

    public Employee queryEmployees(String employeeName){
        int position = findEmployee(employeeName);
        if (position >= 0){
            return this.employeesList.get(position);
        }

        return null;
    }

    private int findEmployeeIndex(Employee employee){
        return this.employeesList.indexOf(employee);
    }

    private int findEmployee(String employeeName){
        for (int i = 0; i < this.employeesList.size(); i++) {
            Employee employee = this.employeesList.get(i);
            if (employee.getFirstName().equals(employeeName)){
                return i;
            }
        }
        return -1;
    }

    public void printEmployees() throws SQLException, ClassNotFoundException {

        databaseHandler.showAll();

    }


}

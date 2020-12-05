package com.mainmethod;

import com.AddEmployees.Employee;
import com.configuration.DatabaseHandler;
import com.employeeslist.EmployeesList;

import java.sql.SQLException;
import java.util.Scanner;

public class RunEmps {

    private static EmployeesList employeesList = new EmployeesList();
    public static Scanner scanner = new Scanner(System.in);
    private static DatabaseHandler databaseHandler = new DatabaseHandler();
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        printOptions();
        
        while(true){
            System.out.print("\nWhat action would like to perform : ");
            int option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option){
                case 1:
                    employeesList.printEmployees();
                    break;
                case 2:
                    addNewEmployee();
                    break;
                case 3:
                    updateEmployeeDetails();
                    break;
                case 4:
                    removeAnEmployee();
                    break;
                case 5:
                    searchForEmployee();
                    break;
            }
        }
    }

    private static void addNewEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("\n==================================================");
        System.out.println("================== Add New Employee ================");
        System.out.println("==================================================\n");

        System.out.print("Enter the first name : ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the last name : ");
        String lastName = scanner.nextLine();

        System.out.print("Enter the position: ");
        String position = scanner.nextLine();

        System.out.print("Enter the salary : R");
        double salary = scanner.nextDouble();

        Employee newEmployee = Employee.createEmployee(firstName, lastName, salary, position);

        if(employeesList.addEmployee(newEmployee)){
            databaseHandler.insertEmployee(newEmployee);
            System.out.println("\nSuccessfully added " + firstName + "  " + lastName + " to the database");
        }else{
            System.out.println("\nCouldn't add "  + firstName + " " + lastName + " to the database");
        }

    }

    private static void updateEmployeeDetails() {
        System.out.println("\n==================================================");
        System.out.println("============= Update Employee's Details ============");
        System.out.println("==================================================\n");


        System.out.print("Enter the Employee's Name: ");
        String firstName = scanner.nextLine();

        Employee existingEmployee = employeesList.queryEmployees(firstName);

        if(existingEmployee == null){
            System.out.println("\nEmployee not found!");
        }

        System.out.print("\nEnter the new first name ");
        String newFirstName = scanner.nextLine();

        System.out.print("Enter the last name: ");
        String newLastName = scanner.nextLine();

        System.out.print("Enter the position: ");
        String newPosition = scanner.nextLine();

        System.out.print("Enter the salary: ");
        double newSalary = scanner.nextDouble();

        Employee updatingEmployee = Employee.createEmployee(newFirstName, newLastName, newSalary, newPosition);

        if(employeesList.updateEmployee(existingEmployee, updatingEmployee)){
            System.out.println("\nSuccessfully updated the Employee's details");
        }else{
            System.out.println("\nCouldn't find " + firstName + " in the database");
        }

    }

    private static void removeAnEmployee() {
        System.out.println("\n==================================================");
        System.out.println("================= Remove an Employee ===============");
        System.out.println("==================================================\n");

        System.out.print("Enter the Employee's Name: ");
        String firstName = scanner.nextLine();

        Employee existingPhone = employeesList.queryEmployees(firstName);

        if(employeesList.removeEmployee(existingPhone)){
            System.out.println("\n" + firstName + "'s details have been successfully deleted!");
        }else {
            System.out.println("\nError deleting employee!...Try Again!!");
        }
    }

    private static void searchForEmployee() {
        System.out.println("\n==================================================");
        System.out.println("================ Search For Employee ===============");
        System.out.println("==================================================\n");

        System.out.print("Enter the Employee's name : ");
        String firstName = scanner.nextLine();

        Employee existingEmployee = employeesList.queryEmployees(firstName);
        if (existingEmployee == null) {
            System.out.println("\nEmployee not found!");
            return;
        }

        System.out.println("\nFirstname : " + existingEmployee.getFirstName() + "\n" +
                "Lastname : " + existingEmployee.getLastName()+ "\n" +
                "Salary  : R" + existingEmployee.getSalary() + "\n" +
                "Position : " + existingEmployee.getPosition());
    }

    private static void printOptions() {
        System.out.println("\n==================================================");
        System.out.println("=============== Employees Management ===============");
        System.out.println("==================================================\n");

        System.out.println("\t 1 - To Display the available all Employees");
        System.out.println("\t 2 - To add a new Employee");
        System.out.println("\t 3 - To update an existing Employee's details");
        System.out.println("\t 4 - To remove a Employee from the the database");
        System.out.println("\t 5 - Query if an existing Employee exists");

    }






}

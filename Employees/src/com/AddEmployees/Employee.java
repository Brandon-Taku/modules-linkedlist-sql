package com.AddEmployees;

public class Employee {

    private String firstName;
    private String lastName;
    private double salary;
    private String Position;

    public Employee(String firstName, String lastName, double salary, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.Position = position;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    @Override
    public String toString() {
        return " -- {" +
                firstName + " - " +
                 lastName + " - " +
                 salary + " - " +
                 Position + " - " +
                "}";
    }

    public static Employee createEmployee(String firstName, String lastName, double salary, String position){
        return new Employee(firstName, lastName, salary, position);
    }
}

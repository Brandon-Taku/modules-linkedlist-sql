package com.configuration;

import com.AddEmployees.Employee;

import javax.xml.namespace.QName;
import java.sql.*;
import java.util.LinkedList;

public class DatabaseHandler extends Config{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.EMPLOYEES_TABLE + " (" + Const.FIRSTNAME + ", "
                + Const.LASTNAME + ", " + Const.POSITION + ", " + Const.SALARY + ") " + "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getPosition());
        preparedStatement.setDouble(4, employee.getSalary());

        preparedStatement.executeUpdate();
    }

    public void updateEmployee(){

    }

    public ResultSet showAll() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + Const.EMPLOYEES_TABLE;

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(resultSet.getString("firstname") + " -- " + resultSet.getString("lastname") +
                  " -- " +  resultSet.getString("position") + " -- " + resultSet.getString("salary"));
        }

        LinkedList<ResultSet> linkedList = new LinkedList<>();

        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println((i+1) + " " + linkedList.get(i));
        }

        return resultSet;
    }

    public void updateDB(Employee employee, int employeeId) throws SQLException, ClassNotFoundException {

        String query = "UPDATE employee_data SET firstname=?, lastname=?, position=?, salary=?" + "WHERE employee_id=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getPosition());
        preparedStatement.setDouble(4, employee.getSalary());
        preparedStatement.setInt(5, employeeId);
        preparedStatement.executeUpdate();
    }

    public void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.EMPLOYEES_TABLE + " WHERE " + Const.EMP_ID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, employeeId);
        preparedStatement.execute();
        preparedStatement.close();
    }

}

module EmployeesMethod {
    requires Employees;
    requires Database;
    requires java.sql;
    exports com.employeeslist;
}
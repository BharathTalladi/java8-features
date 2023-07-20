package org.cloud;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class EmployeeMain {
    public static void main(String[] args) {
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(new Employee(1,"Ramesh", 101,"active",2200000));
        employeeList.add(new Employee(2,"Prasuna", 101,"inactive",1800000));
        employeeList.add(new Employee(3,"Bharath", 102,"active",2500000));
        employeeList.add(new Employee(4,"Rohith", 102,"inactive",1800000));
        employeeList.add(new Employee(5,"Suresh", 103,"active",1700000));
        employeeList.add(new Employee(6,"Pranitha", 103,"inactive",1500000));
        employeeList.add(new Employee(7,"Lokesh", 104,"active",300000));
        employeeList.add(new Employee(8,"Sonika", 104,"inactive",200000));

        //TO print departments- employeeList.stream().map(Employee::getDepId).forEach(System.out::println);
        //To print employees details working in each department
        Map<Integer, List<Employee>> empBasedOnDept=
                employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId, Collectors.toList()));
        empBasedOnDept.entrySet().forEach(entry->
            System.out.println(entry.getKey()+"---"+entry.getValue()));

        //To print employees count working in each department
        Map<Integer, Long> empCountBasedOnDept=
                employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId,Collectors.counting()));
        empCountBasedOnDept.entrySet().forEach(entry->
            System.out.println(entry.getKey()+"---"+entry.getValue()));

        //To print active and inactive employees
        long activeEmployees = employeeList.stream().filter(employee -> "active".equals(employee.getStatus())).count();
        long inActiveEmployees = employeeList.stream().filter(employee -> "inactive".equals(employee.getStatus())).count();
        System.out.println("Active Employees Count:"+activeEmployees);
        System.out.println("In active Employees Count:"+inActiveEmployees);

        //To print max and min salary of the employees
        Optional<Employee> maxSalary= employeeList.stream().max(Comparator.comparingInt(Employee::getSalary));
        System.out.println("Maximum Salary:"+maxSalary);
        Optional<Employee> minSalary= employeeList.stream().min(Comparator.comparingInt(Employee::getSalary));
        System.out.println("Minimum Salary:"+minSalary);

        //To print max salary of the employee from each department
        Map<Integer, Optional<Employee>> maxSalaryBasedOnDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId, Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Employee::getSalary)))));

        maxSalaryBasedOnDept.entrySet().forEach(entry-> System.out.println(entry.getKey()+"--"+entry.getValue()));


    }
}
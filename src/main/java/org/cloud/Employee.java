package org.cloud;

public class Employee {

    private int empId;
    private String name;
    private int depId;
    private String status="active";
    private int salary;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(int empId, String name, int depId, String status, int salary) {
        this.empId = empId;
        this.name = name;
        this.depId = depId;
        this.status = status;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", depId=" + depId +
                ", status='" + status + '\'' +
                ", salary=" + salary +
                '}';
    }
}

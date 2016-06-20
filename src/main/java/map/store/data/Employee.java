package map.store.data;

import java.io.Serializable;

/**
 * Created by bijoy on 20/6/16.
 */
public class Employee implements Serializable {
    final static long serialVersionUID = 1L;

    private String empId;
    private String name;
    private String region;

    public Employee(){}

    public Employee(String empId, String name, String region) {
        this.empId = empId;
        this.name = name;
        this.region = region;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!getEmpId().equals(employee.getEmpId())) return false;
        if (!getName().equals(employee.getName())) return false;
        return getRegion().equals(employee.getRegion());

    }

    @Override
    public int hashCode() {
        int result = getEmpId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getRegion().hashCode();
        return result;
    }
}

package query.data;

import java.io.Serializable;

/**
 * Created by bijoy on 21/6/16.
 */
public class Employee implements Comparable<Employee>,Serializable {

    final static long serialVersionUID = 1l;
    private int id;
    private String name;
    private int age;
    private boolean active;
    private double salary;

    public Employee(){}

    public Employee(int id,String name, int age, boolean live, double price) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = live;
        this.salary = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n----------------------------------" +
               "\nEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", active=" + active +
                ", salary=" + salary +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != employee.getId()) return false;
        if (getAge() != employee.getAge()) return false;
        if (isActive() != employee.isActive()) return false;
        if (Double.compare(employee.getSalary(), getSalary()) != 0) return false;
        return getName().equals(employee.getName());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + (isActive() ? 1 : 0);
        temp = Double.doubleToLongBits(getSalary());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(Employee o) {
        if(this.getId() == o.getId()){
            return 0;
        }else if(this.getId() > o.getId()){
            return 1;
        }else {
            return -1;
        }
    }
}

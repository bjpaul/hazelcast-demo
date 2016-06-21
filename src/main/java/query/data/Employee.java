package query.data;

import java.io.Serializable;

/**
 * Created by bijoy on 21/6/16.
 */
public class Employee implements Serializable {

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
}

package entryprocess.data;

import java.io.Serializable;

/**
 * Created by bijoy on 21/6/16.
 */
public class Employee implements Serializable {
    final static long serialVersionUID = 1l;

    private int salary;

    public Employee(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void incSalary(int delta) {
        salary += delta;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                '}';
    }
}

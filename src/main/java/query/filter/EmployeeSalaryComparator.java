package query.filter;

import query.data.Employee;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by bijoy on 23/6/16.
 */
public class EmployeeSalaryComparator implements Comparator<Map.Entry>, Serializable {
    final static long serialVersionUID = 1l;


    @Override
    public int compare(Map.Entry entry1, Map.Entry entry2) {
        Employee o1 = (Employee)entry1.getValue();
        Employee o2 = (Employee)entry2.getValue();

        if(o1.getSalary() == o2.getSalary()){
            return 0;
        }else if(o1.getSalary() > o2.getSalary()){
            return 1;
        }else {
            return -1;
        }
    }
}

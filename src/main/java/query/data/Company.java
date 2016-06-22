package query.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bijoy on 22/6/16.
 */
public class Company implements Serializable{
    final static long serialVersionUID = 1l;

    private CompanyDetail detail;
    private List<Employee> employees;

    public Company(){}
    public Company(CompanyDetail detail, List<Employee> employees) {
        this.detail = detail;
        this.employees = employees;
    }

    public CompanyDetail getDetail() {
        return detail;
    }

    public void setDetail(CompanyDetail detail) {
        this.detail = detail;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "===================" +
               "\nCompany{" +
                "detail=" + detail +
                ", employees=" + employees +
                '}';
    }
}

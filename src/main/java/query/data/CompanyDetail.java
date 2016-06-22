package query.data;

import java.io.Serializable;

/**
 * Created by bijoy on 22/6/16.
 */
public class CompanyDetail implements Serializable{
    final static long serialVersionUID = 1l;

    private String name;
    private String location;

    public CompanyDetail(){}

    public CompanyDetail(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "\n--------------------------------" +
               "\nCompanyDetail{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

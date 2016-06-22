package query.collectionquery;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import query.data.Company;
import query.data.CompanyDetail;
import query.data.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bijoy on 21/6/16.
 */
public class UpdateMapData {
    public static void main(String[] args) {
        Random random = new Random();
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Company> map = hazelcastInstance.getMap("collectionQueryMap");
        System.out.println("Updating..........");
        CompanyDetail detail;
        List<Employee> employees;
        for (int j = 10; j < 15; j++) {
            detail = new CompanyDetail("Company Name "+j,"Company Location"+j);
            employees = new ArrayList<Employee>();
            for (int i = 0; i < 10; i++) {
                employees.add(new Employee(i, "name" + i, random.nextInt(40), random.nextBoolean(), random.nextInt(40000)));
            }
            map.put(j, new Company(detail, employees));
        }
        System.out.println("Completed................");
    }
}

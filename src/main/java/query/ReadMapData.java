package query;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import query.dao.EmployeePredicate;
import query.dao.EmployeePredicateBuilder;
import query.dao.EmployeeSqlPredicate;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class ReadMapData {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Employee> map = hazelcastInstance.getMap("employeeQueryMap");

        System.out.println(map.values(EmployeeSqlPredicate.activeAndAge(true, 30)));
        System.out.println(map.values(EmployeePredicate.activeAndAge(true, 30)));
        System.out.println(map.values(EmployeePredicateBuilder.activeAndAge(true, 30)));
    }
}

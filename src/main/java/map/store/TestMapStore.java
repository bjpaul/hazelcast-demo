package map.store;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import map.store.data.Employee;

/**
 * Created by bijoy on 20/6/16.
 */
public class TestMapStore {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<String, Employee> employeeIMap = hazelcastInstance.getMap("testMapStore");

       /* for (int i = 0;i < 10; i++){
            employeeIMap.put( "empId"+i, new Employee( "empId"+i, "name"+i, "region"+i));
        }*/

        System.out.println("--------PUT----------");
        employeeIMap.put("empId15", new Employee("emp15", "name15", "region15"));
        System.out.println("--------REMOVE----------");
        employeeIMap.remove("empId15");
        System.out.println("--------GET----------");
        employeeIMap.get("empId4");
        employeeIMap.get("empId5");
    }
}

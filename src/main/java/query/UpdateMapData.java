package query;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import query.data.Employee;

import java.util.Random;

/**
 * Created by bijoy on 21/6/16.
 */
public class UpdateMapData {
    public static void main(String[] args){
        Random random = new Random();
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Employee> map = hazelcastInstance.getMap("employeeQueryMap");
        System.out.println("Updating..........");
        for(int i = 0; i < 50; i++){
            map.put(i, new Employee(i, "name"+i, random.nextInt(40),random.nextBoolean(), random.nextInt(40000)));
        }
        System.out.println("Completed................");
    }
}

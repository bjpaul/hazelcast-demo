package query.basicquery;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import query.dao.TestPredicate;
import query.dao.TestPredicateBuilder;
import query.dao.TestSql;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class ReadMapData {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Employee> map = hazelcastInstance.getMap("employeeQueryMap");

        System.out.println(map.values(TestSql.activeAndAge(true, 30)));
        System.out.println(map.values(TestPredicate.activeAndAge(true, 30)));
        System.out.println(map.values(TestPredicateBuilder.activeAndAge(true, 30)));
    }
}

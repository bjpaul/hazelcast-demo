package query.filter;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;
import instance.Client;
import query.dao.TestPredicate;
import query.dao.TestPredicateBuilder;
import query.dao.TestSqlPredicate;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class ReadMapData {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Employee> map = hazelcastInstance.getMap("employeeQueryMap");

        System.out.println(map.values(new PagingPredicate(TestSqlPredicate.activeAndAge(true, 30), 2)));
        System.out.println(map.values(new PagingPredicate(TestPredicate.activeAndAge(true, 30), 2)));
        System.out.println(map.values(new PagingPredicate(TestPredicateBuilder.activeAndAge(true, 30), 2)));

        /*System.out.println(map.values(
                new PagingPredicate(
                        TestSqlPredicate.activeAndAge(true, 30),
                        new EmployeeSalaryComparator(),
                        2
                )
        ));
        System.out.println(map.values(
                new PagingPredicate(
                        TestPredicate.activeAndAge(true, 30),
                        new EmployeeSalaryComparator(),
                        2)
        ));
        System.out.println(map.values(
                new PagingPredicate(
                        TestPredicateBuilder.activeAndAge(true, 30),
                        new EmployeeSalaryComparator(),
                        2)
        ));*/
    }
}

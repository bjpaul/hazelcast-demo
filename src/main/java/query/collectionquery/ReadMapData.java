package query.collectionquery;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import instance.Client;
import query.dao.TestPredicate;
import query.dao.TestPredicateBuilder;
import query.dao.TestSqlPredicate;
import query.data.Company;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class ReadMapData {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<Integer, Company> map = hazelcastInstance.getMap("collectionQueryMap");

        /*System.out.println(map.values(TestSqlPredicate.companyName("Company Name 12")));
        System.out.println(map.values(TestPredicate.companyName("Company Name 12")));
        System.out.println(map.values(TestPredicateBuilder.companyName("Company Name 12")));*/

        System.out.println(map.values(TestSqlPredicate.companyByFirstActiveEmployee(false)));
        System.out.println(map.values(TestPredicate.companyByFirstActiveEmployee(false)));
        System.out.println(map.values(TestPredicateBuilder.companyByFirstActiveEmployee(false)));
    }
}

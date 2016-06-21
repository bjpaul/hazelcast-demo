package query.dao;

import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;
import com.hazelcast.query.Predicates;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class EmployeePredicateBuilder {

    public static Predicate<Integer, Employee> activeAndAge(boolean isActive, int age){
        System.out.println("---------Predicate Builder----------");
        EntryObject e = new PredicateBuilder().getEntryObject();
        Predicate<Integer, Employee> activePredicate = e.get("active").equal(isActive);
        return e.get("age").greaterThan(age).and(activePredicate);
    }

    public static Predicate<Integer, Employee> ageWithInRange(int fromAge, int toAge){
        System.out.println("---------Predicate Builder----------");
        EntryObject e = new PredicateBuilder().getEntryObject();
        return e.get("age").between(fromAge, toAge);
    }

    public static Predicate<Integer, Employee> ageNotInRange(int fromAge, int toAge){
        System.out.println("---------Predicate Builder----------");
        EntryObject e = new PredicateBuilder().getEntryObject();
        return Predicates.not(e.get("age").between(fromAge, toAge));
    }

    public static Predicate<Integer, Employee> ageNotIn(Integer... ages){
        System.out.println("---------Predicate Builder----------");
        EntryObject e = new PredicateBuilder().getEntryObject();
        return Predicates.not(e.get("age").in(ages));
    }

}

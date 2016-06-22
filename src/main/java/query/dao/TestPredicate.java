package query.dao;

import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import query.data.Company;
import query.data.Employee;

/**
 * Created by bijoy on 21/6/16.
 */
public class TestPredicate {

    public static Predicate<Integer, Employee> activeAndAge(boolean isActive, int age){
        System.out.println("---------Predicate----------");
        Predicate<Integer, Employee> activePredicate = Predicates.equal("active", isActive);
        Predicate<Integer, Employee> agePredicate = Predicates.greaterThan("age", age);
        return Predicates.and(activePredicate, agePredicate); 
    }

    public static Predicate<Integer, Employee> ageWithInRange(int fromAge, int toAge){
        System.out.println("---------Predicate----------");
        return Predicates.between("age", fromAge, toAge);
    }

    public static Predicate<Integer, Employee> ageNotInRange(int fromAge, int toAge){
        System.out.println("---------Predicate----------");
        Predicate<Integer, Employee> betweenPredicate = Predicates.between("age", fromAge, toAge);
        return Predicates.not(betweenPredicate);
    }

    public static Predicate<Integer, Employee> ageNotIn(Integer... ages){
        System.out.println("---------Predicate----------");
        Predicate<Integer, Employee> inPredicate = Predicates.in("age", ages);
        return Predicates.not(inPredicate);
    }

    public static Predicate<Integer, Employee> nameLike(String exp){
        System.out.println("---------Predicate----------");
        return Predicates.like("name", exp);
    }

    public static Predicate<Integer, Company> companyName(String name){
        System.out.println("---------Predicate----------");
        return Predicates.equal("detail.name", name);
    }

    public static Predicate<Integer, Company> companyByFirstActiveEmployee(boolean isAlive){
        System.out.println("---------Predicate----------");
        return Predicates.equal("employees[0].active", isAlive);
    }
}

package query.dao;

import com.hazelcast.query.SqlPredicate;

/**
 * Created by bijoy on 21/6/16.
 */
public class TestSql {

    public static SqlPredicate activeAndAge(boolean isActive, int age){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("active=%s AND age>%s",isActive,age);
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate ageWithInRange(int fromAge, int toAge){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("age BETWEEN %s AND %s",fromAge,toAge);
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate ageNotInRange(int fromAge, int toAge){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("age NOT BETWEEN %s AND %s",fromAge,toAge);
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate ageNotIn(Integer... ages){
        System.out.println("---------SQL Predicate----------");
        String query = "age NOT IN(";
        int i = 0;
        for(int age :ages){
            if(i == 0){
                query+=age;
                i++;
            }else {
                query+=","+age;
            }
        }
        query+=")";
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate nameLike(String exp){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("name Like '%s'",exp);
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate companyName(String name){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("detail.name = '%s'",name);
        System.out.println(query);
        return new SqlPredicate(query);
    }

    public static SqlPredicate companyByFirstActiveEmployee(boolean alive){
        System.out.println("---------SQL Predicate----------");
        String query = String.format("employees[0].active = %s",alive);
        System.out.println(query);
        return new SqlPredicate(query);
    }
}

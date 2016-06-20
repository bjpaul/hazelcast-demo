package map.store.configure.cache;

import com.hazelcast.core.MapStore;
import map.store.configure.db.JDBCConnection;
import map.store.data.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bijoy on 20/6/16.
 */
public class EmpMapStore implements MapStore<String, Employee> {
    public EmpMapStore(){}

    @Override
    public synchronized void store(String key, Employee value) {
        String query;
        if(load(key) != null){
            query = String.format("update employee set name='%s',region='%s' where emp_id='%s'", value.getName(), value.getRegion(),key);
        }else {
            query = String.format("insert into employee values('%s','%s','%s')", key, value.getName(), value.getRegion());
        }
        System.out.println(query);
        try(
                JDBCConnection jdbcConnection = JDBCConnection.getConnnection()
        ) {
            jdbcConnection.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized Employee load(String key) {
        String query = String.format("select * from employee where emp_id ='%s'", key);
        System.out.println(query);
        Employee employee;
        try(
                JDBCConnection jdbcConnection = JDBCConnection.getConnnection()
        ) {
            ResultSet resultSet = jdbcConnection.executeQuery(query);
            if (!resultSet.next()) return null;
            String empId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String region = resultSet.getString(3);
            employee = new Employee(empId,name,region);
            System.out.println(employee);
            return employee;
        } catch (SQLException | ClassNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void delete(String key) {
        String query = String.format("delete from employee where emp_id = '%s'", key);
        System.out.println(query);
        try(
                JDBCConnection jdbcConnection = JDBCConnection.getConnnection()
        ) {
            jdbcConnection.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void storeAll(Map<String, Employee> map) {
        for (Map.Entry<String, Employee> entry : map.entrySet())
            store(entry.getKey(), entry.getValue());
    }

    @Override
    public synchronized Map<String, Employee> loadAll(Collection<String> keys) {
        Map<String, Employee> result = new HashMap<String, Employee>();
        for (String key : keys) result.put(key, load(key));
        return result;
    }

    @Override
    public synchronized void deleteAll(Collection<String> keys) {
        for (String key : keys) delete(key);
    }


    @Override
    public synchronized Iterable<String> loadAllKeys() {
        return null;
    }
}

package entryprocess;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.AbstractEntryProcessor;
import entryprocess.data.Employee;
import instance.Client;

import java.util.Map;


/**
 * Created by bijoy on 20/6/16.
 */
public class ClientInstance {
    public static void main(String[] args) throws Exception {
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<String, Employee> employees = hazelcastInstance.getMap("testEntryProcessorMap");

        employees.put("John", new Employee(1000));
        employees.put("Mark", new Employee(1000));
        employees.put("Spencer", new Employee(1000));
        long startTime = System.currentTimeMillis();
        basic(employees);
//        employees.executeOnEntries(new EmployeeRaiseEntryProcessor());
//        employees.executeOnKey("Mark", new EmployeeRaiseEntryProcessor());
        /*employees.submitToKey("John", new EmployeeRaiseEntryProcessor(),new ExecutionCallback<Employee>() {
            @Override
            public void onResponse(Employee response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });*/
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            System.out.println(entry.getKey() + " salary: " + entry.getValue().getSalary());
        }
        System.out.println("Total time taken : "+(System.currentTimeMillis() - startTime));
    }

    public static void basic(Map<String, Employee> employees){
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            String id = entry.getKey();
            Employee employee = employees.get(id);
            employee.incSalary(10);
            employees.put(entry.getKey(), employee);
        }
    }

    private static class EmployeeRaiseEntryProcessor extends AbstractEntryProcessor<String, Employee> {
        @Override
        public Object process(Map.Entry<String, Employee> entry) {
            Employee value = entry.getValue();
            value.incSalary(10);
            entry.setValue(value);
            return value;
        }
    }
}

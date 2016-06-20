package executors;

import com.hazelcast.core.*;
import executors.tasks.ExecutorsMethods;
import instance.Client;


/**
 * Created by bijoy on 20/6/16.
 */
public class ClientInstance {
    public static void main(String[] args) throws Exception{
        HazelcastInstance hazelcastInstance = Client.instance();
        IMap<String, Integer> map = hazelcastInstance.getMap("testExecutorMap");

        for(int i = 0; i < 100000; i++){
            map.put(""+i, 1);
        }

        System.out.println(map.size());
        IExecutorService executor = hazelcastInstance.getExecutorService("testExecutor");
        ExecutorsMethods.submitToAllMembers(executor);

    }
}

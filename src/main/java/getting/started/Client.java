package getting.started;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Client {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
        Set<String> strings = hazelcastInstance.getSet("mySet");
        for(String s:strings){
            System.out.println(s);
        }
    }
}

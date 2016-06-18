package distributed.objects.map;

import com.hazelcast.core.HazelcastInstance;
import instance.Client;

/**
 * Created by bijoy on 18/6/16.
 */
public class TestClientMap {
    public static void main(String[] args){
       HazelcastInstance hazelcastInstance = Client.instance();

    }
}

package distributed.objects.collection;

import com.hazelcast.core.HazelcastInstance;
import instance.Server;

/**
 * Created by bijoy on 18/6/16.
 */
public class MemberInstance {
    private static HazelcastInstance hazelcastInstance;
    static {
        hazelcastInstance = Server.memberInstance();
    }
    public static void main(String[] args) {
        hazelcastInstance = Server.memberInstance();
    }

    public static HazelcastInstance getHazelcastInstance(){
        return hazelcastInstance;
    }
}

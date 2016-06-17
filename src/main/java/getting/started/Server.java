package getting.started;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Set<String> strings = hazelcastInstance.getSet("mySet");
        IAtomicLong iAtomicLong = hazelcastInstance.getAtomicLong("uniqueId");
        strings.add("Instance "+iAtomicLong.incrementAndGet());
    }
}

package partition;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {
    private static HazelcastInstance hazelcastInstance ;
    public static void main(String[] args){
//        Demo 3
//        System.setProperty("hazelcast.partition.count","350");
        hazelcastInstance = Hazelcast.newHazelcastInstance();

        /*Config config = new Config();
        config.setProperty("hazelcast.partition.count","350");*/
//        hazelcastInstance = Hazelcast.newHazelcastInstance(config);

//        Demo 1
        hazelcastInstance.getPartitionService().addMigrationListener(CustomMigrationListner.getMigrationListener());

//        Demo 2
        Set<String> strings = hazelcastInstance.getSet("mySet");
        IAtomicLong iAtomicLong = hazelcastInstance.getAtomicLong("uniqueId");
        strings.add("Instance " + iAtomicLong.incrementAndGet());

    }
}

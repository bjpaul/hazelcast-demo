package partition;

import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.nio.Address;
import configuration.cluster.*;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {
    private static HazelcastInstance hazelcastInstance ;
    public static void main(String[] args){
//        Demo 3
//        System.setProperty("hazelcast.partition.count","350");
        Config config = new Config();
        config.getMemberAttributeConfig().setStringAttribute("user", System.getProperty("user.name"));
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        hazelcastInstance.getCluster().addMembershipListener(new CustomMembershipListner());

        /*
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

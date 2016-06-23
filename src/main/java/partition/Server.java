package partition;

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

        hazelcastInstance = Hazelcast.newHazelcastInstance();
//        Demo 1
//        hazelcastInstance.getPartitionService().addMigrationListener(CustomMigrationListner.getMigrationListener());

//        Demo 2
//        Set<String> strings = hazelcastInstance.getSet("mySet");
//        IAtomicLong iAtomicLong = hazelcastInstance.getAtomicLong("uniqueId");
//        strings.add("Instance " + iAtomicLong.incrementAndGet());


//        Demo 3
        /*Cluster cluster = hazelcastInstance.getCluster();
        cluster.addMembershipListener(new CustomMembershipListner());
        Set<Member> members = cluster.getMembers();
        for (Member member : members){
            Address address = member.getAddress();
            System.out.println("Address"+address);
            System.out.println("UUID "+member.getUuid());
            System.out.println("Is lite member ??? "+member.isLiteMember());
            System.out.println("Is local member ??? "+member.localMember());
        }*/


        // Demo 4
        /*
        System.setProperty("hazelcast.partition.count","350");
        config.setProperty("hazelcast.partition.count","350");
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);*/

    }


}

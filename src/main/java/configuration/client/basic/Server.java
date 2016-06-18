package configuration.client.basic;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.*;
import com.hazelcast.nio.Address;
import configuration.cluster.*;

import java.util.Set;

/**
 * Created by bijoy on 15/6/16.
 */
public class Server {
    public static void main(String[] args) {
        Config config = new Config();
        config.getGroupConfig()
                .setName("intellimeet")
//                .setName("intellimeet1")
                .setPassword("june");
//        config.setLiteMember(true);
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig()
                .setEnabled(false);
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                .addMember("localhost");
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        membersString(hazelcastInstance.getCluster());
        Set<String> strings = hazelcastInstance.getSet("mySet");
        IAtomicLong iAtomicLong = hazelcastInstance.getAtomicLong("uniqueId");
        strings.add("Instance " + iAtomicLong.incrementAndGet());

    }

    private static void membersString(Cluster cluster){
        Set<Member> members = cluster.getMembers();
        Member localMember = cluster.getLocalMember();
        Address address;

        System.out.print("Members [" + members.size() + "] {");
        for (Member member : members) {
            address = member.getAddress();
            System.out.print("\n {"+member.getStringAttribute("user")+" -> Instance "+address.getPort() % 10+"} ");
            System.out.print("[" + address.getHost() + "]:" + address.getPort());
            if(localMember.getAddress().equals(address)) {
                System.out.print(" this");
            }
        }
        System.out.println("\n}");

        cluster.addMembershipListener(new CustomMembershipListner());
    }
}

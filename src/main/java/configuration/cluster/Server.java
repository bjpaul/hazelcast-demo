package configuration.cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.nio.Address;

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
//                .addMember("localhost.127.0.0.1")
                .addMember("localhost");
        config.getMemberAttributeConfig().setStringAttribute("user", System.getProperty("user.name"));
        Hazelcast.newHazelcastInstance(config);
//        membersString(Hazelcast.newHazelcastInstance(config).getCluster());
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

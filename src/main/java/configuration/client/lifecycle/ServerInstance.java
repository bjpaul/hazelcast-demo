package configuration.client.lifecycle;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.*;
import com.hazelcast.nio.Address;
import configuration.cluster.*;

import java.util.Set;

/**
 * Created by bijoy on 17/6/16.
 */
public class ServerInstance implements LifecycleListener{
    private static HazelcastInstance hazelcastInstance;
    private static String instanceName;
    private static IAtomicLong memberId;
    private static IQueue<String> queue;

    static {
        hazelcastInstance = serverInstance();
        hazelcastInstance.getLifecycleService().addLifecycleListener(new ServerInstance());
        memberId = hazelcastInstance.getAtomicLong("memberId");
        instanceName = "Instance " + memberId.incrementAndGet();
        queue = hazelcastInstance.getQueue("testQueue");
    }

    public static IQueue<String> getQueue() {
        return queue;
    }

    public static String getInstanceName() {
        return instanceName;
    }

    private static HazelcastInstance serverInstance() {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(serverConfig());
        membersString(hazelcastInstance.getCluster());
        return hazelcastInstance;
    }

    private static Config serverConfig() {
        Config config = new Config();
        config.getGroupConfig()
                .setName("intellimeet")
                .setPassword("june");
//        config.setLiteMember(true);
        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig()
                .setEnabled(false);
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                .addMember("localhost");
        return config;
    }



    @Override
    public void stateChanged(LifecycleEvent event) {
        System.out.println("Server -> " + event);
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

        cluster.addMembershipListener(new configuration.cluster.CustomMembershipListner());
    }
}
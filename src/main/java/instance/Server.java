package instance;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;
import com.hazelcast.nio.Address;

import java.util.Set;

/**
 * Created by bijoy on 17/6/16.
 */
public class Server {

    public static Config memberConfig(){
        return ServerInstance.serverConfig();
    }

    public static HazelcastInstance memberInstance(){
        return ServerInstance.serverInstance();
    }


    private static class ServerInstance{
        private static HazelcastInstance hazelcastInstance;
        private static Config config;
        static {
            config = config();
        }


        private static HazelcastInstance serverInstance(){
            if(hazelcastInstance == null){
                hazelcastInstance = createInstance();
            }
            return hazelcastInstance;
        }

        private static HazelcastInstance createInstance() {
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            membersString(hazelcastInstance.getCluster());
            return hazelcastInstance;
        }

        private static Config serverConfig(){
            return config;
        }

        private static Config config() {
            Config config = new Config();
            config.getGroupConfig()
                    .setName("intellimeet")
                    .setPassword("june");
            JoinConfig joinConfig = config.getNetworkConfig().getJoin();
            joinConfig.getMulticastConfig()
                    .setEnabled(false);
            joinConfig.getTcpIpConfig()
                    .setEnabled(true)
                    .addMember("localhost");
            return config;
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

}

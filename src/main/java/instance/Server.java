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
            config.getMemberAttributeConfig()
                    .setStringAttribute("user", System.getProperty("user.name"));
            return config;
        }

        private static void membersString(Cluster cluster){
            Set<Member> members = cluster.getMembers();
            Address address;

            System.out.println("Members [" + members.size() + "] {");
            StringBuilder sb ;
            for (Member member : members) {
                sb= new StringBuilder();
                address = member.getAddress();
                sb.append("    [");
                sb.append(member.getStringAttribute("user"));
                sb.append(" -> Instance ");
                sb.append(address.getPort() % 10);
                sb.append("] [");
                sb.append(address.getHost());
                sb.append("]");
                sb.append(":");
                sb.append(address.getPort());
                if (member.localMember()) {
                    sb.append(" this");
                }
                if (member.isLiteMember()) {
                    sb.append(" lite");
                }
                System.out.println(sb);
            }
            System.out.println("}");

            cluster.addMembershipListener(new CustomMembershipListner());
        }

    }

}
